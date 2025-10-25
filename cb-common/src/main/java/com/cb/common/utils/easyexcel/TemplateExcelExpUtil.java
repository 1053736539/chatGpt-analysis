package com.cb.common.utils.easyexcel;

import cn.hutool.core.io.resource.ResourceUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.excel.write.metadata.fill.FillWrapper;
import com.cb.common.utils.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用excel模版进行导出
 */
public class TemplateExcelExpUtil {

    public TemplateExcelExpUtil(String absolutePath, String classPath) {
        this.absolutePath = absolutePath;
        this.classPath = classPath;
    }

    /**
     * 绝对路径
     */
    private String absolutePath;

    public String getAbsolutePath() {
        return absolutePath;
    }

    public static TemplateExcelExpUtil setAbsolutePath(String absolutePath) {
        return new TemplateExcelExpUtil(absolutePath,null);
    }

    /**
     * 如果是在资源路径下，则设置此字段
     */
    private String classPath;

    public String getClassPath() {
        return classPath;
    }

    public static TemplateExcelExpUtil setClassPath(String classPath) {

        return new TemplateExcelExpUtil(null,classPath);
    }

    /**
     * 导出单列表数据
     * @param outputStream
     * @param data
     * @param list
     * @param listName
     * @throws Exception
     */
    public void export(OutputStream outputStream,Object data, List<?> list,String listName) throws Exception {
        InputStream is = getInputStreamByPath();
        HashMap<String, List<?>> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put(listName,list);
        export(is,outputStream,data,stringObjectHashMap);
    }

    /**
     * 导出多列表数据
     * @param outputStream
     * @param data
     * @throws Exception
     */
    public void export(OutputStream outputStream,Object data,Map<String, List<?>> listData) throws Exception {
        InputStream is = getInputStreamByPath();
        export(is,outputStream,data,listData);
    }
    /**
     * 导出没有列表的模板数据
     * @param outputStream
     * @param data
     * @throws Exception
     */
    public void export(OutputStream outputStream,Object data) throws Exception {
        InputStream is = getInputStreamByPath();
        export(is,outputStream,data,null);
    }

    /**
     * 获取模板输入流
     * @return
     * @throws Exception
     */
    public InputStream getInputStreamByPath() throws Exception {
        InputStream is = null;
        if (StringUtils.isNoneBlank(classPath)){
            is = ResourceUtil.getStream("classpath:"+classPath);
        }
        if (StringUtils.isNoneBlank(absolutePath)){
            is = ResourceUtil.getStream("classpath:"+absolutePath);
        }
        if (null==is)throw new Exception("请使用setAbsolutePath或setClassPath设置模板路径！！");
        return is;
    }
    /**
     * 导出单个列表数据
     * @param is 模板文件输入流
     * @param outputStream 输出流
     * @param data 模板非列表的数据
     * @param listData 模板列表数据,可以是多组数据
     */
    public  void export(InputStream is, OutputStream outputStream, Object data, Map<String, List<?>> listData){
//        InputStream is = null;
        // 取出模板
        try {
            //本地
//            is = ResourceUtil.getStream(path);
            FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
            //导入数据
            ExcelWriter excelWriter = EasyExcel.write(outputStream).withTemplate(is).excelType(ExcelTypeEnum.XLS).build();
            WriteSheet writeSheet = EasyExcel.writerSheet().build();
            // 这个传进去的 map 就是非表格数据
            excelWriter.fill(data, writeSheet);
            // 这个 data 是官方约定就这么写， 传进去的 list 就是要遍历的表格数据
            if (null!=listData&&!listData.isEmpty()){
                for (Map.Entry<String, List<?>> entry : listData.entrySet()) {
                    String listName = entry.getKey();
                    List<?> value = entry.getValue();
                    excelWriter.fill(new FillWrapper(listName, value), fillConfig,writeSheet);
                }
            }
            excelWriter.finish();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
