package com.cb.web.controller.system;

import cn.afterturn.easypoi.entity.ImageEntity;
import com.cb.common.config.RuoYiConfig;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.domain.entity.SysDept;
import com.cb.common.core.domain.entity.SysUserAbilityLabel;
import com.cb.common.core.domain.vo.ExportUserAppointInfo;
import com.cb.common.core.domain.vo.ExportUserVo;
import com.cb.common.core.domain.vo.GwyIdentityTypeVo;
import com.cb.common.core.domain.vo.VSysUser;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.poi.ExcelExpUtil;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.utils.poi.JFreeChartToFileUtil;
import com.cb.common.utils.poi.WordUtil;
import com.cb.system.service.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PieLabelLinkStyle;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/userRoster")
public class ExportUserVoController  extends BaseController {
    @Autowired
    private IExportUserVoService exportUserVoService;
    @Autowired
    private ISysUserInfoStatisticsService userInfoStatisticsService;
    @Autowired
    private ISysDeptService deptService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysUserAbilityLabelService abilityLabelService;

    /**
     * 用户名册导出
     * @param userRoster
     * @return
     */
    @PreAuthorize("@ss.hasPermi('system:userRoster:export')")
    @GetMapping("/exportUserRoster")
    public AjaxResult exportUserRoster(HttpServletResponse response, ExportUserVo userRoster) throws IOException {
        List<ExportUserVo> list = exportUserVoService.selectUserExportList(userRoster);
//        干部标签转换为中文
        SysUserAbilityLabel abilityLabel= new SysUserAbilityLabel();
        List<SysUserAbilityLabel> abilityLabelList=abilityLabelService.selectAbilityLabelList(abilityLabel);
        Map<Integer, String> labelMap = abilityLabelList.stream()
                .collect(Collectors.toMap(
                        SysUserAbilityLabel::getId,
                        SysUserAbilityLabel::getAbilityLabel
                ));
        for (ExportUserVo exportUserVo: list){
            if(StringUtils.isNotBlank(exportUserVo.getAbilityLabel())){
              String userAbilityLabel= Arrays.stream((exportUserVo.getAbilityLabel()).split(","))
                        .map(code -> labelMap.getOrDefault(Integer.valueOf(code), code))
                        .collect(Collectors.joining("/"));
              exportUserVo.setAbilityLabel(userAbilityLabel);
            }
        }
        ExcelExpUtil<ExportUserVo> util = new ExcelExpUtil<ExportUserVo>(ExportUserVo.class);
        return util.exportExcelSupportFreeze(response, list, "用户名册",3,1,3,1);//冻结三列，第一行
    }
    /**
     * 用户名册导出查询列表
     * @param userRoster
     * @return
     */
    @PreAuthorize("@ss.hasPermi('system:userRoster:export')")
    @GetMapping("/listUserRoster")
    public TableDataInfo listUserRoster(HttpServletResponse response, ExportUserVo userRoster) throws IOException {
        startPage();
        List<ExportUserVo> list = exportUserVoService.selectUserExportList(userRoster);
        TableDataInfo dataTable = getDataTable(list);
        return dataTable;
    }

    /**
     * 选拔任用资格信息担任正处级领导职务人员导出
     * @param userAppointInfo
     * @return
     */
    @PreAuthorize("@ss.hasPermi('system:userAppointZcjInfo:export')")
    @GetMapping("/exportUserAppointZcjInfo")
    public AjaxResult exportUserAppointZcjInfo(ExportUserAppointInfo userAppointInfo) {
        List<ExportUserAppointInfo> list = exportUserVoService.selectUserAppointZcjInfoList(userAppointInfo);
        ExcelUtil<ExportUserAppointInfo> util = new ExcelUtil<ExportUserAppointInfo>(ExportUserAppointInfo.class);
        return util.exportExcel(list, "担任正处级领导职务人员");
    }
    /**
     * 选拔任用资格信息担任正处级领导职务人员导出
     * @param userAppointInfo
     * @return
     */
    @PreAuthorize("@ss.hasPermi('system:userAppointZcjInfo:export')")
    @GetMapping("/listUserAppointZcjInfo")
    public TableDataInfo listUserAppointZcjInfo(ExportUserAppointInfo userAppointInfo) {
        startPage();
        List<ExportUserAppointInfo> list = exportUserVoService.selectUserAppointZcjInfoList(userAppointInfo);
        return getDataTable(list);
    }

    /**
     * 导出担任副处级领导职务
     * @param userAppointInfo
     * @return
     */
    @PreAuthorize("@ss.hasPermi('system:userAppointFcjInfo:export')")
    @GetMapping("/exportUserAppointFcjInfo")
    public AjaxResult exportUserAppointFcjInfo(ExportUserAppointInfo userAppointInfo) {
        List<ExportUserAppointInfo> list = exportUserVoService.selectUserAppointFcjInfoList(userAppointInfo);
        ExcelUtil<ExportUserAppointInfo> util = new ExcelUtil<ExportUserAppointInfo>(ExportUserAppointInfo.class);
        return util.exportExcel(list, "担任副处级领导职务人员");
    }    /**
     * 导出担任副处级领导职务
     * @param userAppointInfo
     * @return
     */
    @PreAuthorize("@ss.hasPermi('system:userAppointFcjInfo:export')")
    @GetMapping("/listUserAppointFcjInfo")
    public TableDataInfo listUserAppointFcjInfo(ExportUserAppointInfo userAppointInfo) {
        startPage();
        List<ExportUserAppointInfo> list = exportUserVoService.selectUserAppointFcjInfoList(userAppointInfo);
        return getDataTable(list);
    }

    /**
     * 导出担任事业单位正处级领导职务（管理五级岗位）
    * @param userAppointInfo
    */
    @PreAuthorize("@ss.hasPermi('system:userAppointSyZcjInfo:export')")
    @GetMapping("/exportUserAppointSyZcjInfo")
    public AjaxResult userAppointSyZcjInfo(ExportUserAppointInfo userAppointInfo) {
        List<ExportUserAppointInfo> list = exportUserVoService.selectUserAppointSyZcjInfoList(userAppointInfo);
        ExcelUtil<ExportUserAppointInfo> util = new ExcelUtil<ExportUserAppointInfo>(ExportUserAppointInfo.class);
        return util.exportExcel(list, "担任事业单位正处级领导职务人员");
    }
    /**
     * 导出担任事业单位正处级领导职务（管理五级岗位）
    * @param userAppointInfo
    */
    @PreAuthorize("@ss.hasPermi('system:userAppointSyZcjInfo:export')")
    @GetMapping("/listUserAppointSyZcjInfo")
    public TableDataInfo listUserAppointSyZcjInfo(ExportUserAppointInfo userAppointInfo) {
        startPage();
        List<ExportUserAppointInfo> list = exportUserVoService.selectUserAppointSyZcjInfoList(userAppointInfo);
        return getDataTable(list);
    }

    // 获取当前日期
    LocalDate currentDate = LocalDate.now();
    // 获取当前年份
    int year = currentDate.getYear();
    // 获取当前月份
    int month = currentDate.getMonthValue();

    Long deptId = 100L;

    /**
     * 根据personnelStatisticsTemplate模板导出word
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @PreAuthorize("@ss.hasPermi('system:personnelStatistics:exportWord')")
    @GetMapping("/personnelStatisticsExportWord")
    public AjaxResult exportWord(HttpServletRequest request, HttpServletResponse response) throws IOException {

        SysDept sysDept = deptService.selectDeptById(deptId);

        //List<Map<String, Object>> selectIdentityTypeCount = userInfoStatisticsService.selectIdentityTypeCount(deptId);//职级情况

        //单位名称
        String unitName = sysDept.getDeptName();

        //String zjxzCount = selectIdentityTypeCount.get(0).get("xz").toString();//职级行政编制
        //int zjsybCount = Integer.parseInt(selectIdentityTypeCount.get(0).get("cg").toString()) + Integer.parseInt(selectIdentityTypeCount.get(0).get("sy").toString());

        Map<String, Object> map = new HashMap<>();
        map.put("unitName", unitName);

        map.put("year", year);
        map.put("month", month);

        identityTypeNumber(map); //行政编制和事业编制分布情况
        xzbSybIdentityDistributionNum(map); //行政编制和事业编制性别、民族、政治面貌、年龄、学历情况
        fcjUpXzsybDistributionNum(map); //副处级及以上行政编制和事业编制的性别、民族、政治情况
        fcjUpXzsybAgeDistributionNum(map); //副处级及以上行政编制和事业编制的年龄情况
        fcjUpXzsybEducationDistributionNum(map); //副处级及以上行政编制和事业编制的学历情况
        xzbzjNum(map); //行政编职级数量统计
        sybzjNum(map); //事业编职级数量统计

        putIdentityTypeEchartPie(map); //行政编制和事业编制分布情况饼图
        putXzbSybIdentityDistributionBar(map); //行政编制和事业编制性别、民族、政治面貌、年龄、学历情况柱形图
        putFcjUpXzsybDistributionBar(map); //副处级及以上行政编制和事业编制的性别、民族、政治情况柱形图
        putFcjUpXzsybAgeDistributionBar(map); //副处级及以上行政编制和事业编制的年龄情况柱形图
        putFcjUpXzsybEducationDistributionBar(map); //副处级及以上行政编制和事业编制的学历情况柱形图
        putXzbzjNumBar(map);//行政编职级数量统计条形图
        putSybzjNumBar(map);//事业编职级数量统计条形图

        String fileName = unitName + "人员信息统计导出" + ".docx";
        //获取yml配置地址
        String tempDir = RuoYiConfig.getProfile() + "/download/";
        ClassPathResource classPathResource = new ClassPathResource("static/word/personnelStatisticsTemplate.docx");
        InputStream is = classPathResource.getInputStream();

        String name = WordUtil.easyPoiExport(is, tempDir, fileName, map, request, response);
        return AjaxResult.success(name);
    }

    //行政编制和参公
    /**
     * 根据personnelxzbcgStatisticsTemplate模板导出word
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @GetMapping("/personnelxzbcgStatisticsExportWord")
    public AjaxResult exportxzbcgWord(HttpServletRequest request, HttpServletResponse response) throws IOException {

        SysDept sysDept = deptService.selectDeptById(deptId);
        //单位名称
        String unitName = sysDept.getDeptName();

        Map<String, Object> map = new HashMap<>();
        map.put("unitName", unitName);

        map.put("year", year);
        map.put("month", month);


        identityTypeNumber(map); //行政编制和参公分布情况
        putXzbcgEchartPie(map); //行政编制和参公分布情况饼图
        xzbcgIdentityDistributionNum(map);//行政编制和参公性别、民族、政治面貌、学历、年龄情况统计数量
        putXzbcgIdentityDistributionNumBar(map);//行政编制和参公性别、民族、政治面貌、学历、年龄情况统计条形图

        xzbcgzjNum(map);//行政参公职级情况
        putXzbcgzjNumBar(map);//行政参公职级情况条形图

        fcjUpXzbcgDistributionNum(map);//副处级及以上行政编制和参公性别、民族、政治面貌情况
        putFcjUpXzbcgDistributionNumBar(map);//副处级及以上行政编制和参公性别、民族、政治面貌情况条形图

        fcjUpXzbcgAgeDistributionNum(map);//副处级及以上行政编制和参公年龄情况数量统计
        putFcjUpXzbcgAgeDistributionNumBar(map);//副处级及以上行政编制和参公年龄情况数量统计条形图

        fcjUpXzbcgEducationDistributionNum(map);//副处级及以上行政编制和参公学历情况数量统计
        putFcjUpXzbcgEducationDistributionNumBar(map);//副处级及以上行政编制和参公学历情况数量统计条形图

        String fileName = unitName + "行政编制和参公基本情况" + ".docx";
        //获取yml配置地址
        String tempDir = RuoYiConfig.getProfile() + "/download/";
        ClassPathResource classPathResource = new ClassPathResource("static/word/personnelxzbcgStatisticsTemplate.docx");
        InputStream is = classPathResource.getInputStream();

        String name = WordUtil.easyPoiExport(is, tempDir, fileName, map, request, response);

        return AjaxResult.success(name);
    }

    //事业编
    /**
     * 根据personnelsybStatisticsTemplate模板导出word
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    //@PreAuthorize("@ss.hasPermi('system:personnelxzbcgStatistics:exportWord')")
    @GetMapping("/personnelsybStatisticsExportWord")
    public AjaxResult exportsybWord(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SysDept sysDept = deptService.selectDeptById(deptId);
        //单位名称
        String unitName = sysDept.getDeptName();

        Map<String, Object> map = new HashMap<>();
        map.put("unitName", unitName);

        map.put("year", year);
        map.put("month", month);

        sybIdentityTypeNumber(map);//事业编数量

        sybIdentityDistributionNum(map);//事业编性别、民族、政治面貌、学历、年龄情况
        putSybIdentityDistributionNumBar(map);//事业编性别、民族、政治面貌、学历、年龄情况条形图

        sybzjNum(map); //事业编职级数量统计
        putSybzjNumBar(map);//事业编职级数量统计条形图

        fcjUpSybDistributionNum(map); //副处级及以上事业编性别、民族、政治面貌情况
        putFcjUpSybDistributionNumBar(map); //副处级及以上事业编性别、民族、政治面貌情况条形图

        fcjUpSybAgeDistributionNum(map); //副处级及以上事业编年龄情况数量统计
        putFcjUpSybAgeDistributionNumBar(map); //副处级及以上事业编年龄情况数量统计条形图

        fcjUpSybEducationDistributionNum(map); //副处级及以上事业编学历情况数量统计
        putFcjUpSybEducationDistributionNumBar(map); //副处级及以上事业编学历情况数量统计条形图

        String fileName = unitName + "事业编基本情况" + ".docx";
        //获取yml配置地址
        String tempDir = RuoYiConfig.getProfile() + "/download/";

        ClassPathResource classPathResource = new ClassPathResource("static/word/personnelsybStatisticsTemplate.docx");
        InputStream is = classPathResource.getInputStream();

        String name = WordUtil.easyPoiExport(is, tempDir, fileName, map, request, response);

        return AjaxResult.success(name);

    }

    /**
     *  行政编制和事业编制分布情况
     * @param identityTypeNumberMap
     */
    private void identityTypeNumber(Map<String, Object> identityTypeNumberMap) {
        //行政编制和事业编制分布情况
        List<GwyIdentityTypeVo> selectIdentityTypeNumber = userInfoStatisticsService.selectIdentityTypeNumber(deptId);
        String xzbCount = selectIdentityTypeNumber.get(0).getXz();//行政编制人数
        String cgCount = selectIdentityTypeNumber.get(0).getCg();//事业编（参公）编制人数
        String sybCount = selectIdentityTypeNumber.get(0).getSy();//事业编制人数
        String jdCount = selectIdentityTypeNumber.get(0).getJd();//借调人数

        identityTypeNumberMap.put("xzbCount", xzbCount);
        identityTypeNumberMap.put("cgCount", cgCount);
        identityTypeNumberMap.put("sybCount", sybCount);
        identityTypeNumberMap.put("jdCount", jdCount);
    }

    /**
     *  事业编分布情况
     * @param sybIdentityTypeNumberMap
     */
    private void sybIdentityTypeNumber(Map<String, Object> sybIdentityTypeNumberMap) {
        //行政编制和事业编制分布情况
        List<GwyIdentityTypeVo> selectIdentityTypeNumber = userInfoStatisticsService.selectIdentityTypeNumber(deptId);
        String sybCount = selectIdentityTypeNumber.get(0).getSy();//行政编制人数
        sybIdentityTypeNumberMap.put("sybCount", sybCount);
    }

    /**
     * 行政编制和事业编制性别、民族、政治面貌、年龄、学历情况
     * @param xzbSybIdentityDistributionNumMap
     */
    private void xzbSybIdentityDistributionNum(Map<String, Object> xzbSybIdentityDistributionNumMap){
        //行政编制和事业编制性别、民族、政治面貌、年龄、学历情况
        List<Map<String, Object>> selectXzbSybIdentityDistributionNum = userInfoStatisticsService.selectXzbSybIdentityDistributionNum();
        String xzbWomanNum = selectXzbSybIdentityDistributionNum.get(0).get("num").toString();//行政中有女性人数
        String xzbMinorityNationNum = selectXzbSybIdentityDistributionNum.get(1).get("num").toString();//行政少数民族人数
        String xzbPartyMemberNum = selectXzbSybIdentityDistributionNum.get(2).get("num").toString();//行政中共党员人数
        String xzbNotPartyMemberNum = selectXzbSybIdentityDistributionNum.get(3).get("num").toString();//行政非中共党员人数
        String xzbbkEducationNum = selectXzbSybIdentityDistributionNum.get(4).get("num").toString();//行政大学本科及以上学历的人数
        String xzbYearsOld35BelowNum = selectXzbSybIdentityDistributionNum.get(5).get("num").toString();//行政35岁及以下的人数

        String sybWomanNum = selectXzbSybIdentityDistributionNum.get(6).get("num").toString();//行政中有女性人数
        String sybMinorityNationNum = selectXzbSybIdentityDistributionNum.get(7).get("num").toString();//事业编少数民族人数
        String sybPartyMemberNum = selectXzbSybIdentityDistributionNum.get(8).get("num").toString();//事业编中共党员人数
        String sybNotPartyMemberNum = selectXzbSybIdentityDistributionNum.get(9).get("num").toString();//事业编非中共党员人数
        String sybbkEducationNum = selectXzbSybIdentityDistributionNum.get(10).get("num").toString();//事业编大学本科及以上学历的人数
        String sybYearsOld35BelowNum = selectXzbSybIdentityDistributionNum.get(11).get("num").toString();//事业编35岁及以下的人数

        xzbSybIdentityDistributionNumMap.put("xzbWomanNum", xzbWomanNum);
        xzbSybIdentityDistributionNumMap.put("xzbMinorityNationNum", xzbMinorityNationNum);
        xzbSybIdentityDistributionNumMap.put("xzbPartyMemberNum", xzbPartyMemberNum);
        xzbSybIdentityDistributionNumMap.put("xzbNotPartyMemberNum", xzbNotPartyMemberNum);
        xzbSybIdentityDistributionNumMap.put("xzbbkEducationNum", xzbbkEducationNum);
        xzbSybIdentityDistributionNumMap.put("xzbYearsOld35BelowNum", xzbYearsOld35BelowNum);
        xzbSybIdentityDistributionNumMap.put("sybWomanNum", sybWomanNum);
        xzbSybIdentityDistributionNumMap.put("sybMinorityNationNum", sybMinorityNationNum);
        xzbSybIdentityDistributionNumMap.put("sybPartyMemberNum", sybPartyMemberNum);
        xzbSybIdentityDistributionNumMap.put("sybNotPartyMemberNum", sybNotPartyMemberNum);
        xzbSybIdentityDistributionNumMap.put("sybbkEducationNum", sybbkEducationNum);
        xzbSybIdentityDistributionNumMap.put("sybYearsOld35BelowNum", sybYearsOld35BelowNum);
    }

