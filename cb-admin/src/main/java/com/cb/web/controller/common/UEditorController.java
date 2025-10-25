package com.cb.web.controller.common;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cb.common.config.RuoYiConfig;
import com.cb.framework.config.ServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.alibaba.fastjson.JSONObject;
import com.cb.common.utils.file.FileUploadUtils;

/**
 * @author hujilie
 */
@RestController
@RequestMapping("/ueditor")
public class UEditorController {

    @Autowired
    private ServerConfig serverConfig;

    @RequestMapping("/controller")
    public void ueditorController(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String action = request.getParameter("action");
        if ("config".equals(action)) {
            getConfig(request, response);
        } else if (request instanceof MultipartHttpServletRequest) {
            uploadFile(request, response);
        }
    }

    private void uploadFile(HttpServletRequest request, HttpServletResponse response) {
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
            MultipartFile mf = null;
            if (fileMap.size() > 0) {
                for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
                    mf = entity.getValue();// 获取上传文件对象
                    if (mf.isEmpty()) {
                        continue;
                    }
                    break;
                }
            }
            if (mf != null) {
                String originName = mf.getOriginalFilename();
                // 上传文件路径
                String filePath = RuoYiConfig.getUploadPath();
                // 上传并返回新文件名称
                String fileName = FileUploadUtils.upload(filePath, mf);
                String url = serverConfig.getUrl() + fileName;
                 JSONObject data = new JSONObject();
                data.put("state", "SUCCESS");
                data.put("url", url);
                data.put("originName", originName);
                PrintWriter pw = null;
                try {
                    response.setCharacterEncoding("utf-8");
                    response.setContentType("application/json");
                    pw = response.getWriter();
                    pw.write(data.toJSONString());
                    pw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (pw != null) {
                        pw.close();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getConfig(HttpServletRequest request, HttpServletResponse response) {
        InputStream fis = null;
        BufferedInputStream bis = null;
        try {
            fis = this.getClass().getClassLoader().getResourceAsStream("uediter-config.json");
            byte[] buff = new byte[1024];
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, i);
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
