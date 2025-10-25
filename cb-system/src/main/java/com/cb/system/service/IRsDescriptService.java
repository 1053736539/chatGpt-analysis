package com.cb.system.service;

import java.util.List;
import com.cb.common.core.domain.entity.RsDescript;

/**
 * 干部档案附件信息Service接口
 * 
 * @author ruoyi
 * @date 2025-02-25
 */
public interface IRsDescriptService 
{
    /**
     * 查询干部档案附件信息
     * 
     * @param id 干部档案附件信息ID
     * @return 干部档案附件信息
     */
    public RsDescript selectRsDescriptById(Integer id);

    /**
     * 查询干部档案附件信息列表
     * 
     * @param rsDescript 干部档案附件信息
     * @return 干部档案附件信息集合
     */
    public List<RsDescript> selectRsDescriptList(RsDescript rsDescript);

    /**
     * 新增干部档案附件信息
     * 
     * @param rsDescript 干部档案附件信息
     * @return 结果
     */
    public int insertRsDescript(RsDescript rsDescript);

    /**
     * 修改干部档案附件信息
     * 
     * @param rsDescript 干部档案附件信息
     * @return 结果
     */
    public int updateRsDescript(RsDescript rsDescript);

    /**
     * 批量删除干部档案附件信息
     * 
     * @param ids 需要删除的干部档案附件信息ID
     * @return 结果
     */
    public int deleteRsDescriptByIds(Integer[] ids);

    /**
     * 删除干部档案附件信息信息
     * 
     * @param id 干部档案附件信息ID
     * @return 结果
     */
    public int deleteRsDescriptById(Integer id);

    /**
     * 干部档案附件信息导入
     * @param rsDescriptList
     * @param operName
     * @return
     */
     public String importRsDescript(List<RsDescript> rsDescriptList, String operName);
}
