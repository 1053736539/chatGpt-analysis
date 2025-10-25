package com.cb.exportSql.component;

import com.alibaba.fastjson.JSON;
import com.cb.common.core.domain.entity.RsInfo;
import com.cb.exportSql.domain.SqlFile;
import com.cb.system.mapper.RsInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 李融业
 * @version 1.0
 * @CreateTime 2025/4/27 09:20
 * @Copyright (c) 2025
 * @Description 档案人员信息导出
 */
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ArchivesUserInfoExport extends ExportHandler implements ExportSql {
    // 档案人员信息（daryxx)
    private final String tableName = "db_daryxx";

    private final RsInfoMapper rsInfoMapper;;

    @Override
    public List<Map<String, Object>> exportMethod(Map<String, Object> params) {
        List<RsInfo> result = rsInfoMapper.exportArchivesUserInfoData(params);
        if (!result.isEmpty()) {
            return result.stream()
                    .map(item -> (Map<String, Object>) JSON.parseObject(JSON.toJSONString(item), Map.class))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public void setSqlFileAndData(SqlFile sqlFile, List<Map<String, Object>> result) {
        sqlFile.setTableName(tableName);
    }

    @Override
    public void createSqliteTableSql(StringBuilder sql) {
        sql.append(tableName).append(" (")
            .append("'id' INTEGER NOT NULL PRIMARY KEY,").append("'rsid' INTEGER,")
            .append("'userId' INTEGER,").append("'ar' TEXT,").append("'bm' TEXT,")
            .append("'csny' TEXT,").append("'dqzp' TEXT,").append("'dcdw' TEXT,")
            .append("'dcsj' TEXT,").append("'dcyy' TEXT,").append("'djdw' TEXT,")
            .append("'djsj' TEXT,").append("'djyy' TEXT,").append("'fs' TEXT,")
            .append("'hsgz' TEXT,").append("'hsjs' TEXT,").append("'jg' TEXT,")
            .append("'jrsj' TEXT,").append("'jl' TEXT,").append("'mz' TEXT,")
            .append("'ppsj' TEXT,").append("'rylb' TEXT,").append("'rybh' TEXT,")
            .append("'rmsj' TEXT,").append("'sfzh' TEXT,").append("'tzsj' TEXT,")
            .append("'tzyy' TEXT,").append("'wyz' TEXT,").append("'whcd' TEXT,")
            .append("'xb' TEXT,").append("'xm' TEXT,").append("'ygxz' TEXT,")
            .append("'zzmm' TEXT,").append("'zw' TEXT,").append("'zyzc' TEXT,")
            .append("'zn' TEXT,").append("'bmmc' TEXT,").append("'cs' TEXT,")
            .append("'gzgw' TEXT,").append("'gzdw' TEXT,").append("'rsfs' TEXT,")
            .append("'sxh' INTEGER,").append("'xmpy' TEXT,").append("'qssj' TEXT,")
            .append("'bysj' TEXT,").append("'byyxzy' TEXT,").append("'oldbm' INTEGER,")
            .append("'oldrsid' INTEGER,").append("'chushengdi' TEXT,").append("'worktime' TEXT,")
            .append("'jointime' TEXT,").append("'jobunit' TEXT,").append("'appointtime' TEXT,")
            .append("'idcard' TEXT,").append("'quanrizixueli' TEXT,").append("'quanrizixuewei' TEXT,")
            .append("'quanriziyuanxiao' TEXT,").append("'quanrizizhuanye' TEXT,").append("'zaizhixueli' TEXT,")
            .append("'zaizhixuewei' TEXT,").append("'zaizhiyuanxiao' TEXT,").append("'zaizhizhuanye' TEXT,")
            .append("'qingkuanshuomi' TEXT,").append("'danganzhengliren' TEXT,").append("'danganshenheren' TEXT,")
            .append("'shuzihuacaijiren' TEXT,").append("'shuzihuashenheren' TEXT,").append("'danganjuanshu' TEXT,")
            .append("'baosongdanwei' TEXT,").append("'baosongriqi' TEXT,").append("'modifyyes' INTEGER,")
            .append("'renwubiandong' TEXT,").append("'duanquecailiao' TEXT,").append("'archfile' INTEGER,")
            .append("'state' INTEGER,").append("'strxmpy' TEXT,").append("'mijibiaoshi' TEXT,")
            .append("'params' TEXT,").append("'createBy' TEXT,").append("'createTime' TEXT,")
            .append("'updateBy' TEXT,").append("'updateTime' TEXT,").append("'md5' TEXT")
            .append(")");
    }
}
