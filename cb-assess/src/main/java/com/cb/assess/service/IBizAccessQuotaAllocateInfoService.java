package com.cb.assess.service;

import com.cb.assess.domain.BizAccessQuotaAllocateInfo;
import com.cb.assess.vo.QuotaAllocateInfoVo;

import java.util.List;

/**
 * 年度机关事业单位考核人数及优秀等次名额分配信息Service接口
 * 
 * @author yangxin
 * @date 2023-12-09
 */
public interface IBizAccessQuotaAllocateInfoService 
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
     * 批量删除年度机关事业单位考核人数及优秀等次名额分配信息
     * 
     * @param ids 需要删除的年度机关事业单位考核人数及优秀等次名额分配信息ID
     * @return 结果
     */
    public int deleteBizAccessQuotaAllocateInfoByIds(String[] ids);

    /**
     * 删除年度机关事业单位考核人数及优秀等次名额分配信息信息
     * 
     * @param id 年度机关事业单位考核人数及优秀等次名额分配信息ID
     * @return 结果
     */
    public int deleteBizAccessQuotaAllocateInfoById(String id);

    /**
     * 获取默认表格信息
     * @return
     */
    public List<QuotaAllocateInfoVo.TableItemInfo> getDefaultTable();

    /**
     * 获取详情信息
     * @param id
     * @return
     */
    public BizAccessQuotaAllocateInfo getDetailInfo(String id);

    /**
     * 审核
     * @param info
     */
    public void audit(BizAccessQuotaAllocateInfo info);

    /**
     * 重新提交审核
     * @param id
     */
    public void reSubmit(String id);

    /**
     * 获取指定部门指定年度的优秀名额
     * @param deptId
     * @param year
     * @return
     */
    QuotaAllocateInfoVo.TableItemInfo getDeptAllocateInfo(Long deptId,String year);

    QuotaAllocateInfoVo.TableItemInfo getNoDeptAllocateInfo(String year, String type);

    /**
     * 发布到公告
     * @param id
     */
    void publish2Notice(String id);

    /**
     * 撤销公告
     * @param id
     */
    void revokeNotice(String id);

}
