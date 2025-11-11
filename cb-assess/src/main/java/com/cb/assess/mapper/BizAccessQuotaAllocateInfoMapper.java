package com.cb.assess.mapper;

import com.cb.assess.domain.BizAccessQuotaAllocateInfo;

import java.util.List;

/**
 * 年度机关事业单位考核人数及优秀等次名额分配信息Mapper接口
 * 
 * @author yangxin
 * @date 2023-12-09
 */
public interface BizAccessQuotaAllocateInfoMapper 
{
    /**
     * 查询年度机关事业单位考核人数及优秀等次名额分配信息
     * 
     * @param id 年度机关事业单位考核人数及优秀等次名额分配信息ID
     * @return 年度机关事业单位考核人数及优秀等次名额分配信息
     */
    public BizAccessQuotaAllocateInfo selectBizAccessQuotaAllocateInfoById(String id);

    /**
     * 查询年度机关事业单位考核人数及优秀等次名额分配信息列表
     * 
     * @param bizAccessQuotaAllocateInfo 年度机关事业单位考核人数及优秀等次名额分配信息
     * @return 年度机关事业单位考核人数及优秀等次名额分配信息集合
     */
    public List<BizAccessQuotaAllocateInfo> selectBizAccessQuotaAllocateInfoList(BizAccessQuotaAllocateInfo bizAccessQuotaAllocateInfo);

    /**
     * 新增年度机关事业单位考核人数及优秀等次名额分配信息
     * 
     * @param bizAccessQuotaAllocateInfo 年度机关事业单位考核人数及优秀等次名额分配信息
     * @return 结果
     */
    public int insertBizAccessQuotaAllocateInfo(BizAccessQuotaAllocateInfo bizAccessQuotaAllocateInfo);

    /**
     * 修改年度机关事业单位考核人数及优秀等次名额分配信息
     * 
     * @param bizAccessQuotaAllocateInfo 年度机关事业单位考核人数及优秀等次名额分配信息
     * @return 结果
     */
    public int updateBizAccessQuotaAllocateInfo(BizAccessQuotaAllocateInfo bizAccessQuotaAllocateInfo);

    /**
     * 删除年度机关事业单位考核人数及优秀等次名额分配信息
     * 
     * @param id 年度机关事业单位考核人数及优秀等次名额分配信息ID
     * @return 结果
     */
    public int deleteBizAccessQuotaAllocateInfoById(String id);

    /**
     * 批量删除年度机关事业单位考核人数及优秀等次名额分配信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizAccessQuotaAllocateInfoByIds(String[] ids);
}
