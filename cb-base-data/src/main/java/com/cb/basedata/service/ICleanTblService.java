package com.cb.basedata.service;

import com.cb.basedata.handler.CleanTblResultHandler;

import java.util.List;
import java.util.Map;

public interface ICleanTblService {
    public String selectTablePrimaryKeyColumn(String owner, String tableName);


    public List<Map<String, Object>> selectDataList(String tableName, String columns, String condition);

    public void streamQueryDataList(String tableName, String columns, String condition, CleanTblResultHandler resultHandler);

    public Map<String, Object> selectData(String tableName, String columns, String condition);

    public int insertDataToTable(String tableName, Map<String, Object> row);

    public int updateDataToTable(String tableName, Map<String, Object> row, String condition);
}
