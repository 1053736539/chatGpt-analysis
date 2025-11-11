package com.cb.probity.service;

import java.util.List;

import com.cb.probity.domain.BizProbityCertificate;

/**
 * 廉政档案-6.1本人持有护照、往来港澳台证件情况Service接口
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public interface IBizProbityCertificateService {
    /**
     * 查询廉政档案-6.1本人持有护照、往来港澳台证件情况
     *
     * @param id 廉政档案-6.1本人持有护照、往来港澳台证件情况ID
     * @return 廉政档案-6.1本人持有护照、往来港澳台证件情况
     */
    public BizProbityCertificate selectBizProbityCertificateById(String id);

    /**
     * 查询廉政档案-6.1本人持有护照、往来港澳台证件情况列表
     *
     * @param bizProbityCertificate 廉政档案-6.1本人持有护照、往来港澳台证件情况
     * @return 廉政档案-6.1本人持有护照、往来港澳台证件情况集合
     */
    public List<BizProbityCertificate> selectBizProbityCertificateList(BizProbityCertificate bizProbityCertificate);

    /**
     * 新增廉政档案-6.1本人持有护照、往来港澳台证件情况
     *
     * @param bizProbityCertificate 廉政档案-6.1本人持有护照、往来港澳台证件情况
     * @return 结果
     */
    public int insertBizProbityCertificate(BizProbityCertificate bizProbityCertificate);

    /**
     * 批量新增廉政档案-6.1本人持有护照、往来港澳台证件情况
     *
     * @param entities
     * @return
     */
    public int insertBatch(List<BizProbityCertificate> entities);

    /**
     * 修改廉政档案-6.1本人持有护照、往来港澳台证件情况
     *
     * @param bizProbityCertificate 廉政档案-6.1本人持有护照、往来港澳台证件情况
     * @return 结果
     */
    public int updateBizProbityCertificate(BizProbityCertificate bizProbityCertificate);

    /**
     * 批量删除廉政档案-6.1本人持有护照、往来港澳台证件情况
     *
     * @param ids 需要删除的廉政档案-6.1本人持有护照、往来港澳台证件情况ID
     * @return 结果
     */
    public int deleteBizProbityCertificateByIds(String[] ids);

    /**
     * 删除廉政档案-6.1本人持有护照、往来港澳台证件情况信息
     *
     * @param id 廉政档案-6.1本人持有护照、往来港澳台证件情况ID
     * @return 结果
     */
    public int deleteBizProbityCertificateById(String id);
}
