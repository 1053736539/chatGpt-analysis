package com.cb.oa.vo;

/**
 * @Description:
 * @Author ARPHS
 * @Date 2023/11/30 11:38
 */
public class DeptItem {

    private String id;//ID
    private String deptCode;//机构编码
    private String name;//名称
    private String nameLetter;//名称首字母
    private Integer sort;//排序
    private String pid;//父 ID
    private String treePids;//所有上级 ID,'/'分隔
    private Integer treeLevel;//层级，从 0 开始
    private String enabled; //状态 1-启用 0-禁用

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameLetter() {
        return nameLetter;
    }

    public void setNameLetter(String nameLetter) {
        this.nameLetter = nameLetter;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getTreePids() {
        return treePids;
    }

    public void setTreePids(String treePids) {
        this.treePids = treePids;
    }

    public Integer getTreeLevel() {
        return treeLevel;
    }

    public void setTreeLevel(Integer treeLevel) {
        this.treeLevel = treeLevel;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }
}
