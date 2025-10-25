package com.cb.diy.model.resp;

/**
 * 预警数据
 * @author xiehong
 */
public class WarningData {
    /**
     * 指标ID
     */
    private Long id;
    /**
     * 模型名称
     */
    private String name;
    /**
     * 预警数
     */
    private Integer total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
