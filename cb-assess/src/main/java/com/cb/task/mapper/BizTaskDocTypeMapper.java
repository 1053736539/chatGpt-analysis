package com.cb.task.mapper;

import com.cb.task.domain.BizTaskDocType;

import java.util.List;

/**
 * 任务公文类型Mapper接口
 * 
 * @author yangxin
 * @date 2023-11-11
 */
public interface BizTaskDocTypeMapper 
{
    /**
     * 查询任务公文类型
     * 
     * @param id 任务公文类型ID
     * @return 任务公文类型
     */
    public BizTaskDocType selectBizTaskDocTypeById(Long id);

    public BizTaskDocType selectBizTaskDocTypeByCode(String code);

    /**
     * 查询任务公文类型列表
     * 
     * @param bizTaskDocType 任务公文类型
     * @return 任务公文类型集合
     */
    public List<BizTaskDocType> selectBizTaskDocTypeList(BizTaskDocType bizTaskDocType);

    /**
     * 获取我可见的分类列表
     * @param bizTaskDocType
     * @return
     */
    public List<BizTaskDocType> listMy(BizTaskDocType bizTaskDocType);

    /**
     * 新增任务公文类型
     * 
     * @param bizTaskDocType 任务公文类型
     * @return 结果
     */
    public int insertBizTaskDocType(BizTaskDocType bizTaskDocType);

    /**
     * 修改任务公文类型
     * 
     * @param bizTaskDocType 任务公文类型
     * @return 结果
     */
    public int updateBizTaskDocType(BizTaskDocType bizTaskDocType);

    /**
     * 删除任务公文类型
     * 
     * @param id 任务公文类型ID
     * @return 结果
     */
    public int deleteBizTaskDocTypeById(Long id);

    /**
     * 批量删除任务公文类型
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizTaskDocTypeByIds(Long[] ids);

    /**
     * 虚拟删除
     * @param ids
     * @return
     */
    public int vDeleteByIds(Long[] ids);
}
