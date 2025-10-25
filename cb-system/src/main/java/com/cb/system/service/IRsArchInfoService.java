package com.cb.system.service;

import java.util.List;
import com.cb.common.core.domain.entity.RsArchInfo;

/**
 * 干部档案目录Service接口
 * 
 * @author ruoyi
 * @date 2025-02-26
 */
public interface IRsArchInfoService 
{
    /**
     * 查询干部档案目录
     * 
     * @param id 干部档案目录ID
     * @return 干部档案目录
     */
    public RsArchInfo selectRsArchInfoById(Integer id);

    /**
     * 查询干部档案目录列表
     * 
     * @param rsArchInfo 干部档案目录
     * @return 干部档案目录集合
     */
    public List<RsArchInfo> selectRsArchInfoList(RsArchInfo rsArchInfo);

    /**
     * 新增干部档案目录
     * 
     * @param rsArchInfo 干部档案目录
     * @return 结果
     */
    public int insertRsArchInfo(RsArchInfo rsArchInfo);

    /**
     * 修改干部档案目录
     * 
     * @param rsArchInfo 干部档案目录
     * @return 结果
     */
    public int updateRsArchInfo(RsArchInfo rsArchInfo);

    /**
     * 批量删除干部档案目录
     * 
     * @param ids 需要删除的干部档案目录ID
     * @return 结果
     */
    public int deleteRsArchInfoByIds(Integer[] ids);

    /**
     * 删除干部档案目录信息
     * 
     * @param id 干部档案目录ID
     * @return 结果
     */
    public int deleteRsArchInfoById(Integer id);

    /**
     * 干部档案目录导入
     * @param rsArchInfoList
     * @param operName
     * @return
     */
    public  String importRsArchInfo(List<RsArchInfo> rsArchInfoList, String operName);

    /**
     * 查询档案目录信息
     * @param rsArchInfo
     * @return
     */
    List<RsArchInfo> selectArchInfoCatalogList(RsArchInfo rsArchInfo);
}
