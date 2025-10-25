package com.cb.web.controller.export;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.cb.exportSql.domain.SqlFile;
import com.cb.exportSql.service.IPersonnelSqlService;
import com.cb.knowledge.base.service.IKnowledgeBaseService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author 李融业
 * @version 1.0
 * @CreateTime 2025/4/25 17:15
 * @Copyright (c) 2025
 * @Description 人事数据相关导出Sql类， 包括人员、请假、档案
 */
@RestController
@RequestMapping("/personnel/sql")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PersonnelExportSqlController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final IPersonnelSqlService personnelSqlService;

    private final IKnowledgeBaseService knowledgeBaseService;

    /**
     * @description 导出sqlite 和 文件（新增需求，直接导出为平板的sqlite.db文件）
     * @createtime 2025/5/17 上午10:56
     * @author 李融业
     * @version 1.0
     */
    @GetMapping("/exportSqliteDbAndFile")
    public void exportSqliteDbAndFile(HttpServletResponse response) {
        downLoad(response,"pad_files_" + System.currentTimeMillis(), zos -> {
            try {
                logger.info("开始导出sqlite.db文件");
                // 获取db文件
                String filePath = personnelSqlService.exportSqliteDb();
                addFilesToZip(zos, "db", new File(filePath));
                // 获取头像图片
                logger.info("开始导出头像文件");
                List<File> avatars = personnelSqlService.exportUserAvatars();
                addFilesToZip(zos, "avatars", avatars.toArray(new File[0]));
                // 获取知识库文件
                logger.info("开始导出知识库文件");
                List<File> ledgeBases = knowledgeBaseService.exportKnowLedgeBases();
                addFilesToZip(zos, "knowledgeBase", ledgeBases.toArray(new File[0]));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @GetMapping("/exportAndDownload")
    public void exportAndDownload(@RequestParam(value = "isAll", required = false) Boolean isAll, @RequestParam(value = "exportKey", required = false) String exportKey, HttpServletResponse response) {
        downLoad(response,"personnel_sqlFiles_" + System.currentTimeMillis(), zos -> {
            // 获取sql文件
            List<SqlFile> sqlFiles = personnelSqlService.exportLeave$user$archivesSql(isAll == null || isAll, exportKey);
            // 添加文件到ZIP
            try {
                addSqlFilesToZip(zos, sqlFiles);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @GetMapping("/exportImage")
    public void exportImage(HttpServletResponse response) {
        downLoad(response, "imgFile", zos -> {
            List<File> files = personnelSqlService.exportUserAvatars();
            try {
                addFilesToZip(zos, "", files.toArray(new File[0]));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void downLoad(HttpServletResponse response, String zipName, Consumer<ZipOutputStream> zipConsumer) {
        // 设置响应内容类型为ZIP文件
        response.setContentType("application/zip");
        // 设置响应头，指定文件名
        response.setHeader("Content-Disposition", "attachment; filename=" + zipName + ".zip");
        try {
            // 创建一个临时文件作为ZIP文件
            File tempFile = File.createTempFile(zipName, ".zip");
            try(
                    FileOutputStream fos = new FileOutputStream(tempFile);
                    BufferedOutputStream bos = new BufferedOutputStream(fos);
                    ZipOutputStream zos = new ZipOutputStream(bos);
            ) {
                zipConsumer.accept(zos);
                zos.flush();
            }
            try (
                    // 将ZIP文件写入响应输出流
                    FileInputStream fis = new FileInputStream(tempFile);
                    BufferedInputStream bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
            ) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = bis.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
                os.flush();
                // 删除临时文件
                tempFile.delete();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to export and download files.", e);
        }
    }

    private void addSqlFilesToZip(ZipOutputStream zos, List<SqlFile> files) throws IOException {
        if (CollectionUtil.isNotEmpty(files)) {
            for (SqlFile file : files) {
                ZipEntry zipEntry = new ZipEntry(file.getFileName());
                zos.putNextEntry(zipEntry);
                zos.write(file.getFileContent().getBytes());
                zos.closeEntry();
            }
        }
    }

    private void addFilesToZip(ZipOutputStream zos, String packageName, File... files) throws IOException {
        if (ArrayUtil.isNotEmpty(files)) {
            for (File file : files) {
                ZipEntry zipEntry = new ZipEntry(StrUtil.isNotBlank(packageName)? packageName + File.separator + file.getName() : file.getName())  ;
                zos.putNextEntry(zipEntry);
                try (FileInputStream fis = new FileInputStream(file)) {
                    // 缓冲区读取数据
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, length);
                    }
                } finally {
                    logger.info("文件：" + file.getName() + " 导出成功");
                }
                zos.closeEntry();
            }
        }
    }

}
