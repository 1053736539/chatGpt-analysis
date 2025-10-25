package com.cb.diy.converter;

import com.cb.diy.DiyTools;
import com.cb.diy.model.DiyNode;
import com.cb.diy.model.DiyProcess;
import org.springframework.stereotype.Component;

/**
 * 执行
 * @author xiehong
 */
@Component
public class ExecuteSqlConverter extends AbstractSqlConverter {
    @Override
    public String getShape() {
        return "execute-node";
    }

    @Override
    public String getSql(DiyProcess diyProcess, DiyNode diyNode) throws Exception {
        return DiyTools.getRuleSql(diyProcess, diyNode);
    }
}
