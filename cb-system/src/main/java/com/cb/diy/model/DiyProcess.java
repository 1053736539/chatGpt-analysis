package com.cb.diy.model;

import com.alibaba.fastjson.JSONObject;
import com.cb.common.utils.StringUtils;
import com.cb.diy.DiyTools;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DIY流程
 * @author xiehong
 */
public class DiyProcess {
    private List<DiyCell> cells;

    public DiyProcess(String process) {
        this.cells = DiyTools.toDiyCells(process);
    }

    public static DiyProcess newInstance(String process) {
        return new DiyProcess(process);
    }

    public List<DiyCell> getCells() {
        return cells;
    }

    /**
     * 获取所有节点
     */
    public List<DiyNode> getNodes() {
        List<DiyNode> list = new ArrayList<>();
        for (DiyCell cell : cells) {
            if (StringUtils.isNotBlank(cell.getShape()) && !cell.getShape().equals("edge")) {
                list.add((DiyNode) cell);
            }
        }
        return list;
    }

    public void setCells(String process) {
        this.cells = DiyTools.toDiyCells(process);
    }

    /**
     * 获取启动节点
     */
    public DiyNode getStartNode() {
        List<DiyCell> startNodes = getCells("start-node");
        if (startNodes == null || startNodes.size() == 0) {
            return null;
        }
        return (DiyNode) startNodes.get(startNodes.size() - 1);
    }

    /**
     * 获取节点的所有输入边
     * @param node 节点
     */
    public List<DiyEdge> getIncomingEdges(DiyNode node) {
        if (cells == null || cells.size() == 0 || node == null || StringUtils.isBlank(node.getId())) {
            return null;
        }
        List<DiyEdge> list = new ArrayList<>();
        for (DiyCell cell : cells) {
            if (cell instanceof DiyEdge) {
                DiyEdge edge = (DiyEdge) cell;
                String cellId = edge.getTarget().getCell();
                if (StringUtils.isNotBlank(cellId) && cellId.equals(node.getId())) {
                    list.add(edge);
                }
            }
        }
        return list;
    }

    /**
     * 获取节点的所有输出边
     * @param node 节点
     */
    public List<DiyEdge> getOutgoingEdges(DiyNode node) {
        if (cells == null || cells.size() == 0 || node == null || StringUtils.isBlank(node.getId())) {
            return null;
        }
        List<DiyEdge> list = new ArrayList<>();
        for (DiyCell cell : cells) {
            if (cell instanceof DiyEdge) {
                DiyEdge edge = (DiyEdge) cell;
                String cellId = edge.getSource().getCell();
                if (StringUtils.isNotBlank(cellId) && cellId.equals(node.getId())) {
                    list.add(edge);
                }
            }
        }
        return list;
    }

    /**
     * 获取节点的所有边
     * @param node 节点
     */
    public List<DiyEdge> getEdges(DiyNode node) {
        if (cells == null || cells.size() == 0 || node == null || StringUtils.isBlank(node.getId())) {
            return null;
        }
        List<DiyEdge> list = new ArrayList<>();
        for (DiyCell cell : cells) {
            if (cell instanceof DiyEdge) {
                DiyEdge edge = (DiyEdge) cell;
                String sourceCellId = edge.getSource().getCell();
                String targetCellId = edge.getTarget().getCell();
                if ((StringUtils.isNotBlank(sourceCellId) && sourceCellId.equals(node.getId())) || (StringUtils.isNotBlank(targetCellId) && targetCellId.equals(node.getId()))) {
                    list.add(edge);
                }
            }
        }
        return list;
    }

