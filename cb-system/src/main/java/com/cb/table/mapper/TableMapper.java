package com.cb.table.mapper;


import com.cb.table.domain.Table;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TableMapper {
    public List<Table> selectTableListAll();

    public List<Table> selectTableListPrefix(String prefix);

    public List<Table> selectTableListSuffix(String suffix);

    public Table selectTableListByTableName(String tableName);

    /**
     * 获取源数据表
     * @return
     */
    public List<Table> selectBaseDataSourceTableList();

    /**
     * 获取清洗后数据表
     * @return
     */
    public List<Table> selectBaseDataCleanTableList();


    List<Map<String, Object>> selectDiyList(@Param("diySql") String diysql, @Param("whereSql") String whereSql);
    Integer selectDiyCount(@Param("diySql") String diysql, @Param("whereSql") String whereSql);


    public boolean createTable(@Param("tableName") String tableName,@Param("columnList") String columnList
            ,@Param("primaryKeys") String primaryKeys);

    public boolean commentTable(@Param("tableName")String tableName,@Param("comment") String comment);

    public boolean commentColumn(@Param("tableName")String tableName,@Param("column")String column,@Param("comment") String comment);
}
