package com.cb.web.controller.wps;

import com.cb.common.config.RuoYiConfig;
import com.cb.common.constant.Constants;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.domain.model.LoginUser;
import com.cb.common.core.redis.RedisCache;
import com.cb.common.utils.StringUtils;
import com.cb.filemanage.domain.BizAttach;
import com.cb.filemanage.service.IBizAttachService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static com.cb.common.constant.Constants.RESOURCE_PREFIX;

@RestController
@RequestMapping("")
public class WpsController {

    @Autowired
    private RedisCache redisCache;
    @Autowired
    private IBizAttachService bizAttachService;
    @Autowired
    private ServerProperties serverProperties;

    private static final Logger log = LoggerFactory.getLogger(WpsController.class);

    @GetMapping("/wpsPage/openword")
    public AjaxResult openWord(HttpServletRequest request, HttpServletResponse response ,String fileId) throws IOException {
        String mHttpUrlName = request.getRequestURI();
        String mScriptName = request.getServletPath();
        int port = serverProperties.getPort();
        String mServerUrl = "http://" + request.getServerName() + ":" + port + mHttpUrlName.substring(0, mHttpUrlName.lastIndexOf(mScriptName));//取得OfficeServer文件的完整URL
        String userKey = (String) request.getSession().getAttribute(Constants.LOGIN_TOKEN_KEY);
        String url = mServerUrl + "/wps/openword?fileId="+fileId + "&userKey="+userKey;
        return AjaxResult.success("ok", url);
    }

    @GetMapping("/wps/openword")
    public ModelAndView openWord(HttpServletRequest request, String fileId, String userKey) {
        ModelAndView mv = new ModelAndView("wps/openword");
        String mHttpUrlName = request.getRequestURI();
        String mScriptName = request.getServletPath();
        String mServerUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + mHttpUrlName.substring(0, mHttpUrlName.lastIndexOf(mScriptName));//取得OfficeServer文件的完整URL
        mv.addObject("mServerUrl", mServerUrl);
        mv.addObject("fileId", fileId);
        mv.addObject("userKey", userKey);
        return mv;
    }

    @PostMapping("/wps/saveFile")
    public AjaxResult saveFile(MultipartFile file, String fileId, String userKey, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(StringUtils.isNotEmpty(userKey)) {
            LoginUser user = redisCache.getCacheObject(userKey);
            if(user != null) {
                String filePath = RuoYiConfig.getUploadPath();
                BizAttach bizAttach = bizAttachService.selectBizAttachByAttachId(fileId);
                if(bizAttach == null) {
                    return AjaxResult.error(404, "文件不存在");
                }
                String path=filePath+bizAttach.getPath().replace(RESOURCE_PREFIX+"/upload","");
                InputStream inputStream = null;
                OutputStream outputStream = null;
                try {
                    inputStream = file.getInputStream();
                    outputStream = new FileOutputStream(path);
                    IOUtils.copy(inputStream, outputStream);
                }catch (Exception e) {
                    return AjaxResult.error(500, "保存文件出错！");
                }finally {
                    if(inputStream != null) {
                        try {
                            inputStream.close();
                        }catch (Exception e){
                        }
                    }
                    if(outputStream != null) {
                        try {
                            outputStream.close();
                        }catch (Exception e){
                        }
                    }
                }
                return AjaxResult.success();
            }
        }
        return AjaxResult.error(401, "没有权限");
    }
}
