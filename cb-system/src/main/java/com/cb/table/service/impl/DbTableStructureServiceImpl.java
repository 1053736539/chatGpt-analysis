package com.cb.table.service.impl;

import java.util.*;

import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.table.domain.DbTableStructure;
import com.cb.table.domain.DbTableStructureColumn;
import com.cb.table.enums.DataTypeEnum;
import com.cb.table.mapper.DbTableStructureColumnMapper;
import com.cb.table.mapper.DbTableStructureMapper;
import com.cb.table.service.IDbTableStructureService;
import com.cb.table.service.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cb.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;

/**
 * online表单Service业务层处理
 *
 * @author ouyang
 * @date 2024-11-06
 */
@Service
public class DbTableStructureServiceImpl implements IDbTableStructureService {
    @Autowired
    private DbTableStructureMapper structureMapper;

    @Autowired
    private DbTableStructureColumnMapper columnMapper;

    @Autowired
    private ITableService tableService;

    /**
     * 查询online表单
     *
     * @param id online表单ID
     * @return online表单
     */
    @Override
    public DbTableStructure selectDbTableStructureById(String id) {
        DbTableStructure dbTableStructure = structureMapper.selectDbTableStructureById(id);
        List<DbTableStructureColumn> columnList = columnMapper.selectDbTableStructureColumnByTableId(id);
        dbTableStructure.setTableStructureColumnList(columnList);
        return dbTableStructure;
    }

    /**
     * 查询online表单列表
     *
     * @param dbTableStructure online表单
     * @return online表单
     */
    @Override
    public List<DbTableStructure> selectDbTableStructureList(DbTableStructure dbTableStructure) {
        return structureMapper.selectDbTableStructureList(dbTableStructure);
    }

    /**
     * 新增online表单
     *
     * @param dbTableStructure online表单
     * @return 结果
     */
    @Transactional
    @Override
    public int insertDbTableStructure(DbTableStructure dbTableStructure) {
        dbTableStructure.setId(IdUtils.randomUUID());
        dbTableStructure.setCreateBy(SecurityUtils.getUsername());
        dbTableStructure.setCreateTime(DateUtils.getNowDate());
        int rows = structureMapper.insertDbTableStructure(dbTableStructure);
        insertDbTableStructureColumn(dbTableStructure);
        return rows;
    }

    /**
     * 修改online表单
     *
     * @param structure online表单
     * @return 结果
     */
    @Transactional
    @Override
    public int updateDbTableStructure(DbTableStructure structure) {
        structure.setUpdateBy(SecurityUtils.getUsername());
        structure.setUpdateTime(DateUtils.getNowDate());
        structureMapper.deleteDbTableStructureColumnByTableId(structure.getId());
        insertDbTableStructureColumn(structure);
        return structureMapper.updateDbTableStructure(structure);
    }

    /**
     * 批量删除online表单
     *
     * @param ids 需要删除的online表单ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteDbTableStructureByIds(String[] ids) {
        structureMapper.deleteDbTableStructureColumnByTableIds(ids);
        return structureMapper.deleteDbTableStructureByIds(ids);
    }

    /**
     * 删除online表单信息
     *
     * @param id online表单ID
     * @return 结果
     */
    @Override
    public int deleteDbTableStructureById(String id) {
        structureMapper.deleteDbTableStructureColumnByTableId(id);
        return structureMapper.deleteDbTableStructureById(id);
    }

    /**
     * 新增数据库字段信息
     *
     * @param dbTableStructure online表单对象
     */
    public void insertDbTableStructureColumn(DbTableStructure dbTableStructure) {
        List<DbTableStructureColumn> dbTableStructureColumnList = dbTableStructure.getTableStructureColumnList();
        String id = dbTableStructure.getId();
        if (StringUtils.isNotNull(dbTableStructureColumnList)) {
            List<DbTableStructureColumn> list = new ArrayList<DbTableStructureColumn>();
            for (DbTableStructureColumn dbTableStructureColumn : dbTableStructureColumnList) {
                dbTableStructureColumn.setColumnId(IdUtils.randomUUID());
                dbTableStructureColumn.setTableId(id);
                dbTableStructureColumn.setCreateBy(SecurityUtils.getUsername());
                dbTableStructureColumn.setCreateTime(DateUtils.getNowDate());
                list.add(dbTableStructureColumn);
            }
            if (list.size() > 0) {
                structureMapper.batchDbTableStructureColumn(list);
            }
        }
    }

