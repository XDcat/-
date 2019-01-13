package com.xd.iGraph;

public class VNode {
    /*
    邻接表的顶点节点类
     */
    public Object data;
    public ArcNode firstArc;
    public VNode(){
        this(null, null);
    }

    public VNode(Object data) {
        this(data, null);
    }

    public VNode(Object data, ArcNode firstArc) {
        this.data = data;
        this.firstArc = firstArc;
    }
}