    /**
     * 获取下一个流程节点集合
     * @param node 节点
     */
    public List<DiyNode> getNextNodes(DiyNode node) {
        List<DiyEdge> edges = getOutgoingEdges(node);
        if (edges == null || edges.size() == 0) {
            return null;
        }
        List<String> nextNodeIds = new ArrayList<>();
        for (DiyEdge edge : edges) {
            String targetId = edge.getTarget().getCell();
            if (StringUtils.isNotBlank(targetId)) {
                nextNodeIds.add(targetId);
            }
        }
        if (nextNodeIds.size() == 0) {
            return null;
        }
        List<DiyNode> list = new ArrayList<>();
        for (DiyCell cell : cells) {
            if (StringUtils.isNotBlank(cell.getShape()) && !cell.getShape().equals("edge")) {
                if (StringUtils.isNotBlank(cell.getId()) && nextNodeIds.contains(cell.getId())) {
                    list.add((DiyNode) cell);
                }
            }
        }
        return list;
    }

    /**
     * 查找上个匹配节点
     * @param diyNode 节点
     * @param shape   节点类型
     */
    public List<DiyNode> findPreviousNode(DiyNode diyNode, String shape) {
        List<DiyEdge> edges = getIncomingEdges(diyNode);
        if (edges == null || edges.size() == 0) {
            return null;
        }
        List<DiyNode> nodes = new ArrayList<>();
        List<String> sourceIds = edges.stream().map(e -> e.getSource().getCell()).collect(Collectors.toList());
        for (DiyCell cell : cells) {
            if (cell instanceof DiyNode) {
                String cellShape = cell.getShape();
                if (StringUtils.isNotBlank(cellShape) && cellShape.equals(shape) && sourceIds.contains(cell.getId())) {
                    nodes.add((DiyNode) cell);
                }
            }
        }
        return nodes;
    }

    /**
     * 查找下个匹配节点
     * @param diyNode 节点
     * @param shape   节点类型
     */
    public List<DiyNode> findNextNode(DiyNode diyNode, String shape) {
        List<DiyEdge> edges = getOutgoingEdges(diyNode);
        if (edges == null || edges.size() == 0) {
            return null;
        }
        List<DiyNode> nodes = new ArrayList<>();
        List<String> targetIds = edges.stream().map(e -> e.getTarget().getCell()).collect(Collectors.toList());
        for (DiyCell cell : cells) {
            if (cell instanceof DiyNode) {
                String cellShape = cell.getShape();
                if (StringUtils.isNotBlank(cellShape) && cellShape.equals(shape) && targetIds.contains(cell.getId())) {
                    nodes.add((DiyNode) cell);
                }
            }
        }
        return nodes;
    }

    /**
     * 查询连接桩ID
     * @param diyNode   节点
     * @param groupName 连接桩组名
     */
    public String findNodePortId(DiyNode diyNode, String groupName) {
        DiyNodePorts diyNodePorts = diyNode.getPorts();
        if (diyNodePorts == null || diyNodePorts.getItems() == null || diyNodePorts.getItems().size() == 0) {
            return null;
        }
        for (int i = 0; i < diyNodePorts.getItems().size(); i++) {
            JSONObject item = diyNodePorts.getItems().getJSONObject(i);
            String group = item.getString("group");
            if (StringUtils.isNotBlank(group) && group.equals(groupName)) {
                return item.getString("id");
            }
        }
        return null;
    }

    /**
     * 查询节点
     * @param id 节点ID
     */
    public DiyNode findNode(String id) {
        List<DiyNode> nodes = getNodes();
        if (nodes != null && nodes.size() > 0) {
            for (DiyNode node : nodes) {
                if (node.getId().equals(id)) {
                    return node;
                }
            }
        }
        return null;
    }

    /**
     * 获取节点
     * @param shape 节点类型
     */
    public List<DiyCell> getCells(String shape) {
        if (cells == null || cells.size() == 0) {
            return null;
        }
        List<DiyCell> nodes = new ArrayList<>();
        for (DiyCell cell : cells) {
            if (StringUtils.isNotBlank(cell.getShape()) && cell.getShape().equals(shape)) {
                nodes.add(cell);
            }
        }
        return nodes;
    }
}
