package com.cb.diy.converter;

import com.cb.diy.model.DiyNode;
import com.cb.diy.model.DiyProcess;

import java.util.Map;

/**
 * sql转换接口
 * @author xiehong
 */
public abstract class AbstractSqlConverter {
    /**
     * 获取节点类型
     */
    public abstract String getShape();

//    /**
//     * 获取sql
//     */
//    protected abstract String getSql(Object data, Map<String, Object> variables, List<TaskNodeData> taskNodeDatas, DiyNode diyNode, DiyProcess diyProcess) throws Exception;

    /**
     * 获取sql
     */
    public abstract String getSql(DiyProcess diyProcess, DiyNode diyNode) throws Exception;

//    /**
//     * 执行
//     * @param diyNode       节点
//     * @param taskNodeDatas 任务数据
//     * @param taskNodeDatas 参数
//     */
//    public void run(DiyProcess diyProcess, DiyNode diyNode, List<TaskNodeData> taskNodeDatas, Map<String, Object> variables) throws Exception {
//        String sql = getSql(diyNode.getData(), variables, taskNodeDatas, diyNode, diyProcess);
//        TaskNodeData taskNodeData = new TaskNodeData();
//        taskNodeData.setId(diyNode.getId());
//        taskNodeData.setShape(diyNode.getShape());
//        taskNodeData.setData(diyNode.getData());
//        taskNodeData.setSql(sql);
//        taskNodeDatas.add(taskNodeData);
//    }
}