    /**
     * 副处级及以上行政编制和事业编制的性别、民族、政治情况
     * @param fcjUpXzsybDistributionNumMap
     */
    private void fcjUpXzsybDistributionNum(Map<String, Object> fcjUpXzsybDistributionNumMap){
        //副处级及以上行政编制和事业编制的性别、民族、政治情况
        List<Map<String, Object>> selectFcjUpXzsybDistributionNum = userInfoStatisticsService.selectFcjUpXzsybDistributionNum();

        /*String fcjupxzbNum = selectFcjUpXzsybDistributionNum.get(0).get("num").toString();//副处级及以上行政编制的人员
        String fcjupxzbWomanCadreNum = selectFcjUpXzsybDistributionNum.get(1).get("num").toString();//副处级及以上行政编制的女干部数量
        String fcjupxzbMinorityNationCadreNum = selectFcjUpXzsybDistributionNum.get(2).get("num").toString();//副处级及以上行政编制的少数民族干部数量
        String fcjupxzbPartyMemberNum = selectFcjUpXzsybDistributionNum.get(3).get("num").toString();//副处级及以上行政编制的中共党员数量
        String fcjupxzbNotPartyMemberNum = selectFcjUpXzsybDistributionNum.get(4).get("num").toString();//副处级及以上行政编制的非中共党员数量
        String fcjupsybNum = selectFcjUpXzsybDistributionNum.get(5).get("num").toString();//副处级及以上事业编制的人员
        String fcjupsybWomanCadreNum = selectFcjUpXzsybDistributionNum.get(6).get("num").toString();//副处级及以上事业编制的女干部数量
        String fcjupsybMinorityNationCadreNum = selectFcjUpXzsybDistributionNum.get(7).get("num").toString();//副处级及以上事业编制的少数民族干部数量
        String fcjupsybPartyMemberNum = selectFcjUpXzsybDistributionNum.get(8).get("num").toString();//副处级及以上事业编制的中共党员数量
        String fcjupsybNotPartyMemberNum = selectFcjUpXzsybDistributionNum.get(9).get("num").toString();//副处级及以上事业编制的非中共党员数量

        fcjUpXzsybDistributionNumMap.put("fcjupxzbNum", fcjupxzbNum);
        fcjUpXzsybDistributionNumMap.put("fcjupxzbWomanCadreNum", fcjupxzbWomanCadreNum);
        fcjUpXzsybDistributionNumMap.put("fcjupxzbMinorityNationCadreNum", fcjupxzbMinorityNationCadreNum);
        fcjUpXzsybDistributionNumMap.put("fcjupxzbPartyMemberNum", fcjupxzbPartyMemberNum);
        fcjUpXzsybDistributionNumMap.put("fcjupxzbNotPartyMemberNum", fcjupxzbNotPartyMemberNum);
        fcjUpXzsybDistributionNumMap.put("fcjupsybNum", fcjupsybNum);
        fcjUpXzsybDistributionNumMap.put("fcjupsybWomanCadreNum", fcjupsybWomanCadreNum);
        fcjUpXzsybDistributionNumMap.put("fcjupsybMinorityNationCadreNum", fcjupsybMinorityNationCadreNum);
        fcjUpXzsybDistributionNumMap.put("fcjupsybPartyMemberNum", fcjupsybPartyMemberNum);
        fcjUpXzsybDistributionNumMap.put("fcjupsybNotPartyMemberNum", fcjupsybNotPartyMemberNum);*/
    }

    /**
     * 副处级及以上行政编制和事业编制的年龄情况
     * @param fcjUpXzsybAgeDistributionNumMap
     */
    private void fcjUpXzsybAgeDistributionNum(Map<String, Object> fcjUpXzsybAgeDistributionNumMap){
        //副处级及以上行政编制和事业编制的年龄情况
        List<Map<String, Object>> selectFcjUpXzsybAgeDistributionNum = userInfoStatisticsService.selectFcjUpXzsybAgeDistributionNum();

        String fcjupxzbYearsOld35BelowNum = selectFcjUpXzsybAgeDistributionNum.get(0).get("num").toString();//行政编35岁及以下人数
        String fcjupxzbYearsOld36to40Num = selectFcjUpXzsybAgeDistributionNum.get(1).get("num").toString();//行政编36岁至40岁的人数
        String fcjupxzbYearsOld41to45Num = selectFcjUpXzsybAgeDistributionNum.get(2).get("num").toString();//行政编41岁至45岁的人数
        String fcjupxzbYearsOld46to50Num = selectFcjUpXzsybAgeDistributionNum.get(3).get("num").toString();//行政编46岁至50岁的人数
        String fcjupxzbYearsOld51to54Num = selectFcjUpXzsybAgeDistributionNum.get(4).get("num").toString();//行政编51岁至54岁的人数
        String fcjupxzbYearsOld55overNum = selectFcjUpXzsybAgeDistributionNum.get(5).get("num").toString();//行政编55岁及以上的人数
        String fcjupsybYearsOld35BelowNum = selectFcjUpXzsybAgeDistributionNum.get(6).get("num").toString();//事业编35岁及以下人数
        String fcjupsybYearsOld36to40Num = selectFcjUpXzsybAgeDistributionNum.get(7).get("num").toString();//事业编36岁至40岁的人数
        String fcjupsybYearsOld41to45Num = selectFcjUpXzsybAgeDistributionNum.get(8).get("num").toString();//事业编41岁至45岁的人数
        String fcjupsybYearsOld46to50Num = selectFcjUpXzsybAgeDistributionNum.get(9).get("num").toString();//事业编46岁至50岁的人数
        String fcjupsybYearsOld51to54Num = selectFcjUpXzsybAgeDistributionNum.get(10).get("num").toString();//事业编51岁至54岁的人数
        String fcjupsybYearsOld55overNum = selectFcjUpXzsybAgeDistributionNum.get(11).get("num").toString();//事业编55岁及以上的人数

        fcjUpXzsybAgeDistributionNumMap.put("fcjupxzbYearsOld35BelowNum", fcjupxzbYearsOld35BelowNum);
        fcjUpXzsybAgeDistributionNumMap.put("fcjupxzbYearsOld36to40Num", fcjupxzbYearsOld36to40Num);
        fcjUpXzsybAgeDistributionNumMap.put("fcjupxzbYearsOld41to45Num", fcjupxzbYearsOld41to45Num);
        fcjUpXzsybAgeDistributionNumMap.put("fcjupxzbYearsOld46to50Num", fcjupxzbYearsOld46to50Num);
        fcjUpXzsybAgeDistributionNumMap.put("fcjupxzbYearsOld51to54Num", fcjupxzbYearsOld51to54Num);
        fcjUpXzsybAgeDistributionNumMap.put("fcjupxzbYearsOld55overNum", fcjupxzbYearsOld55overNum);
        fcjUpXzsybAgeDistributionNumMap.put("fcjupsybYearsOld35BelowNum", fcjupsybYearsOld35BelowNum);
        fcjUpXzsybAgeDistributionNumMap.put("fcjupsybYearsOld36to40Num", fcjupsybYearsOld36to40Num);
        fcjUpXzsybAgeDistributionNumMap.put("fcjupsybYearsOld41to45Num", fcjupsybYearsOld41to45Num);
        fcjUpXzsybAgeDistributionNumMap.put("fcjupsybYearsOld46to50Num", fcjupsybYearsOld46to50Num);
        fcjUpXzsybAgeDistributionNumMap.put("fcjupsybYearsOld51to54Num", fcjupsybYearsOld51to54Num);
        fcjUpXzsybAgeDistributionNumMap.put("fcjupsybYearsOld55overNum", fcjupsybYearsOld55overNum);
    }

    /**
     * 副处级及以上行政编制和事业编制的学历情况
     * @param fcjUpXzsybEducationDistributionNumMap
     */
    private void fcjUpXzsybEducationDistributionNum(Map<String, Object> fcjUpXzsybEducationDistributionNumMap){
        //副处级及以上行政编制和事业编制的学历情况
        List<Map<String, Object>> selectFcjUpXzsybEducationDistributionNum = userInfoStatisticsService.selectFcjUpXzsybEducationDistributionNum();
        String fcjupxzbGraduateDegreeNum = selectFcjUpXzsybEducationDistributionNum.get(0).get("num").toString();//行政编制研究生学历的人数
        String fcjupxzbBachelorDegreeNum = selectFcjUpXzsybEducationDistributionNum.get(1).get("num").toString();//行政编制大学本科学历的人数
        String fcjupxzbJuniorCollegeNum = selectFcjUpXzsybEducationDistributionNum.get(2).get("num").toString();//行政编制大学专科及以下学历的人数
        String fcjupsybGraduateDegreeNum = selectFcjUpXzsybEducationDistributionNum.get(3).get("num").toString();//事业编制研究生学历的人数
        String fcjupsybBachelorDegreeNum = selectFcjUpXzsybEducationDistributionNum.get(4).get("num").toString();//事业编制大学本科学历的人数
        String fcjupsybJuniorCollegeNum = selectFcjUpXzsybEducationDistributionNum.get(5).get("num").toString();//事业编制大学专科及以下学历的人数

        fcjUpXzsybEducationDistributionNumMap.put("fcjupxzbGraduateDegreeNum", fcjupxzbGraduateDegreeNum);//行政编制研究生学历的人数
        fcjUpXzsybEducationDistributionNumMap.put("fcjupxzbBachelorDegreeNum", fcjupxzbBachelorDegreeNum);//行政编制大学本科学历的人数
        fcjUpXzsybEducationDistributionNumMap.put("fcjupxzbJuniorCollegeNum", fcjupxzbJuniorCollegeNum);//行政编制大学专科及以下学历的人数
        fcjUpXzsybEducationDistributionNumMap.put("fcjupsybGraduateDegreeNum", fcjupsybGraduateDegreeNum);//事业编制研究生学历的人数
        fcjUpXzsybEducationDistributionNumMap.put("fcjupsybBachelorDegreeNum", fcjupsybBachelorDegreeNum);//事业编制大学本科学历的人数
        fcjUpXzsybEducationDistributionNumMap.put("fcjupsybJuniorCollegeNum", fcjupsybJuniorCollegeNum);//事业编制大学专科及以下学历的人数
    }

    /**
     * 行政编职级数量统计
     * @param xzbzjNumMap
     */
    private void xzbzjNum(Map<String, Object> xzbzjNumMap){
        //行政编职级数量统计
        List<Map<String, Object>> selectXzbzjNum = userInfoStatisticsService.selectXzbzjNum();
        String xzbztNum = selectXzbzjNum.get(0).get("num").toString();//行政编制中正厅级人数
        String xzbftNum = selectXzbzjNum.get(1).get("num").toString();//行政编制中副厅级人数
        String xzbzcNum = selectXzbzjNum.get(2).get("num").toString();//行政编制中正处级人数
        String xzbfcNum = selectXzbzjNum.get(3).get("num").toString();//行政编制中副处级人数
        String xzbyjxsyNum = selectXzbzjNum.get(4).get("num").toString();//行政编制中一级巡视员人数
        String xzbejxsyNum = selectXzbzjNum.get(5).get("num").toString();//行政编制中二级巡视员人数
        String xzbyjdyyNum = selectXzbzjNum.get(6).get("num").toString();//行政编制中一级调研员人数
        String xzbejdyyNum = selectXzbzjNum.get(7).get("num").toString();//行政编制中二级调研员人数
        String xzbsanjidyyNum = selectXzbzjNum.get(8).get("num").toString();//行政编制中三级调研员人数
        String xzbsjidyyNum = selectXzbzjNum.get(9).get("num").toString();//行政编制中四级调研员人数
        String xzbyjzrkyNum = selectXzbzjNum.get(10).get("num").toString();//行政编制中一级主任科员人数
        String xzbejzrkyNum = selectXzbzjNum.get(11).get("num").toString();//行政编制中二级主任科员人数
        String xzbsanjizrkyNum = selectXzbzjNum.get(12).get("num").toString();//行政编制中三级主任科员人数
        String xzbsjizrkyNum = selectXzbzjNum.get(13).get("num").toString();//行政编制中四级主任科员人数
        String xzbyjkyNum = selectXzbzjNum.get(14).get("num").toString();//行政编制中一级科员人数
        String xzbejkyNum = selectXzbzjNum.get(15).get("num").toString();//行政编制中二级科员人数
        String xzbsyqNum = selectXzbzjNum.get(16).get("num").toString();//行政编制中试用期人数
        String xzbgjjsNum = selectXzbzjNum.get(17).get("num").toString();//行政编制中高级技师人数
        String xzbjsNum = selectXzbzjNum.get(18).get("num").toString();//行政编制中技师人数
        String xzbgjgNum = selectXzbzjNum.get(19).get("num").toString();//行政编制中高级工人数
        String xzbzjgNum = selectXzbzjNum.get(20).get("num").toString();//行政编制中中级工人数
        String xzbcjgNum = selectXzbzjNum.get(21).get("num").toString();//行政编制中初级工人数

        xzbzjNumMap.put("xzbztNum", xzbztNum);//行政编制中正厅级人数
        xzbzjNumMap.put("xzbftNum", xzbftNum);//行政编制中副厅级人数
        xzbzjNumMap.put("xzbzcNum", xzbzcNum);//行政编制中正处级人数
        xzbzjNumMap.put("xzbfcNum", xzbfcNum);//行政编制中副处级人数
        xzbzjNumMap.put("xzbyjxsyNum", xzbyjxsyNum);//行政编制中一级巡视员人数
        xzbzjNumMap.put("xzbejxsyNum", xzbejxsyNum);//行政编制中二级巡视员人数
        xzbzjNumMap.put("xzbyjdyyNum", xzbyjdyyNum);//行政编制中一级调研员人数
        xzbzjNumMap.put("xzbejdyyNum", xzbejdyyNum);//行政编制中二级调研员人数
        xzbzjNumMap.put("xzbsanjidyyNum", xzbsanjidyyNum);//行政编制中三级调研员人数
        xzbzjNumMap.put("xzbsjidyyNum", xzbsjidyyNum);//行政编制中四级调研员人数
        xzbzjNumMap.put("xzbyjzrkyNum", xzbyjzrkyNum);//行政编制中一级主任科员人数
        xzbzjNumMap.put("xzbejzrkyNum", xzbejzrkyNum);//行政编制中二级主任科员人数
        xzbzjNumMap.put("xzbsanjizrkyNum", xzbsanjizrkyNum);//行政编制中三级主任科员人数
        xzbzjNumMap.put("xzbsjizrkyNum", xzbsjizrkyNum);//行政编制中四级主任科员人数
        xzbzjNumMap.put("xzbyjkyNum", xzbyjkyNum);//行政编制中一级科员人数
        xzbzjNumMap.put("xzbejkyNum", xzbejkyNum);//行政编制中二级科员人数
        xzbzjNumMap.put("xzbsyqNum", xzbsyqNum);//行政编制中试用期人数
        xzbzjNumMap.put("xzbgjjsNum", xzbgjjsNum);//行政编制中高级技师人数
        xzbzjNumMap.put("xzbjsNum", xzbjsNum);//行政编制中技师人数
        xzbzjNumMap.put("xzbgjgNum", xzbgjgNum);//行政编制中高级工人数
        xzbzjNumMap.put("xzbzjgNum", xzbzjgNum);//行政编制中中级工人数
        xzbzjNumMap.put("xzbcjgNum", xzbcjgNum);//行政编制中初级工人数
    }

    /**
     * 事业编职级数量统计
     * @param sybzjNumMap
     */
    private void sybzjNum(Map<String, Object> sybzjNumMap){
        //事业编职级数量统计
        List<Map<String, Object>> selectSybzjNum = userInfoStatisticsService.selectSybzjNum();
        String sybzcjNum = selectSybzjNum.get(0).get("num").toString();//事业编中正处级人数
        String sybfcjNum = selectSybzjNum.get(1).get("num").toString();//事业编中副处级人数
        String sybzkjNum = selectSybzjNum.get(2).get("num").toString();//事业编中正科级人数
        String sybfkjNum = selectSybzjNum.get(3).get("num").toString();//事业编中副科级人数
        String syb5LevelStaffNum = selectSybzjNum.get(4).get("num").toString();//事业编中五级职员人数
        String syb6LevelStaffNum = selectSybzjNum.get(5).get("num").toString();//事业编中六级职员人数
        String syb7LevelStaffNum = selectSybzjNum.get(6).get("num").toString();//事业编中七级职员人数
        String syb8LevelStaffNum = selectSybzjNum.get(7).get("num").toString();//事业编中八级职员人数
        String syb9LevelStaffNum = selectSybzjNum.get(8).get("num").toString();//事业编中九级职员人数
        String syb10LevelStaffNum = selectSybzjNum.get(9).get("num").toString();//事业编中十级职员人数
        //事业编中正高级人数
        int sybzgjNum = Integer.parseInt(selectSybzjNum.get(10).get("num").toString())
                + Integer.parseInt(selectSybzjNum.get(11).get("num").toString())
                + Integer.parseInt(selectSybzjNum.get(12).get("num").toString())
                + Integer.parseInt(selectSybzjNum.get(13).get("num").toString());
        //事业编中副高级人数
        int sybfgjNum = Integer.parseInt(selectSybzjNum.get(14).get("num").toString())
                + Integer.parseInt(selectSybzjNum.get(15).get("num").toString())
                + Integer.parseInt(selectSybzjNum.get(16).get("num").toString());
        //事业编中中级人数
        int sybzjNum = Integer.parseInt(selectSybzjNum.get(17).get("num").toString())
                + Integer.parseInt(selectSybzjNum.get(18).get("num").toString())
                + Integer.parseInt(selectSybzjNum.get(19).get("num").toString());
        //事业编中初级人数
        int sybcjNum = Integer.parseInt(selectSybzjNum.get(20).get("num").toString()) + Integer.parseInt(selectSybzjNum.get(21).get("num").toString());
        String sybgjjsNum = selectSybzjNum.get(22).get("num").toString();//事业编中高级技师人数
        String sybjsNum = selectSybzjNum.get(23).get("num").toString();//事业编中技师人数
        String sybgjgNum = selectSybzjNum.get(24).get("num").toString();//事业编中高级工人数
        String sybzjgNum = selectSybzjNum.get(25).get("num").toString();//事业编中中级工人数
        String sybcjgNum = selectSybzjNum.get(26).get("num").toString();//事业编中初级工人数

        sybzjNumMap.put("sybzcjNum", sybzcjNum);//事业编制中正处级人数
        sybzjNumMap.put("sybfcjNum", sybfcjNum);//事业编制中副处级人数
        sybzjNumMap.put("sybzkjNum", sybzkjNum);//事业编制中正科级人数
        sybzjNumMap.put("sybfkjNum", sybfkjNum);//事业编制中副科级人数
        sybzjNumMap.put("syb5LevelStaffNum", syb5LevelStaffNum);//事业编制中五级职员人数
        sybzjNumMap.put("syb6LevelStaffNum", syb6LevelStaffNum);//事业编制中六级职员人数
        sybzjNumMap.put("syb7LevelStaffNum", syb7LevelStaffNum);//事业编制中七级职员人数
        sybzjNumMap.put("syb8LevelStaffNum", syb8LevelStaffNum);//事业编制中八级职员人数
        sybzjNumMap.put("syb9LevelStaffNum", syb9LevelStaffNum);//事业编制中九级职员人数
        sybzjNumMap.put("syb10LevelStaffNum", syb10LevelStaffNum);//事业编制中十级职员人数
        sybzjNumMap.put("sybzgjNum", sybzgjNum);//事业编制中正高级人数
        sybzjNumMap.put("sybfgjNum", sybfgjNum);//事业编制中副高级人数
        sybzjNumMap.put("sybzjNum", sybzjNum);//事业编制中中级人数
        sybzjNumMap.put("sybcjNum", sybcjNum);//事业编制中初级人数
        sybzjNumMap.put("sybgjjsNum", sybgjjsNum);//事业编制中高级技师人数
        sybzjNumMap.put("sybjsNum", sybjsNum);//事业编制中技师人数
        sybzjNumMap.put("sybgjgNum", sybgjgNum);//事业编制中高级工人数
        sybzjNumMap.put("sybzjgNum", sybzjgNum);//事业编制中中级工人数
        sybzjNumMap.put("sybcjgNum", sybcjgNum);//事业编制中初级工人数
    }

