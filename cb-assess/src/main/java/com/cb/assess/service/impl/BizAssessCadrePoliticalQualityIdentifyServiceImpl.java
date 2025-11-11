package com.cb.assess.service.impl;

import java.util.Date;
import java.util.List;

import com.cb.assess.domain.BizAssessCadrePoliticalQualityIdentify;
import com.cb.assess.domain.vo.BizAssessCadrePoliticalQualityIdentifyVo;
import com.cb.assess.mapper.BizAssessCadrePoliticalQualityIdentifyMapper;
import com.cb.assess.service.IBizAssessCadrePoliticalQualityIdentifyService;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 政治素质鉴定Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-12-22
 */
@Service
public class BizAssessCadrePoliticalQualityIdentifyServiceImpl implements IBizAssessCadrePoliticalQualityIdentifyService
{
    @Autowired
    private BizAssessCadrePoliticalQualityIdentifyMapper bizAssessCadrePoliticalQualityIdentifyMapper;

    /**
     * 查询政治素质鉴定
     * 
     * @param id 政治素质鉴定ID
     * @return 政治素质鉴定
     */
    @Override
    public BizAssessCadrePoliticalQualityIdentifyVo selectBizAssessCadrePoliticalQualityIdentifyById(String id)
    {
        return bizAssessCadrePoliticalQualityIdentifyMapper.selectBizAssessCadrePoliticalQualityIdentifyById(id);
    }
    @Override
    public BizAssessCadrePoliticalQualityIdentify selectStaticsByUser(String userId, String assessmentYear)
    {
        return bizAssessCadrePoliticalQualityIdentifyMapper.selectStaticsByUser(userId,assessmentYear);
    }

    /**
     * 查询政治素质鉴定列表
     * 
     * @param bizAssessCadrePoliticalQualityIdentify 政治素质鉴定
     * @return 政治素质鉴定
     */
    @Override
    public List<BizAssessCadrePoliticalQualityIdentifyVo> selectBizAssessCadrePoliticalQualityIdentifyList(BizAssessCadrePoliticalQualityIdentifyVo bizAssessCadrePoliticalQualityIdentify)
    {
        return bizAssessCadrePoliticalQualityIdentifyMapper.selectBizAssessCadrePoliticalQualityIdentifyList(bizAssessCadrePoliticalQualityIdentify);
    }
    @Override
    public List<BizAssessCadrePoliticalQualityIdentifyVo> listByManagerUser(BizAssessCadrePoliticalQualityIdentifyVo bizAssessCadrePoliticalQualityIdentify)
    {
        return bizAssessCadrePoliticalQualityIdentifyMapper.listByManagerUser(bizAssessCadrePoliticalQualityIdentify);
    }

    /**
     * 查询数据中的所有年，供前端做筛选
     * @return
     */
    @Override
    public List<BizAssessCadrePoliticalQualityIdentifyVo> selectYears()
    {
        return bizAssessCadrePoliticalQualityIdentifyMapper.selectYears();
    }

    /**
     * 新增政治素质鉴定
     * 
     * @param bizAssessCadrePoliticalQualityIdentify 政治素质鉴定
     * @return 结果
     */
    @Override
    public int insertBizAssessCadrePoliticalQualityIdentify(BizAssessCadrePoliticalQualityIdentify bizAssessCadrePoliticalQualityIdentify)
    {
        bizAssessCadrePoliticalQualityIdentify.setId(UUID.randomUUID().toString());
        bizAssessCadrePoliticalQualityIdentify.setCreateBy(SecurityUtils.getUsername());
        bizAssessCadrePoliticalQualityIdentify.setCreateTime(new Date());
        return bizAssessCadrePoliticalQualityIdentifyMapper.insertBizAssessCadrePoliticalQualityIdentify(bizAssessCadrePoliticalQualityIdentify);
    }

    /**
     * 修改政治素质鉴定
     * 
     * @param bizAssessCadrePoliticalQualityIdentify 政治素质鉴定
     * @return 结果
     */
    @Override
    public int updateBizAssessCadrePoliticalQualityIdentify(BizAssessCadrePoliticalQualityIdentify bizAssessCadrePoliticalQualityIdentify)
    {
        bizAssessCadrePoliticalQualityIdentify.setUpdateTime(DateUtils.getNowDate());
        bizAssessCadrePoliticalQualityIdentify.setUpdateBy(SecurityUtils.getUsername());
        return bizAssessCadrePoliticalQualityIdentifyMapper.updateBizAssessCadrePoliticalQualityIdentify(bizAssessCadrePoliticalQualityIdentify);
    }
    @Override
    public int updateSetIsReport(BizAssessCadrePoliticalQualityIdentify bizAssessCadrePoliticalQualityIdentify)
    {
        if (StringUtils.isBlank(bizAssessCadrePoliticalQualityIdentify.getAssessmentYear())
                ||StringUtils.isBlank(bizAssessCadrePoliticalQualityIdentify.getIsReport())
        ||null==bizAssessCadrePoliticalQualityIdentify.getDeptId()){
            return 0;
        }
        bizAssessCadrePoliticalQualityIdentify.setUpdateTime(DateUtils.getNowDate());
        bizAssessCadrePoliticalQualityIdentify.setUpdateBy(SecurityUtils.getUsername());
        return bizAssessCadrePoliticalQualityIdentifyMapper.updateSetIsReport(bizAssessCadrePoliticalQualityIdentify);
    }

    /**
     * 批量删除政治素质鉴定
     * 
     * @param ids 需要删除的政治素质鉴定ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessCadrePoliticalQualityIdentifyByIds(String[] ids)
    {
        return bizAssessCadrePoliticalQualityIdentifyMapper.deleteBizAssessCadrePoliticalQualityIdentifyByIds(ids);
    }

    /**
     * 删除政治素质鉴定信息
     * 
     * @param id 政治素质鉴定ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessCadrePoliticalQualityIdentifyById(String id)
    {
        return bizAssessCadrePoliticalQualityIdentifyMapper.deleteBizAssessCadrePoliticalQualityIdentifyById(id);
    }
}
