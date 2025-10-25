package com.cb.system.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONArray;
import com.cb.common.config.RuoYiConfig;
import com.cb.common.constant.Constants;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.core.domain.entity.UserDeptPost;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.file.FileUploadUtils;
import com.cb.system.vo.WordFieldPositionVo;
import com.cb.system.vo.WordUserVo;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.config.ConfigureBuilder;
import com.deepoove.poi.data.PictureRenderData;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.hwpf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.core.io.ClassPathResource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Description:
 * @Author: ARPHS
 * @Date: 2025-01-16 13:56
 * @Version: 1.0
 **/
public class WordImportUtil {

    private static Pattern birthDatePattern = Pattern.compile("(\\d{4}[./-]\\d{1,2}(?:[./-]\\d{1,2})?)");

    public static List<WordUserVo> batchReadUserByWord(List<File> fileList){
        if(ObjectUtil.isEmpty(fileList)){
            return Collections.emptyList();
        }
        List<WordUserVo> rst = new LinkedList<>();
        for (int i = 0; i < fileList.size(); i++) {
            File file = fileList.get(i);
            if(!file.exists() || file.isDirectory()){
                continue;
            }
            try{
                WordUserVo wordUserVo = readUserByWord(file);
                rst.add(wordUserVo);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return rst;
    }

    public static WordUserVo readUserByWord(File file) {
        if(null == file ){
            throw new IllegalArgumentException("要读取的文件不能为null！");
        }else if (file.isDirectory() || !file.exists()){
            throw new RuntimeException(String.format("%s文件不存在！", file.getName()));
        } else {
            try(FileInputStream fis = new FileInputStream(file)){
                String suffix = FileUtil.getSuffix(file).toLowerCase();
                if(suffix.equals("docx")){
                    return readUserByDocx(fis);
                } else if(suffix.equals("doc")){
                    return readUserByDoc(fis);
                } else {
                    throw new RuntimeException(String.format("%s的格式暂不支持读取！", suffix));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static WordUserVo readUserByDocx(InputStream inputStream){
        WordUserVo rst = new WordUserVo();
        try{
            XWPFDocument document = new XWPFDocument(inputStream);
            // 获取所有表格
            List<XWPFTable> tables = document.getTables();
            for (int i = 0; i < tables.size(); i++) {
                XWPFTable table = tables.get(i);
                List<WordFieldPositionVo> positionVoList = new LinkedList<>();
                if(i == 0){
                    XWPFTableCell cell = table.getRow(5).getCell(1);
                    String label = cell.getText().replaceAll("\\s", "");
                    String[] firstTableFieldPosition = "在职教育".equals(label) ?
                            WordUserVo.firstTableFieldPosition_edu_single_row : WordUserVo.firstTableFieldPosition_edu_double_row;
                    positionVoList = WordFieldPositionVo.of(firstTableFieldPosition);
                } else if(i == 1) {
                    positionVoList = WordFieldPositionVo.of(WordUserVo.secondTableFieldPosition);
                    List<WordUserVo.FamilyMemberInfo> familyMemberInfoList = getFamilyMemberInfoList(table);
                    rst.setFamilyMemberInfoList(familyMemberInfoList);
                }
                for (WordFieldPositionVo positionVo : positionVoList) {
                    String fieldName = positionVo.getFieldName();
                    String label = positionVo.getLabel();
                    int rowIndex = positionVo.getRowIndex();
                    int colIndex = positionVo.getColIndex();
                    try{
                        XWPFTableCell cell = table.getRow(rowIndex).getCell(colIndex);
                        if("resumeItemList".equals(fieldName)){
                            //简历
                            List<XWPFParagraph> paragraphs = cell.getParagraphs();
                            List<WordUserVo.ResumeItem> resumeItemList = paragraphs.stream()
                                    .map(XWPFParagraph::getText)
                                    .filter(StringUtils::isNotBlank)
                                    .map(WordImportUtil::getResumeItem)
                                    .filter(Objects::nonNull)
                                    .collect(Collectors.toList());
                            rst.setResumeItemList(resumeItemList);
                        } else {
                            String text = cell.getText();
                            if(StringUtils.isNotBlank(text)){
                                text = text.replaceAll("\\s+", "");
                            }
                            try{
                                Field field = WordUserVo.class.getDeclaredField(fieldName);
                                if(!field.isAccessible()){
                                    field.setAccessible(true);
                                }
                                field.set(rst,text);
                            } catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new WordReadFieldException(String.format("字段：【%s】读取内容失败！失败原因：%s",label,e.getMessage()));
                    }

                }
            }
            //有头像
            List<XWPFPictureData> pictures = document.getAllPictures();
            if(!pictures.isEmpty()){
                XWPFPictureData picture = pictures.get(0);
                String picName = picture.getFileName();
                String extension = picture.suggestFileExtension();
                try (InputStream is = new ByteArrayInputStream(picture.getData())) {
                    BufferedImage image = ImageIO.read(is);
                    rst.setHeadImage(image);
                    rst.setHeadImageExtension(extension);
//                    String headImage = FileUploadUtils.saveHeadImage(image, extension);
//                    rst.setHeadImage(headImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (WordReadFieldException e){
            throw e;
        } catch (Exception e){
            throw new RuntimeException("从word中读取用户信息失败！", e);
        }
        return rst;
    }

    private static WordUserVo readUserByDoc(InputStream inputStream){
        WordUserVo rst = new WordUserVo();
        try{
            HWPFDocument document = new HWPFDocument(inputStream);
            // 获取文档范围
            Range range = document.getRange();
            TableIterator it = new TableIterator(range);
            int tableIndex = 0;
            while (it.hasNext()) {
                //只找前两个表格
                if(tableIndex > 1){
                    break;
                }
                Table table = it.next();
                List<WordFieldPositionVo> positionVoList = new LinkedList<>();
                if(tableIndex == 0){
                    TableCell cell = table.getRow(5).getCell(1);
                    String label = getDocTableCellText(cell).replaceAll("\\s+", "");
                    String[] firstTableFieldPosition = "在职教育".equals(label) ?
                            WordUserVo.firstTableFieldPosition_edu_single_row : WordUserVo.firstTableFieldPosition_edu_double_row;
                    positionVoList = WordFieldPositionVo.of(firstTableFieldPosition);
                } else if(tableIndex == 1) {
                    positionVoList = WordFieldPositionVo.of(WordUserVo.secondTableFieldPosition);
                    List<WordUserVo.FamilyMemberInfo> familyMemberInfoList = getFamilyMemberInfoList(table);
                    rst.setFamilyMemberInfoList(familyMemberInfoList);
                }

                for (WordFieldPositionVo positionVo : positionVoList) {
                    String fieldName = positionVo.getFieldName();
                    String label = positionVo.getLabel();
                    int rowIndex = positionVo.getRowIndex();
                    int colIndex = positionVo.getColIndex();
                    try{
                        TableCell cell = table.getRow(rowIndex).getCell(colIndex);
                        if ("resumeItemList".equals(fieldName)) {
                            //简历
                            int num = cell.numParagraphs();
                            List<WordUserVo.ResumeItem> resumeItemList =  IntStream.range(0, num)
                                    .mapToObj(i -> cell.getParagraph(i).text())
                                    .filter(StringUtils::isNotBlank)
                                    .map(s -> s.substring(0, s.length() - 1)) //去除后面的特殊符号
                                    .map(WordImportUtil::getResumeItem)
                                    .filter(Objects::nonNull)
                                    .collect(Collectors.toList());
                            rst.setResumeItemList(resumeItemList);
                        } else {
                            String text = getDocTableCellText(cell);
                            if (StringUtils.isNotBlank(text)) {
                                text = text.replaceAll("\\s+", "");
                            }
                            try {
                                Field field = WordUserVo.class.getDeclaredField(fieldName);
                                if (!field.isAccessible()) {
                                    field.setAccessible(true);
                                }
                                field.set(rst, text);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new WordReadFieldException(String.format("字段：【%s】读取内容失败！失败原因：%s",label,e.getMessage()));
                    }
                }
                tableIndex += 1;
            }

            // 获取文档中的第一张图片
            PicturesTable picturesTable = document.getPicturesTable();
            List<Picture> pictures = picturesTable.getAllPictures();
            if(ObjectUtil.isNotEmpty(pictures)){
                Picture picture = pictures.get(0);
                String extension = picture.suggestFileExtension();
                try (InputStream is = new ByteArrayInputStream(picture.getContent())) {
                    BufferedImage image = ImageIO.read(is);
                    rst.setHeadImage(image);
                    rst.setHeadImageExtension(extension);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (WordReadFieldException e){
            throw e;
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("从word中读取用户信息失败！", e);
        }
        return rst;
    }

    /**
     * 获取doc文件中表格的单元格文本内容
     * @param td
     * @return
     */
    private static String getDocTableCellText(TableCell td){
        //取得单元格的内容
        StringBuilder text = new StringBuilder();
        for (int k = 0; k < td.numParagraphs(); k++) {
            Paragraph para = td.getParagraph(k);
            String s = para.text();
            //去除后面的特殊符号
            if (null != s && !"".equals(s)) {
                s = s.substring(0, s.length() - 1);
            }
            text.append(s);
        }
        return text.toString();
    }

    private static List<WordUserVo.FamilyMemberInfo> getFamilyMemberInfoList(XWPFTable table) {
        try {
            List<WordUserVo.FamilyMemberInfo> familyMemberInfoList = new ArrayList<>();
            List<XWPFTableRow> rows = table.getRows();
            boolean begin = false;
            for (int i = 0; i < rows.size(); i++) {
                XWPFTableRow row = rows.get(i);
                String firstCellText = row.getCell(0).getText().replaceAll("\\s+", "");
                if ("呈报单位".equals(firstCellText) || "个人优缺点".equals(firstCellText)) {
                    break;
                } else if ("家庭主要成员及重要社会关系".equals(firstCellText)) {
                    begin = true;
                    continue;
                }
                if (begin) {
                    String relation = row.getCell(1).getText();
                    String name = row.getCell(2).getText();
                    String age = row.getCell(3).getText();
                    String politicStatus = row.getCell(4).getText();
                    String workDeptAndPosition = row.getCell(5).getText();
                    boolean usedRow = Arrays.stream(new String[]{relation, name, age, politicStatus, workDeptAndPosition})
                            .anyMatch(StringUtils::isNotBlank);
                    if (usedRow) {
                        WordUserVo.FamilyMemberInfo familyMemberInfo = new WordUserVo.FamilyMemberInfo(relation, name, age, politicStatus, workDeptAndPosition);
                        familyMemberInfoList.add(familyMemberInfo);
                    }
                }
            }
            return familyMemberInfoList;
        } catch (Exception e){
            String label = "家庭主要成员及重要社会关系";
            throw new WordReadFieldException(String.format("字段：【%s】读取内容失败！失败原因：%s",label,e.getMessage()));
        }
    }

    private static List<WordUserVo.FamilyMemberInfo> getFamilyMemberInfoList(Table table) {
        try {
            List<WordUserVo.FamilyMemberInfo> familyMemberInfoList = new ArrayList<>();
            int rows = table.numRows();
            boolean begin = false;
            for (int i = 0; i < rows; i++) {
                TableRow row = table.getRow(i);
                String firstCellText = getDocTableCellText(row.getCell(0)).replaceAll("\\s+", "");
                if ("呈报单位".equals(firstCellText) || "个人优缺点".equals(firstCellText)) {
                    break;
                } else if ("家庭主要成员及重要社会关系".equals(firstCellText)) {
                    begin = true;
                    continue;
                }
                if (begin) {
                    String relation = getDocTableCellText(row.getCell(1));
                    String name = getDocTableCellText(row.getCell(2));
                    String age = getDocTableCellText(row.getCell(3));
                    String politicStatus = getDocTableCellText(row.getCell(4));
                    String workDeptAndPosition = getDocTableCellText(row.getCell(5));
                    boolean usedRow = Arrays.stream(new String[]{relation, name, age, politicStatus, workDeptAndPosition})
                            .anyMatch(StringUtils::isNotBlank);
                    if (usedRow) {
                        WordUserVo.FamilyMemberInfo familyMemberInfo = new WordUserVo.FamilyMemberInfo(relation, name, age, politicStatus, workDeptAndPosition);
                        familyMemberInfoList.add(familyMemberInfo);
                    }
                }
            }
            return familyMemberInfoList;
        } catch (Exception e){
            String label = "家庭主要成员及重要社会关系";
            throw new WordReadFieldException(String.format("字段：【%s】读取内容失败！失败原因：%s",label,e.getMessage()));
        }
    }

    public static WordUserVo.ResumeItem getResumeItem(String text) {
        String regex = "(\\d{4}[-./年]\\d{1,2}(?:[-./月])?(?:\\d{1,2}[日]?)?)\\s*(?:--\\s*(\\d{4}[-./年]\\d{1,2}(?:[-./月])?(?:\\d{1,2}[日]?)?)?)?\\s+(.*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            String startTime = matcher.group(1);
            String endTime = matcher.group(2);
            String content = matcher.group(3);
            if(StringUtils.isNotBlank(startTime)){
                startTime = DateUtils.dateStdFormat(startTime,true,"yyyy.MM");
            }
            if(StringUtils.isNotBlank(endTime)){
                endTime = DateUtils.dateStdFormat(endTime,true,"yyyy.MM");
            }
            return new WordUserVo.ResumeItem(startTime, endTime, content);
        }
        return null;
    }

    public static List<SysUser> convertWordUserVoList(List<WordUserVo> wordUserVoList) {
        if (ObjectUtil.isEmpty(wordUserVoList)) {
            return Collections.emptyList();
        }
        return wordUserVoList.parallelStream().map(WordImportUtil::convertWordUserVo).collect(Collectors.toList());
    }

    public static SysUser convertWordUserVo(WordUserVo vo) {
        SysUser sysUser = new SysUser();
        Long deptId = vo.getDeptId();
        String name = vo.getName();
        if(StringUtils.isNotBlank(name)){
            name = name.replaceAll("\\s+", "");
        }
        if(null != deptId){
            sysUser.setDeptId(deptId);
            UserDeptPost userDeptPost = new UserDeptPost();
            userDeptPost.setDeptId(deptId);
            sysUser.setUserDeptPostList(Collections.singletonList(userDeptPost));
        }
        sysUser.setIdentityType("1");
        sysUser.setNickName(name);
        sysUser.setName(name);
        sysUser.setNation(vo.getNation());
        sysUser.setNativePlace(vo.getNativePlace());
        sysUser.setBirthPlace(vo.getBirthPlace());
        sysUser.setHealthCondition(vo.getHealthStatus());
        sysUser.setSpeciality(vo.getFamiliarProfession());
        String gender = vo.getGender();
        if(StringUtils.isNotBlank(gender)){
            String dictValue = "2";
            if(gender.contains("男")){
                dictValue = "0";
            } else if(gender.contains("女")){
                dictValue = "1";
            }
            sysUser.setSex(dictValue);
        }
        sysUser.setPartyJoinTime(DateUtils.dateStdFormat(vo.getJoinPartyDate(),true));
        sysUser.setStartWorkTime(DateUtils.dateStdFormat(vo.getBeginWorkDate(),true));
        String birthDate = vo.getBirthDate();
        if(StringUtils.isNotBlank(birthDate)){
            Matcher matcher = birthDatePattern.matcher(birthDate);
            if(matcher.find()){
                String dStr = matcher.group(1);
                sysUser.setBirthday(DateUtils.dateStdFormat(dStr,true));
            }
        }
        BufferedImage headImage = vo.getHeadImage();
        String headImageExtension = vo.getHeadImageExtension();
        if(null != headImage) {
            try{
                String avatar = FileUploadUtils.saveHeadImage(headImage, headImageExtension);
                sysUser.setAvatar(avatar);
            } catch (Exception e){
            }
        }
        sysUser.setProfessionalDuty(vo.getProfessionalDuty());
        sysUser.setFullTimeEducationLevel(vo.getFullTimeEducationLevel());
        sysUser.setFullTimeEducationSchoolAndMajor(vo.getFullTimeEducationSchoolAndMajor());
        sysUser.setOnJobEducationLevel(vo.getOnJobEducationLevel());
        sysUser.setOnJobEducationSchoolAndMajor(vo.getOnJobEducationSchoolAndMajor());
        sysUser.setCurrentPosition(vo.getCurrentPosition());
        sysUser.setProposedAppointmentPosition(vo.getProposedAppointmentPosition());
        sysUser.setProposedRemovalPosition(vo.getProposedRemovalPosition());
//        List<SysUserResumeInfo> sysUserResumeInfoList = vo.getResumeItemList().stream().map(item -> {
//            SysUserResumeInfo resumeInfo = new SysUserResumeInfo();
//            resumeInfo.setStartDate(item.getBeginDate());
//            resumeInfo.setEndDate(item.getEndDate());
//            resumeInfo.setWorkJobName(item.getContent());
//            resumeInfo.setDelFlag("0");
//            return resumeInfo;
//        }).collect(Collectors.toList());
//        sysUser.setSysUserResumeInfoList(sysUserResumeInfoList);
        sysUser.setResumeJsonArray(JSONArray.toJSONString(vo.getResumeItemList()));
        sysUser.setRewardAndPunishment(vo.getRewardAndPunishment());
        sysUser.setAnnualAssessment(vo.getAnnualAssessment());
        sysUser.setReasonForAppointmentOrRemoval(vo.getReasonForAppointmentOrRemoval());
//        List<SysUserFamilyMemberSocialInfo> sysUserFamilyMemberSocialInfoList = vo.getFamilyMemberInfoList().stream().map(item -> {
//            SysUserFamilyMemberSocialInfo familyMemberSocialInfo = new SysUserFamilyMemberSocialInfo();
//            familyMemberSocialInfo.setAppellation(item.getRelation());
//            familyMemberSocialInfo.setFamilyMembersName(item.getName());
//            familyMemberSocialInfo.setBirthDate(item.getAge());
//            familyMemberSocialInfo.setPoliticalOutlook(item.getPoliticStatus());
//            familyMemberSocialInfo.setWorkUnitAndPosition(item.getWorkDeptAndPosition());
//            familyMemberSocialInfo.setDelFlag("0");
//            return familyMemberSocialInfo;
//        }).collect(Collectors.toList());
//        sysUser.setFamilyInfos(sysUserFamilyMemberSocialInfoList);
        sysUser.setFamilyMemberJsonArray(JSONArray.toJSONString(vo.getFamilyMemberInfoList()));
        sysUser.setIsHostingWork("0");
        sysUser.setPersonnelStatus("1");


        return sysUser;
    }

    public static WordUserVo convertSysUserToWordUserVo(SysUser sysUser) {
        WordUserVo vo = new WordUserVo();
        vo.setDeptId(sysUser.getDeptId());
        vo.setName(sysUser.getName());
        vo.setNation(sysUser.getNation());
        vo.setNativePlace(sysUser.getNativePlace());
        vo.setBirthPlace(sysUser.getBirthPlace());
        vo.setHealthStatus(sysUser.getHealthCondition());
        vo.setFamiliarProfession(sysUser.getSpeciality());
        String sex = sysUser.getSex();
        sex = "0".equals(sex) ? "男" : "1".equals(sex) ? "女" : "未知";
        vo.setGender(sex);
        vo.setJoinPartyDate(DateUtils.dateStdFormat(sysUser.getPartyJoinTime(),true,"yyyy.MM"));
        vo.setBeginWorkDate(DateUtils.dateStdFormat(sysUser.getStartWorkTime(),true,"yyyy.MM"));
        vo.setBirthDate(DateUtils.dateStdFormat(sysUser.getBirthday(),true,"yyyy.MM"));
        vo.setProfessionalDuty(sysUser.getProfessionalDuty());
        vo.setFullTimeEducationLevel(sysUser.getFullTimeEducationLevel());
        vo.setFullTimeEducationSchoolAndMajor(sysUser.getFullTimeEducationSchoolAndMajor());
        vo.setOnJobEducationLevel(sysUser.getOnJobEducationLevel());
        vo.setOnJobEducationSchoolAndMajor(sysUser.getOnJobEducationSchoolAndMajor());
        vo.setCurrentPosition(sysUser.getCurrentPosition());
        vo.setProposedAppointmentPosition(sysUser.getProposedAppointmentPosition());
        vo.setProposedRemovalPosition(sysUser.getProposedRemovalPosition());
        vo.setRewardAndPunishment(sysUser.getRewardAndPunishment());
        vo.setReasonForAppointmentOrRemoval(sysUser.getReasonForAppointmentOrRemoval());

        List<WordUserVo.ResumeItem> resumeItemList = new ArrayList<>();
        String resumeJsonArray = sysUser.getResumeJsonArray();
        if(StringUtils.isNotBlank(resumeJsonArray)){
            resumeItemList = JSONArray.parseArray(resumeJsonArray, WordUserVo.ResumeItem.class);
        }
        vo.setResumeItemList(resumeItemList);
        List<WordUserVo.FamilyMemberInfo> familyMemberInfoList = new ArrayList<>();
        String familyMemberJsonArray = sysUser.getFamilyMemberJsonArray();
        if(StringUtils.isNotBlank(familyMemberJsonArray)){
            familyMemberInfoList = JSONArray.parseArray(familyMemberJsonArray, WordUserVo.FamilyMemberInfo.class);
        }
        vo.setFamilyMemberInfoList(familyMemberInfoList);
        //年度考核结果
        String annualAssessment = sysUser.getAnnualAssessment();
        if(StringUtils.isNotBlank(annualAssessment)){
            try{
                List<WordUserVo.AnnualAssessment> annualAssessmentList = JSONArray.parseArray(annualAssessment, WordUserVo.AnnualAssessment.class);
                annualAssessment = annualAssessmentList.stream().map(item->{
                    return String.format("%s年%s", item.getYear(), item.getContent());
                }).collect(Collectors.joining("；"));
            } catch (Exception e){
            }
        }
        vo.setAnnualAssessment(annualAssessment);
        return vo;
    }

    public static void writeUserToWord(SysUser sysUser, OutputStream os) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("static/word/kj-word-user-export-tpl.docx");
        InputStream is = classPathResource.getInputStream();
        WordUserVo vo = convertSysUserToWordUserVo(sysUser);
        vo.getResumeItemList().forEach(item->{
            String endDate = item.getEndDate();
            if(null == endDate || "".equals(endDate.trim())){
                item.setEndDate(null);
            }
        });
        Map<String, Object> model = BeanUtil.beanToMap(vo);

        // 设置头像
        model.put("headImage", null);
        String avatar = sysUser.getAvatar();
        if(StringUtils.isNotBlank(avatar) && avatar.startsWith("/profile/avatar")){
            String localPath = RuoYiConfig.getProfile();
            String downloadPath = localPath + StringUtils.substringAfter(avatar, Constants.RESOURCE_PREFIX);
            File file = new File(downloadPath);
            if(file.exists() && file.isFile()){
                String suffix = FileUtil.getSuffix(downloadPath);
                model.put("headImage", new PictureRenderData(140,190, '.' + suffix, new FileInputStream(file)));
            }
        }
        ConfigureBuilder configureBuilder = Configure.builder().useSpringEL()
                .bind("familyMemberInfoList", new FamilyMemberTablePolicy())
                .useSpringEL();
        XWPFTemplate template = XWPFTemplate.compile(is,configureBuilder.build()).render(model);
        template.write(os);
    }

    private static void printDocx(InputStream inputStream){
        try {
            XWPFDocument document = new XWPFDocument(inputStream);
            List<XWPFTable> tables = document.getTables();
            for (int i = 0; i < tables.size(); i++) {
                XWPFTable table = tables.get(i);
                System.out.println("Table " + (i + 1) + ":");
                // 遍历表格的每一行
                for (XWPFTableRow row : table.getRows()) {
                    for (int j = 0; j < row.getTableCells().size(); j++) {
                        XWPFTableCell cell = row.getCell(j);

                        System.out.print(cell.getText() + "|\t");
                    }
                    System.out.println();
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printDoc(InputStream inputStream){
        try {
            HWPFDocument document = new HWPFDocument(inputStream);
            // 获取文档范围
            Range range = document.getRange();
            TableIterator it = new TableIterator(range);
            int tableIndex = 0;
            while (it.hasNext()) {
                //只找前两个表格
                if(tableIndex > 1){
                    break;
                }
                Table table = it.next();
                System.out.println("Table " + (tableIndex + 1) + ":");
                //迭代行，默认从0开始,可以依据需要设置i的值,改变起始行数，也可设置读取到那行，只需修改循环的判断条件即可
                for (int i = 0; i < table.numRows(); i++) {
                    TableRow tr = table.getRow(i);
                    //迭代列，默认从0开始
                    for (int j = 0; j < tr.numCells(); j++) {
                        TableCell td = tr.getCell(j);//取得单元格
                        //取得单元格的内容
                        String text="";
                        for (int k = 0; k < td.numParagraphs(); k++) {
                            Paragraph para = td.getParagraph(k);
                            String s = para.text();
                            //去除后面的特殊符号
                            if (null != s && !"".equals(s)) {
                                s = s.substring(0, s.length() - 1);
                            }
                            text+=s;
                        }
                        System.out.print(text + "|\t");
                    }
                    System.out.println();
                }
                System.out.println();
                tableIndex += 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, InvalidFormatException {
        String path = "C:\\Users\\ARPHS\\Desktop\\2.李建莉（副主任、一级主任科员、一级监察官）.doc";
        FileInputStream fis = new FileInputStream(path);
//        WordUserVo wordUserVo = readUserByWord(new File(path));
//        System.out.println(wordUserVo);
//        printDocx(fis);
        printDoc(fis);
//        String str = "2024年8月--         呈贡区委常委、区纪委书记、区监察委员会副主任、代理主任、四级高级监察官";
//        WordUserVo.ResumeItem resumeItem = getResumeItem(str);
//        System.out.println(resumeItem);
    }

}