    /**
     * 行政编制和事业编制分布情况饼图
     * @param map
     * @throws IOException
     */
    private void putIdentityTypeEchartPie(Map<String, Object> map) throws IOException {
        File file2 = File.createTempFile("temp", "png");
        /*String chartImgTemp = File.separator + RuoYiConfig.getProfile() + "/download/";
        File file2 = File.createTempFile(File.separator + chartImgTemp, "png");*/

        DefaultPieDataset pds = new DefaultPieDataset();

        //行政编制和事业编制分布情况
        List<GwyIdentityTypeVo> selectIdentityTypeNumber = userInfoStatisticsService.selectIdentityTypeNumber(deptId);
        String gwyCount = selectIdentityTypeNumber.get(0).getXz();//行政编制人数
        String cgCount = selectIdentityTypeNumber.get(0).getCg();//事业编（参公）编制人数
        String sybCount = selectIdentityTypeNumber.get(0).getSy();//事业编制人数
        String jdCount = selectIdentityTypeNumber.get(0).getJd();//借调人数

        pds.setValue("行政编制", Double.parseDouble(gwyCount));
        pds.setValue("事业编制（参公）", Double.parseDouble(cgCount));
        pds.setValue("事业编制", Double.parseDouble(sybCount));
        pds.setValue("借调人数", Double.parseDouble(jdCount));

        JFreeChartToFileUtil.createPieChart3D(pds, file2, "编制类型");
        ImageEntity image = new ImageEntity();
        image.setHeight(300);
        image.setWidth(600);
        System.out.println(file2.getAbsolutePath());
        image.setUrl(file2.getAbsolutePath());
        image.setType(ImageEntity.URL);
        map.put("identityTypeEchart", image);
    }

