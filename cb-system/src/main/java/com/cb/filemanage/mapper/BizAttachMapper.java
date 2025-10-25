package com.cb.filemanage.mapper;

import java.util.List;
import com.cb.filemanage.domain.BizAttach;
import org.apache.ibatis.annotations.Param;

/**
 * 业务文件Mapper接口
 * 
 * @author ruoyi
 * @date 2023-05-19
 */
public interface BizAttachMapper 
{
    /**
     * 查询业务文件
     * 
     * @param id 业务文件ID
     * @return 业务文件
     */
    public BizAttach selectBizAttachById(Long id);

    public BizAttach selectBizAttachByAttachId(String attachId);

    /**
     * 查询业务文件列表
     * 
     * @param bizAttach 业务文件
     * @return 业务文件集合
     */
    public List<BizAttach> selectBizAttachList(BizAttach bizAttach);
    public List<BizAttach> selectBizAttachListByIds(@Param("attachIds") List<String> attachIds);

    /**
     * 新增业务文件
     * 
     * @param bizAttach 业务文件
     * @return 结果
     */
    public int insertBizAttach(BizAttach bizAttach);

    /**
     * 修改业务文件
     * 
     * @param bizAttach 业务文件
     * @return 结果
     */
    public int updateBizAttach(BizAttach bizAttach);


    public int updateBizAttachByAttachId(BizAttach bizAttach);

    /**
     * 删除业务文件
     * 
     * @param id 业务文件ID
     * @return 结果
     */
    public int deleteBizAttachById(Long id);

    /**
     * 批量删除业务文件
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizAttachByIds(Long[] ids);



    public int logicDeleteBizAttachByAttachId(@Param("attachId") String  attachId, @Param("delFlag")int delFlag);
}
