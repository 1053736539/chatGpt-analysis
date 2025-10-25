package com.cb.diy.model;

/**
 * SQL节点
 * @author xiehong
 */
public class SqlNode {
    private String id;
    private String shape;
    private Object data;
    private String sql;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    @Override
    public String toString() {
        return "SqlNode{" +
                "id='" + id + '\'' +
                ", shape='" + shape + '\'' +
                ", data=" + data +
                ", sql='" + sql + '\'' +
                '}';
    }
}