    /**
     * 行政编制和参公分布情况饼图
     * @param map
     * @throws IOException
     */
    private void putXzbcgEchartPie(Map<String, Object> map) throws IOException {
        File file2 = File.createTempFile("temp", "png");
        JFreeChart chart = ChartFactory.createPieChart3D("行政编制和参公分布情况", createDataset(), false, false, true);
        // 设置抗锯齿，防止字体显示不清楚
        chart.setTextAntiAlias(false);
        // 如果不使用Font,中文将显示不出来
        Font font = new Font("宋体", Font.BOLD, 12);
        // 设置图片标题的字体
        chart.getTitle().setFont(font);
        // 得到图块,准备设置标签的字体
        PiePlot plot = (PiePlot) chart.getPlot();
        //PiePlot3D plot = (PiePlot3D) chart.getPlot();
        // 设置标签字体
        plot.setLabelFont(font);
        plot.setStartAngle(3.14f / 2f);
        // 设置plot的前景色透明度
        plot.setForegroundAlpha(0.7f);
        // 设置plot的背景色透明度
        plot.setBackgroundAlpha(0.0f);
        plot.setCircular(false);// 指定显示的饼图上圆形(false)还椭圆形(true)
        plot.setOutlinePaint(null);// 去除背景边框线
        plot.setSectionPaint("行政编制", new Color(255, 85, 85));
        plot.setSectionPaint("参公", Color.BLUE);
        plot.setSectionPaint("借调", Color.ORANGE);

        // 设置外层图片 无边框 无背景色 背景图片透明
        chart.setBorderVisible(false);
        //chart.setBackgroundPaint(null);
        //chart.setBackgroundImageAlpha(0.0f);
        plot.setOutlinePaint(Color.white);
        //连接线类型为直线
        plot.setLabelLinkStyle(PieLabelLinkStyle.QUAD_CURVE);
        //设置Label字体
        plot.setLabelFont(new java.awt.Font("宋体", java.awt.Font.BOLD, 12));

        // 图片中显示百分比:默认方式
        //plot.setLabelGenerator(new StandardPieSectionLabelGenerator(StandardPieToolTipGenerator.DEFAULT_TOOLTIP_FORMAT));
        // 图片中显示百分比:自定义方式，{0} 表示选项， {1} 表示数值， {2} 表示所占比例 ,小数点后两位
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})",
                NumberFormat.getNumberInstance(),
                new DecimalFormat("0.00%")));
        // 设置标签生成器(默认{0})
        // {0}:key {1}:value {2}:百分比 {3}:sum
        //plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}({1}占{2})"));
        plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})"));


        // 将内存中的图片写到本地硬盘
        ChartUtilities.saveChartAsPNG(file2, chart, 600, 300);

        ImageEntity image = new ImageEntity();
        image.setHeight(300);
        image.setWidth(600);
        System.out.println(file2.getAbsolutePath());
        image.setUrl(file2.getAbsolutePath());
        image.setType(ImageEntity.URL);
        map.put("xzbcgEchartPie", image);
    }

    public DefaultPieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        //行政编制和参公分布情况
        List<GwyIdentityTypeVo> selectIdentityTypeNumber = userInfoStatisticsService.selectIdentityTypeNumber(deptId);
        String gwyCount = selectIdentityTypeNumber.get(0).getXz();//行政编制人数
        String cgCount = selectIdentityTypeNumber.get(0).getCg();//参公人数
        String jdCount = selectIdentityTypeNumber.get(0).getJd();//借调人数

        dataset.setValue("行政编制", Double.parseDouble(gwyCount));
        dataset.setValue("参公", Double.parseDouble(cgCount));
        dataset.setValue("借调人数", Double.parseDouble(jdCount));
        return dataset;
    }

    /**
     * 行政编制和事业编制性别、民族、政治面貌、年龄、学历情况柱形图
     * @param dataMap
     * @throws IOException
     */
    public void putXzbSybIdentityDistributionBar(Map<String, Object> dataMap) throws IOException {
        File file2 = File.createTempFile("temp", "png");
        /*String chartImgTemp = File.separator + RuoYiConfig.getProfile() + "/download/";
        File file2 = File.createTempFile(File.separator + chartImgTemp, "png");*/

        //创建数据
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        List<Map<String, Object>> selectXzbSybIdentityDistributionNum = userInfoStatisticsService.selectXzbSybIdentityDistributionNum();
        String xzbWomanNum = selectXzbSybIdentityDistributionNum.get(0).get("num").toString();//行政中有女性人数
        String xzbMinorityNationNum = selectXzbSybIdentityDistributionNum.get(1).get("num").toString();//行政少数民族人数
        String xzbPartyMemberNum = selectXzbSybIdentityDistributionNum.get(2).get("num").toString();//行政中共党员人数
        String xzbNotPartyMemberNum = selectXzbSybIdentityDistributionNum.get(3).get("num").toString();//行政非中共党员人数
        String xzbbkEducationNum = selectXzbSybIdentityDistributionNum.get(4).get("num").toString();//行政大学本科及以上学历的人数
        String xzbYearsOld35BelowNum = selectXzbSybIdentityDistributionNum.get(5).get("num").toString();//行政35岁及以下的人数
        String sybWomanNum = selectXzbSybIdentityDistributionNum.get(6).get("num").toString();//行政中有女性人数
        String sybMinorityNationNum = selectXzbSybIdentityDistributionNum.get(7).get("num").toString();//事业编少数民族人数
        String sybPartyMemberNum = selectXzbSybIdentityDistributionNum.get(8).get("num").toString();//事业编中共党员人数
        String sybNotPartyMemberNum = selectXzbSybIdentityDistributionNum.get(9).get("num").toString();//事业编非中共党员人数
        String sybbkEducationNum = selectXzbSybIdentityDistributionNum.get(10).get("num").toString();//事业编大学本科及以上学历的人数
        String sybYearsOld35BelowNum = selectXzbSybIdentityDistributionNum.get(11).get("num").toString();//事业编35岁及以下的人数

        dataset.addValue(Double.parseDouble(xzbWomanNum), "行政编制", "女性");
        dataset.addValue(Double.parseDouble(xzbMinorityNationNum), "行政编制", "少数民族");
        dataset.addValue(Double.parseDouble(xzbPartyMemberNum), "行政编制", "中共党员");
        dataset.addValue(Double.parseDouble(xzbNotPartyMemberNum), "行政编制", "非中共党员");
        dataset.addValue(Double.parseDouble(xzbYearsOld35BelowNum), "行政编制", "35岁及以下");
        dataset.addValue(Double.parseDouble(xzbbkEducationNum), "行政编制", "本科及以上学历");
        dataset.addValue(Double.parseDouble(sybWomanNum), "事业编制", "女性");
        dataset.addValue(Double.parseDouble(sybMinorityNationNum), "事业编制", "少数民族");
        dataset.addValue(Double.parseDouble(sybPartyMemberNum), "事业编制", "中共党员");
        dataset.addValue(Double.parseDouble(sybNotPartyMemberNum), "事业编制", "非中共党员");
        dataset.addValue(Double.parseDouble(sybYearsOld35BelowNum), "事业编制", "35岁及以下");
        dataset.addValue(Double.parseDouble(sybbkEducationNum), "事业编制", "本科及以上学历");

         JFreeChartToFileUtil.createBarChart3D(dataset, file2, "性别、民族、政治面貌、年龄、学历情况");
        ImageEntity image = new ImageEntity();
        image.setHeight(300);
        image.setWidth(600);
        System.out.println(file2.getAbsolutePath());
        image.setUrl(file2.getAbsolutePath());
        image.setType(ImageEntity.URL);
        dataMap.put("xzbSybIdentityDistributionEchart", image);
    }

    /**
     * 副处级及以上行政编制和事业编制的性别、民族、政治情况柱形图
     * @param dataMap
     * @throws IOException
     */
    public void putFcjUpXzsybDistributionBar(Map<String, Object> dataMap) throws IOException {
        File file2 = File.createTempFile("temp", "png");
        /*String chartImgTemp = File.separator + RuoYiConfig.getProfile() + "/download/";
        File file2 = File.createTempFile(File.separator + chartImgTemp, "png");*/

        //创建数据
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        //副处级及以上行政编制和事业编制的性别、民族、政治情况
        List<Map<String, Object>> selectFcjUpXzsybDistributionNum = userInfoStatisticsService.selectFcjUpXzsybDistributionNum();

        /*String fcjupxzbNum = selectFcjUpXzsybDistributionNum.get(0).get("num").toString();//副处级及以上行政编制的人员
        String fcjupxzbWomanCadreNum = selectFcjUpXzsybDistributionNum.get(1).get("num").toString();//副处级及以上行政编制的女干部数量
        String fcjupxzbMinorityNationCadreNum = selectFcjUpXzsybDistributionNum.get(2).get("num").toString();//副处级及以上行政编制的少数民族干部数量
        String fcjupxzbPartyMemberNum = selectFcjUpXzsybDistributionNum.get(3).get("num").toString();//副处级及以上行政编制的中共党员数量
        String fcjupxzbNotPartyMemberNum = selectFcjUpXzsybDistributionNum.get(4).get("num").toString();//副处级及以上行政编制的非中共党员数量
        String fcjupsybNum = selectFcjUpXzsybDistributionNum.get(5).get("num").toString();//副处级及以上事业编制的人员
        String fcjupsybWomanCadreNum = selectFcjUpXzsybDistributionNum.get(6).get("num").toString();//副处级及以上事业编制的女干部数量
        String fcjupsybMinorityNationCadreNum = selectFcjUpXzsybDistributionNum.get(7).get("num").toString();//副处级及以上事业编制的少数民族干部数量
        String fcjupsybPartyMemberNum = selectFcjUpXzsybDistributionNum.get(8).get("num").toString();//副处级及以上事业编制的中共党员数量
        String fcjupsybNotPartyMemberNum = selectFcjUpXzsybDistributionNum.get(9).get("num").toString();//副处级及以上事业编制的非中共党员数量

        dataset.addValue(Double.parseDouble(fcjupxzbNum), "行政编制", "副处级及以上");
        dataset.addValue(Double.parseDouble(fcjupxzbWomanCadreNum), "行政编制", "女干部");
        dataset.addValue(Double.parseDouble(fcjupxzbMinorityNationCadreNum), "行政编制", "少数民族干部");
        dataset.addValue(Double.parseDouble(fcjupxzbPartyMemberNum), "行政编制", "中共党员");
        dataset.addValue(Double.parseDouble(fcjupxzbNotPartyMemberNum), "行政编制", "非中共党员");

        dataset.addValue(Double.parseDouble(fcjupsybNum), "事业编制", "副处级及以上");
        dataset.addValue(Double.parseDouble(fcjupsybWomanCadreNum), "事业编制", "女干部");
        dataset.addValue(Double.parseDouble(fcjupsybMinorityNationCadreNum), "事业编制", "少数民族干部");
        dataset.addValue(Double.parseDouble(fcjupsybPartyMemberNum), "事业编制", "中共党员");
        dataset.addValue(Double.parseDouble(fcjupsybNotPartyMemberNum), "事业编制", "非中共党员");

        JFreeChartToFileUtil.createBarChart3D(dataset, file2, "性别、民族、政治情况");
        ImageEntity image = new ImageEntity();
        image.setHeight(300);
        image.setWidth(600);
        System.out.println(file2.getAbsolutePath());
        image.setUrl(file2.getAbsolutePath());
        image.setType(ImageEntity.URL);
        dataMap.put("fcjUpXzsybDistributionEchart", image);*/
    }

    /**
     * 副处级及以上行政编制和事业编制的年龄情况柱形图
     * @param dataMap
     * @throws IOException
     */
    public void putFcjUpXzsybAgeDistributionBar(Map<String, Object> dataMap) throws IOException {
        File file2 = File.createTempFile("temp", "png");
        /*String chartImgTemp = File.separator + RuoYiConfig.getProfile() + "/download/";
        File file2 = File.createTempFile(File.separator + chartImgTemp, "png");*/

        //创建数据
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        //副处级及以上行政编制和事业编制的年龄情况
        List<Map<String, Object>> selectFcjUpXzsybAgeDistributionNum = userInfoStatisticsService.selectFcjUpXzsybAgeDistributionNum();

        String fcjupxzbYearsOld35BelowNum = selectFcjUpXzsybAgeDistributionNum.get(0).get("num").toString();//行政编35岁及以下人数
        String fcjupxzbYearsOld36to40Num = selectFcjUpXzsybAgeDistributionNum.get(1).get("num").toString();//行政编36岁至40岁的人数
        String fcjupxzbYearsOld41to45Num = selectFcjUpXzsybAgeDistributionNum.get(2).get("num").toString();//行政编41岁至45岁的人数
        String fcjupxzbYearsOld46to50Num = selectFcjUpXzsybAgeDistributionNum.get(3).get("num").toString();//行政编46岁至50岁的人数
        String fcjupxzbYearsOld51to54Num = selectFcjUpXzsybAgeDistributionNum.get(4).get("num").toString();//行政编51岁至54岁的人数
        String fcjupxzbYearsOld55overNum = selectFcjUpXzsybAgeDistributionNum.get(5).get("num").toString();//行政编55岁及以上的人数
        String fcjupsybYearsOld35BelowNum = selectFcjUpXzsybAgeDistributionNum.get(6).get("num").toString();//事业编35岁及以下人数
        String fcjupsybYearsOld36to40Num = selectFcjUpXzsybAgeDistributionNum.get(7).get("num").toString();//事业编36岁至40岁的人数
        String fcjupsybYearsOld41to45Num = selectFcjUpXzsybAgeDistributionNum.get(8).get("num").toString();//事业编41岁至45岁的人数
        String fcjupsybYearsOld46to50Num = selectFcjUpXzsybAgeDistributionNum.get(9).get("num").toString();//事业编46岁至50岁的人数
        String fcjupsybYearsOld51to54Num = selectFcjUpXzsybAgeDistributionNum.get(10).get("num").toString();//事业编51岁至54岁的人数
        String fcjupsybYearsOld55overNum = selectFcjUpXzsybAgeDistributionNum.get(11).get("num").toString();//事业编55岁及以上的人数

        dataset.addValue(Double.parseDouble(fcjupxzbYearsOld35BelowNum), "行政编制", "35岁及以下");
        dataset.addValue(Double.parseDouble(fcjupxzbYearsOld36to40Num), "行政编制", "36岁至40岁");
        dataset.addValue(Double.parseDouble(fcjupxzbYearsOld41to45Num), "行政编制", "41岁至45岁");
        dataset.addValue(Double.parseDouble(fcjupxzbYearsOld46to50Num), "行政编制", "46岁至50岁");
        dataset.addValue(Double.parseDouble(fcjupxzbYearsOld51to54Num), "行政编制", "51岁至54岁");
        dataset.addValue(Double.parseDouble(fcjupxzbYearsOld55overNum), "行政编制", "55岁及以上");
        dataset.addValue(Double.parseDouble(fcjupsybYearsOld35BelowNum), "事业编制", "35岁及以下");
        dataset.addValue(Double.parseDouble(fcjupsybYearsOld36to40Num), "事业编制", "36岁至40岁");
        dataset.addValue(Double.parseDouble(fcjupsybYearsOld41to45Num), "事业编制", "41岁至45岁");
        dataset.addValue(Double.parseDouble(fcjupsybYearsOld46to50Num), "事业编制", "46岁至50岁");
        dataset.addValue(Double.parseDouble(fcjupsybYearsOld51to54Num), "事业编制", "51岁至54岁");
        dataset.addValue(Double.parseDouble(fcjupsybYearsOld55overNum), "事业编制", "55岁及以上");

        JFreeChartToFileUtil.createBarChart3D(dataset, file2, "年龄情况");
        ImageEntity image = new ImageEntity();
        image.setHeight(300);
        image.setWidth(600);
        System.out.println(file2.getAbsolutePath());
        image.setUrl(file2.getAbsolutePath());
        image.setType(ImageEntity.URL);
        dataMap.put("fcjUpXzsybAgeDistributionEchart", image);
    }

    /**
     * 副处级及以上行政编制和事业编制的学历情况柱形图
     * @param dataMap
     * @throws IOException
     */
    public void putFcjUpXzsybEducationDistributionBar(Map<String, Object> dataMap) throws IOException {
        File file2 = File.createTempFile("temp", "png");
        /*String chartImgTemp = File.separator + RuoYiConfig.getProfile() + "/download/";
        File file2 = File.createTempFile(File.separator + chartImgTemp, "png");*/

        //创建数据
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        //副处级及以上行政编制和事业编制的学历情况
        List<Map<String, Object>> selectFcjUpXzsybEducationDistributionNum = userInfoStatisticsService.selectFcjUpXzsybEducationDistributionNum();
        String fcjupxzbGraduateDegreeNum = selectFcjUpXzsybEducationDistributionNum.get(0).get("num").toString();//行政编制研究生学历的人数
        String fcjupxzbBachelorDegreeNum = selectFcjUpXzsybEducationDistributionNum.get(1).get("num").toString();//行政编制大学本科学历的人数
        String fcjupxzbJuniorCollegeNum = selectFcjUpXzsybEducationDistributionNum.get(2).get("num").toString();//行政编制大学专科及以下学历的人数
        String fcjupsybGraduateDegreeNum = selectFcjUpXzsybEducationDistributionNum.get(3).get("num").toString();//事业编制研究生学历的人数
        String fcjupsybBachelorDegreeNum = selectFcjUpXzsybEducationDistributionNum.get(4).get("num").toString();//事业编制大学本科学历的人数
        String fcjupsybJuniorCollegeNum = selectFcjUpXzsybEducationDistributionNum.get(5).get("num").toString();//事业编制大学专科及以下学历的人数

        dataset.addValue(Double.parseDouble(fcjupxzbGraduateDegreeNum), "行政编制", "研究生学历");
        dataset.addValue(Double.parseDouble(fcjupxzbBachelorDegreeNum), "行政编制", "大学本科学历");
        dataset.addValue(Double.parseDouble(fcjupxzbJuniorCollegeNum), "行政编制", "大学专科及以下学历");
        dataset.addValue(Double.parseDouble(fcjupsybGraduateDegreeNum), "事业编制", "研究生学历");
        dataset.addValue(Double.parseDouble(fcjupsybBachelorDegreeNum), "事业编制", "大学本科学历");
        dataset.addValue(Double.parseDouble(fcjupsybJuniorCollegeNum), "事业编制", "大学专科及以下学历");

        JFreeChartToFileUtil.createBarChart3D(dataset, file2, "学历情况");
        ImageEntity image = new ImageEntity();
        image.setHeight(300);
        image.setWidth(600);
        System.out.println(file2.getAbsolutePath());
        image.setUrl(file2.getAbsolutePath());
        image.setType(ImageEntity.URL);
        dataMap.put("fcjUpXzsybEducationDistributionEchart", image);
    }

    /**
     * 行政编职级数量统计条形图
     * @param dataMap
     * @throws IOException
     */
    public void putXzbzjNumBar(Map<String, Object> dataMap) throws IOException {
        File file2 = File.createTempFile("temp", "png");
        /*String chartImgTemp = File.separator + RuoYiConfig.getProfile() + "/download/";
        File file2 = File.createTempFile(File.separator + chartImgTemp, "png");*/

        //创建数据
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        //行政编职级数量统计
        List<Map<String, Object>> selectXzbzjNum = userInfoStatisticsService.selectXzbzjNum();
        String xzbztNum = selectXzbzjNum.get(0).get("num").toString();//行政编制中正厅级人数
        String xzbftNum = selectXzbzjNum.get(1).get("num").toString();//行政编制中副厅级人数
        String xzbzcNum = selectXzbzjNum.get(2).get("num").toString();//行政编制中正处级人数
        String xzbfcNum = selectXzbzjNum.get(3).get("num").toString();//行政编制中副处级人数
        String xzbyjxsyNum = selectXzbzjNum.get(4).get("num").toString();//行政编制中一级巡视员人数
        String xzbejxsyNum = selectXzbzjNum.get(5).get("num").toString();//行政编制中二级巡视员人数
        String xzbyjdyyNum = selectXzbzjNum.get(6).get("num").toString();//行政编制中一级调研员人数
        String xzbejdyyNum = selectXzbzjNum.get(7).get("num").toString();//行政编制中二级调研员人数
        String xzbsanjidyyNum = selectXzbzjNum.get(8).get("num").toString();//行政编制中三级调研员人数
        String xzbsjidyyNum = selectXzbzjNum.get(9).get("num").toString();//行政编制中四级调研员人数
        String xzbyjzrkyNum = selectXzbzjNum.get(10).get("num").toString();//行政编制中一级主任科员人数
        String xzbejzrkyNum = selectXzbzjNum.get(11).get("num").toString();//行政编制中二级主任科员人数
        String xzbsanjizrkyNum = selectXzbzjNum.get(12).get("num").toString();//行政编制中三级主任科员人数
        String xzbsjizrkyNum = selectXzbzjNum.get(13).get("num").toString();//行政编制中四级主任科员人数
        String xzbyjkyNum = selectXzbzjNum.get(14).get("num").toString();//行政编制中一级科员人数
        String xzbejkyNum = selectXzbzjNum.get(15).get("num").toString();//行政编制中二级科员人数
        String xzbsyqNum = selectXzbzjNum.get(16).get("num").toString();//行政编制中试用期人数
        String xzbgjjsNum = selectXzbzjNum.get(17).get("num").toString();//行政编制中高级技师人数
        String xzbjsNum = selectXzbzjNum.get(18).get("num").toString();//行政编制中技师人数
        String xzbgjgNum = selectXzbzjNum.get(19).get("num").toString();//行政编制中高级工人数
        String xzbzjgNum = selectXzbzjNum.get(20).get("num").toString();//行政编制中中级工人数
        String xzbcjgNum = selectXzbzjNum.get(21).get("num").toString();//行政编制中初级工人数

        dataset.addValue(Double.parseDouble(xzbztNum), "行政编制", "正厅级");
        dataset.addValue(Double.parseDouble(xzbftNum), "行政编制", "副厅级");
        dataset.addValue(Double.parseDouble(xzbzcNum), "行政编制", "正处级");
        dataset.addValue(Double.parseDouble(xzbfcNum), "行政编制", "副处级");
        dataset.addValue(Double.parseDouble(xzbyjxsyNum), "行政编制", "一级巡视员");
        dataset.addValue(Double.parseDouble(xzbejxsyNum), "行政编制", "二级巡视员");
        dataset.addValue(Double.parseDouble(xzbyjdyyNum), "行政编制", "一级调研员");
        dataset.addValue(Double.parseDouble(xzbejdyyNum), "行政编制", "二级调研员");
        dataset.addValue(Double.parseDouble(xzbsanjidyyNum), "行政编制", "三级调研员");
        dataset.addValue(Double.parseDouble(xzbsjidyyNum), "行政编制", "四级调研员");
        dataset.addValue(Double.parseDouble(xzbyjzrkyNum), "行政编制", "一级主任科员");
        dataset.addValue(Double.parseDouble(xzbejzrkyNum), "行政编制", "二级主任科员");
        dataset.addValue(Double.parseDouble(xzbsanjizrkyNum), "行政编制", "三级主任科员");
        dataset.addValue(Double.parseDouble(xzbsjizrkyNum), "行政编制", "四级主任科员");
        dataset.addValue(Double.parseDouble(xzbyjkyNum), "行政编制", "一级科员");
        dataset.addValue(Double.parseDouble(xzbejkyNum), "行政编制", "二级科员");
        dataset.addValue(Double.parseDouble(xzbsyqNum), "行政编制", "试用期");
        dataset.addValue(Double.parseDouble(xzbgjjsNum), "行政编制", "高级技师");
        dataset.addValue(Double.parseDouble(xzbjsNum), "行政编制", "技师");
        dataset.addValue(Double.parseDouble(xzbgjgNum), "行政编制", "高级工");
        dataset.addValue(Double.parseDouble(xzbzjgNum), "行政编制", "中级工");
        dataset.addValue(Double.parseDouble(xzbcjgNum), "行政编制", "初级工");

        JFreeChartToFileUtil.createBarChart(dataset, file2, "行政编职级", "","数量");
        ImageEntity image = new ImageEntity();
        image.setHeight(300);
        image.setWidth(600);
        System.out.println(file2.getAbsolutePath());
        image.setUrl(file2.getAbsolutePath());
        image.setType(ImageEntity.URL);
        dataMap.put("xzbZjNumEchart", image);
    }

    /**
     * 事业编职级数量统计条形图
     * @param dataMap
     * @throws IOException
     */
    public void putSybzjNumBar(Map<String, Object> dataMap) throws IOException {
        File file2 = File.createTempFile("temp", "png");
        /*String chartImgTemp = File.separator + RuoYiConfig.getProfile() + "/download/";
        File file2 = File.createTempFile(File.separator + chartImgTemp, "png");*/

        //创建数据
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        //事业编职级数量统计
        List<Map<String, Object>> selectSybzjNum = userInfoStatisticsService.selectSybzjNum();
        String sybzcjNum = selectSybzjNum.get(0).get("num").toString();//事业编中正处级人数
        String sybfcjNum = selectSybzjNum.get(1).get("num").toString();//事业编中副处级人数
        String sybzkjNum = selectSybzjNum.get(2).get("num").toString();//事业编中正科级人数
        String sybfkjNum = selectSybzjNum.get(3).get("num").toString();//事业编中副科级人数
        String syb5LevelStaffNum = selectSybzjNum.get(4).get("num").toString();//事业编中五级职员人数
        String syb6LevelStaffNum = selectSybzjNum.get(5).get("num").toString();//事业编中六级职员人数
        String syb7LevelStaffNum = selectSybzjNum.get(6).get("num").toString();//事业编中七级职员人数
        String syb8LevelStaffNum = selectSybzjNum.get(7).get("num").toString();//事业编中八级职员人数
        String syb9LevelStaffNum = selectSybzjNum.get(8).get("num").toString();//事业编中九级职员人数
        String syb10LevelStaffNum = selectSybzjNum.get(9).get("num").toString();//事业编中十级职员人数
        //事业编中正高级人数
        int sybzgjNum = Integer.parseInt(selectSybzjNum.get(10).get("num").toString())
                + Integer.parseInt(selectSybzjNum.get(11).get("num").toString())
                + Integer.parseInt(selectSybzjNum.get(12).get("num").toString())
                + Integer.parseInt(selectSybzjNum.get(13).get("num").toString());
        //事业编中副高级人数
        int sybfgjNum = Integer.parseInt(selectSybzjNum.get(14).get("num").toString())
                + Integer.parseInt(selectSybzjNum.get(15).get("num").toString())
                + Integer.parseInt(selectSybzjNum.get(16).get("num").toString());
        //事业编中中级人数
        int sybzjNum = Integer.parseInt(selectSybzjNum.get(17).get("num").toString())
                + Integer.parseInt(selectSybzjNum.get(18).get("num").toString())
                + Integer.parseInt(selectSybzjNum.get(19).get("num").toString());
        //事业编中初级人数
        int sybcjNum = Integer.parseInt(selectSybzjNum.get(20).get("num").toString()) + Integer.parseInt(selectSybzjNum.get(21).get("num").toString());
        String sybgjjsNum = selectSybzjNum.get(22).get("num").toString();//事业编中高级技师人数
        String sybjsNum = selectSybzjNum.get(23).get("num").toString();//事业编中技师人数
        String sybgjgNum = selectSybzjNum.get(24).get("num").toString();//事业编中高级工人数
        String sybzjgNum = selectSybzjNum.get(25).get("num").toString();//事业编中中级工人数
        String sybcjgNum = selectSybzjNum.get(26).get("num").toString();//事业编中初级工人数

        dataset.addValue(Double.parseDouble(sybzcjNum), "事业编制", "正处级");
        dataset.addValue(Double.parseDouble(sybfcjNum), "事业编制", "副处级");
        dataset.addValue(Double.parseDouble(sybzkjNum), "事业编制", "正科级");
        dataset.addValue(Double.parseDouble(sybfkjNum), "事业编制", "副科级");
        dataset.addValue(Double.parseDouble(syb5LevelStaffNum), "事业编制", "五级职员");
        dataset.addValue(Double.parseDouble(syb6LevelStaffNum), "事业编制", "六级职员");
        dataset.addValue(Double.parseDouble(syb7LevelStaffNum), "事业编制", "七级职员");
        dataset.addValue(Double.parseDouble(syb8LevelStaffNum), "事业编制", "八级职员");
        dataset.addValue(Double.parseDouble(syb9LevelStaffNum), "事业编制", "九级职员");
        dataset.addValue(Double.parseDouble(syb10LevelStaffNum), "事业编制", "十级职员");
        dataset.addValue(sybzgjNum, "事业编制", "正高级");
        dataset.addValue(sybfgjNum, "事业编制", "副高级");
        dataset.addValue(sybzjNum, "事业编制", "中级");
        dataset.addValue(sybcjNum, "事业编制", "初级");
        dataset.addValue(Double.parseDouble(sybgjjsNum), "事业编制", "高级技师");
        dataset.addValue(Double.parseDouble(sybjsNum), "事业编制", "技师");
        dataset.addValue(Double.parseDouble(sybgjgNum), "事业编制", "高级工");
        dataset.addValue(Double.parseDouble(sybzjgNum), "事业编制", "中级工");
        dataset.addValue(Double.parseDouble(sybcjgNum), "事业编制", "初级工");

        Font font = new Font("宋体", Font.BOLD, 12);
        StandardChartTheme standardChartTheme = new StandardChartTheme("CN"); // 创建主题样式
        standardChartTheme.setExtraLargeFont(font); // 设置标题字体
        standardChartTheme.setRegularFont(font); // 设置图例的字体
        standardChartTheme.setLargeFont(font); // 设置轴向的字体
        //standardChartTheme.setChartBackgroundPaint(Color.WHITE);// 设置主题背景色
        ChartFactory.setChartTheme(standardChartTheme);// 应用主题样式

        JFreeChartToFileUtil.createBarChart(dataset, file2, "事业编职级", "","数量");
        ImageEntity image = new ImageEntity();
        image.setHeight(300);
        image.setWidth(600);
        System.out.println(file2.getAbsolutePath());
        image.setUrl(file2.getAbsolutePath());
        image.setType(ImageEntity.URL);
        dataMap.put("sybZjNumEchart", image);
    }

    /**
     * 行政编制和参公性别、民族、政治面貌、学历、年龄情况
     * @param xzbcgIdentityDistributionNumMap
     */
    private void xzbcgIdentityDistributionNum(Map<String, Object> xzbcgIdentityDistributionNumMap){
        //行政编制性别、民族、政治面貌、学历、年龄情况
        List<Map<String, Object>> selectXzbIdentityDistributionNum = userInfoStatisticsService.selectXzbIdentityDistributionNum();

        //参公性别、民族、政治面貌、学历、年龄情况
        List<Map<String, Object>> selectCgIdentityDistributionNum = userInfoStatisticsService.selectCgIdentityDistributionNum();


        int xzbcgMaleNum = Integer.parseInt(selectXzbIdentityDistributionNum.get(0).get("num").toString())
                + Integer.parseInt(selectCgIdentityDistributionNum.get(0).get("num").toString());//男性人数
        int xzbcgWomanNum = Integer.parseInt(selectXzbIdentityDistributionNum.get(1).get("num").toString())
                + Integer.parseInt(selectCgIdentityDistributionNum.get(1).get("num").toString());//女性人数
        int xzbcgMinorityNationNum = Integer.parseInt(selectXzbIdentityDistributionNum.get(2).get("num").toString())
                + Integer.parseInt(selectCgIdentityDistributionNum.get(2).get("num").toString());//少数民族人数
        int xzbcgPartyMemberNum = Integer.parseInt(selectXzbIdentityDistributionNum.get(3).get("num").toString())
                + Integer.parseInt(selectCgIdentityDistributionNum.get(3).get("num").toString());//中共党员人数
        int xzbcgDemocraticPartyNum = Integer.parseInt(selectXzbIdentityDistributionNum.get(4).get("num").toString())
                + Integer.parseInt(selectCgIdentityDistributionNum.get(4).get("num").toString());//民主党派人数
        int xzbcgMultitudeNum = Integer.parseInt(selectXzbIdentityDistributionNum.get(5).get("num").toString())
                + Integer.parseInt(selectCgIdentityDistributionNum.get(5).get("num").toString());//群众人数
        int xzbcgDoctoralStudentNum = Integer.parseInt(selectXzbIdentityDistributionNum.get(6).get("num").toString())
                + Integer.parseInt(selectCgIdentityDistributionNum.get(6).get("num").toString());//博士研究生人数
        int xzbcgPostgraduateNum = Integer.parseInt(selectXzbIdentityDistributionNum.get(7).get("num").toString())
                + Integer.parseInt(selectCgIdentityDistributionNum.get(7).get("num").toString());//硕士研究生人数
        int xzbcgGraduateStudentNum = Integer.parseInt(selectXzbIdentityDistributionNum.get(8).get("num").toString())
                + Integer.parseInt(selectCgIdentityDistributionNum.get(8).get("num").toString());//在职硕士研究生人数
        int xzbcgUniversityNum = Integer.parseInt(selectXzbIdentityDistributionNum.get(9).get("num").toString())
                + Integer.parseInt(selectCgIdentityDistributionNum.get(9).get("num").toString());//大学人数
        int xzbcgJuniorCollegeNum = Integer.parseInt(selectXzbIdentityDistributionNum.get(10).get("num").toString())
                + Integer.parseInt(selectCgIdentityDistributionNum.get(10).get("num").toString());//大专人数
        int xzbcgHighSchoolNum = Integer.parseInt(selectXzbIdentityDistributionNum.get(11).get("num").toString())
                + Integer.parseInt(selectCgIdentityDistributionNum.get(11).get("num").toString());//高中人数


        xzbcgIdentityDistributionNumMap.put("xzbcgMaleNum", xzbcgMaleNum);
        xzbcgIdentityDistributionNumMap.put("xzbcgWomanNum", xzbcgWomanNum);
        xzbcgIdentityDistributionNumMap.put("xzbcgMinorityNationNum", xzbcgMinorityNationNum);
        xzbcgIdentityDistributionNumMap.put("xzbcgPartyMemberNum", xzbcgPartyMemberNum);
        xzbcgIdentityDistributionNumMap.put("xzbcgDemocraticPartyNum", xzbcgDemocraticPartyNum);
        xzbcgIdentityDistributionNumMap.put("xzbcgMultitudeNum", xzbcgMultitudeNum);
        xzbcgIdentityDistributionNumMap.put("xzbcgDoctoralStudentNum", xzbcgDoctoralStudentNum);
        xzbcgIdentityDistributionNumMap.put("xzbcgPostgraduateNum", xzbcgPostgraduateNum);
        xzbcgIdentityDistributionNumMap.put("xzbcgGraduateStudentNum", xzbcgGraduateStudentNum);
        xzbcgIdentityDistributionNumMap.put("xzbcgUniversityNum", xzbcgUniversityNum);
        xzbcgIdentityDistributionNumMap.put("xzbcgJuniorCollegeNum", xzbcgJuniorCollegeNum);
        xzbcgIdentityDistributionNumMap.put("xzbcgHighSchoolNum", xzbcgHighSchoolNum);

        List<VSysUser> userList = userService.selectAllVSysUserList();
        Map<String, Integer> map = userInfoStatisticsService.selectUserAgeStageNum(new String[]{"1", "2"}, new String[]{"1", "2"},userList);
        xzbcgIdentityDistributionNumMap.put("xzbcgYearsOld35BelowNum", map.get("35BelowNum"));
        xzbcgIdentityDistributionNumMap.put("xzbcgYearsOld36to40Num", map.get("36to40Num"));
        xzbcgIdentityDistributionNumMap.put("xzbcgYearsOld41to45Num", map.get("41to45Num"));
        xzbcgIdentityDistributionNumMap.put("xzbcgYearsOld46to50Num", map.get("46to50Num"));
        xzbcgIdentityDistributionNumMap.put("xzbcgYearsOld51to54Num", map.get("51to54Num"));
        xzbcgIdentityDistributionNumMap.put("xzbcgYearsOld55overNum", map.get("55overNum"));
    }

    /**
     * 事业编性别、民族、政治面貌、学历、年龄情况
     * @param sybIdentityDistributionNumMap
     */
    private void sybIdentityDistributionNum(Map<String, Object> sybIdentityDistributionNumMap){
        //事业编性别、民族、政治面貌、学历、年龄情况
        List<Map<String, Object>> selectSybIdentityDistributionNum = userInfoStatisticsService.selectSybIdentityDistributionNum();

        int sybMaleNum = Integer.parseInt(selectSybIdentityDistributionNum.get(0).get("num").toString());//男性人数
        int sybWomanNum = Integer.parseInt(selectSybIdentityDistributionNum.get(1).get("num").toString());//女性人数
        int sybMinorityNationNum = Integer.parseInt(selectSybIdentityDistributionNum.get(2).get("num").toString());//少数民族人数
        int sybPartyMemberNum = Integer.parseInt(selectSybIdentityDistributionNum.get(3).get("num").toString());//中共党员人数
        int sybDemocraticPartyNum = Integer.parseInt(selectSybIdentityDistributionNum.get(4).get("num").toString());//民主党派人数
        int sybMultitudeNum = Integer.parseInt(selectSybIdentityDistributionNum.get(5).get("num").toString());//群众人数
        int sybDoctoralStudentNum = Integer.parseInt(selectSybIdentityDistributionNum.get(6).get("num").toString());//博士研究生人数
        int sybPostgraduateNum = Integer.parseInt(selectSybIdentityDistributionNum.get(7).get("num").toString());//硕士研究生人数
        int sybGraduateStudentNum = Integer.parseInt(selectSybIdentityDistributionNum.get(8).get("num").toString());//在职硕士研究生人数
        int sybUniversityNum = Integer.parseInt(selectSybIdentityDistributionNum.get(9).get("num").toString());//大学人数
        int sybJuniorCollegeNum = Integer.parseInt(selectSybIdentityDistributionNum.get(10).get("num").toString());//大专人数
        int sybHighSchoolNum = Integer.parseInt(selectSybIdentityDistributionNum.get(11).get("num").toString());//高中人数


        sybIdentityDistributionNumMap.put("sybMaleNum", sybMaleNum);
        sybIdentityDistributionNumMap.put("sybWomanNum", sybWomanNum);
        sybIdentityDistributionNumMap.put("sybMinorityNationNum", sybMinorityNationNum);
        sybIdentityDistributionNumMap.put("sybPartyMemberNum", sybPartyMemberNum);
        sybIdentityDistributionNumMap.put("sybDemocraticPartyNum", sybDemocraticPartyNum);
        sybIdentityDistributionNumMap.put("sybMultitudeNum", sybMultitudeNum);
        sybIdentityDistributionNumMap.put("sybDoctoralStudentNum", sybDoctoralStudentNum);
        sybIdentityDistributionNumMap.put("sybPostgraduateNum", sybPostgraduateNum);
        sybIdentityDistributionNumMap.put("sybGraduateStudentNum", sybGraduateStudentNum);
        sybIdentityDistributionNumMap.put("sybUniversityNum", sybUniversityNum);
        sybIdentityDistributionNumMap.put("sybJuniorCollegeNum", sybJuniorCollegeNum);
        sybIdentityDistributionNumMap.put("sybHighSchoolNum", sybHighSchoolNum);

        List<VSysUser> userList = userService.selectAllVSysUserList();
        Map<String, Integer> map = userInfoStatisticsService.selectUserAgeStageNum(new String[]{"3"}, new String[]{"1", "2"},userList);
        sybIdentityDistributionNumMap.put("sybYearsOld35BelowNum", map.get("35BelowNum"));
        sybIdentityDistributionNumMap.put("sybYearsOld36to40Num", map.get("36to40Num"));
        sybIdentityDistributionNumMap.put("sybYearsOld41to45Num", map.get("41to45Num"));
        sybIdentityDistributionNumMap.put("sybYearsOld46to50Num", map.get("46to50Num"));
        sybIdentityDistributionNumMap.put("sybYearsOld51to54Num", map.get("51to54Num"));
        sybIdentityDistributionNumMap.put("sybYearsOld55overNum", map.get("55overNum"));
    }



    /**
     * 行政参公职级情况
     * @param xzbcgzjNumMap
     */
    private void xzbcgzjNum(Map<String, Object> xzbcgzjNumMap){
        //行政编职级数量统计
        List<Map<String, Object>> selectXzbzjNum = userInfoStatisticsService.selectXzbzjNum();
        //参公职级数量统计
        List<Map<String, Object>> selectCgzjNum = userInfoStatisticsService.selectCgzjNum();

        int xzbcgztNum = Integer.parseInt(selectXzbzjNum.get(0).get("num").toString())
                + Integer.parseInt(selectCgzjNum.get(0).get("num").toString());//正厅级人数
        int xzbcgftNum = Integer.parseInt(selectXzbzjNum.get(1).get("num").toString())
                + Integer.parseInt(selectCgzjNum.get(1).get("num").toString());//副厅级人数
        int xzbcgzcNum = Integer.parseInt(selectXzbzjNum.get(2).get("num").toString())
                + Integer.parseInt(selectCgzjNum.get(2).get("num").toString());//正处级人数
        int xzbcgfcNum = Integer.parseInt(selectXzbzjNum.get(3).get("num").toString())
                + Integer.parseInt(selectCgzjNum.get(3).get("num").toString());//副处级人数
        int xzbcgyjxsyNum = Integer.parseInt(selectXzbzjNum.get(4).get("num").toString())
                + Integer.parseInt(selectCgzjNum.get(4).get("num").toString());//一级巡视员人数
        int xzbcgejxsyNum = Integer.parseInt(selectXzbzjNum.get(5).get("num").toString())
                + Integer.parseInt(selectCgzjNum.get(5).get("num").toString());//二级巡视员人数
        int xzbcgyjdyyNum = Integer.parseInt(selectXzbzjNum.get(6).get("num").toString())
                + Integer.parseInt(selectCgzjNum.get(6).get("num").toString());//一级调研员人数
        int xzbcgejdyyNum = Integer.parseInt(selectXzbzjNum.get(7).get("num").toString())
                + Integer.parseInt(selectCgzjNum.get(7).get("num").toString());//二级调研员人数
        int xzbcgsanjidyyNum = Integer.parseInt(selectXzbzjNum.get(8).get("num").toString())
                + Integer.parseInt(selectCgzjNum.get(8).get("num").toString());//三级调研员人数
        int xzbcgsjidyyNum = Integer.parseInt(selectXzbzjNum.get(9).get("num").toString())
                + Integer.parseInt(selectCgzjNum.get(9).get("num").toString());//四级调研员人数
        int xzbcgyjzrkyNum = Integer.parseInt(selectXzbzjNum.get(10).get("num").toString())
                + Integer.parseInt(selectCgzjNum.get(10).get("num").toString());//一级主任科员人数
        int xzbcgejzrkyNum = Integer.parseInt(selectXzbzjNum.get(11).get("num").toString())
                + Integer.parseInt(selectCgzjNum.get(11).get("num").toString());//二级主任科员人数
        int xzbcgsanjizrkyNum = Integer.parseInt(selectXzbzjNum.get(12).get("num").toString())
                + Integer.parseInt(selectCgzjNum.get(12).get("num").toString());//三级主任科员人数
        int xzbcgsjizrkyNum = Integer.parseInt(selectXzbzjNum.get(13).get("num").toString())
                + Integer.parseInt(selectCgzjNum.get(13).get("num").toString());//四级级主任科员人数
        int xzbcgyjkyNum = Integer.parseInt(selectXzbzjNum.get(14).get("num").toString())
                + Integer.parseInt(selectCgzjNum.get(14).get("num").toString());//一级科员人数
        int xzbcgejkyNum = Integer.parseInt(selectXzbzjNum.get(15).get("num").toString())
                + Integer.parseInt(selectCgzjNum.get(15).get("num").toString());//二级科员人数
        int xzbcgsyqNum = Integer.parseInt(selectXzbzjNum.get(16).get("num").toString())
                + Integer.parseInt(selectCgzjNum.get(16).get("num").toString());//试用期人数

        xzbcgzjNumMap.put("xzbcgztNum", xzbcgztNum);
        xzbcgzjNumMap.put("xzbcgftNum", xzbcgftNum);
        xzbcgzjNumMap.put("xzbcgzcNum", xzbcgzcNum);
        xzbcgzjNumMap.put("xzbcgfcNum", xzbcgfcNum);
        xzbcgzjNumMap.put("xzbcgyjxsyNum", xzbcgyjxsyNum);
        xzbcgzjNumMap.put("xzbcgejxsyNum", xzbcgejxsyNum);
        xzbcgzjNumMap.put("xzbcgyjdyyNum", xzbcgyjdyyNum);
        xzbcgzjNumMap.put("xzbcgejdyyNum", xzbcgejdyyNum);
        xzbcgzjNumMap.put("xzbcgsanjidyyNum", xzbcgsanjidyyNum);
        xzbcgzjNumMap.put("xzbcgsjidyyNum", xzbcgsjidyyNum);
        xzbcgzjNumMap.put("xzbcgyjzrkyNum", xzbcgyjzrkyNum);
        xzbcgzjNumMap.put("xzbcgejzrkyNum", xzbcgejzrkyNum);
        xzbcgzjNumMap.put("xzbcgsanjizrkyNum", xzbcgsanjizrkyNum);
        xzbcgzjNumMap.put("xzbcgsjizrkyNum", xzbcgsjizrkyNum);
        xzbcgzjNumMap.put("xzbcgyjkyNum", xzbcgyjkyNum);
        xzbcgzjNumMap.put("xzbcgejkyNum", xzbcgejkyNum);
        xzbcgzjNumMap.put("xzbcgsyqNum", xzbcgsyqNum);
    }

    /**
     * 副处级及以上行政编制和参公性别、民族、政治面貌情况
     * @param fcjUpXzbcgDistributionNumMap
     */
    private void fcjUpXzbcgDistributionNum(Map<String, Object> fcjUpXzbcgDistributionNumMap){
        //副处级及以上行政编制性别、民族、政治面貌情况统计
        List<Map<String, Object>> selectFcjUpXzbDistributionNum = userInfoStatisticsService.selectFcjUpXzbDistributionNum();
        //副处级及以上参公性别、民族、政治面貌情况统计
        List<Map<String, Object>> selectFcjUpCgDistributionNum = userInfoStatisticsService.selectFcjUpCgDistributionNum();
        int fcjUpXzbcgNum = Integer.parseInt(selectFcjUpXzbDistributionNum.get(0).get("num").toString())
                + Integer.parseInt(selectFcjUpCgDistributionNum.get(0).get("num").toString());//副处级及以上行政编制和参公人数
        int fcjUpXzbcgMaleNum = Integer.parseInt(selectFcjUpXzbDistributionNum.get(1).get("num").toString())
                + Integer.parseInt(selectFcjUpCgDistributionNum.get(1).get("num").toString());//男干部人数
        int fcjUpXzbcgWomanNum = Integer.parseInt(selectFcjUpXzbDistributionNum.get(2).get("num").toString())
                + Integer.parseInt(selectFcjUpCgDistributionNum.get(2).get("num").toString());//女干部人数
        int fcjUpXzbcgssmzNum = Integer.parseInt(selectFcjUpXzbDistributionNum.get(3).get("num").toString())
                + Integer.parseInt(selectFcjUpCgDistributionNum.get(3).get("num").toString());//少数民族干部人数
        int fcjUpXzbcgPartyNum = Integer.parseInt(selectFcjUpXzbDistributionNum.get(4).get("num").toString())
                + Integer.parseInt(selectFcjUpCgDistributionNum.get(4).get("num").toString());//中共党员人数
        int fcjUpXzbcgmzPartyNum = Integer.parseInt(selectFcjUpXzbDistributionNum.get(5).get("num").toString())
                + Integer.parseInt(selectFcjUpCgDistributionNum.get(5).get("num").toString());//民主党派人数
        int fcjUpXzbcgqzNum = Integer.parseInt(selectFcjUpXzbDistributionNum.get(6).get("num").toString())
                + Integer.parseInt(selectFcjUpCgDistributionNum.get(6).get("num").toString());//群众人数

        fcjUpXzbcgDistributionNumMap.put("fcjUpXzbcgNum", fcjUpXzbcgNum);
        fcjUpXzbcgDistributionNumMap.put("fcjUpXzbcgMaleNum", fcjUpXzbcgMaleNum);
        fcjUpXzbcgDistributionNumMap.put("fcjUpXzbcgWomanNum", fcjUpXzbcgWomanNum);
        fcjUpXzbcgDistributionNumMap.put("fcjUpXzbcgssmzNum", fcjUpXzbcgssmzNum);
        fcjUpXzbcgDistributionNumMap.put("fcjUpXzbcgPartyNum", fcjUpXzbcgPartyNum);
        fcjUpXzbcgDistributionNumMap.put("fcjUpXzbcgmzPartyNum", fcjUpXzbcgmzPartyNum);
        fcjUpXzbcgDistributionNumMap.put("fcjUpXzbcgqzNum", fcjUpXzbcgqzNum);
    }

    /**
     * 副处级及以上事业编性别、民族、政治面貌情况
     * @param fcjUpSybDistributionNumMap
     */
    private void fcjUpSybDistributionNum(Map<String, Object> fcjUpSybDistributionNumMap){
        //副处级及以上事业编性别、民族、政治面貌情况统计
        List<Map<String, Object>> selectFcjUpSybDistributionNum = userInfoStatisticsService.selectFcjUpSybDistributionNum();
        int fcjupsybcgNum = Integer.parseInt(selectFcjUpSybDistributionNum.get(0).get("num").toString());//副处级及以上行政编制和参公人数
        int fcjupsybMaleNum = Integer.parseInt(selectFcjUpSybDistributionNum.get(1).get("num").toString());//男干部人数
        int fcjupsybWomanNum = Integer.parseInt(selectFcjUpSybDistributionNum.get(2).get("num").toString());//女干部人数
        int fcjupsybssmzNum = Integer.parseInt(selectFcjUpSybDistributionNum.get(3).get("num").toString());//少数民族干部人数
        int fcjupsybPartyNum = Integer.parseInt(selectFcjUpSybDistributionNum.get(4).get("num").toString());//中共党员人数
        int fcjupsybmzPartyNum = Integer.parseInt(selectFcjUpSybDistributionNum.get(5).get("num").toString());//民主党派人数
        int fcjupsybqzNum = Integer.parseInt(selectFcjUpSybDistributionNum.get(6).get("num").toString());//群众人数

        fcjUpSybDistributionNumMap.put("fcjupsybcgNum", fcjupsybcgNum);
        fcjUpSybDistributionNumMap.put("fcjupsybMaleNum", fcjupsybMaleNum);
        fcjUpSybDistributionNumMap.put("fcjupsybWomanNum", fcjupsybWomanNum);
        fcjUpSybDistributionNumMap.put("fcjupsybssmzNum", fcjupsybssmzNum);
        fcjUpSybDistributionNumMap.put("fcjupsybPartyNum", fcjupsybPartyNum);
        fcjUpSybDistributionNumMap.put("fcjupsybmzPartyNum", fcjupsybmzPartyNum);
        fcjUpSybDistributionNumMap.put("fcjupsybqzNum", fcjupsybqzNum);
    }

    /**
     * 副处级及以上行政编制和参公年龄情况数量统计
     * @param fcjUpXzbcgAgeDistributionNumMap
     */
    private void fcjUpXzbcgAgeDistributionNum(Map<String, Object> fcjUpXzbcgAgeDistributionNumMap){
        //副处级及以上行政编制和参公年龄情况数量统计
        List<Map<String, Object>> selectFcjUpXzbcgAgeDistributionNum = userInfoStatisticsService.selectFcjUpXzbcgAgeDistributionNum();

        int fcjUpYearsOld35BelowNum = Integer.parseInt(selectFcjUpXzbcgAgeDistributionNum.get(0).get("num").toString())
                + Integer.parseInt(selectFcjUpXzbcgAgeDistributionNum.get(6).get("num").toString());//副处级及以上行政编制和参公35岁及以下人数
        int fcjUpYearsOld36to40Num = Integer.parseInt(selectFcjUpXzbcgAgeDistributionNum.get(1).get("num").toString())
                + Integer.parseInt(selectFcjUpXzbcgAgeDistributionNum.get(7).get("num").toString());//副处级及以上行政编制和参公36岁至40岁人数
        int fcjUpYearsOld41to45Num = Integer.parseInt(selectFcjUpXzbcgAgeDistributionNum.get(2).get("num").toString())
                + Integer.parseInt(selectFcjUpXzbcgAgeDistributionNum.get(8).get("num").toString());//副处级及以上行政编制和参公41岁至45岁人数
        int fcjUpYearsOld46to50Num = Integer.parseInt(selectFcjUpXzbcgAgeDistributionNum.get(3).get("num").toString())
                + Integer.parseInt(selectFcjUpXzbcgAgeDistributionNum.get(9).get("num").toString());//副处级及以上行政编制和参公46岁至50岁人数
        int fcjUpYearsOld51to54Num = Integer.parseInt(selectFcjUpXzbcgAgeDistributionNum.get(4).get("num").toString())
                + Integer.parseInt(selectFcjUpXzbcgAgeDistributionNum.get(10).get("num").toString());//副处级及以上行政编制和参公51岁至54岁人数
        int fcjUpYearsOld55overNum = Integer.parseInt(selectFcjUpXzbcgAgeDistributionNum.get(5).get("num").toString())
                + Integer.parseInt(selectFcjUpXzbcgAgeDistributionNum.get(11).get("num").toString());//副处级及以上行政编制和参公55岁及以上人数

        fcjUpXzbcgAgeDistributionNumMap.put("fcjUpYearsOld35BelowNum", fcjUpYearsOld35BelowNum);
        fcjUpXzbcgAgeDistributionNumMap.put("fcjUpYearsOld36to40Num", fcjUpYearsOld36to40Num);
        fcjUpXzbcgAgeDistributionNumMap.put("fcjUpYearsOld41to45Num", fcjUpYearsOld41to45Num);
        fcjUpXzbcgAgeDistributionNumMap.put("fcjUpYearsOld46to50Num", fcjUpYearsOld46to50Num);
        fcjUpXzbcgAgeDistributionNumMap.put("fcjUpYearsOld51to54Num", fcjUpYearsOld51to54Num);
        fcjUpXzbcgAgeDistributionNumMap.put("fcjUpYearsOld55overNum", fcjUpYearsOld55overNum);
    }

    /**
     * 副处级及以上事业编年龄情况数量统计
     * @param fcjUpSybAgeDistributionNumMap
     */
    private void fcjUpSybAgeDistributionNum(Map<String, Object> fcjUpSybAgeDistributionNumMap){
        //副处级及以上事业编年龄情况数量统计
        List<Map<String, Object>> fcjUpSybAgeDistributionNum = userInfoStatisticsService.fcjUpSybAgeDistributionNum();

        int fcjUpYearsOld35BelowNum = Integer.parseInt(fcjUpSybAgeDistributionNum.get(0).get("num").toString());//副处级及以上事业编35岁及以下人数
        int fcjUpYearsOld36to40Num = Integer.parseInt(fcjUpSybAgeDistributionNum.get(1).get("num").toString());//副处级及以上事业编36岁至40岁人数
        int fcjUpYearsOld41to45Num = Integer.parseInt(fcjUpSybAgeDistributionNum.get(2).get("num").toString());//副处级及以上事业编41岁至45岁人数
        int fcjUpYearsOld46to50Num = Integer.parseInt(fcjUpSybAgeDistributionNum.get(3).get("num").toString());//副处级及以上事业编46岁至50岁人数
        int fcjUpYearsOld51to54Num = Integer.parseInt(fcjUpSybAgeDistributionNum.get(4).get("num").toString());//副处级及以上事业编51岁至54岁人数
        int fcjUpYearsOld55overNum = Integer.parseInt(fcjUpSybAgeDistributionNum.get(5).get("num").toString());//副处级及以上事业编55岁及以上人数

        fcjUpSybAgeDistributionNumMap.put("fcjUpYearsOld35BelowNum", fcjUpYearsOld35BelowNum);
        fcjUpSybAgeDistributionNumMap.put("fcjUpYearsOld36to40Num", fcjUpYearsOld36to40Num);
        fcjUpSybAgeDistributionNumMap.put("fcjUpYearsOld41to45Num", fcjUpYearsOld41to45Num);
        fcjUpSybAgeDistributionNumMap.put("fcjUpYearsOld46to50Num", fcjUpYearsOld46to50Num);
        fcjUpSybAgeDistributionNumMap.put("fcjUpYearsOld51to54Num", fcjUpYearsOld51to54Num);
        fcjUpSybAgeDistributionNumMap.put("fcjUpYearsOld55overNum", fcjUpYearsOld55overNum);
    }


    /**
     * 副处级及以上行政编制和参公学历情况数量统计
     * @param fcjUpXzbcgEducationDistributionNumMap
     */
    private void fcjUpXzbcgEducationDistributionNum(Map<String, Object> fcjUpXzbcgEducationDistributionNumMap){
        //副处级及以上行政编制学历情况数量统计
        List<Map<String, Object>> selectFcjUpXzbEducationDistributionNum = userInfoStatisticsService.selectFcjUpXzbEducationDistributionNum();
        //副处级及以上参公学历情况数量统计
        List<Map<String, Object>> selectFcjUpCgEducationDistributionNum = userInfoStatisticsService.selectFcjUpCgEducationDistributionNum();

        int fcjupDoctoralStudentNum = Integer.parseInt(selectFcjUpXzbEducationDistributionNum.get(0).get("num").toString())
                + Integer.parseInt(selectFcjUpCgEducationDistributionNum.get(0).get("num").toString());//副处级及以上行政编制和参公博士研究生人数
        int fcjupPostgraduateNum = Integer.parseInt(selectFcjUpXzbEducationDistributionNum.get(1).get("num").toString())
                + Integer.parseInt(selectFcjUpCgEducationDistributionNum.get(1).get("num").toString());//副处级及以上行政编制和参公硕士研究生人数
        int fcjupGraduateStudentNum = Integer.parseInt(selectFcjUpXzbEducationDistributionNum.get(2).get("num").toString())
                + Integer.parseInt(selectFcjUpCgEducationDistributionNum.get(2).get("num").toString());//副处级及以上行政编制和参公研究生人数
        int fcjupUniversityNum = Integer.parseInt(selectFcjUpXzbEducationDistributionNum.get(3).get("num").toString())
                + Integer.parseInt(selectFcjUpCgEducationDistributionNum.get(3).get("num").toString());//副处级及以上行政编制和参公大学人数
        int fcjupJuniorCollegeNum = Integer.parseInt(selectFcjUpXzbEducationDistributionNum.get(4).get("num").toString())
                + Integer.parseInt(selectFcjUpCgEducationDistributionNum.get(4).get("num").toString());//副处级及以上行政编制和参公大专人数
        int fcjupHighSchoolNum = Integer.parseInt(selectFcjUpXzbEducationDistributionNum.get(5).get("num").toString())
                + Integer.parseInt(selectFcjUpCgEducationDistributionNum.get(5).get("num").toString());//副处级及以上行政编制和参公高中人数

        fcjUpXzbcgEducationDistributionNumMap.put("fcjupDoctoralStudentNum", fcjupDoctoralStudentNum);
        fcjUpXzbcgEducationDistributionNumMap.put("fcjupPostgraduateNum", fcjupPostgraduateNum);
        fcjUpXzbcgEducationDistributionNumMap.put("fcjupGraduateStudentNum", fcjupGraduateStudentNum);
        fcjUpXzbcgEducationDistributionNumMap.put("fcjupUniversityNum", fcjupUniversityNum);
        fcjUpXzbcgEducationDistributionNumMap.put("fcjupJuniorCollegeNum", fcjupJuniorCollegeNum);
        fcjUpXzbcgEducationDistributionNumMap.put("fcjupHighSchoolNum", fcjupHighSchoolNum);
    }

    /**
     * 副处级及以上事业编学历情况数量统计
     * @param fcjUpSybEducationDistributionNumMap
     */
    private void fcjUpSybEducationDistributionNum(Map<String, Object> fcjUpSybEducationDistributionNumMap){
        //副处级及以上事业编学历情况数量统计
        List<Map<String, Object>> selectFcjUpSybEducationDistributionNum = userInfoStatisticsService.selectFcjUpSybEducationDistributionNum();
        int fcjupDoctoralStudentNum = Integer.parseInt(selectFcjUpSybEducationDistributionNum.get(0).get("num").toString());//副处级及以上事业编博士研究生人数
        int fcjupPostgraduateNum = Integer.parseInt(selectFcjUpSybEducationDistributionNum.get(1).get("num").toString());//副处级及以上事业编硕士研究生人数
        int fcjupGraduateStudentNum = Integer.parseInt(selectFcjUpSybEducationDistributionNum.get(2).get("num").toString());//副处级及以上事业编研究生人数
        int fcjupUniversityNum = Integer.parseInt(selectFcjUpSybEducationDistributionNum.get(3).get("num").toString());//副处级及以上事业编大学人数
        int fcjupJuniorCollegeNum = Integer.parseInt(selectFcjUpSybEducationDistributionNum.get(4).get("num").toString());//副处级及以上事业编大专人数
        int fcjupHighSchoolNum = Integer.parseInt(selectFcjUpSybEducationDistributionNum.get(5).get("num").toString());//副处级及以上事业编高中人数

        fcjUpSybEducationDistributionNumMap.put("fcjupDoctoralStudentNum", fcjupDoctoralStudentNum);
        fcjUpSybEducationDistributionNumMap.put("fcjupPostgraduateNum", fcjupPostgraduateNum);
        fcjUpSybEducationDistributionNumMap.put("fcjupGraduateStudentNum", fcjupGraduateStudentNum);
        fcjUpSybEducationDistributionNumMap.put("fcjupUniversityNum", fcjupUniversityNum);
        fcjUpSybEducationDistributionNumMap.put("fcjupJuniorCollegeNum", fcjupJuniorCollegeNum);
        fcjUpSybEducationDistributionNumMap.put("fcjupHighSchoolNum", fcjupHighSchoolNum);
    }

    /**
     * 行政编制和参公性别、民族、政治面貌、学历、年龄情况条形图
     * @param dataMap
     * @throws IOException
     */
    public void putXzbcgIdentityDistributionNumBar(Map<String, Object> dataMap) throws IOException {
        File file2 = File.createTempFile("temp", "png");
        List<VSysUser> userList = userService.selectAllVSysUserList();
        //创建数据
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        //行政编制性别、民族、政治面貌、学历、年龄情况
        List<Map<String, Object>> selectXzbIdentityDistributionNum = userInfoStatisticsService.selectXzbIdentityDistributionNum();
        Map<String, Integer> xzbMap = userInfoStatisticsService.selectUserAgeStageNum(new String[]{"1"}, new String[]{"1", "2"},userList);

        //参公性别、民族、政治面貌、学历、年龄情况
        List<Map<String, Object>> selectCgIdentityDistributionNum = userInfoStatisticsService.selectCgIdentityDistributionNum();
        Map<String, Integer> cgMap = userInfoStatisticsService.selectUserAgeStageNum(new String[]{"2"}, new String[]{"1", "2"},userList);

        int xzbMaleNum = Integer.parseInt(selectXzbIdentityDistributionNum.get(0).get("num").toString());//行政编制男性人数
        int cgMaleNum = Integer.parseInt(selectCgIdentityDistributionNum.get(0).get("num").toString());//参公男性人数
        int xzbWomanNum = Integer.parseInt(selectXzbIdentityDistributionNum.get(1).get("num").toString());//行政编制女性人数
        int cgWomanNum = Integer.parseInt(selectCgIdentityDistributionNum.get(1).get("num").toString());//参公女性人数
        int xzbMinorityNationNum = Integer.parseInt(selectXzbIdentityDistributionNum.get(2).get("num").toString());//行政编制少数民族人数
        int cgMinorityNationNum = Integer.parseInt(selectCgIdentityDistributionNum.get(2).get("num").toString());//参公少数民族人数
        int xzbPartyMemberNum = Integer.parseInt(selectXzbIdentityDistributionNum.get(3).get("num").toString());//行政编制中共党员人数
        int cgPartyMemberNum = Integer.parseInt(selectCgIdentityDistributionNum.get(3).get("num").toString());//参公中共党员人数
        int xzbDemocraticPartyNum = Integer.parseInt(selectXzbIdentityDistributionNum.get(4).get("num").toString());//行政编制民主党派人数
        int cgDemocraticPartyNum = Integer.parseInt(selectCgIdentityDistributionNum.get(4).get("num").toString());//参公民主党派人数
        int xzbMultitudeNum = Integer.parseInt(selectXzbIdentityDistributionNum.get(5).get("num").toString());//行政编制群众人数
        int cgMultitudeNum = Integer.parseInt(selectCgIdentityDistributionNum.get(5).get("num").toString());//参公群众人数
        int xzbDoctoralStudentNum = Integer.parseInt(selectXzbIdentityDistributionNum.get(6).get("num").toString());//行政编制博士研究生人数
        int cgDoctoralStudentNum = Integer.parseInt(selectCgIdentityDistributionNum.get(6).get("num").toString());//参公博士研究生人数
        int xzbPostgraduateNum = Integer.parseInt(selectXzbIdentityDistributionNum.get(7).get("num").toString());//行政编制硕士研究生人数
        int cgPostgraduateNum = Integer.parseInt(selectCgIdentityDistributionNum.get(7).get("num").toString());//参公硕士研究生人数
        int xzbGraduateStudentNum = Integer.parseInt(selectXzbIdentityDistributionNum.get(8).get("num").toString());//行政编制在职硕士研究生人数
        int cgGraduateStudentNum = Integer.parseInt(selectCgIdentityDistributionNum.get(8).get("num").toString());//参公在职硕士研究生人数
        int xzbUniversityNum = Integer.parseInt(selectXzbIdentityDistributionNum.get(9).get("num").toString());//行政编制大学人数
        int cgUniversityNum = Integer.parseInt(selectCgIdentityDistributionNum.get(9).get("num").toString());//参公大学人数
        int xzbJuniorCollegeNum = Integer.parseInt(selectXzbIdentityDistributionNum.get(10).get("num").toString());//行政编制大专人数
        int cgJuniorCollegeNum = Integer.parseInt(selectCgIdentityDistributionNum.get(10).get("num").toString());//参公大专人数
        int xzbHighSchoolNum = Integer.parseInt(selectXzbIdentityDistributionNum.get(11).get("num").toString());//行政编制高中人数
        int cgHighSchoolNum = Integer.parseInt(selectCgIdentityDistributionNum.get(11).get("num").toString());//参公高中人数



        dataset.addValue(xzbMaleNum, "行政编制", "男性");
        dataset.addValue(cgMaleNum, "参公", "男性");
        dataset.addValue(xzbWomanNum, "行政编制", "女性");
        dataset.addValue(cgWomanNum, "参公", "女性");
        dataset.addValue(xzbMinorityNationNum, "行政编制", "少数民族");
        dataset.addValue(cgMinorityNationNum, "参公", "少数民族");
        dataset.addValue(xzbPartyMemberNum, "行政编制", "中共党员");
        dataset.addValue(cgPartyMemberNum, "参公", "中共党员");
        dataset.addValue(xzbDemocraticPartyNum, "行政编制", "民主党派");
        dataset.addValue(cgDemocraticPartyNum, "参公", "民主党派");
        dataset.addValue(xzbMultitudeNum, "行政编制", "群众");
        dataset.addValue(cgMultitudeNum, "参公", "群众");
        dataset.addValue(xzbDoctoralStudentNum, "行政编制", "博士研究生");
        dataset.addValue(cgDoctoralStudentNum, "参公", "博士研究生");
        dataset.addValue(xzbPostgraduateNum, "行政编制", "硕士研究生");
        dataset.addValue(cgPostgraduateNum, "参公", "硕士研究生");
        dataset.addValue(xzbGraduateStudentNum, "行政编制", "在职硕士研究生");
        dataset.addValue(cgGraduateStudentNum, "参公", "在职硕士研究生");
        dataset.addValue(xzbUniversityNum, "行政编制", "大学");
        dataset.addValue(cgUniversityNum, "参公", "大学");
        dataset.addValue(xzbJuniorCollegeNum, "行政编制", "大专");
        dataset.addValue(cgJuniorCollegeNum, "参公", "大专");
        dataset.addValue(xzbHighSchoolNum, "行政编制", "高中");
        dataset.addValue(cgHighSchoolNum, "参公", "高中");
        dataset.addValue(xzbMap.get("35BelowNum"), "行政编制", "35岁及以下");
        dataset.addValue(cgMap.get("35BelowNum"), "参公", "35岁及以下");
        dataset.addValue(xzbMap.get("36to40Num"), "行政编制", "36岁至40岁");
        dataset.addValue(cgMap.get("36to40Num"), "参公", "36岁至40岁");
        dataset.addValue(xzbMap.get("41to45Num"), "行政编制", "41岁至45岁");
        dataset.addValue(cgMap.get("41to45Num"), "参公", "41岁至45岁");
        dataset.addValue(xzbMap.get("46to50Num"), "行政编制", "46岁至50岁");
        dataset.addValue(cgMap.get("46to50Num"), "参公", "46岁至50岁");
        dataset.addValue(xzbMap.get("51to54Num"), "行政编制", "51岁至54岁");
        dataset.addValue(cgMap.get("51to54Num"), "参公", "51岁至54岁");
        dataset.addValue(xzbMap.get("55overNum"), "行政编制", "55岁以上");
        dataset.addValue(cgMap.get("55overNum"), "参公", "55岁以上");


        Font font = new Font("宋体", Font.BOLD, 12);
        StandardChartTheme standardChartTheme = new StandardChartTheme("CN"); // 创建主题样式
        standardChartTheme.setExtraLargeFont(font); // 设置标题字体
        standardChartTheme.setRegularFont(font); // 设置图例的字体
        standardChartTheme.setLargeFont(font); // 设置轴向的字体
        ChartFactory.setChartTheme(standardChartTheme);// 应用主题样式

        JFreeChartToFileUtil.createBarChart(dataset, file2, "行政编制和参公性别、民族、政治面貌、学历、年龄情况", "","数量（人）");
        ImageEntity image = new ImageEntity();
        image.setHeight(300);
        image.setWidth(600);
        System.out.println(file2.getAbsolutePath());
        image.setUrl(file2.getAbsolutePath());
        image.setType(ImageEntity.URL);
        dataMap.put("xzbcgEchartBar", image);
    }

    /**
     * 事业编性别、民族、政治面貌、学历、年龄情况条形图
     * @param dataMap
     * @throws IOException
     */
    public void putSybIdentityDistributionNumBar(Map<String, Object> dataMap) throws IOException {
        File file2 = File.createTempFile("temp", "png");

        //创建数据
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        //事业编性别、民族、政治面貌、学历、年龄情况
        List<Map<String, Object>> selectSybIdentityDistributionNum = userInfoStatisticsService.selectSybIdentityDistributionNum();
        int sybMaleNum = Integer.parseInt(selectSybIdentityDistributionNum.get(0).get("num").toString());//男性人数
        int sybWomanNum = Integer.parseInt(selectSybIdentityDistributionNum.get(1).get("num").toString());//女性人数
        int sybMinorityNationNum = Integer.parseInt(selectSybIdentityDistributionNum.get(2).get("num").toString());//少数民族人数
        int sybPartyMemberNum = Integer.parseInt(selectSybIdentityDistributionNum.get(3).get("num").toString());//中共党员人数
        int sybDemocraticPartyNum = Integer.parseInt(selectSybIdentityDistributionNum.get(4).get("num").toString());//民主党派人数
        int sybMultitudeNum = Integer.parseInt(selectSybIdentityDistributionNum.get(5).get("num").toString());//群众人数
        int sybDoctoralStudentNum = Integer.parseInt(selectSybIdentityDistributionNum.get(6).get("num").toString());//博士研究生人数
        int sybPostgraduateNum = Integer.parseInt(selectSybIdentityDistributionNum.get(7).get("num").toString());//硕士研究生人数
        int sybGraduateStudentNum = Integer.parseInt(selectSybIdentityDistributionNum.get(8).get("num").toString());//在职硕士研究生人数
        int sybUniversityNum = Integer.parseInt(selectSybIdentityDistributionNum.get(9).get("num").toString());//大学人数
        int sybJuniorCollegeNum = Integer.parseInt(selectSybIdentityDistributionNum.get(10).get("num").toString());//大专人数
        int sybHighSchoolNum = Integer.parseInt(selectSybIdentityDistributionNum.get(11).get("num").toString());//高中人数


        dataset.addValue(sybMaleNum, "事业编", "男性");
        dataset.addValue(sybWomanNum, "事业编", "女性");
        dataset.addValue(sybMinorityNationNum, "事业编", "少数民族");
        dataset.addValue(sybPartyMemberNum, "事业编", "中共党员");
        dataset.addValue(sybDemocraticPartyNum, "事业编", "民主党派");
        dataset.addValue(sybMultitudeNum, "事业编", "群众");
        dataset.addValue(sybDoctoralStudentNum, "事业编", "博士研究生");
        dataset.addValue(sybPostgraduateNum, "事业编", "硕士研究生");
        dataset.addValue(sybGraduateStudentNum, "事业编", "在职硕士研究生");
        dataset.addValue(sybUniversityNum, "事业编", "大学");
        dataset.addValue(sybJuniorCollegeNum, "事业编", "大专");
        dataset.addValue(sybHighSchoolNum, "事业编", "高中");
        List<VSysUser> userList = userService.selectAllVSysUserList();
        Map<String, Integer> map = userInfoStatisticsService.selectUserAgeStageNum(new String[]{"3"}, new String[]{"1", "2"},userList);
        dataset.addValue(map.get("35BelowNum") , "事业编", "35岁及以下");
        dataset.addValue(map.get("36to40Num") , "事业编", "36岁至40岁");
        dataset.addValue(map.get("41to45Num") , "事业编", "41岁至45岁");
        dataset.addValue(map.get("46to50Num") , "事业编", "46岁至50岁");
        dataset.addValue(map.get("51to54Num") , "事业编", "51岁至54岁");
        dataset.addValue(map.get("55overNum") , "事业编", "55岁以上");

        Font font = new Font("宋体", Font.BOLD, 12);
        StandardChartTheme standardChartTheme = new StandardChartTheme("CN"); // 创建主题样式
        standardChartTheme.setExtraLargeFont(font); // 设置标题字体
        standardChartTheme.setRegularFont(font); // 设置图例的字体
        standardChartTheme.setLargeFont(font); // 设置轴向的字体
        //standardChartTheme.setChartBackgroundPaint(Color.WHITE);// 设置主题背景色
        ChartFactory.setChartTheme(standardChartTheme);// 应用主题样式

        JFreeChartToFileUtil.createBarChart(dataset, file2, "事业编性别、民族、政治面貌、学历、年龄情况", "","数量（人）");
        ImageEntity image = new ImageEntity();
        image.setHeight(300);
        image.setWidth(600);
        System.out.println(file2.getAbsolutePath());
        image.setUrl(file2.getAbsolutePath());
        image.setType(ImageEntity.URL);
        dataMap.put("sybEchartBar", image);
    }

    /**
     * 行政参公职级情况条形图
     * @param dataMap
     * @throws IOException
     */
    public void putXzbcgzjNumBar(Map<String, Object> dataMap) throws IOException {
        File file2 = File.createTempFile("temp", "png");

        //创建数据
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        //行政编职级数量统计
        List<Map<String, Object>> selectXzbzjNum = userInfoStatisticsService.selectXzbzjNum();
        //参公职级数量统计
        List<Map<String, Object>> selectCgzjNum = userInfoStatisticsService.selectCgzjNum();

        int xzbztNum = Integer.parseInt(selectXzbzjNum.get(0).get("num").toString());//行政编正厅级人数
        int cgztNum =  Integer.parseInt(selectCgzjNum.get(0).get("num").toString());//参公正厅级人数
        int xzbftNum = Integer.parseInt(selectXzbzjNum.get(1).get("num").toString());//行政编副厅级人数
        int cgftNum = Integer.parseInt(selectCgzjNum.get(1).get("num").toString());//参公副厅级人数
        int xzbzcNum = Integer.parseInt(selectXzbzjNum.get(2).get("num").toString());//行政编正处级人数
        int cgzcNum = Integer.parseInt(selectCgzjNum.get(2).get("num").toString());//参公正处级人数
        int xzbfcNum = Integer.parseInt(selectXzbzjNum.get(3).get("num").toString());//行政编副处级人数
        int cgfcNum = Integer.parseInt(selectCgzjNum.get(3).get("num").toString());//参公副处级人数
        int xzbyjxsyNum = Integer.parseInt(selectXzbzjNum.get(4).get("num").toString());//行政编一级巡视员人数
        int cgyjxsyNum = Integer.parseInt(selectCgzjNum.get(4).get("num").toString());//参公一级巡视员人数
        int xzbejxsyNum = Integer.parseInt(selectXzbzjNum.get(5).get("num").toString());//行政编二级巡视员人数
        int cgejxsyNum = Integer.parseInt(selectCgzjNum.get(5).get("num").toString());//参公二级巡视员人数
        int xzbyjdyyNum = Integer.parseInt(selectXzbzjNum.get(6).get("num").toString());//行政编一级调研员人数
        int cgyjdyyNum = Integer.parseInt(selectCgzjNum.get(6).get("num").toString());//参公一级调研员人数
        int xzbejdyyNum = Integer.parseInt(selectXzbzjNum.get(7).get("num").toString());//行政编二级调研员人数
        int cgejdyyNum = Integer.parseInt(selectCgzjNum.get(7).get("num").toString());//参公二级调研员人数
        int xzbsanjidyyNum = Integer.parseInt(selectXzbzjNum.get(8).get("num").toString());//行政编三级调研员人数
        int cgsanjidyyNum = Integer.parseInt(selectCgzjNum.get(8).get("num").toString());//参公三级调研员人数
        int xzbsjidyyNum = Integer.parseInt(selectXzbzjNum.get(9).get("num").toString());//行政编四级调研员人数
        int cgsjidyyNum = Integer.parseInt(selectCgzjNum.get(9).get("num").toString());//参公四级调研员人数
        int xzbyjzrkyNum = Integer.parseInt(selectXzbzjNum.get(10).get("num").toString());//行政编一级主任科员人数
        int bcgyjzrkyNum = Integer.parseInt(selectCgzjNum.get(10).get("num").toString());//参公一级主任科员人数
        int xzbejzrkyNum = Integer.parseInt(selectXzbzjNum.get(11).get("num").toString());//行政编二级主任科员人数
        int cgejzrkyNum = Integer.parseInt(selectCgzjNum.get(11).get("num").toString());//参公二级主任科员人数
        int xzbsanjizrkyNum = Integer.parseInt(selectXzbzjNum.get(12).get("num").toString());//行政编三级级主任科员人数
        int cgsanjizrkyNum = Integer.parseInt(selectCgzjNum.get(12).get("num").toString());//参公三级主任科员人数
        int xzbsjizrkyNum = Integer.parseInt(selectXzbzjNum.get(13).get("num").toString());//行政编四级级主任科员人数
        int cgsjizrkyNum = Integer.parseInt(selectCgzjNum.get(13).get("num").toString());//参公四级级主任科员人数
        int xzbyjkyNum = Integer.parseInt(selectXzbzjNum.get(14).get("num").toString());//行政编一级科员人数
        int cgyjkyNum = Integer.parseInt(selectCgzjNum.get(14).get("num").toString());//参公一级科员人数
        int xzbejkyNum = Integer.parseInt(selectXzbzjNum.get(15).get("num").toString());//行政编二级科员人数
        int cgejkyNum = Integer.parseInt(selectCgzjNum.get(15).get("num").toString());//参公二级科员人数
        int xzbsyqNum = Integer.parseInt(selectXzbzjNum.get(16).get("num").toString());//行政编试用期人数
        int cgsyqNum = Integer.parseInt(selectCgzjNum.get(16).get("num").toString());//参公试用期人数

        dataset.addValue(xzbztNum, "行政编制", "正厅级");
        dataset.addValue(cgztNum, "参公", "正厅级");
        dataset.addValue(xzbftNum, "行政编制", "副厅级");
        dataset.addValue(cgftNum, "参公", "副厅级");
        dataset.addValue(xzbzcNum, "行政编制", "正处级");
        dataset.addValue(cgzcNum, "参公", "正处级");
        dataset.addValue(xzbfcNum, "行政编制", "副处级");
        dataset.addValue(cgfcNum, "参公", "副处级");
        dataset.addValue(xzbyjxsyNum, "行政编制", "一级巡视员");
        dataset.addValue(cgyjxsyNum, "参公", "一级巡视员");
        dataset.addValue(xzbejxsyNum, "行政编制", "二级巡视员");
        dataset.addValue(cgejxsyNum, "参公", "二级巡视员");
        dataset.addValue(xzbyjdyyNum, "行政编制", "一级调研员");
        dataset.addValue(cgyjdyyNum, "参公", "一级调研员");
        dataset.addValue(xzbejdyyNum, "行政编制", "二级调研员");
        dataset.addValue(cgejdyyNum, "参公", "二级调研员");
        dataset.addValue(xzbsanjidyyNum, "行政编制", "三级调研员");
        dataset.addValue(cgsanjidyyNum, "参公", "三级调研员");
        dataset.addValue(xzbsjidyyNum, "行政编制", "四级调研员");
        dataset.addValue(cgsjidyyNum, "参公", "四级调研员");
        dataset.addValue(xzbyjzrkyNum, "行政编制", "一级主任科员");
        dataset.addValue(bcgyjzrkyNum, "参公", "一级主任科员");
        dataset.addValue(xzbejzrkyNum, "行政编制", "二级主任科员");
        dataset.addValue(cgejzrkyNum, "参公", "二级主任科员");
        dataset.addValue(xzbsanjizrkyNum, "行政编制", "三级主任科员");
        dataset.addValue(cgsanjizrkyNum, "参公", "三级主任科员");
        dataset.addValue(xzbsjizrkyNum, "行政编制", "四级主任科员");
        dataset.addValue(cgsjizrkyNum, "参公", "四级主任科员");
        dataset.addValue(xzbyjkyNum, "行政编制", "一级科员");
        dataset.addValue(cgyjkyNum, "参公", "一级科员");
        dataset.addValue(xzbejkyNum, "行政编制", "二级科员");
        dataset.addValue(cgejkyNum, "参公", "二级科员");
        dataset.addValue(xzbsyqNum, "行政编制", "试用期");
        dataset.addValue(cgsyqNum, "参公", "试用期");

        Font font = new Font("宋体", Font.BOLD, 12);
        StandardChartTheme standardChartTheme = new StandardChartTheme("CN"); // 创建主题样式
        standardChartTheme.setExtraLargeFont(font); // 设置标题字体
        standardChartTheme.setRegularFont(font); // 设置图例的字体
        standardChartTheme.setLargeFont(font); // 设置轴向的字体
        //standardChartTheme.setChartBackgroundPaint(Color.WHITE);// 设置主题背景色
        ChartFactory.setChartTheme(standardChartTheme);// 应用主题样式

        JFreeChartToFileUtil.createBarChart(dataset, file2, "行政编制和参公职级情况", "","数量（人）");
        ImageEntity image = new ImageEntity();
        image.setHeight(300);
        image.setWidth(600);
        System.out.println(file2.getAbsolutePath());
        image.setUrl(file2.getAbsolutePath());
        image.setType(ImageEntity.URL);
        dataMap.put("xzbcgzjEchartBar", image);
    }

    /**
     * 副处级及以上行政编制和参公性别、民族、政治面貌情况条形图
     * @param dataMap
     * @throws IOException
     */
    public void putFcjUpXzbcgDistributionNumBar(Map<String, Object> dataMap) throws IOException {
        File file2 = File.createTempFile("temp", "png");
        //创建数据
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        //副处级及以上行政编制性别、民族、政治面貌情况统计
        List<Map<String, Object>> selectFcjUpXzbDistributionNum = userInfoStatisticsService.selectFcjUpXzbDistributionNum();
        //副处级及以上参公性别、民族、政治面貌情况统计
        List<Map<String, Object>> selectFcjUpCgDistributionNum = userInfoStatisticsService.selectFcjUpCgDistributionNum();
        //int fcjUpXzbNum = Integer.parseInt(selectFcjUpXzbDistributionNum.get(0).get("num").toString());//副处级及以上行政编制人数
        //int fcjUpCgNum =  Integer.parseInt(selectFcjUpCgDistributionNum.get(0).get("num").toString());//副处级及以上参公人数
        int fcjUpXzbMaleCadreNum = Integer.parseInt(selectFcjUpXzbDistributionNum.get(1).get("num").toString());//副处级及以上行政编制男干部人数
        int fcjUpCgMaleCadreNum = Integer.parseInt(selectFcjUpCgDistributionNum.get(1).get("num").toString());//副处级及以上参公男干部人数
        int fcjUpXzbWomanCadreNum = Integer.parseInt(selectFcjUpXzbDistributionNum.get(2).get("num").toString());//副处级及以上行政编制女干部人数
        int fcjUpCgWomanCadreNum = Integer.parseInt(selectFcjUpCgDistributionNum.get(2).get("num").toString());//副处级及以上参公女干部人数
        int fcjUpXzbMinorityNationCadreNum = Integer.parseInt(selectFcjUpXzbDistributionNum.get(3).get("num").toString());//副处级及以上行政编制少数民族干部人数
        int fcjUpCgMinorityNationCadreNum = Integer.parseInt(selectFcjUpCgDistributionNum.get(3).get("num").toString());//副处级及以上参公少数民族干部人数
        int fcjUpXzbPartyMemberNum = Integer.parseInt(selectFcjUpXzbDistributionNum.get(4).get("num").toString());//副处级及以上行政编制中共党员人数
        int fcjUpCgPartyMemberNum = Integer.parseInt(selectFcjUpCgDistributionNum.get(4).get("num").toString());//副处级及以上参公中共党员人数
        int fcjUpXzbDemocraticPartyNum = Integer.parseInt(selectFcjUpXzbDistributionNum.get(5).get("num").toString());//副处级及以上行政编制民主党派人数
        int fcjUpCgDemocraticPartyNum = Integer.parseInt(selectFcjUpCgDistributionNum.get(5).get("num").toString());//副处级及以上参公民主党派人数
        int fcjUpXzbMultitudeNum = Integer.parseInt(selectFcjUpXzbDistributionNum.get(6).get("num").toString());//副处级及以上行政编制群众人数
        int fcjUpCgMultitudeNum = Integer.parseInt(selectFcjUpCgDistributionNum.get(6).get("num").toString());//副处级及以上参公群众人数

        dataset.addValue(fcjUpXzbMaleCadreNum, "行政编制", "男干部");
        dataset.addValue(fcjUpCgMaleCadreNum, "参公", "男干部");
        dataset.addValue(fcjUpXzbWomanCadreNum, "行政编制", "女干部");
        dataset.addValue(fcjUpCgWomanCadreNum, "参公", "女干部");
        dataset.addValue(fcjUpXzbMinorityNationCadreNum, "行政编制", "少数民族干部");
        dataset.addValue(fcjUpCgMinorityNationCadreNum, "参公", "少数民族干部");
        dataset.addValue(fcjUpXzbPartyMemberNum, "行政编制", "中共党员");
        dataset.addValue(fcjUpCgPartyMemberNum, "参公", "中共党员");
        dataset.addValue(fcjUpXzbDemocraticPartyNum, "行政编制", "民主党派");
        dataset.addValue(fcjUpCgDemocraticPartyNum, "参公", "民主党派");
        dataset.addValue(fcjUpXzbMultitudeNum, "行政编制", "群众");
        dataset.addValue(fcjUpCgMultitudeNum, "参公", "群众");

        Font font = new Font("宋体", Font.BOLD, 12);
        StandardChartTheme standardChartTheme = new StandardChartTheme("CN"); // 创建主题样式
        standardChartTheme.setExtraLargeFont(font); // 设置标题字体
        standardChartTheme.setRegularFont(font); // 设置图例的字体
        standardChartTheme.setLargeFont(font); // 设置轴向的字体
        //standardChartTheme.setChartBackgroundPaint(Color.WHITE);// 设置主题背景色
        ChartFactory.setChartTheme(standardChartTheme);// 应用主题样式

        JFreeChartToFileUtil.createBarChart(dataset, file2, "性别、民族、政治面貌情况", "","数量（人）");
        ImageEntity image = new ImageEntity();
        image.setHeight(300);
        image.setWidth(600);
        System.out.println(file2.getAbsolutePath());
        image.setUrl(file2.getAbsolutePath());
        image.setType(ImageEntity.URL);
        dataMap.put("fcjUpXzbcgDistributionEchartBar", image);
    }

    /**
     * 副处级及以上事业编性别、民族、政治面貌情况条形图
     * @param dataMap
     * @throws IOException
     */
    public void putFcjUpSybDistributionNumBar(Map<String, Object> dataMap) throws IOException {
        File file2 = File.createTempFile("temp", "png");
        //创建数据
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        //副处级及以上事业编性别、民族、政治面貌情况统计
        List<Map<String, Object>> selectFcjUpSybDistributionNum = userInfoStatisticsService.selectFcjUpSybDistributionNum();
        //int fcjUpSybcgNum = Integer.parseInt(selectFcjUpSybDistributionNum.get(0).get("num").toString());//副处级及以上行政编制和参公人数
        int fcjUpSybMaleNum = Integer.parseInt(selectFcjUpSybDistributionNum.get(1).get("num").toString());//男干部人数
        int fcjUpSybWomanNum = Integer.parseInt(selectFcjUpSybDistributionNum.get(2).get("num").toString());//女干部人数
        int fcjUpSybssmzNum = Integer.parseInt(selectFcjUpSybDistributionNum.get(3).get("num").toString());//少数民族干部人数
        int fcjUpSybPartyNum = Integer.parseInt(selectFcjUpSybDistributionNum.get(4).get("num").toString());//中共党员人数
        int fcjUpSybmzPartyNum = Integer.parseInt(selectFcjUpSybDistributionNum.get(5).get("num").toString());//民主党派人数
        int fcjUpSybqzNum = Integer.parseInt(selectFcjUpSybDistributionNum.get(6).get("num").toString());//群众人数

        dataset.addValue(fcjUpSybMaleNum, "事业编", "男干部");
        dataset.addValue(fcjUpSybWomanNum, "事业编", "女干部");
        dataset.addValue(fcjUpSybssmzNum, "事业编", "少数民族干部");
        dataset.addValue(fcjUpSybPartyNum, "事业编", "中共党员");
        dataset.addValue(fcjUpSybmzPartyNum, "事业编", "民主党派");
        dataset.addValue(fcjUpSybqzNum, "事业编", "群众");

        Font font = new Font("宋体", Font.BOLD, 12);
        StandardChartTheme standardChartTheme = new StandardChartTheme("CN"); // 创建主题样式
        standardChartTheme.setExtraLargeFont(font); // 设置标题字体
        standardChartTheme.setRegularFont(font); // 设置图例的字体
        standardChartTheme.setLargeFont(font); // 设置轴向的字体
        //standardChartTheme.setChartBackgroundPaint(Color.WHITE);// 设置主题背景色
        ChartFactory.setChartTheme(standardChartTheme);// 应用主题样式

        JFreeChartToFileUtil.createBarChart(dataset, file2, "性别、民族、政治面貌情况", "","数量（人）");
        ImageEntity image = new ImageEntity();
        image.setHeight(300);
        image.setWidth(600);
        System.out.println(file2.getAbsolutePath());
        image.setUrl(file2.getAbsolutePath());
        image.setType(ImageEntity.URL);
        dataMap.put("fcjUpSybDistributionEchartBar", image);
    }

    /**
     * 副处级及以上行政编制和参公年龄情况数量统计条形图
     * @param dataMap
     * @throws IOException
     */
    public void putFcjUpXzbcgAgeDistributionNumBar(Map<String, Object> dataMap) throws IOException {
        File file2 = File.createTempFile("temp", "png");

        //创建数据
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        //副处级及以上行政编制和参公年龄情况数量统计
        List<Map<String, Object>> selectFcjUpXzbcgAgeDistributionNum = userInfoStatisticsService.selectFcjUpXzbcgAgeDistributionNum();

        int fcjUpXzbYearsOld35BelowNum = Integer.parseInt(selectFcjUpXzbcgAgeDistributionNum.get(0).get("num").toString());//副处级及以上行政编制35岁及以下人数
        int fcjUpCgYearsOld35BelowNum = Integer.parseInt(selectFcjUpXzbcgAgeDistributionNum.get(6).get("num").toString());//副处级及以上参公35岁及以下人数
        int fcjUpXzbYearsOld36to40Num = Integer.parseInt(selectFcjUpXzbcgAgeDistributionNum.get(1).get("num").toString());//副处级及以上行政编制36岁至40岁人数
        int fcjUpCgYearsOld36to40Num = Integer.parseInt(selectFcjUpXzbcgAgeDistributionNum.get(7).get("num").toString());//副处级及以上参公36岁至40岁人数
        int fcjUpXzbYearsOld41to45Num = Integer.parseInt(selectFcjUpXzbcgAgeDistributionNum.get(2).get("num").toString());//副处级及以上行政编制41岁至45岁人数
        int fcjUpCgYearsOld41to45Num = Integer.parseInt(selectFcjUpXzbcgAgeDistributionNum.get(8).get("num").toString());//副处级及以上参公41岁至45岁人数
        int fcjUpXzbYearsOld46to50Num = Integer.parseInt(selectFcjUpXzbcgAgeDistributionNum.get(3).get("num").toString());//副处级及以上行政编制46岁至50岁人数
        int fcjUpCgYearsOld46to50Num = Integer.parseInt(selectFcjUpXzbcgAgeDistributionNum.get(9).get("num").toString());//副处级及以上参公46岁至50岁人数
        int fcjUpXzbYearsOld51to54Num = Integer.parseInt(selectFcjUpXzbcgAgeDistributionNum.get(4).get("num").toString());//副处级及以上行政编制51岁至54岁人数
        int fcjUpcgYearsOld51to54Num = Integer.parseInt(selectFcjUpXzbcgAgeDistributionNum.get(10).get("num").toString());//副处级及以上参公51岁至54岁人数
        int fcjUpXzbYearsOld55overNum = Integer.parseInt(selectFcjUpXzbcgAgeDistributionNum.get(5).get("num").toString());//副处级及以上行政编制55岁及以上人数
        int fcjUpCgYearsOld55overNum = Integer.parseInt(selectFcjUpXzbcgAgeDistributionNum.get(11).get("num").toString());//副处级及以上参公55岁及以上人数

        dataset.addValue(fcjUpXzbYearsOld35BelowNum, "行政编制", "35岁及以下");
        dataset.addValue(fcjUpCgYearsOld35BelowNum, "参公", "35岁及以下");
        dataset.addValue(fcjUpXzbYearsOld36to40Num, "行政编制", "36岁至40岁");
        dataset.addValue(fcjUpCgYearsOld36to40Num, "参公", "36岁至40岁");
        dataset.addValue(fcjUpXzbYearsOld41to45Num, "行政编制", "41岁至45岁");
        dataset.addValue(fcjUpCgYearsOld41to45Num, "参公", "41岁至45岁");
        dataset.addValue(fcjUpXzbYearsOld46to50Num, "行政编制", "46岁至50岁");
        dataset.addValue(fcjUpCgYearsOld46to50Num, "参公", "46岁至50岁");
        dataset.addValue(fcjUpXzbYearsOld51to54Num, "行政编制", "51岁至54岁");
        dataset.addValue(fcjUpcgYearsOld51to54Num, "参公", "51岁至54岁");
        dataset.addValue(fcjUpXzbYearsOld55overNum, "行政编制", "55岁及以上");
        dataset.addValue(fcjUpCgYearsOld55overNum, "参公", "55岁及以上");

        Font font = new Font("宋体", Font.BOLD, 12);
        StandardChartTheme standardChartTheme = new StandardChartTheme("CN"); // 创建主题样式
        standardChartTheme.setExtraLargeFont(font); // 设置标题字体
        standardChartTheme.setRegularFont(font); // 设置图例的字体
        standardChartTheme.setLargeFont(font); // 设置轴向的字体
        //standardChartTheme.setChartBackgroundPaint(Color.WHITE);// 设置主题背景色
        ChartFactory.setChartTheme(standardChartTheme);// 应用主题样式

        JFreeChartToFileUtil.createBarChart(dataset, file2, "年龄情况", "","数量（人）");
        ImageEntity image = new ImageEntity();
        image.setHeight(300);
        image.setWidth(600);
        System.out.println(file2.getAbsolutePath());
        image.setUrl(file2.getAbsolutePath());
        image.setType(ImageEntity.URL);
        dataMap.put("fcjUpXzbcgAgeDistributionEchartBar", image);
    }

    /**
     * 副处级及以上事业编年龄情况数量统计条形图
     * @param dataMap
     * @throws IOException
     */
    public void putFcjUpSybAgeDistributionNumBar(Map<String, Object> dataMap) throws IOException {
        File file2 = File.createTempFile("temp", "png");

        //创建数据
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        //副处级及以上事业编年龄情况数量统计
        List<Map<String, Object>> fcjUpSybAgeDistributionNum = userInfoStatisticsService.fcjUpSybAgeDistributionNum();

        int fcjUpYearsOld35BelowNum = Integer.parseInt(fcjUpSybAgeDistributionNum.get(0).get("num").toString());//副处级及以上事业编35岁及以下人数
        int fcjUpYearsOld36to40Num = Integer.parseInt(fcjUpSybAgeDistributionNum.get(1).get("num").toString());//副处级及以上事业编36岁至40岁人数
        int fcjUpYearsOld41to45Num = Integer.parseInt(fcjUpSybAgeDistributionNum.get(2).get("num").toString());//副处级及以上事业编41岁至45岁人数
        int fcjUpYearsOld46to50Num = Integer.parseInt(fcjUpSybAgeDistributionNum.get(3).get("num").toString());//副处级及以上事业编46岁至50岁人数
        int fcjUpYearsOld51to54Num = Integer.parseInt(fcjUpSybAgeDistributionNum.get(4).get("num").toString());//副处级及以上事业编51岁至54岁人数
        int fcjUpYearsOld55overNum = Integer.parseInt(fcjUpSybAgeDistributionNum.get(5).get("num").toString());//副处级及以上事业编55岁及以上人数

        dataset.addValue(fcjUpYearsOld35BelowNum, "事业编", "35岁及以下");
        dataset.addValue(fcjUpYearsOld36to40Num, "事业编", "36岁至40岁");
        dataset.addValue(fcjUpYearsOld41to45Num, "事业编", "41岁至45岁");
        dataset.addValue(fcjUpYearsOld46to50Num, "事业编", "46岁至50岁");
        dataset.addValue(fcjUpYearsOld51to54Num, "事业编", "51岁至54岁");
        dataset.addValue(fcjUpYearsOld55overNum, "事业编", "55岁及以上");

        Font font = new Font("宋体", Font.BOLD, 12);
        StandardChartTheme standardChartTheme = new StandardChartTheme("CN"); // 创建主题样式
        standardChartTheme.setExtraLargeFont(font); // 设置标题字体
        standardChartTheme.setRegularFont(font); // 设置图例的字体
        standardChartTheme.setLargeFont(font); // 设置轴向的字体
        //standardChartTheme.setChartBackgroundPaint(Color.WHITE);// 设置主题背景色
        ChartFactory.setChartTheme(standardChartTheme);// 应用主题样式

        JFreeChartToFileUtil.createBarChart(dataset, file2, "年龄情况", "","数量（人）");
        ImageEntity image = new ImageEntity();
        image.setHeight(300);
        image.setWidth(600);
        System.out.println(file2.getAbsolutePath());
        image.setUrl(file2.getAbsolutePath());
        image.setType(ImageEntity.URL);
        dataMap.put("fcjUpSybAgeDistributionEchartBar", image);
    }


    /**
     * 副处级及以上行政编制和参公学历情况数量统计条形图
     * @param dataMap
     * @throws IOException
     */
    public void putFcjUpXzbcgEducationDistributionNumBar(Map<String, Object> dataMap) throws IOException {
        File file2 = File.createTempFile("temp", "png");
        //创建数据
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        //副处级及以上行政编制学历情况数量统计
        List<Map<String, Object>> selectFcjUpXzbEducationDistributionNum = userInfoStatisticsService.selectFcjUpXzbEducationDistributionNum();
        //副处级及以上参公学历情况数量统计
        List<Map<String, Object>> selectFcjUpCgEducationDistributionNum = userInfoStatisticsService.selectFcjUpCgEducationDistributionNum();

        int fcjupxzbDoctoralStudentNum = Integer.parseInt(selectFcjUpXzbEducationDistributionNum.get(0).get("num").toString());//副处级及以上行政编制博士研究生人数
        int fcjupcgDoctoralStudentNum = Integer.parseInt(selectFcjUpCgEducationDistributionNum.get(0).get("num").toString());//副处级及以上参公博士研究生人数
        int fcjupxzbPostgraduateNum = Integer.parseInt(selectFcjUpXzbEducationDistributionNum.get(1).get("num").toString());//副处级及以上行政编制硕士研究生人数
        int fcjupcgPostgraduateNum = Integer.parseInt(selectFcjUpCgEducationDistributionNum.get(1).get("num").toString());//副处级及以上参公硕士研究生人数
        int fcjupxzbGraduateStudentNum = Integer.parseInt(selectFcjUpXzbEducationDistributionNum.get(2).get("num").toString());//副处级及以上行政编制研究生人数
        int fcjupcgGraduateStudentNum = Integer.parseInt(selectFcjUpCgEducationDistributionNum.get(2).get("num").toString());//副处级及以上参公研究生人数
        int fcjupxzbUniversityNum = Integer.parseInt(selectFcjUpXzbEducationDistributionNum.get(3).get("num").toString());//副处级及以上行政编制大学人数
        int fcjupcgUniversityNum = Integer.parseInt(selectFcjUpCgEducationDistributionNum.get(3).get("num").toString());//副处级及以上参公大学人数
        int fcjupxzbJuniorCollegeNum = Integer.parseInt(selectFcjUpXzbEducationDistributionNum.get(4).get("num").toString());//副处级及以上行政编制大专人数
        int fcjupcgJuniorCollegeNum = Integer.parseInt(selectFcjUpCgEducationDistributionNum.get(4).get("num").toString());//副处级及以上参公大专人数
        int fcjupxzbHighSchoolNum = Integer.parseInt(selectFcjUpXzbEducationDistributionNum.get(5).get("num").toString());//副处级及以上行政编制高中人数
        int fcjupcgHighSchoolNum = Integer.parseInt(selectFcjUpCgEducationDistributionNum.get(5).get("num").toString());//副处级及以上参公高中人数

        dataset.addValue(fcjupxzbDoctoralStudentNum, "行政编制", "博士研究生");
        dataset.addValue(fcjupcgDoctoralStudentNum, "参公", "博士研究生");
        dataset.addValue(fcjupxzbPostgraduateNum, "行政编制", "硕士研究生");
        dataset.addValue(fcjupcgPostgraduateNum, "参公", "硕士研究生");
        dataset.addValue(fcjupxzbGraduateStudentNum, "行政编制", "研究生");
        dataset.addValue(fcjupcgGraduateStudentNum, "参公", "研究生");
        dataset.addValue(fcjupxzbUniversityNum, "行政编制", "大学");
        dataset.addValue(fcjupcgUniversityNum, "参公", "大学");
        dataset.addValue(fcjupxzbJuniorCollegeNum, "行政编制", "大专");
        dataset.addValue(fcjupcgJuniorCollegeNum, "参公", "大专");
        dataset.addValue(fcjupxzbHighSchoolNum, "行政编制", "高中");
        dataset.addValue(fcjupcgHighSchoolNum, "参公", "高中");

        Font font = new Font("宋体", Font.BOLD, 12);
        StandardChartTheme standardChartTheme = new StandardChartTheme("CN"); // 创建主题样式
        standardChartTheme.setExtraLargeFont(font); // 设置标题字体
        standardChartTheme.setRegularFont(font); // 设置图例的字体
        standardChartTheme.setLargeFont(font); // 设置轴向的字体
        //standardChartTheme.setChartBackgroundPaint(Color.WHITE);// 设置主题背景色
        ChartFactory.setChartTheme(standardChartTheme);// 应用主题样式

        JFreeChartToFileUtil.createBarChart(dataset, file2, "学历情况", "","数量（人）");
        ImageEntity image = new ImageEntity();
        image.setHeight(300);
        image.setWidth(600);
        System.out.println(file2.getAbsolutePath());
        image.setUrl(file2.getAbsolutePath());
        image.setType(ImageEntity.URL);
        dataMap.put("fcjUpXzbcgEduDistributionEchartBar", image);
    }

    /**
     * 副处级及以上事业编学历情况数量统计条形图
     * @param dataMap
     * @throws IOException
     */
    public void putFcjUpSybEducationDistributionNumBar(Map<String, Object> dataMap) throws IOException {
        File file2 = File.createTempFile("temp", "png");
        //创建数据
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        //副处级及以上事业编学历情况数量统计
        List<Map<String, Object>> selectFcjUpSybEducationDistributionNum = userInfoStatisticsService.selectFcjUpSybEducationDistributionNum();
        int fcjupDoctoralStudentNum = Integer.parseInt(selectFcjUpSybEducationDistributionNum.get(0).get("num").toString());//副处级及以上事业编博士研究生人数
        int fcjupPostgraduateNum = Integer.parseInt(selectFcjUpSybEducationDistributionNum.get(1).get("num").toString());//副处级及以上事业编硕士研究生人数
        int fcjupGraduateStudentNum = Integer.parseInt(selectFcjUpSybEducationDistributionNum.get(2).get("num").toString());//副处级及以上事业编研究生人数
        int fcjupUniversityNum = Integer.parseInt(selectFcjUpSybEducationDistributionNum.get(3).get("num").toString());//副处级及以上事业编大学人数
        int fcjupJuniorCollegeNum = Integer.parseInt(selectFcjUpSybEducationDistributionNum.get(4).get("num").toString());//副处级及以上事业编大专人数
        int fcjupHighSchoolNum = Integer.parseInt(selectFcjUpSybEducationDistributionNum.get(5).get("num").toString());//副处级及以上事业编高中人数

        dataset.addValue(fcjupDoctoralStudentNum, "事业编", "博士研究生");
        dataset.addValue(fcjupPostgraduateNum, "事业编", "硕士研究生");
        dataset.addValue(fcjupGraduateStudentNum, "事业编", "研究生");
        dataset.addValue(fcjupUniversityNum, "事业编", "大学");
        dataset.addValue(fcjupJuniorCollegeNum, "事业编", "大专");
        dataset.addValue(fcjupHighSchoolNum, "事业编", "高中");

        Font font = new Font("宋体", Font.BOLD, 12);
        StandardChartTheme standardChartTheme = new StandardChartTheme("CN"); // 创建主题样式
        standardChartTheme.setExtraLargeFont(font); // 设置标题字体
        standardChartTheme.setRegularFont(font); // 设置图例的字体
        standardChartTheme.setLargeFont(font); // 设置轴向的字体
        //standardChartTheme.setChartBackgroundPaint(Color.WHITE);// 设置主题背景色
        ChartFactory.setChartTheme(standardChartTheme);// 应用主题样式

        JFreeChartToFileUtil.createBarChart(dataset, file2, "学历情况", "","数量（人）");
        ImageEntity image = new ImageEntity();
        image.setHeight(300);
        image.setWidth(600);
        System.out.println(file2.getAbsolutePath());
        image.setUrl(file2.getAbsolutePath());
        image.setType(ImageEntity.URL);
        dataMap.put("fcjUpSybEduDistributionEchartBar", image);
    }




}
