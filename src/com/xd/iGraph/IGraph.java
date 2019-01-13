package com.xd.iGraph;

public interface IGraph {
    /*
    图的接口类
    顶点的确定都是由v，即位置确定
    只有在需要值的时候，返回值
     */
    void createGraph();
    int getVexNum();  // 顶点的数目
    int getArcNum();  // 边的数目
    Object getVex(int v);  // 给定位置，返回顶点的值
    int localVex(Object vex);  // 给定顶点，返回位置
    int firstAdjVex(int v);  // 返回第一个邻接点的位置（如果没有，返回－１）
    int nextAdjVex(int v, int w);  // 返回v相对于w的下一个邻接点的位置
}
