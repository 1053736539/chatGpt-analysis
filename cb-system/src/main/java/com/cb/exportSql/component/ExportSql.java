package com.cb.exportSql.component;

import com.cb.exportSql.domain.SqlFile;

import java.sql.Connection;
import java.util.List;

/**
 * @author 李融业
 * @version 1.0
 * @CreateTime 2025/4/25 18:09
 * @Copyright (c) 2025
 * @Description 导出接口
 */
public interface ExportSql {
    /**
     * @description 导出sql文件
     * @param isAll
     * @return SqlFile
     * @createtime 2025/4/25 下午6:12
     * @author 李融业
     * @version 1.0
     */
    List<SqlFile> export(boolean isAll, boolean isBuildSql);

    /**
     * @description 导出数据到db文件
     * @param connection
     * @createtime 2025/4/25 下午6:12
     * @author 李融业
     * @version 1.0
     */
     void exportToDb(Connection connection) throws Exception;
}
