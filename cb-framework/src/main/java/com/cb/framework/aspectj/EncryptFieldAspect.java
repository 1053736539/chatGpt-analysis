package com.cb.framework.aspectj;

import com.alibaba.fastjson.JSONObject;
import com.cb.common.annotation.EncryptField;
import com.cb.common.annotation.EncryptMethod;
import com.cb.common.encipher.service.IEncipherService;
import com.cb.common.enums.EODType;
import com.cb.common.enums.EncryptType;
import com.cb.common.utils.spring.SpringUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 安全字段加密解密切面
 *
 * @author: ouyan
 * @date:2021/04/20
 */
@Order
@Aspect
@Component
public class EncryptFieldAspect {
    Logger log = LoggerFactory.getLogger(this.getClass());

    private IEncipherService encipherService = SpringUtils.getBean(IEncipherService.class);

    @Pointcut("@annotation(com.cb.common.annotation.EncryptMethod)")
    public void annotationPointCut() {
    }

    /**
     * 数据加密
     *
     * @param joinPoint
     */
    @Before("annotationPointCut() && @annotation(encryptMethod)")
    public void doBefore(JoinPoint joinPoint, EncryptMethod encryptMethod) {
        EODType eodType = encryptMethod.businessType();
        if (eodType == EODType.DECRYPT) {//解密操作不在此处做
            return;
        }
        try {
            Object[] args = joinPoint.getArgs();
            String[] argNames = encryptMethod.argNames();
            Set<Integer> argIndexSet = new HashSet<>();
            if (argNames.length > 0) {
                Set<String> argSet = new HashSet<>();
                argSet = Arrays.stream(argNames).filter(i -> StringUtils.isNotBlank(i)).collect(Collectors.toSet());
                // 方法签名
                Signature signature = joinPoint.getSignature();
                // 获取代理对象的method方法
                Method proxyMethod = ((MethodSignature) signature).getMethod();
                // 获取目标对象上的method方法（防止有protected或private修饰的方法反射获取失败）
                Method targetMethod = joinPoint.getTarget().getClass().getDeclaredMethod(signature.getName(), proxyMethod.getParameterTypes());
                Parameter[] parameters = targetMethod.getParameters();
                for (int i = 0; i < parameters.length; i++) {
                    String name = parameters[i].getName();
                    if (argSet.contains(name)) {
                        argIndexSet.add(i);
                    }
                }
            } else {
                //未指定变量名时，取入参下标
                argIndexSet.add(encryptMethod.argIndex());
            }
            //有入参需要处理
            if (!argIndexSet.isEmpty()) {
                argIndexSet.forEach(i -> {
                    Object handleArg = args[i];
                    handleArg(handleArg, eodType);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //处理
    private void handleArg(Object origin, EODType eodType) {
        if (null != origin) {
            if (origin instanceof Collection) {
                Collection c = (Collection) origin;
                c.forEach(i -> {
                    handleArg(i, eodType);
                });
            } else {
                if (Objects.isNull(origin)) {
                    return;
                }
                Field[] fields = origin.getClass().getDeclaredFields();
                //全字段map
                Map<String, Field> fieldMap = Arrays.stream(fields).collect(Collectors.toMap(i -> i.getName(), i -> i, (a, b) -> b));
                List<Field> sm3FieldList = Arrays.stream(fields).filter(i ->
                        i.isAnnotationPresent(EncryptField.class) && i.getAnnotation(EncryptField.class).fieldType() == EncryptType.SM3
                ).collect(Collectors.toList());
                List<Field> sm4FieldList = Arrays.stream(fields).filter(i ->
                        i.isAnnotationPresent(EncryptField.class) && i.getAnnotation(EncryptField.class).fieldType() == EncryptType.SM4
                ).collect(Collectors.toList());

                List<Field> hmacFieldList = Arrays.stream(fields)
                        .filter(i -> i.isAnnotationPresent(EncryptField.class) && i.getAnnotation(EncryptField.class).fieldType() == EncryptType.HMAC)
                        .collect(Collectors.toList());
                if (eodType == EODType.ENCRYPT) {
                    this.encrypt(origin, fieldMap, sm3FieldList, sm4FieldList, hmacFieldList);
                } else {
                    this.decrypt(origin, fieldMap, sm4FieldList, hmacFieldList);
                }

            }
        }
    }

    private void encrypt(Object origin, Map<String, Field> fieldMap, List<Field> sm3FieldList, List<Field> sm4FieldList, List<Field> hmacFieldList) {
        try {
            //1.先做sm3 加密计算
            for (Field field : sm3FieldList) {
                Object o = field.get(origin);
                if (null == o || (o instanceof String && o != null && ((String) o).isEmpty())) {
                    continue;
                }
                String str = (String) o;

                String encrypt = encipherService.sm3Encrypt(str);
                if (StringUtils.isNotBlank(encrypt)) {
                    field.set(origin, encrypt);
                }
            }
            //2.sm4 加密计算
            for (Field field : sm4FieldList) {
                field.setAccessible(true);
                Object o = field.get(origin);
                if (null == o || (o instanceof String && o != null && ((String) o).isEmpty())) {
                    continue;
                }
                String str = (String) o;
                String encrypt = encipherService.sm4EncryptEcb(str);
                field.set(origin, encrypt);
            }
            // 3 等加密计算的执行完成，再做HMAC计算

            for (Field field : hmacFieldList) {
                String[] macSourceFields = field.getAnnotation(EncryptField.class).macSourceField();
                String macSourceFieldsValueString = this.macSourceFieldsValue2String(origin, fieldMap, macSourceFields);
                log.info("macSourceFieldsValueString 加密前={}", macSourceFieldsValueString);
                if (StringUtils.isNotBlank(macSourceFieldsValueString)) {
                    field.setAccessible(true);
                    String hmac = encipherService.sm3Encrypt(macSourceFieldsValueString);
                    field.set(origin, hmac);
                }
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    private String macSourceFieldsValue2String(Object origin, Map<String, Field> fieldMap, String[] macSourceFields) {
        JSONObject jsonObject = new JSONObject();
        for (String macSourceField : macSourceFields) {
            Field field = fieldMap.get(macSourceField);
            field.setAccessible(true);
            try {
                Object o = field.get(origin);
                jsonObject.put(macSourceField, o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return jsonObject.toJSONString();
    }


    private void decrypt(Object origin, Map<String, Field> fieldMap, List<Field> sm4FieldList, List<Field> hmacFieldList) {
        try {
            // TODO 1.先做hmac计算

            for (Field field : hmacFieldList) {
                String[] macSourceFields = field.getAnnotation(EncryptField.class).macSourceField();
                String macSourceFieldsValueString = this.macSourceFieldsValue2String(origin, fieldMap, macSourceFields);
                log.info("macSourceFieldsValueString 校验时={}", macSourceFieldsValueString);
                if (StringUtils.isNotBlank(macSourceFieldsValueString)) {
                    field.setAccessible(true);
                    Object o = field.get(origin);
                    if (null == o || (o instanceof String && o != null && ((String) o).isEmpty())) {
                        field.set(origin, "安全");
                        continue;
                    }
                    String ciphertext = (String) o;
                    boolean ok = encipherService.sm3Verify(macSourceFieldsValueString, ciphertext);
                    if (ok) {
                        field.set(origin, "安全");
                    } else {
                        field.set(origin, "数据异常");
                    }

                }
            }
            //2.再做sm4解密
            for (Field field : sm4FieldList) {
                field.setAccessible(true);
                Object o = field.get(origin);
                if (null == o || (o instanceof String && o != null && ((String) o).isEmpty())) {
                    continue;
                }
                String str = (String) o;
                String encrypt = encipherService.sm4DecryptEcb(str);
                field.set(origin, encrypt);
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回时对result解密
     *
     * @param joinPoint
     * @param result    返回结果
     * @return
     */
    @AfterReturning(returning = "result", pointcut = "annotationPointCut() && @annotation(encryptMethod)")
    public Object AfterReturn(JoinPoint joinPoint, Object result, EncryptMethod encryptMethod) {
        EODType eodType = encryptMethod.businessType();
        if (eodType == EODType.ENCRYPT) {//加密操作不在此处做
            return result;
        }
        try {
            handleArg(result, eodType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
