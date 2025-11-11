package com.cb.assess.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.cb.assess.domain.vo.BizAssessAnnualAssessmentVo;
import com.cb.assess.domain.vo.RegularAssessmentVo;
import com.cb.assess.enums.Grade;
import com.cb.assess.service.BizAssessAnnualReviewStatisticsService;
import com.cb.assess.utils.PoiTlUtil;
import com.cb.common.core.domain.entity.SysDictData;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.uuid.UUID;
import com.cb.system.service.ISysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import com.cb.assess.mapper.BizAssessAnnualAssessmentMapper;
import com.cb.assess.domain.BizAssessAnnualAssessment;
import com.cb.assess.service.IBizAssessAnnualAssessmentService;

import javax.annotation.Resource;

/**
 * 年度考核Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-11-17
 */
@Service
public class BizAssessAnnualAssessmentServiceImpl implements IBizAssessAnnualAssessmentService 
{
    @Autowired
    private BizAssessAnnualAssessmentMapper bizAssessAnnualAssessmentMapper;

    @Resource
    private ISysDictTypeService sysDictTypeService;
    @Autowired
    private BizAssessAnnualReviewStatisticsService bizAssessAnnualReviewStatisticsService;
    /**
     * 查询年度考核
     * 
     * @param id 年度考核ID
     * @return 年度考核
     */
    @Override
    public BizAssessAnnualAssessmentVo selectBizAssessAnnualAssessmentById(String id)
    {
        return bizAssessAnnualAssessmentMapper.selectBizAssessAnnualAssessmentById(id);
    }
    @Override
    public void exportOne(String id, OutputStream outputStream) throws IOException {
        BizAssessAnnualAssessmentVo vo = selectBizAssessAnnualAssessmentById(id);
        Map<String, Object> map = BeanUtil.beanToMap(vo);

        // 处理成模板需要的时间格式
        map.entrySet().stream().forEach(entry -> {
            String key = entry.getKey();
            Object value = entry.getValue();
            if(value != null && value.getClass() ==  Date.class ){
                String format = DateUtil.format((Date) value, "yyyy年MM月dd日");
                map.put(key,format);
            }

           if(value != null && value.getClass() ==  Date.class ){
                   // 两特殊的单独处理
                if(key.equals("evaluationLevelTime")) {
                    Date date = DateUtil.parse(value.toString());
                    String format = DateUtil.format(date, "yyyy年MM月dd日");
                    map.put(key,format);
                }
                // 两特殊的单独处理
                if(key.equals("organOpinionsTime")) {
                    Date date = DateUtil.parse(value.toString());
                    String format = DateUtil.format(date, "yyyy年MM月dd日");
                    map.put(key,format);
                }
           }
        });
        InputStream is = null;
        String identityType = vo.getEstablishType();
        List<SysDictData> sysUserSexList = sysDictTypeService.selectDictDataByType("sys_user_sex");
        List<SysDictData> workTitleGwyList = sysDictTypeService.selectDictDataByType("work_title_gwy");
        List<SysDictData> workTitleSybList = sysDictTypeService.selectDictDataByType("work_title_syb");

        Map<String, String> sysUserSexMap = sysUserSexList.stream().collect(Collectors.toMap(SysDictData::getDictValue, SysDictData::getDictLabel));
        Map<String, String> workTitleGwyMap = workTitleGwyList.stream().collect(Collectors.toMap(SysDictData::getDictValue, SysDictData::getDictLabel));
        Map<String, String> workTitleSybMap = workTitleSybList.stream().collect(Collectors.toMap(SysDictData::getDictValue, SysDictData::getDictLabel));
        //区分公务员和事业单位
        if ("1".equals(identityType) || "2".equals(identityType)) {
            ClassPathResource classPathResource = new ClassPathResource("template/AnnualAssessmentOfCivilServants.docx");
            is = classPathResource.getInputStream();
            String s = workTitleGwyMap.get(vo.getWorkTitle());
            map.put("workTitle",s);
        } else if ("3".equals(identityType)) {
            ClassPathResource classPathResource = new ClassPathResource("template/AnnualAssessmentOfBusiness.docx");
            is = classPathResource.getInputStream();
            String s = workTitleSybMap.get(vo.getWorkTitle());
            map.put("workTitle",s);
        }
        List<String> list = new ArrayList<>();
        list.add("personalSummary");
        map.put("sex",sysUserSexMap.get(vo.getSex()));
        /*年度平时考核情况：“好”{{good}}次，“较好” {{preferably}} 次，“一般”{{ordinary}}  次，“较差”{{range}} 次。*/
        Long userId = vo.getUserId();
        String year = vo.getYear();
        List<RegularAssessmentVo> regularVos = bizAssessAnnualReviewStatisticsService.regularAssessmentListById(userId, year);
        long good = regularVos.stream().filter(o -> o.getGrade().equals(Grade.EXCELLENT.getDesc())).count();
        long preferably = regularVos.stream().filter(o -> o.getGrade().equals(Grade.BETTER.getDesc())).count();
        long ordinary = regularVos.stream().filter(o -> o.getGrade().equals(Grade.GENERAL.getDesc())).count();
        long range = regularVos.stream().filter(o -> o.getGrade().equals(Grade.INFERIOR.getDesc())).count();
        map.put("good",good);
        map.put("preferably",preferably);
        map.put("ordinary",ordinary);
        map.put("range",range);

        PoiTlUtil.export2Word(is, outputStream, map, list, true);
        outputStream.flush();
    }

