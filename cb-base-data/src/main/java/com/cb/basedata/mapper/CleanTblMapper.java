package com.cb.basedata.mapper;

import com.cb.basedata.handler.CleanTblResultHandler;
import org.apache.ibatis.annotations.Param;


import java.util.List;
import java.util.Map;

public interface CleanTblMapper {

    /**
     * 根据名和表拥有者获取主键字段
     * @param owner
     * @param tableName
     * @return
     */
    public String selectTablePrimaryKeyColumn(@Param("owner") String owner, @Param("tableName") String tableName);


    /**
     * 获取指定源数据表名的数据
     * @param tableName
     * @param columns
     * @param condition
     * @return
     */
    public List<Map<String, Object>> selectDataList(@Param("tableName") String tableName, @Param("columns") String columns, @Param("condition") String condition);

    public void streamQueryDataList(@Param("tableName") String tableName,
                                    @Param("columns") String columns,
                                    @Param("condition") String condition,
                                    CleanTblResultHandler resultHandler
    );

    public Map<String, Object> selectData(@Param("tableName") String tableName, @Param("columns") String columns, @Param("condition") String condition);

    List<Map<String, Object>> selectDiyList(@Param("diySql") String diysql, @Param("whereSql") String whereSql, @Param("params") Map<String, Object> params);

    public int insertDataToTable(@Param("tableName") String tableName,@Param("row") Map<String,Object> row);

    public int updateDataToTable(@Param("tableName") String tableName,@Param("row")Map<String, Object> row,@Param("condition") String condition);

}
