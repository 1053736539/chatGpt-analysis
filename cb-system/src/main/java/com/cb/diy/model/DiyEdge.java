package com.cb.diy.model;

/**
 * 边
 * @author xiehong
 */
public class DiyEdge extends DiyCell {
    private DiyEdgeConcern source;
    private DiyEdgeConcern target;

    public DiyEdgeConcern getSource() {
        return source;
    }

    public void setSource(DiyEdgeConcern source) {
        this.source = source;
    }

    public DiyEdgeConcern getTarget() {
        return target;
    }

    public void setTarget(DiyEdgeConcern target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return "DiyEdge{" +
                "source=" + source +
                ", target=" + target +
                '}';
    }
}