    /**
     * 查询年度考核列表
     * 
     * @param bizAssessAnnualAssessment 年度考核
     * @return 年度考核
     */
    @Override
    public List<BizAssessAnnualAssessmentVo> selectBizAssessAnnualAssessmentList(BizAssessAnnualAssessmentVo bizAssessAnnualAssessment)
    {
        return bizAssessAnnualAssessmentMapper.selectBizAssessAnnualAssessmentList(bizAssessAnnualAssessment);
    }
    @Override
    public List<BizAssessAnnualAssessmentVo> selectYears(){
         return bizAssessAnnualAssessmentMapper.selectYears();
    }
    /**
     * 新增年度考核
     * 
     * @param bizAssessAnnualAssessment 年度考核
     * @return 结果
     */
    @Override
    public int insertBizAssessAnnualAssessment(BizAssessAnnualAssessment bizAssessAnnualAssessment)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        BizAssessAnnualAssessmentVo bizAssessAnnualAssessmentVo = new BizAssessAnnualAssessmentVo();
        bizAssessAnnualAssessmentVo.setYear(bizAssessAnnualAssessment.getYear());
        bizAssessAnnualAssessmentVo.setUserId(user.getUserId());
        List<BizAssessAnnualAssessmentVo> bizAssessAnnualAssessmentVos = bizAssessAnnualAssessmentMapper.selectBizAssessAnnualAssessmentList(bizAssessAnnualAssessmentVo);
        if (bizAssessAnnualAssessmentVos.isEmpty()){
            bizAssessAnnualAssessment.setId(UUID.randomUUID().toString());
            bizAssessAnnualAssessment.setCreateBy(SecurityUtils.getUsername());
            bizAssessAnnualAssessment.setUserId(user.getUserId());
            bizAssessAnnualAssessment.setCreateTime(DateUtils.getNowDate());
            bizAssessAnnualAssessment.setMySummaryTime(DateUtils.getNowDate());
            //职务和职级
            bizAssessAnnualAssessment.setPost(user.getWorkPost());
            bizAssessAnnualAssessment.setWorkTitle(user.getWorkTitle());
            bizAssessAnnualAssessment.setMySummaryTime(DateUtils.getNowDate());
            return bizAssessAnnualAssessmentMapper.insertBizAssessAnnualAssessment(bizAssessAnnualAssessment);
        }
        return 0;
    }

    /**
     * 修改年度考核
     * 
     * @param bizAssessAnnualAssessment 年度考核
     * @return 结果
     */
    @Override
    public int updateBizAssessAnnualAssessment(BizAssessAnnualAssessment bizAssessAnnualAssessment)
    {
        bizAssessAnnualAssessment.setUpdateTime(DateUtils.getNowDate());
        bizAssessAnnualAssessment.setUpdateBy(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        return bizAssessAnnualAssessmentMapper.updateBizAssessAnnualAssessment(bizAssessAnnualAssessment);
    }

    /**
     * 批量删除年度考核
     * 
     * @param ids 需要删除的年度考核ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessAnnualAssessmentByIds(String[] ids)
    {
        return bizAssessAnnualAssessmentMapper.deleteBizAssessAnnualAssessmentByIds(ids);
    }

    /**
     * 删除年度考核信息
     * 
     * @param id 年度考核ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessAnnualAssessmentById(String id)
    {
        return bizAssessAnnualAssessmentMapper.deleteBizAssessAnnualAssessmentById(id);
    }
}
