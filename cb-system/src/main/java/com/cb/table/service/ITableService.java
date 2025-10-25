package com.cb.table.service;


import com.cb.table.domain.Table;

import java.util.List;

public interface ITableService {
    public List<Table> selectTableListAll();

    public List<Table> selectTableListPrefix(String prefix);

    public List<Table> selectTableListSuffix(String suffix);

    public Table selectTableListByTableName(String tableName);

    public List<Table> selectBaseDataSourceTableList();

    public List<Table> selectBaseDataCleanTableList();

    public Boolean createTable(String tableName, String columnList, String primaryKeys);

    public Boolean commentTable(String tableName, String comment);

    public Boolean commentColumn(String tableName, String column, String comment);
}
