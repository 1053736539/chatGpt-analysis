package com.cb.common.utils.poi;

import cn.afterturn.easypoi.entity.ImageEntity;
import cn.afterturn.easypoi.word.WordExportUtil;
import cn.afterturn.easypoi.word.entity.MyXWPFDocument;
import cn.hutool.core.lang.Assert;
import com.cb.common.utils.file.FileUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class WordUtil {

    public static void exportWord(String templatePath, String targetPath, Map<String, Object> params) {
        File targetFile = new File(targetPath);
        String temDir = targetFile.getParent();
        File dir = new File(temDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fos = null;
        try {
            XWPFDocument doc = WordExportUtil.exportWord07(templatePath, params);
            fos = new FileOutputStream(targetPath);
            doc.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) {
        String templatePath = "J:\\works\\template1.docx"; //模板路径
        String targetPath = "J:\\works\\XXX统计局公务员基本情况_1.docx";//生成文档路径
        String realImagePath = "j:\\works\\chart1.png";
        //定义一个Map集合params
        Map<String, Object> params = new HashMap<>();
        params.put("year", "2023");
        params.put("month", "11");
        params.put("gwyCount", "145");
        params.put("cgCount", "15");
        params.put("sybCount", "34");

        ImageEntity simage = new ImageEntity();
        simage.setHeight(300);
        simage.setWidth(500);
        //将图片的本地路径导入进去
        simage.setUrl(realImagePath);
        //将simage对象的类型设置为URL类型。
        //该图像实体表示的是一个通过URL链接获取的远程图像，而不是一个本地存储的图像。
        simage.setType(ImageEntity.URL);

        //将图片添加进去
        params.put("chart1", simage);

        simage = new ImageEntity();
        simage.setHeight(300);
        simage.setWidth(500);
        simage.setType(ImageEntity.Data);
        BufferedImage bufferedImage = new BufferedImage(300, 500, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = bufferedImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.RED);// 设置边框色
        g2.fillRect(0, 0, 500, 300);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", out);//写入流中
        } catch (IOException e) {
            e.printStackTrace();
        }
        simage.setData(out.toByteArray()); //字节流读取
        params.put("chart2", simage);
        WordUtil.exportWord(templatePath, targetPath, params);
    }


    /**
     * EasyPoi 替换数据 导出 word
     * <p>第一步生成替换后的word文件，只支持docx</p>
     * <p>第二步下载生成的文件</p>
     * <p>第三步删除生成的临时文件</p>
     * 模版变量中变量格式：{{foo}}
     *
     * @param templatePath  word模板文件流
     * @param tempDir        生成临时文件存放地址
     * @param fileName      文件名
     * @param data          替换的参数
     * @param request       HttpServletRequest
     * @param response      HttpServletResponse
     */
    public static String easyPoiExport(InputStream templatePath, String tempDir, String fileName, Map<String, Object> data, HttpServletRequest request, HttpServletResponse response) {
        Assert.notNull(templatePath, "模板路径不能为空");
        Assert.notNull(tempDir, "临时文件路径不能为空");
        Assert.notNull(fileName, "文件名称不能为空");
        Assert.isTrue(fileName.endsWith(".docx"), "文件名称仅支持docx格式");

        if (!tempDir.endsWith("/")) {
            tempDir = tempDir + File.separator;
        }

        File file = new File(tempDir);
        if (!file.exists()) {
            file.mkdirs();
        }

        try {
            String userAgent = request.getHeader("user-agent").toLowerCase();
            if (userAgent.contains("msie") || userAgent.contains("like gecko")) {
                //fileName = URLEncoder.encode(fileName, "UTF-8");
                FileUtils.setAttachmentResponseHeader(response, URLEncoder.encode(fileName, "UTF-8"));
            } else {
                fileName = new String(fileName.getBytes("utf-8"), "ISO-8859-1");
            }

            /*XWPFDocument document = WordExportUtil.exportWord07(templatePath, data);
            String tempPath = tempDir + fileName;
            FileOutputStream out = new FileOutputStream(tempPath);
            document.write(out);*/

            MyXWPFDocument doc = new MyXWPFDocument(templatePath);
            WordExportUtil.exportWord07(doc, data);
            String tmpPath = tempDir + fileName;
            FileOutputStream fos = new FileOutputStream(tmpPath);
            doc.write(fos);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //deleteTempFile(tempDir, fileName);//这一步看具体需求，要不要删;先注释掉，不然下载的word为空
        }
        return fileName;
    }

    public static void easyPoiExport(InputStream templatePath, Map<String, Object> data, HttpServletResponse response) throws IOException {
        Assert.notNull(templatePath, "模板路径不能为空");
//        Assert.notNull(tempDir, "临时文件路径不能为空");
//        Assert.notNull(fileName, "文件名称不能为空");
//        Assert.isTrue(fileName.endsWith(".docx"), "文件名称仅支持docx格式");

//        if (!tempDir.endsWith("/")) {
//            tempDir = tempDir + File.separator;
//        }

//        File file = new File(tempDir);
//        if (!file.exists()) {
//            file.mkdirs();
//        }

        try {
//            String userAgent = request.getHeader("user-agent").toLowerCase();
//            if (userAgent.contains("msie") || userAgent.contains("like gecko")) {
                //fileName = URLEncoder.encode(fileName, "UTF-8");
//                FileUtils.setAttachmentResponseHeader(response, URLEncoder.encode(fileName, "UTF-8"));
//            } else {
//                fileName = new String(fileName.getBytes("utf-8"), "ISO-8859-1");
//            }

            /*XWPFDocument document = WordExportUtil.exportWord07(templatePath, data);
            String tempPath = tempDir + fileName;
            FileOutputStream out = new FileOutputStream(tempPath);
            document.write(out);*/

            MyXWPFDocument doc = new MyXWPFDocument(templatePath);
            WordExportUtil.exportWord07(doc, data);
//            String tmpPath = tempDir + fileName;
//            FileOutputStream fos = new FileOutputStream(tmpPath);
            ServletOutputStream outputStream = response.getOutputStream();
            doc.write(outputStream);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null!=templatePath){
                templatePath.close();
            }
            //deleteTempFile(tempDir, fileName);//这一步看具体需求，要不要删;先注释掉，不然下载的word为空
        }
        return;
    }
    /**
     * 删除临时生成的文件
     */
    public static void deleteTempFile(String filePath, String fileName) {
        File file = new File(filePath + fileName);
        File f = new File(filePath);
        file.delete();
        f.delete();
    }

}
