package com.cb.filemanage.service;

import java.util.List;
import com.cb.filemanage.domain.BizAttach;

/**
 * 业务文件Service接口
 * 
 * @author ruoyi
 * @date 2023-05-19
 */
public interface IBizAttachService 
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
    public List<BizAttach> selectVFolderOrFileListByIds(String attachIds);

    /**
     * 新增业务文件
     * 
     * @param bizAttach 业务文件
     * @return 结果
     */
    public int insertBizAttach(BizAttach bizAttach);


    public int saveAttach(BizAttach bizAttach);

    /**
     * @Auther: yangcd
     * @Date: 2023/11/8 17:05
     * @param
     * @Description: 素质档案使用
     */
    public int addAttach(BizAttach bizAttach);

    /**
     * 修改业务文件
     * 
     * @param bizAttach 业务文件
     * @return 结果
     */
    public int updateBizAttach(BizAttach bizAttach);

    public int updateBizAttachByAttachId(BizAttach bizAttach);

    /**
     * 批量删除业务文件
     * 
     * @param ids 需要删除的业务文件ID
     * @return 结果
     */
    public int deleteBizAttachByIds(Long[] ids);

    /**
     * 删除业务文件信息
     * 
     * @param id 业务文件ID
     * @return 结果
     */
    public int deleteBizAttachById(Long id);



    public int logicDeleteBizAttachByAttachId(String attachId,int delFlag);
}