    @Override
    public void asyncStructure2Db(String id) {
        DbTableStructure structure = this.selectDbTableStructureById(id);
        List<DbTableStructureColumn> columnList = structure.getTableStructureColumnList();
        String tableName = structure.getTableName();
        Map<String, List<String>> map = buildColumnList(columnList);
        List<String> columns = map.get("columns");
        List<String> primaryKeys = map.get("primaryKey");
        tableService.createTable(tableName, String.join(",", columns), String.join(",", primaryKeys));
        executeComment(structure);
        structureMapper.changeAsyncStatus(id);
    }

    private Map<String, List<String>> buildColumnList(List<DbTableStructureColumn> columnList) {
        List<String> columns = new ArrayList<>();
        List<String> primaryKeys = new ArrayList<>();
        columnList
                .stream()
                .sorted(Comparator.comparingInt(DbTableStructureColumn::getOrderNum))
                .filter(column -> column.getSyncDb() == 1)
                .forEach(column -> {
                    String columnName = StringUtils.humpToLine(column.getColumnName());
                    String columnComments = column.getColumnComments();
                    Integer columnLength = column.getColumnLength();
                    Integer columnPointLength = column.getColumnPointLength();
                    String columnType = column.getColumnType();
                    String defaultValue = column.getDefaultValue();
                    Integer mustFill = column.getMustFill();//非空 0 否 1是
                    DataTypeEnum dataTypeEnum = DataTypeEnum.ofJdbcType(columnType);
                    Integer primaryKey = column.getPrimaryKey();
                    if (1 == primaryKey) {
                        primaryKeys.add(String.format("\"%s\"", columnName));
                    }
                    StringBuilder builder = new StringBuilder();

                    // 处理字段、字段类型和字段长度
                    if (DataTypeEnum.INTEGER.getJavaType().equals(columnType) || DataTypeEnum.LONG.getJavaType().equals(columnType)) {
                        builder.append(String.format("\"%s\" %s", columnName, dataTypeEnum.getJdbcType()));
                    } else if (DataTypeEnum.FLOAT.getJavaType().equals(columnType)
                            || DataTypeEnum.DOUBLE.getJavaType().equals(columnType)
                            || DataTypeEnum.BIG_DECIMAL.getJavaType().equals(columnType)) {
                        builder.append(String.format("\"%s\" %s(%d,%d)", columnName, dataTypeEnum.getJdbcType(), columnLength, columnPointLength));
                    } else if (DataTypeEnum.DATE_TIME.getJavaType().equals(columnType)) {
                        builder.append(String.format("\"%s\" %s(%d)", columnName, dataTypeEnum.getJdbcType(), 0));
                    } else {
                        builder.append(String.format("\"%s\" %s(%d)", columnName, dataTypeEnum.getJdbcType(), columnLength));
                    }
                    String d = "";

                    // 处理默认值
                    if (DataTypeEnum.FLOAT.getJavaType().equals(columnType)
                            || DataTypeEnum.DOUBLE.getJavaType().equals(columnType)
                            || DataTypeEnum.BIG_DECIMAL.getJavaType().equals(columnType)
                            || DataTypeEnum.INTEGER.getJavaType().equals(columnType)
                            || DataTypeEnum.LONG.getJavaType().equals(columnType)
                    ) {
                        d = StringUtils.isNotEmpty(defaultValue) ? String.format(" DEFAULT %s", defaultValue) : "";
                    } else if (DataTypeEnum.DATE_TIME.getJavaType().equals(columnType)) {

                    } else {
                        d = StringUtils.isNotEmpty(defaultValue) ? String.format(" DEFAULT '%s'", defaultValue) : "";
                    }
                    //  处理 非空
                    String m = mustFill == 1 ? " NOT NULL" : "";

                    builder.append(d).append(m);
                    columns.add(builder.toString());
                });
        Map<String, List<String>> map = new HashMap<>();
        map.put("columns", columns);
        map.put("primaryKey", primaryKeys);
        return map;
    }

    private void executeComment(DbTableStructure structure) {
        String tableName = structure.getTableName();
        tableService.commentTable(tableName, structure.getTableComments());
        for (DbTableStructureColumn column : structure.getTableStructureColumnList()) {
            String columnName = StringUtils.humpToLine(column.getColumnName());
            String columnComments = column.getColumnComments();
            tableService.commentColumn(tableName, columnName, columnComments);
        }
    }

}
