package com.cb.table.service.impl;

import com.cb.table.domain.Table;
import com.cb.table.mapper.TableMapper;
import com.cb.table.service.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ITableServiceImpl implements ITableService {
    @Autowired
    private TableMapper tableMapper;

    @Override
    public List<Table> selectTableListAll() {
        return tableMapper.selectTableListAll();
    }
    @Override
    public List<Table> selectTableListPrefix(String prefix) {
        return tableMapper.selectTableListPrefix(prefix);
    }

    @Override
    public List<Table> selectTableListSuffix(String suffix) {
        return tableMapper.selectTableListSuffix(suffix);
    }

    @Override
    public Table selectTableListByTableName(String tableName) {
        return tableMapper.selectTableListByTableName(tableName);
    }

    @Override
    public List<Table> selectBaseDataSourceTableList() {
        return tableMapper.selectBaseDataSourceTableList();
    }

    @Override
    public List<Table> selectBaseDataCleanTableList() {
        return tableMapper.selectBaseDataCleanTableList();
    }

    @Override
    public Boolean createTable(String tableName, String columnList, String primaryKeys) {
        return tableMapper.createTable(tableName, columnList, primaryKeys);
    }

    @Override
    public Boolean commentTable(String tableName, String comment) {
        return tableMapper.commentTable(tableName,comment);
    }

    @Override
    public Boolean commentColumn(String tableName, String column, String comment) {
        return tableMapper.commentColumn(tableName, column, comment);
    }

}
