package com.cb.framework.aspectj;

import cn.hutool.core.util.StrUtil;
import com.cb.common.annotation.SecretDataScope;
import com.cb.common.core.domain.BaseEntity;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.core.domain.model.LoginUser;
import com.cb.common.enums.SecretLevel;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 涉密等级数据过滤处理
 *
 * @author ouyan
 */
@Aspect
@Component
public class SecretDataScopeAspect {
    /**
     * 数据权限过滤关键字
     */
    public static final String SECRET_DATA_SCOPE = "secretDataScope";

    @Before("@annotation(secretDataScope)")
    public void doBefore(JoinPoint point, SecretDataScope secretDataScope) throws Throwable {
        clearScope(point);
        handleDataScope(point, secretDataScope);
    }

    protected void handleDataScope(final JoinPoint joinPoint, SecretDataScope secretDataScope) {
        // 获取当前的用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        if (StringUtils.isNotNull(loginUser)) {
            SysUser currentUser = loginUser.getUser();
            // 如果是超级管理员，则不过滤数据
            if (StringUtils.isNotNull(currentUser)) {
                Integer secretLevel = currentUser.getSecretLevel() != null ? currentUser.getSecretLevel() : SecretLevel.NON.getLevel();
                List<String> levels = Arrays.stream(SecretLevel.values())
                        .filter(i -> i.getLevel() <= secretLevel)
                        .map(SecretLevel::getLevel)
                        .map(String::valueOf)
                        .collect(Collectors.toList());
                dataScopeFilter(joinPoint, secretDataScope, levels);
            }
        }
    }

    public static void dataScopeFilter(JoinPoint joinPoint, SecretDataScope secretDataScope, List<String> levels) {
        StringBuilder sqlString = new StringBuilder();
        String field = secretDataScope.field();
        if (field != null && field != "") {
            sqlString.append(StringUtils.format(" AND {} IN ({}) ", StrUtil.toUnderlineCase(field), String.join(",", levels)));
        }
        if (StringUtils.isNotBlank(sqlString.toString())) {
            Object params = joinPoint.getArgs()[0];
            if (StringUtils.isNotNull(params) && params instanceof BaseEntity) {
                BaseEntity baseEntity = (BaseEntity) params;
                baseEntity.getParams().put(SECRET_DATA_SCOPE, sqlString);
            }
        }
    }

    /**
     * 拼接权限sql前先清空params.dataScope参数防止注入
     */
    private void clearScope(final JoinPoint joinPoint) {
        Object params = joinPoint.getArgs()[0];
        if (StringUtils.isNotNull(params) && params instanceof BaseEntity) {
            BaseEntity baseEntity = (BaseEntity) params;
            baseEntity.getParams().put(SECRET_DATA_SCOPE, "");
        }
    }
}
