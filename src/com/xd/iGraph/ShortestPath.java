package com.xd.iGraph;


import java.util.Arrays;

class ShortestPath {
    private int[] D;  // 各个顶到到v0的距离
    private String[] DP;  //各个顶点到v0的路径
    private static int MAXPATH = Integer.MAX_VALUE;  // 无限大

    public ShortestPath(MGraph G, int v0) {
        /*
        求无向连通图G中节点v0，到各个节点的最短距离。
         */
        System.out.println("开始寻找最小路径。");
        System.out.println("起点顶点：" + G.getVex(v0));
        int vexNum = G.getVexNum();  // 节点数目
        D = new int[vexNum];  // 每一个节点到v0的距离，如果无法到达就是MAXPATH；
        DP = new String[vexNum];
        // 初始化D,DP
        for (int v = 0; v < vexNum; v++) {
            D[v] = MAXPATH;
            DP[v] = "";
        }
        D[v0] = 0;  // 距离为0
        DP[v0] = "" + G.getVex(v0);
        /*
        扫描已经找到距离的节点的邻接点到v0的距离（=已经找到的节点距离+1）；
        找出其中的最小值，加入D中；
        重复，直到完成；
        困难：保存距离。
         */
        int count = 0;
        while (true) {
            // 循环找到v0到各个顶点的距离，直到没有路
            // 跳出的判断条件：Arrays.equals(t, nonTable(vexNum))
            int[][] t = nonTable(vexNum);  // t[v][w]代表v0到w的距离，而v代表着这条路径长度是由v确定的。
            for (int v = 0; v < vexNum; v++) {
                if (D[v] < MAXPATH) {
                    // 找到D中的一个有距离的节点
                    int vPath = D[v]; // base路径长
                    // 遍历邻接点
                    int adjVex = G.firstAdjVex(v);
                    while (adjVex != -1) {
                        t[v][adjVex] = 1 + vPath;
                        adjVex = G.nextAdjVex(v, adjVex);
                    }
                }

            }
            // 将已经确定路径长度的顶点去除
            for (int v = 0; v < vexNum; v++) {
                if (D[v] < MAXPATH) {
                    // 找到D中的一个有距离的节点
                    for (int w = 0; w < vexNum; w++) {
                        t[w][v] = MAXPATH;
                    }
                }
            }
            // 找到最小的路径以及顶点
            int minPath = MAXPATH;
            int minV = 0;
            int minVPre = 0;
            for (int v = 0; v < vexNum; v++) {
                for (int w = 0; w < vexNum; w++) {
                    if (t[v][w] < minPath) {
                        minPath = t[v][w];
                        minV = w;
                        minVPre = v;
                    }
                }
            }
            // 将最小的顶点加入到D中
            // 跳出条件
            if (minPath != MAXPATH) {
                D[minV] = minPath;
                DP[minV] = DP[minVPre] + "->" + G.getVex(minV);
            } else {
                break;
            }
        }
    }

    public int[][] nonTable(int num) {
        int[][] t = new int[num][num];
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                t[i][j] = MAXPATH;
            }
        }
        return t;
    }

    public int[] get_D() {
        return D;
    }

    public static void main(String args[]) {
        System.out.println("开始创建图。");
        Object[] vexs = new Object[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] arcs = new int[][]
                {{0, 1, 0, 0, 0, 0, 0},
                        {1, 0, 1, 0, 0, 0, 0},
                        {0, 1, 0, 0, 1, 0, 1},
                        {0, 0, 0, 0, 1, 0, 0},
                        {0, 0, 1, 1, 0, 1, 0},
                        {0, 0, 0, 0, 1, 0, 1},
                        {0, 0, 1, 0, 0, 1, 0}};
        GraphKind kind = GraphKind.UDG;
        MGraph G = new MGraph(kind, 7, 7, vexs, arcs);
        ShortestPath shortestPath = new ShortestPath(G, 1);
        int[] path = shortestPath.get_D();
        String[] pathDes = shortestPath.get_DP();
        for (int i = 0; i < path.length; i++) {
            System.out.println("\t>" + vexs[i] + " "+ path[i] + " " + pathDes[i]);
        }
        System.out.println("运行完毕。");
    }

    private String[] get_DP() {
        return DP;
    }
}

