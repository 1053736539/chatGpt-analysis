package com.cb.common.utils.tree;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description :树型构造辅助类  pojo类需要实现接口 {@link TreeSupport}
 *                 一般使用POJO类直接继承抽象类 {@link AbstractTreeNode}
 * 注意：如果POJO的ID不是常规包装类(Integer,Long....)和String，需要自己重写equals方法
 * @Author ARPHS
 */

public class TreeBuildUtil {

    /**
     * 根据给定的集合构造树结构
     * @param collection
     * @param <NODE>
     * @param <ID>
     * @return
     */
    public static <NODE extends TreeSupport<NODE,ID>,ID extends Serializable> List<NODE> buildTree(Collection<NODE> collection){
        if(collection == null || collection.size() < 1){
            return Collections.emptyList();
        }
        List<NODE> rootList = getRootNode(collection);
        rootList.forEach(i->setChild(i,collection));
        return rootList;
    }


    /**
     * 获取根节点
     * @param collection 集合
     * @return
     */
    public static <NODE extends TreeSupport<NODE,ID>,ID extends Serializable> List<NODE> getRootNode(Collection<NODE> collection){
        Set<ID> allIds = collection.stream().map(TreeSupport::getId).collect(Collectors.toSet());
        // 过滤不可用的,倒序排
        return collection.stream().filter(i->!allIds.contains(i.getParentId())).filter(i->i.isEnable())
                .sorted(Comparator.comparingInt(NODE::getSort)).collect(Collectors.toList());
    }

    /**
     * 递归设置子节点
     * @param node 当前节点
     * @param collection 集合
     */
    private static <NODE extends TreeSupport<NODE,ID>,ID extends Serializable> void setChild(NODE node, Collection<NODE> collection){
        // 过滤不可用的,倒序排
        List<NODE> children = collection.stream().filter(i->node.getId().equals(i.getParentId())).filter(i->i.isEnable())
                .sorted(Comparator.comparingInt(NODE::getSort).reversed()).collect(Collectors.toList());
        if(children == null || children.size() < 1){
            //node.setChildren(Collections.emptyList());
        } else {
            children.forEach(child->setChild(child,collection));
            node.setChildren(children);
        }
    }

}
