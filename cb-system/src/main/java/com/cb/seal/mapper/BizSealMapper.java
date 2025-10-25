package com.cb.seal.mapper;

import com.cb.seal.domain.BizSeal;

import java.util.List;

/**
 * 公章信息Mapper接口
 * 
 * @author yangxin
 * @date 2023-12-04
 */
public interface BizSealMapper 
{
    /**
     * 查询公章信息
     * 
     * @param id 公章信息ID
     * @return 公章信息
     */
    public BizSeal selectBizSealById(Long id);

    /**
     * 查询公章信息列表
     * 
     * @param bizSeal 公章信息
     * @return 公章信息集合
     */
    public List<BizSeal> selectBizSealList(BizSeal bizSeal);

    /**
     * 新增公章信息
     * 
     * @param bizSeal 公章信息
     * @return 结果
     */
    public int insertBizSeal(BizSeal bizSeal);

    /**
     * 修改公章信息
     * 
     * @param bizSeal 公章信息
     * @return 结果
     */
    public int updateBizSeal(BizSeal bizSeal);

    /**
     * 删除公章信息
     * 
     * @param id 公章信息ID
     * @return 结果
     */
    public int deleteBizSealById(Long id);

    /**
     * 批量删除公章信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizSealByIds(Long[] ids);
}
