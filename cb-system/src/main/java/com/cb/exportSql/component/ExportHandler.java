package com.cb.exportSql.component;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.cb.common.core.redis.RedisCache;
import com.cb.common.utils.sign.Md5Utils;
import com.cb.exportSql.domain.SqlFile;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author 李融业
 * @version 1.0
 * @CreateTime 2025/4/27 08:52
 * @Copyright (c) 2025
 * @Description 导出的处理基类
 */
public abstract class ExportHandler implements Conditions, ExportSql {
    private final static String UPDATE_TABLE_DML = "UPDATE {} SET {} WHERE {};\n";

    private final static String INSERT_TABLE_DML = "INSERT INTO {} ({}) VALUES ({});\n";

    // 数据库文件
    public static final String DB_NAME = "gbgl.db";

    private RedisCache redisCache;

    public abstract List<Map<String, Object>> exportMethod(Map<String, Object> params);

    public abstract void setSqlFileAndData(SqlFile sqlFile, List<Map<String, Object>> result);

    // 创建表
    public abstract void createSqliteTableSql(StringBuilder sql);

    @Override
    public List<SqlFile> export(boolean isAll, boolean isBuildSql) {
        try {
            Map<String, String> lastCursor = null;
            if (!isAll) {
                // 获取当前游标
                String className = this.getClass().getSimpleName();
                lastCursor = redisCache.getCacheMap( className + "_export_cursor");
            }
            // 缓存丢失，则全部重新导出
            List<SqlFile> sqlFiles = new ArrayList<>(16);
            SqlFile sqlFile = buildComparisonConditionsAndExecute(lastCursor, isBuildSql);
            if (sqlFile != null && !Objects.equals(sqlFile.getFileContent(), "^$")) {
                sqlFiles.add(sqlFile);
            }
            List<SqlFile> exportSqlFiles = buildExportConditionsAndExecute(lastCursor, isBuildSql);
            if (!exportSqlFiles.isEmpty()) {
                sqlFiles.addAll(exportSqlFiles);
            }
            return sqlFiles;
        } catch (Exception e) {
            e.printStackTrace();
            return ListUtil.empty();
        }
    }

    @Override
    public void exportToDb(Connection connection) throws Exception {
        if (connection != null) {
            // 创建表
            createSqliteTable(connection);
            // 插入数据
            insertSqliteData(connection);
        }
    }

    private SqlFile buildComparisonConditionsAndExecute(Map<String, String> lastCursor, boolean isBuildSql) {
        Map<String, Object> params = new HashMap<>(8);
        if (lastCursor != null && lastCursor.size() >= 2) {
            // 此方法只适用有递增趋势的数据
            params.putAll(comparisonConditions());
            params.put("id", Convert.toInt(lastCursor.get("lastId")));
            params.put("lastExportTime", lastCursor.get("lastExportTime"));
            params.put("isUpdate", true);
            params.put("limit", 500);
            SqlFile sqlFile = new SqlFile();
            sqlFile.setSql(isBuildSql);
            sqlFile.setFileName(this.getClass().getSimpleName(), "update", lastCursor.get("lastId"), lastCursor.get("lastExportTime"));
            int count;
            do {
                List<Map<String, Object>> results = exportMethod(Collections.unmodifiableMap(params));
                if (CollectionUtil.isNotEmpty(results)) {
                    count = results.size();
                    params.put("historyId", results.get(results.size() - 1).get(primaryKeyName()));
                    produceSqlFile(sqlFile, results, true);
                } else {
                    count = 0;
                }
            } while (count == 500);
            return sqlFile;
        }
       return null;
    }

