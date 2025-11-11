package com.cb.basedata.service.impl;

import com.cb.basedata.handler.CleanTblResultHandler;
import com.cb.basedata.mapper.CleanTblMapper;
import com.cb.basedata.service.ICleanTblService;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class CleanTblServiceImpl implements ICleanTblService {
    @Autowired
    private CleanTblMapper cleanTblMapper;
    @Override
    public String selectTablePrimaryKeyColumn(String owner, String tableName) {
        return cleanTblMapper.selectTablePrimaryKeyColumn(owner,tableName);
    }

    @Override
    public List<Map<String, Object>> selectDataList(String tableName, String columns, String condition) {
        return cleanTblMapper.selectDataList(tableName, columns, condition);
    }

    @Override
    public void streamQueryDataList(String tableName, String columns, String condition, CleanTblResultHandler resultHandler) {
        cleanTblMapper.streamQueryDataList(tableName,columns,condition,resultHandler);
    }

    @Override
    public Map<String, Object> selectData(String tableName, String columns, String condition) {
        return cleanTblMapper.selectData(tableName, columns, condition);
    }

    @Override
    public int insertDataToTable(String tableName, Map<String, Object> row) {
        return cleanTblMapper.insertDataToTable(tableName, row);
    }

    @Override
    public int updateDataToTable(String tableName, Map<String, Object> row, String condition) {
        return cleanTblMapper.updateDataToTable(tableName, row, condition);
    }
}
