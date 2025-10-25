package com.cb.framework.web.service;

import javax.annotation.Resource;

import com.cb.common.constant.HttpStatus;
import com.cb.common.core.domain.entity.SysDept;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.enums.UserStatus;
import com.cb.common.exception.BaseException;
import com.cb.common.exception.user.DefaultPasswordException;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.ServletUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.sign.Md5Utils;
import com.cb.oa.service.ISysUserOutService;
import com.cb.system.domain.vo.DeptPostVo;
import com.cb.system.service.ISysCommonConfigService;
import com.cb.system.service.ISysConfigService;
import com.cb.system.service.ISysDeptService;
import com.cb.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.cb.common.constant.Constants;
import com.cb.common.core.domain.model.LoginUser;
import com.cb.common.core.redis.RedisCache;
import com.cb.common.exception.CustomException;
import com.cb.common.exception.user.CaptchaException;
import com.cb.common.exception.user.CaptchaExpireException;
import com.cb.common.exception.user.UserPasswordNotMatchException;
import com.cb.common.utils.MessageUtils;
import com.cb.framework.manager.AsyncManager;
import com.cb.framework.manager.factory.AsyncFactory;

import java.util.concurrent.TimeUnit;

import static com.cb.common.utils.SecurityUtils.encryptPassword;

/**
 * 登录校验方法
 *
 * @author ruoyi
 */
@Component
public class SysLoginService {
    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ISysCommonConfigService commonConfigService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysDeptService deptService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private ISysUserOutService userOutService;

    @Autowired
    private ISysConfigService configService;

    @Value("${sso.oa.appId}")
    private String appId;

