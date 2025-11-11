package com.cb.assess.mapper;

import com.cb.assess.domain.BizAssessQuotaAllocation;
import com.cb.assess.domain.vo.BizAssessQuotaAllocationVo;

import java.util.List;


/**
 * xxxx年度机关事业单位考核人数及优秀等次名额分配Mapper接口
 * 
 * @author ruoyi
 * @date 2023-11-16
 */
public interface BizAssessQuotaAllocationMapper 
{
    /**
     * 查询xxxx年度机关事业单位考核人数及优秀等次名额分配
     * 
     * @param id xxxx年度机关事业单位考核人数及优秀等次名额分配ID
     * @return xxxx年度机关事业单位考核人数及优秀等次名额分配
     */
    public BizAssessQuotaAllocation selectBizAssessQuotaAllocationById(String id);

    /**
     * 查询xxxx年度机关事业单位考核人数及优秀等次名额分配列表
     * 
     * @param bizAssessQuotaAllocation xxxx年度机关事业单位考核人数及优秀等次名额分配
     * @return xxxx年度机关事业单位考核人数及优秀等次名额分配集合
     */
    public List<BizAssessQuotaAllocationVo> selectBizAssessQuotaAllocationList(BizAssessQuotaAllocationVo bizAssessQuotaAllocation);

    /**
     * 新增xxxx年度机关事业单位考核人数及优秀等次名额分配
     * 
     * @param bizAssessQuotaAllocation xxxx年度机关事业单位考核人数及优秀等次名额分配
     * @return 结果
     */
    public int insertBizAssessQuotaAllocation(BizAssessQuotaAllocation bizAssessQuotaAllocation);

    /**
     * 修改xxxx年度机关事业单位考核人数及优秀等次名额分配
     * 
     * @param bizAssessQuotaAllocation xxxx年度机关事业单位考核人数及优秀等次名额分配
     * @return 结果
     */
    public int updateBizAssessQuotaAllocation(BizAssessQuotaAllocation bizAssessQuotaAllocation);

    /**
     * 删除xxxx年度机关事业单位考核人数及优秀等次名额分配
     * 
     * @param id xxxx年度机关事业单位考核人数及优秀等次名额分配ID
     * @return 结果
     */
    public int deleteBizAssessQuotaAllocationById(String id);

    /**
     * 批量删除xxxx年度机关事业单位考核人数及优秀等次名额分配
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizAssessQuotaAllocationByIds(String[] ids);
}
