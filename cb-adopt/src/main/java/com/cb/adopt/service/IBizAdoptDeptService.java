package com.cb.adopt.service;

import java.util.List;
import com.cb.adopt.domain.BizAdoptDept;

/**
 * 报送单位信息Service接口
 * 
 * @author ruoyi
 * @date 2025-06-25
 */
public interface IBizAdoptDeptService 
{
    /**
     * 查询报送单位信息
     * 
     * @param id 报送单位信息ID
     * @return 报送单位信息
     */
    public BizAdoptDept selectBizAdoptDeptById(Integer id);

    /**
     * 查询报送单位信息列表
     * 
     * @param bizAdoptDept 报送单位信息
     * @return 报送单位信息集合
     */
    public List<BizAdoptDept> selectBizAdoptDeptList(BizAdoptDept bizAdoptDept);

    /**
     * 新增报送单位信息
     * 
     * @param bizAdoptDept 报送单位信息
     * @return 结果
     */
    public int insertBizAdoptDept(BizAdoptDept bizAdoptDept);

    /**
     * 批量新增报送单位信息
     * @param entities
     * @return
     */
    public int insertBatch(List<BizAdoptDept> entities);

    /**
     * 修改报送单位信息
     * 
     * @param bizAdoptDept 报送单位信息
     * @return 结果
     */
    public int updateBizAdoptDept(BizAdoptDept bizAdoptDept);

    /**
     * 批量删除报送单位信息
     * 
     * @param ids 需要删除的报送单位信息ID
     * @return 结果
     */
    public int deleteBizAdoptDeptByIds(Integer[] ids);

    /**
     * 删除报送单位信息信息
     * 
     * @param id 报送单位信息ID
     * @return 结果
     */
    public int deleteBizAdoptDeptById(Integer id);
}
