package com.cb.common.utils.tree;

import java.io.Serializable;
import java.util.List;

/**
 * @Description :树节点支持
 * @param <NODE> pojo类
 * @param <ID> pojo类的id类型
 * @Author ARPHS
 */
public interface TreeSupport<NODE,ID extends Serializable> {

    /**
     * 获取子节点集合
     * @return
     */
    List<NODE> getChildren();

    /**
     * 设置子节点集合
     * @param children
     */
    void setChildren(List<NODE> children);

    /**
     * 获取节点id
     * @return
     */
//    @JsonIgnore
    ID getId();

    /**
     * 获取父节点id
     * @return
     */
    ID getParentId();

    /**
     * 获取排序值，同级大的在先
     * @return
     */
//    @JsonIgnore
    int getSort();

    /**
     * 是否是可用的，不是则丢弃，从该节点之下的子孙都丢弃
     * @return
     */
//    @JsonIgnore
    boolean isEnable();

}
/**
 使用说明：实现该接口并指定泛型为<POJO类，POJO类的ID类型>并实现接口方法
 class Item implement TreeSupport<Item,Integer>{
     private Integer id;
     private Integer parentId;
     private Collection<Item> children;
     ...
     @Override
     public Collection<ListResItem> getChildren() {
        return children;
     }

     @Override
     public void setChildren(Collection<ListResItem> children) {
        this.children = children;
     }

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