    @Value("${sso.oa.appKey}")
    private String appKey;

    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param code     验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    public String login(String username, String password, String code, String uuid) {
        /*String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisCache.getCacheObject(verifyKey);
        Boolean lockSwitch = commonConfigService.selectPasswordLockSwitch();
        redisCache.deleteObject(verifyKey);
        if (captcha == null) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha)) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
            throw new CaptchaException();
        }*/
        validateCaptcha(username,code,uuid);
        Boolean lockSwitch = commonConfigService.selectPasswordLockSwitch();
        // 用户验证
        Authentication authentication = null;
        try {
            if (lockSwitch) inspectLock(username); // 检查账号是否锁定
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
//                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
//                throw new UserPasswordNotMatchException();
                // 登录锁定检查
                if(lockSwitch){
                    loginLock(username);
                }else{
                    AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                    throw new UserPasswordNotMatchException();
                }
            } else {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new CustomException(e.getMessage());
            }
        }
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String defaultEncryptedPassword = encryptPassword("123456"); // 这里是你的加密方式
        if (loginUser.getUser().getPassword().equals(defaultEncryptedPassword)) {
            throw new DefaultPasswordException(); // 自定义异常，表示需要修改密码
        }
        // 登录成功后清除账号锁定缓存
        if(lockSwitch){
            redisCache.deleteObject(Constants.LOGIN_LOCK_KEY + username);
        }
        // 生成token
        return tokenService.createToken(loginUser);
    }


    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public void validateCaptcha(String username, String code, String uuid)
    {
        boolean captchaEnabled = configService.selectCaptchaEnabled();
        if (captchaEnabled)
        {
            String verifyKey = Constants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
            String captcha = redisCache.getCacheObject(verifyKey);
            redisCache.deleteObject(verifyKey);
            if (captcha == null)
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
                throw new CaptchaExpireException();
            }
            if (!code.equalsIgnoreCase(captcha))
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
                throw new CaptchaException();
            }
        }
    }

    /**
     * 用户锁定
     *
     * @param userName
     */
    private void loginLock(String userName) {
        String[] lockCache = inspectLock(userName); // 检查账号是否被锁定
        int number = Integer.parseInt(lockCache[0]);
        long expireNow = System.currentTimeMillis();
        // 密码错误锁定次数
        int errorsNo = commonConfigService.selectPasswordErrorsNo();
        // 锁定时间
        int lockTime = commonConfigService.selectPasswordLockTime();
        int lockSeconds = lockTime * 60 * 1000;
        if (number++ >= (errorsNo - 1)) {   // number 从0开始，所以需要errorsNo -1
            expireNow += lockSeconds; // 锁定时间
        }

        if (number == errorsNo) {
            redisCache.setCacheObject(Constants.LOGIN_LOCK_KEY + userName, (number + "," + expireNow),
                    lockSeconds, TimeUnit.MILLISECONDS); // 缓存一天
            throw new CustomException("您输入的账号或密码已经错误" + number + "次，账号将锁定" + lockTime + "分钟",
                    HttpStatus.LOGIN_ABOUT_TO_LOCK_STATUS);
        } else {
            redisCache.setCacheObject(Constants.LOGIN_LOCK_KEY + userName, (number + "," + 0),
                    lockSeconds, TimeUnit.MILLISECONDS); // 缓存一天
            throw new CustomException("您输入的账号或密码已经错误" + number + "次，账号将在" + errorsNo + "次错误后锁定!",
                    HttpStatus.LOGIN_ABOUT_TO_LOCK_STATUS);
        }
    }

    // 账号是否锁定
    private String[] inspectLock(String userName) {
        String lockStr = redisCache.getCacheObject(Constants.LOGIN_LOCK_KEY + userName);
        if (StringUtils.isBlank(lockStr)) {
            lockStr = "0,0";
        }
        String[] lockCache = lockStr.split(",");
        long expireTime = Long.parseLong(lockCache[1]);
        expireTime = System.currentTimeMillis() - expireTime;

        if (expireTime < 0) {
            long diff = Math.abs(expireTime);
            throw new CustomException("账号已锁定，请" + DateUtils.getCountdownTime(diff) + "后重试！",
                    HttpStatus.LOGIN_LOCK_STATUS);
        }
        return lockCache;
    }

    public void changeOnlineDept(DeptPostVo vo){
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        Long onlineDeptId = vo.getDeptId();
        SysUser user = loginUser.getUser();
        Long userId = user.getUserId();
        userService.updateDeptId(userId,onlineDeptId);
        SysDept sysDept = deptService.selectDeptById(onlineDeptId);
        user.setDept(sysDept);
        user.setDeptId(onlineDeptId);
        tokenService.refreshToken(loginUser);
    }

    public String ssoLogin(String appId, String uid, String timestamp, String sign) {
        if(!this.appId.equals(appId)) {
            throw new CustomException("无效的appId");
        }
        // 生成令牌
        String string1 = "appId="+appId+"&appKey="+appKey+"&timestamp="+timestamp+"&uid="+uid;
        String sign1 = Md5Utils.hash(string1);
        if(!sign1.equals(sign)) {
            throw new CustomException("签名不正确!");
        }
        long t1 = Long.parseLong(timestamp);
        long t2 = System.currentTimeMillis();
        //签名5分钟有效
        if(t2 - t1 > (1000*60*5)) {
            throw new CustomException("签名已过期!");
        }

        SysUser user = userOutService.selectSysUserByOaUserId(uid);

        if (com.cb.common.utils.StringUtils.isNull(user))
        {
            throw new UsernameNotFoundException("OA用户：" + uid + " 没有对应的系统用户");
        }
        else if (UserStatus.DELETED.getCode().equals(user.getDelFlag()))
        {
            throw new BaseException("对不起，OA用户：" + uid + " 对应的系统用户已被删除");
        }
        else if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
            throw new BaseException("对不起，OA用户：" + uid + " 对应的系统用户已被停用");
        }
        LoginUser loginUser = new LoginUser(user, permissionService.getMenuPermission(user));
        // 生成token
        return tokenService.createToken(loginUser);
    }

}
