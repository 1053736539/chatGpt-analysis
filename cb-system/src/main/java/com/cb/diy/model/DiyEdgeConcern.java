package com.cb.diy.model;

/**
 * 边节点
 * @author xiehong
 */
public class DiyEdgeConcern {
    private String cell;
    private String port;

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "EdgeConcern{" + "cell='" + cell + '\'' + ", port='" + port + '\'' + '}';
    }
}
