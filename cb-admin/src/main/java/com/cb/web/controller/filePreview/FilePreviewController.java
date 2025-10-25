package com.cb.web.controller.filePreview;

import cn.hutool.core.codec.Base64;
import cn.hutool.http.HttpUtil;
import com.cb.common.exception.CustomException;
import com.cb.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.net.URLDecoder;

/**
 * @Description: 第三方文件预览跨域中转
 * @Author ARPHS
 * @Date 2024/1/25 15:42
 */
@Controller
@Validated
public class FilePreviewController {
    private static final Logger log = LoggerFactory.getLogger(FilePreviewController.class);

    @GetMapping("/filePreview")
    public void previewLink(HttpServletRequest request, HttpServletResponse response,@NotBlank(message = "文件url不能为空") String url,String name,boolean previewPdf){
        try{
            url = Base64.decodeStr(url);
            url = URLDecoder.decode(url, "UTF-8");
            response.reset();
            response.setCharacterEncoding("utf-8");
            if(previewPdf){
                response.setContentType("application/pdf");
            } else {
                response.setContentType("application/octet-stream");
            }
            if(StringUtils.isBlank(name)){
                response.setHeader("Content-Disposition", "attachment;filename=" + name);
            }
            HttpUtil.download(url, response.getOutputStream(), true);
        } catch (Exception e){
            log.error("预览第三方文件下载失败" + url);
            throw new CustomException("预览第三方文件下载失败");
        }
    }
}
