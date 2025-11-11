package com.cb.assess.utils;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.config.ConfigureBuilder;
import org.ddr.poi.html.HtmlRenderPolicy;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.*;

public class PoiTlUtil {

    public static void export2Word(InputStream is, OutputStream out, Map<String, Object> data, List<String> htmKeys, boolean closeOut) {
        if (null == is) {
            throw new IllegalArgumentException("模板文件不能为空");
        }
        HtmlRenderPolicy htmlRenderPolicy = new HtmlRenderPolicy();
        ConfigureBuilder builder = Configure.builder();

        if (null != htmKeys && !htmKeys.isEmpty()) {
            HashSet<String> set = new HashSet<>(htmKeys);
            Iterator<String> iterator = set.iterator();
            while (iterator.hasNext()) {
                String next = iterator.next();
                builder.bind(next, htmlRenderPolicy);
            }
        }
        Configure configure = builder.build();
        try {
            // 读取模板文件，并渲染数据
            XWPFTemplate template = XWPFTemplate.compile(is, configure).render(data);
            if (closeOut) {
                template.writeAndClose(out);
            } else {
                template.write(out);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void export2Word(String resPath, OutputStream out, Map<String, Object> data, List<String> htmKeys, boolean closeOut) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(resPath);
        InputStream inputStream = classPathResource.getInputStream();
        export2Word(inputStream, out, data, htmKeys, closeOut);
    }

    public static void export2Word(File file, OutputStream out, Map<String, Object> data, List<String> htmKeys, boolean closeOut) throws IOException {
        FileInputStream is = new FileInputStream(file);
        export2Word(is, out, data, htmKeys, closeOut);
    }
}
