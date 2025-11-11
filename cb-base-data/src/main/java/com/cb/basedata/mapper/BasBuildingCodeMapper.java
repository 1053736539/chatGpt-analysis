package com.cb.basedata.mapper;

import java.util.List;
import com.cb.basedata.domain.BasBuildingCode;

/**
 * 楼盘编码Mapper接口
 * 
 * @author ouyan
 * @date 2024-10-25
 */
public interface BasBuildingCodeMapper 
{
    /**
     * 查询楼盘编码
     * 
     * @param id 楼盘编码ID
     * @return 楼盘编码
     */
    public BasBuildingCode selectBasBuildingCodeById(String id);

    /**
     * 查询楼盘编码列表
     * 
     * @param basBuildingCode 楼盘编码
     * @return 楼盘编码集合
     */
    public List<BasBuildingCode> selectBasBuildingCodeList(BasBuildingCode basBuildingCode);


    public List<BasBuildingCode> selectBasBuildingCodeAllList();

    /**
     * 新增楼盘编码
     * 
     * @param basBuildingCode 楼盘编码
     * @return 结果
     */
    public int insertBasBuildingCode(BasBuildingCode basBuildingCode);

    /**
     * 修改楼盘编码
     * 
     * @param basBuildingCode 楼盘编码
     * @return 结果
     */
    public int updateBasBuildingCode(BasBuildingCode basBuildingCode);

    /**
     * 删除楼盘编码
     * 
     * @param id 楼盘编码ID
     * @return 结果
     */
    public int deleteBasBuildingCodeById(String id);

    /**
     * 批量删除楼盘编码
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBasBuildingCodeByIds(String[] ids);

    public BasBuildingCode selectBasBuildingCodeByCode(String code);
}
