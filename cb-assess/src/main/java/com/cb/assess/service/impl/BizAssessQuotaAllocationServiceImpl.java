package com.cb.assess.service.impl;

import java.util.List;

import com.cb.assess.domain.BizAssessQuotaAllocation;
import com.cb.assess.domain.vo.BizAssessQuotaAllocationVo;
import com.cb.assess.mapper.BizAssessQuotaAllocationMapper;
import com.cb.assess.service.IBizAssessQuotaAllocationService;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.uuid.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * xxxx年度机关事业单位考核人数及优秀等次名额分配Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-11-16
 */
@Service
public class BizAssessQuotaAllocationServiceImpl implements IBizAssessQuotaAllocationService
{
    @Autowired
    private BizAssessQuotaAllocationMapper bizAssessQuotaAllocationMapper;

    /**
     * 查询xxxx年度机关事业单位考核人数及优秀等次名额分配
     * 
     * @param id xxxx年度机关事业单位考核人数及优秀等次名额分配ID
     * @return xxxx年度机关事业单位考核人数及优秀等次名额分配
     */
    @Override
    public BizAssessQuotaAllocation selectBizAssessQuotaAllocationById(String id)
    {
        return bizAssessQuotaAllocationMapper.selectBizAssessQuotaAllocationById(id);
    }

    /**
     * 查询xxxx年度机关事业单位考核人数及优秀等次名额分配列表
     * 
     * @param bizAssessQuotaAllocation xxxx年度机关事业单位考核人数及优秀等次名额分配
     * @return xxxx年度机关事业单位考核人数及优秀等次名额分配
     */
    @Override
    public List<BizAssessQuotaAllocationVo> selectBizAssessQuotaAllocationList(BizAssessQuotaAllocationVo bizAssessQuotaAllocation)
    {
        return bizAssessQuotaAllocationMapper.selectBizAssessQuotaAllocationList(bizAssessQuotaAllocation);
    }

    /**
     * 新增xxxx年度机关事业单位考核人数及优秀等次名额分配
     * 
     * @param bizAssessQuotaAllocation xxxx年度机关事业单位考核人数及优秀等次名额分配
     * @return 结果
     */
    @Override
    public int insertBizAssessQuotaAllocation(BizAssessQuotaAllocation bizAssessQuotaAllocation)
    {
        bizAssessQuotaAllocation.setId(UUID.randomUUID().toString());
        bizAssessQuotaAllocation.setCreateBy(SecurityUtils.getLoginUser().getUser().getUserName());
        bizAssessQuotaAllocation.setCreateTime(DateUtils.getNowDate());
        return bizAssessQuotaAllocationMapper.insertBizAssessQuotaAllocation(bizAssessQuotaAllocation);
    }

    /**
     * 修改xxxx年度机关事业单位考核人数及优秀等次名额分配
     * 
     * @param bizAssessQuotaAllocation xxxx年度机关事业单位考核人数及优秀等次名额分配
     * @return 结果
     */
    @Override
    public int updateBizAssessQuotaAllocation(BizAssessQuotaAllocation bizAssessQuotaAllocation)
    {
        bizAssessQuotaAllocation.setUpdateBy(SecurityUtils.getLoginUser().getUser().getUserName());
        bizAssessQuotaAllocation.setUpdateTime(DateUtils.getNowDate());
        return bizAssessQuotaAllocationMapper.updateBizAssessQuotaAllocation(bizAssessQuotaAllocation);
    }

    /**
     * 批量删除xxxx年度机关事业单位考核人数及优秀等次名额分配
     * 
     * @param ids 需要删除的xxxx年度机关事业单位考核人数及优秀等次名额分配ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessQuotaAllocationByIds(String[] ids)
    {
        return bizAssessQuotaAllocationMapper.deleteBizAssessQuotaAllocationByIds(ids);
    }

    /**
     * 删除xxxx年度机关事业单位考核人数及优秀等次名额分配信息
     * 
     * @param id xxxx年度机关事业单位考核人数及优秀等次名额分配ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessQuotaAllocationById(String id)
    {
        return bizAssessQuotaAllocationMapper.deleteBizAssessQuotaAllocationById(id);
    }
}
