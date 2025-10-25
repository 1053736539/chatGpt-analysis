package com.cb.exportSql.service.impl;

import cn.hutool.core.util.StrUtil;
import com.cb.common.config.RuoYiConfig;
import com.cb.exportSql.component.ExportHandler;
import com.cb.exportSql.component.ExportSql;
import com.cb.exportSql.domain.SqlFile;
import com.cb.exportSql.service.IPersonnelSqlService;
import com.cb.system.mapper.SysUserMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.*;

/**
 * @author 李融业
 * @version 1.0
 * @CreateTime 2025/4/25 17:20
 * @Copyright (c) 2025
 * @Description 人事数据相关导出Sql类， 包括人员、请假、档案
 */
@Slf4j
@Service
public class PersonnelSqlServiceImpl implements IPersonnelSqlService {
    // Hikari连接池
    private static HikariDataSource dataSource;

    @Resource
    private Map<String, ExportSql> exportSqlMap;

    private SysUserMapper sysUserMapper;

    // 初始化连接池
    private void initSqliteConnectionPool(String dbPath) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:sqlite:" + dbPath);
        config.setDriverClassName("org.sqlite.JDBC");
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);
        dataSource = new HikariDataSource(config);
    }

    @Override
    public String exportSqliteDb() throws Exception {
        // 创建数据库文件
        String filePath = StrUtil.addSuffixIfNot(RuoYiConfig.getDownloadPath(), File.separator) + ExportHandler.DB_NAME;
        File dbFile = new File(filePath);
        if (!dbFile.getParentFile().exists()) {
            dbFile.getParentFile().mkdirs();
        }
        if (dbFile.exists()) {
            log.info("数据库文件（" + ExportHandler.DB_NAME + "）已存在，删除文件");
            dbFile.delete();
        }
        log.info("创建数据库文件（" + ExportHandler.DB_NAME + "）");
        dbFile.createNewFile();
        initSqliteConnectionPool(filePath);
        for (Map.Entry<String, ExportSql> entry : exportSqlMap.entrySet()) {
            log.info("开始导出" + entry.getKey() + "数据");
            entry.getValue().exportToDb(dataSource.getConnection());
        }
        closeConnectionPool();
        return filePath;
    }

    @Override
    public List<SqlFile> exportLeave$user$archivesSql(boolean isAll, String exportKey) {
        List<SqlFile> sqlFiles = new ArrayList<>(16);
        if (StrUtil.isNotBlank(exportKey)) {
            sqlFiles.addAll(exportSqlMap.get(exportKey).export(isAll, false));
        } else {
            // 全量导出
            exportSqlMap.entrySet().forEach(entry -> {
                sqlFiles.addAll(entry.getValue().export(isAll, false));
            });
        }
        return sqlFiles;
    }

    @Override
    public List<File> exportUserAvatars() {
        List<File> result;
        List<String> avatars = sysUserMapper.selectUserAvatars();
        if (avatars != null && !avatars.isEmpty()) {
            // 增加数据头像去重
            Set<String> setAvatars = new HashSet<>(avatars);
            result = new ArrayList<>(avatars.size());
            for (String avatar : setAvatars) {
                if (StrUtil.isNotBlank(avatar)) {
                    File avatarFile = getFile(avatar);
                    if (avatarFile.exists() && avatarFile.isFile()) {
                        result.add(avatarFile);
                    }
                }
            }
            return result;
        }
        return Collections.emptyList();
    }

    private static File getFile(String avatar) {
        String filePath;
        if (avatar.startsWith("/profile/upload")) {
            filePath = RuoYiConfig.getUploadPath() + avatar.replaceFirst("/profile/upload", "");
        } else if (avatar.startsWith("/profile/avatar")) {
            filePath = RuoYiConfig.getAvatarPath() + avatar.replaceFirst("/profile/avatar", "");
        } else {
            filePath = RuoYiConfig.getOtherAvatarPath() + avatar.replaceFirst("/profile/otherAvatar", "");
        }
        return new File(filePath);
    }

    // 关闭连接池
    private void closeConnectionPool() {
        if (dataSource instanceof HikariDataSource) {
            ((HikariDataSource) dataSource).close();
        }
    }

    @Autowired
    public void setSysUserMapper(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }
}
