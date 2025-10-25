package com.cb.system.mapper;

import com.cb.common.core.domain.entity.RsArchInfo;

import java.util.List;
import java.util.Map;

/**
 * 干部档案目录Mapper接口
 * 
 * @author ruoyi
 * @date 2025-02-26
 */
public interface RsArchInfoMapper 
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
     * 删除干部档案目录
     * 
     * @param id 干部档案目录ID
     * @return 结果
     */
    public int deleteRsArchInfoById(Integer id);

    /**
     * 批量删除干部档案目录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRsArchInfoByIds(Integer[] ids);

    /**
     * 查询档案目录信息
     * @param rsArchInfo
     * @return
     */
    public List<RsArchInfo> selectArchInfoCatalogList(RsArchInfo rsArchInfo);

    /**
     * 导出档案目录数据
     * @param params
     * @return
     */
    List<RsArchInfo> exportArchivesCatalogueOfMaterialsData(Map<String, Object> params);
}