    private List<SqlFile> buildExportConditionsAndExecute(Map<String, String> lastCursor, boolean isBuildSql) {
        Map<String, Object> params = new HashMap<>(8);
        if (CollectionUtil.isNotEmpty(lastCursor)) {
            params.putAll(exportConditions());
            params.put("id", Convert.toInt(lastCursor.get("lastId")));
            params.put("isUpdate", false);
        }
        // 每500条取一次
        params.put("limit", 500);
        int count;
        List<SqlFile> sqlFiles = new ArrayList<>(8);
        do {
            List<Map<String, Object>> results = exportMethod(Collections.unmodifiableMap(params));
            if (CollectionUtil.isNotEmpty(results)) {
                count = results.size();
                String id = Convert.toStr(results.get(results.size() - 1).get(primaryKeyName()));
                SqlFile sqlFile = new SqlFile();
                sqlFile.setSql(isBuildSql);
                sqlFile.setFileName(this.getClass().getSimpleName(), "insert", Convert.toStr(results.get(0).get(primaryKeyName())), id);
                produceSqlFile(sqlFile, results, false);
                sqlFiles.add(sqlFile);
                if (count < 500) {
                    redisCache.setCacheMap(this.getClass().getSimpleName() + "_export_cursor", MapUtil.builder("lastId", id).put("lastExportTime", LocalDateTime.now().format(DatePattern.NORM_DATETIME_FORMATTER)).build());
                } else {
                    params.put("id", id);
                }
            } else {
                count = 0;
            }
        } while (count == 500);
        return sqlFiles;
    }

    private void produceSqlFile(SqlFile sqlFile, List<Map<String, Object>> result, boolean isUpdate) {
        setSqlFileAndData(sqlFile, result);
        for (Map<String, Object> objectMap : result) {
            sqlFile.setFileContent(sqlFile.isSql()? buildSql(sqlFile.getTableName(), objectMap, isUpdate): buildSqlData(sqlFile.getTableName(), objectMap));
        }
    }

    private String buildSql(String tableName, Map<String, Object> dataMap, boolean isUpdate) {
        // 计算数据的MD5值
        String md5 = Md5Utils.hash(dataMap.values().stream().reduce((a, b) -> a + "" + b).get() + "");
        dataMap.put("md5", md5);
        Set<String> keys = dataMap.keySet();
        if (isUpdate) {
            return StrUtil.format(
                UPDATE_TABLE_DML,
                tableName,
                keys.stream().map(key -> key + "=" + (dataMap.get(key) instanceof String? "'" + dataMap.get(key) + "'" : dataMap.get(key))).reduce((a, b) -> a + "," + b).get(),
                "id=" + dataMap.get("id")
            );
        } else {
            return StrUtil.format(
                INSERT_TABLE_DML,
                tableName,
                StrUtil.removeSuffix(keys.stream().reduce("", (a, b) -> a + "'" + b + "',"), ","),
                StrUtil.removeSuffix(keys.stream().map(dataMap::get).reduce("", (a, b) -> a  + "'" + (b instanceof String? b : JSON.toJSONString(b)) + "'," ).toString(), ",")
            );
        }
    }

    private String buildSqlData(String tableName, Map<String, Object> dataMap) {
        // 计算数据的MD5值
        String md5 = Md5Utils.hash(dataMap.values().stream().reduce((a, b) -> a + "" + b).get() + "");
        dataMap.put("md5", md5);
        dataMap.put("tableName", tableName);
        return JSON.toJSONString(dataMap) + ";";
    }

    protected void createSqliteTable(Connection conn) throws SQLException {
        StringBuilder strBuilder = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        createSqliteTableSql(strBuilder);
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(strBuilder.toString());
        }
    }

    protected void insertSqliteData(Connection conn) throws SQLException {
        List<SqlFile> sqlFiles = export(true, true);
        try (Statement stmt = conn.createStatement()) {
            for (SqlFile sqlFile : sqlFiles) {
                for (String sql : sqlFile.getFileContentList()) {
                    stmt.addBatch(sql);
                }
            }
            stmt.executeBatch();
        }
    }

    @Autowired
    public void setRedisCache(RedisCache redisCache) {
        this.redisCache = redisCache;
    }
}
