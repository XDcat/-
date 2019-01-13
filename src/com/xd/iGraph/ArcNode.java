package com.xd.iGraph;

public class ArcNode {
    /*
    邻接表的边节点类
     */
    public int adjVex;  // 所指向的顶点
    public int value;  // 权值
    public ArcNode nextArc;  // 下一条
    public ArcNode(){
        this(-1, 0, null);
    }

    public ArcNode(int adjVex) {
        this(adjVex, 0, null);
    }

    public ArcNode(int adjVex, int value) {
        this(adjVex, value, null);
    }

    public ArcNode(int adjVex, int value, ArcNode nextArc) {
        this.adjVex  = adjVex;
        this.value = value;
        this.nextArc = nextArc;
    }
}
