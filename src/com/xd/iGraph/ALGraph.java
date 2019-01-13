package com.xd.iGraph;

import java.util.Scanner;

public class ALGraph implements IGraph{
    private GraphKind kind;
    private int vexNum, arcNum;
    private VNode[] vexs;
    public ALGraph(){
        this(null, 0, 0, null);
    }

    public ALGraph(GraphKind kind, int vexNum, int arcNum, VNode[] vexs) {
        this.kind = kind;
        this.vexNum = vexNum;
        this.arcNum = arcNum;
        this.vexs = vexs;
    }

    @Override
    public void createGraph() {
    }
    @Override
    public int getVexNum() {
        return vexNum;
    }

    @Override
    public int getArcNum() {
        return arcNum;
    }

    @Override
    public Object getVex(int v) {
        return vexs[v].data;
    }

    @Override
    public int localVex(Object vex) {
        for (int i = 0; i < vexNum; i++) {
            if (vexs[i].data.equals(vex)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int firstAdjVex(int v) {
        VNode vex = vexs[v];
        if (vex.firstArc != null) {
            return vex.firstArc.adjVex;
        } else {
            return -1;
        }
    }

    @Override
    public int nextAdjVex(int v, int w) {
        VNode vex = vexs[v];
        ArcNode arcvw = null;
        for (ArcNode arc = vex.firstArc; arc != null; arc = arc.nextArc) {
            if (arc.adjVex == w){
                arcvw = arc;
                break;
            }
        }
        if (arcvw != null && arcvw.nextArc != null) {
            return arcvw.nextArc.adjVex;
        } else {
            return -1;
        }
    }
}
