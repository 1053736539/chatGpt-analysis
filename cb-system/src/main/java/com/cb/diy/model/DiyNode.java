package com.cb.diy.model;

import java.util.Map;

/**
 * 节点
 * @author xiehong
 */
public class DiyNode extends DiyCell {
    private Map<String, Object> position;
    private DiyNodePorts ports;
    private Map<String, Object> size;
    private String view;
    private Object data;

    public Map<String, Object> getPosition() {
        return position;
    }

    public void setPosition(Map<String, Object> position) {
        this.position = position;
    }

    public DiyNodePorts getPorts() {
        return ports;
    }

    public void setPorts(DiyNodePorts ports) {
        this.ports = ports;
    }

    public Map<String, Object> getSize() {
        return size;
    }

    public void setSize(Map<String, Object> size) {
        this.size = size;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DiyNode{" +
                "position=" + position +
                ", ports=" + ports +
                ", size=" + size +
                ", view='" + view + '\'' +
                ", data=" + data +
                '}';
    }
}
