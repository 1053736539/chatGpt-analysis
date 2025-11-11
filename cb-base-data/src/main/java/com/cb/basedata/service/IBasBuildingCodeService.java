package com.cb.basedata.service;

import java.util.List;
import java.util.Map;

import com.cb.basedata.domain.BasBuildingCode;

/**
 * 楼盘编码Service接口
 * 
 * @author ouyan
 * @date 2024-10-25
 */
public interface IBasBuildingCodeService 
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
     * 批量删除楼盘编码
     * 
     * @param ids 需要删除的楼盘编码ID
     * @return 结果
     */
    public int deleteBasBuildingCodeByIds(String[] ids);

    /**
     * 删除楼盘编码信息
     * 
     * @param id 楼盘编码ID
     * @return 结果
     */
    public int deleteBasBuildingCodeById(String id);

    public String importBasBuildingCode(List<BasBuildingCode> list, Boolean isUpdateSupport);

    public Map<String,BasBuildingCode> selectBasBuildingCodeMap();
}
