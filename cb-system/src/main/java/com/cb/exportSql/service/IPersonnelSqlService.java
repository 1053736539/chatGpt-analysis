package com.cb.exportSql.service;

import com.cb.exportSql.domain.SqlFile;

import java.io.File;
import java.util.List;

/**
 * @author 李融业
 * @version 1.0
 * @CreateTime 2025/4/25 17:20
 * @Copyright (c) 2025
 * @Description 人事数据相关导出Sql类， 包括人员、请假、档案
 */
public interface IPersonnelSqlService {
    /**
     * @description 导出 平板db文件
     * @return String
     * @createtime 2025/5/10 下午5:48
     * @author 李融业
     * @version 1.0
     */
    String exportSqliteDb() throws Exception ;

    /**
     * @description 导出 请假$人员$档案 数据的sql
     * @param isAll 是否导出所有数据
     * @param exportKey 导出的key
     * @return List<SqlFile>
     * @createtime 2025/4/25 下午5:48
     * @author 李融业
     * @version 1.0
     */
    List<SqlFile> exportLeave$user$archivesSql(boolean isAll, String exportKey);

    /**
     * @description 导出 人员头像图片
     * @param exportKey 导出的key
     * @return List<File>
     * @createtime 2025/5/10 下午5:48
     * @author 李融业
     * @version 1.0
     */
    List<File> exportUserAvatars();

}
