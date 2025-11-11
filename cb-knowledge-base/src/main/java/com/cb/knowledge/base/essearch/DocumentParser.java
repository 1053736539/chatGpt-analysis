package com.cb.knowledge.base.essearch;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hslf.usermodel.HSLFShape;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.hslf.usermodel.HSLFTextParagraph;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.sl.extractor.SlideShowExtractor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;

/**
 * @author 李融业
 * @version 1.0
 * @CreateTime 2025/5/16 16:17
 * @Copyright (c) 2025
 * @Description 文档解析器
 */
public class DocumentParser {
    private static final Logger logger = LoggerFactory.getLogger(DocumentParser.class);
    private static final int EXCEL_MAX_ROWS = 10000; // 防止OOM

    public static String parse(File file) throws Exception {
        String name = file.getName().toLowerCase();
        try {
            if (name.endsWith(".txt")) {
                return parseText(file);
            } else if (name.endsWith(".docx")) {
                return parseDocx(file);
            } else if (name.endsWith(".doc")) {
                return parseDoc(file);
            } else if (name.endsWith(".pdf")) {
                return parsePdf(file);
            } else if (name.endsWith(".xlsx") || name.endsWith(".xls")) {
                return parseExcel(file);
            } else if (name.endsWith(".pptx")) {
                return parsePptx(file);
            } else if (name.endsWith(".ppt")) {
                return parsePpt(file);
            } else {
                throw new IllegalArgumentException("不支持解析的文件格式: " + file.getName());
            }
        } catch (Exception e) {
            logger.error("解析文件 {} 时出错，文件路径: {}, 错误信息: {}", file.getName(), file.getAbsolutePath(), e.getMessage(), e);
            throw e;
        }
    }

    private static String parseText(File file) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        }
        return sb.toString();
    }

    private static String parseDocx(File file) throws Exception {
        try (XWPFDocument doc = new XWPFDocument(Files.newInputStream(file.toPath()))) {
            return new XWPFWordExtractor(doc).getText();
        }
    }

    private static String parseDoc(File file) throws Exception {
        try (FileInputStream fis = new FileInputStream(file);
            HWPFDocument doc = new HWPFDocument(fis)) {
            return new WordExtractor(doc).getText();
        }
    }

    private static String parsePdf(File file) throws Exception {
        try (PDDocument doc = PDDocument.load(file)) {
            return new PDFTextStripper().getText(doc);
        }
    }

    private static String parseExcel(File file) throws Exception {
        StringBuilder sb = new StringBuilder();
        try (Workbook workbook = WorkbookFactory.create(file)) {
            for (Sheet sheet : workbook) {
                int rowCount = 0;
                for (Row row : sheet) {
                    if (rowCount++ > EXCEL_MAX_ROWS) break;
                    for (Cell cell : row) {
                        sb.append(cell.toString()).append(" ");
                    }
                }
            }
        }
        return sb.toString();
    }

    private static String parsePpt(File file) throws Exception {
        try (HSLFSlideShow slideshow = new HSLFSlideShow(Files.newInputStream(file.toPath()))) {
            SlideShowExtractor<HSLFShape, HSLFTextParagraph> extractor = new SlideShowExtractor<>(slideshow);
            return extractor.getText();
        }
    }

    private static String parsePptx(File file) throws Exception {
        try (XMLSlideShow slideshow = new XMLSlideShow(new FileInputStream(file))) {
            SlideShowExtractor<XSLFShape, XSLFTextParagraph> extractor = new SlideShowExtractor<>(slideshow);
            return extractor.getText();
        }
    }
}
