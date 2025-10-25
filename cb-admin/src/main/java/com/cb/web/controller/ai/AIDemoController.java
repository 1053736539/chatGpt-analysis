package com.cb.web.controller.ai;

import cn.hutool.core.io.IoUtil;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.exception.CustomException;
import com.cb.common.utils.StringUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * AI相关的接口
 */
@RestController
@RequestMapping("/ai/demo")
public class AIDemoController extends BaseController {

    @RequestMapping("/gen/docFull/{type}")
    public AjaxResult generateFullDoc(@PathVariable("type") String type, HttpServletRequest request, HttpServletResponse response , String name) throws IOException {
        String fileName, keyword;
        if("jjslbg".equals(type)) {
            fileName = "jjslbgFull.txt";
            keyword = "张某立";
        } else if("zzstfx".equals(type)) {
            fileName = "zzstfxFull.txt";
            keyword = "xx单位";
        } else {
            return AjaxResult.error("生成失败");
        }
        String text = loadText(fileName, keyword, name);
        return AjaxResult.success("ok", text);
    }
    @RequestMapping("/gen/docOutline/{type}")
    public AjaxResult generateOutline(@PathVariable("type") String type, HttpServletRequest request, HttpServletResponse response , String name) throws IOException {
        String fileName, keyword;
        if("jjslbg".equals(type)) {
            fileName = "jjslbgOutline.txt";
            keyword = "张某立";
        } else if("zzstfx".equals(type)) {
            fileName = "zzstfxOutline.txt";
            keyword = "xx单位";
        } else {
            return AjaxResult.error("生成失败");
        }
        String text = loadText(fileName, keyword, name);
        return AjaxResult.success("ok", text);
    }

    private String loadText(String fileName, String keyword, String name) {
        String text = "";
        ClassPathResource classPathResource = new ClassPathResource("ai/" + fileName);
        try{
            InputStream is = classPathResource.getInputStream();
            text = IoUtil.read(is, "utf-8");
            if(StringUtils.isNotEmpty(name)) {
                text = text.replaceAll(keyword, name);
            }
        } catch (IOException e) {
        }
        return text;
    }
}
