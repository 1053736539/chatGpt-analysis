package com.cb.common.utils.tree;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 抽象树节点类,抽象类已持有了children集合
 * @Author ARPHS
 */
public abstract class AbstractTreeNode<NODE,ID extends Serializable> implements Serializable,TreeSupport<NODE,ID> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    private List<NODE> children;

    @Override
    public List<NODE> getChildren() {
        return children;
    }

    @Override
    public void setChildren(List<NODE> children) {
        this.children = children;
    }

}
/**
 使用说明：继承该抽象类并指定泛型为<POJO类，POJO类的ID类型>并实现未实现的抽象方法
 class Item extend AbstractTreeNode<Item,Integer>{
     private Integer id;
     private Integer parentId;
     ...

     @Override
     public Integer getId() {
        return typeId;
     }

     @Override
     public Integer getParentId() {
        return parentId;
     }
 }
 */

