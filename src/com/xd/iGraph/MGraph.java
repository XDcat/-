package com.xd.iGraph;

import java.util.Scanner;

public class MGraph implements IGraph {
    /*
    图：邻接矩阵表示
    1. 顶点用数组vexs表示
    2. 顶点的关系用邻接矩阵arcs表示
    (描述一个图，就是要描述其顶点和边)
     */
    public final static int INFINITY = Integer.MAX_VALUE - 100;  // 不能是最大值，因为可能会进行加减法
    private GraphKind kind;  // 种类
    private int vexNum, arcNum;  // 顶点数目
    private Object[] vexs;  // 顶点的数组
    private int[][] arcs;  // 邻接矩阵

    public MGraph() {
        this(null, 0, 0, null, null);
    }

    public MGraph(GraphKind kind, int vexNum, int arcNum, Object[] vexs, int[][] arcs) {
        this.kind = kind;
        this.vexNum = vexNum;
        this.arcNum = arcNum;
        this.vexs = vexs;
        this.arcs = arcs;
    }

    @Override
    public void createGraph() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入数据类型：");
        GraphKind kind = GraphKind.valueOf(sc.next());
        switch (kind) {
            case DG:
                createDG();
                return;
            case UDG:
                createUDG();
                return;
            case DN:
                createDN();
                return;
            case UDN:
                createUDN();
                return;
            default:
                System.out.println("输入有误，创建失败，请再次尝试。");
        }
    }

    private void createUDN() {
        /*
        无向网
         */
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入顶点数：");
        vexNum = sc.nextInt();
        System.out.println("请输入边数：");
        arcNum = sc.nextInt();
        // 创建顶点
        vexs = new Object[vexNum];
        System.out.println("请分别输入图的各个顶点：");
        for (int i = 0; i < vexNum; i++) {
            vexs[i] = sc.next();
        }
        // 创建边
        arcs = new int[vexNum][vexNum];
        for (int i = 0; i < vexNum; i++) {
            // 初始化网
            for (int j = 0; j < vexNum; j++) {
                arcs[i][j] = INFINITY;
            }
        }
        System.out.println("请输入各个边的两个顶点，及其权值");
        for (int i = 0; i < arcNum; i++) {
            int v = localVex(sc.next());
            int u = localVex(sc.next());
            arcs[v][u] =arcs[u][v] =  sc.nextInt();
        }
    }

    private void createDN() {
        /*
        有向网
         */
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入顶点数：");
        vexNum = sc.nextInt();
        System.out.println("请输入边数：");
        arcNum = sc.nextInt();
        // 创建顶点
        vexs = new Object[vexNum];
        System.out.println("请分别输入图的各个顶点：");
        for (int i = 0; i < vexNum; i++) {
            vexs[i] = sc.next();
        }
        // 创建边
        arcs = new int[vexNum][vexNum];
        for (int i = 0; i < vexNum; i++) {
            // 初始化网
            for (int j = 0; j < vexNum; j++) {
                arcs[i][j] = INFINITY;
            }
        }
        System.out.println("请输入各个边的两个顶点，及其权值");
        for (int i = 0; i < arcNum; i++) {
            int v = localVex(sc.next());
            int u = localVex(sc.next());
            arcs[v][u] = sc.nextInt();
        }
    }

    private void createUDG() {
        /*
        无向图
         */
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入顶点数：");
        vexNum = sc.nextInt();
        System.out.println("请输入边数：");
        arcNum = sc.nextInt();
        // 创建顶点
        vexs = new Object[vexNum];
        System.out.println("请分别输入图的各个顶点：");
        for (int i = 0; i < vexNum; i++) {
            vexs[i] = sc.next();
        }
        // 创建边
        arcs = new int[vexNum][vexNum];
        for (int i = 0; i < vexNum; i++) {
            // 初始化网
            for (int j = 0; j < vexNum; j++) {
                arcs[i][j] = 0;
            }
        }
        System.out.println("请输入各个边的两个顶点");
        for (int i = 0; i < arcNum; i++) {
            int v = localVex(sc.next());
            int u = localVex(sc.next());
            arcs[v][u] = arcs[u][v] = 1;
        }

    }

    private void createDG() {
        /*
        有向网
         */
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入顶点数：");
        vexNum = sc.nextInt();
        System.out.println("请输入边数：");
        arcNum = sc.nextInt();
        // 创建顶点
        vexs = new Object[vexNum];
        System.out.println("请分别输入图的各个顶点：");
        for (int i = 0; i < vexNum; i++) {
            vexs[i] = sc.next();
        }
        // 创建边
        arcs = new int[vexNum][vexNum];
        for (int i = 0; i < vexNum; i++) {
            // 初始化网
            for (int j = 0; j < vexNum; j++) {
                arcs[i][j] = INFINITY;
            }
        }
        System.out.println("请输入各个边的两个顶点");
        for (int i = 0; i < arcNum; i++) {
            int v = localVex(sc.next());
            int u = localVex(sc.next());
            arcs[v][u] = 1;
        }
    }

    @Override
    public int getVexNum() {
        return vexs.length;
    }

    @Override
    public int getArcNum() {
        return arcNum;
    }

    @Override
    public Object getVex(int v) {
        return vexs[v];
    }

    @Override
    public int localVex(Object vex) {
        for (int i = 0; i < vexNum; i++) {
            if (vexs[i].equals(vex))
                return i;
        }
        return -1;
    }

    @Override
    public int firstAdjVex(int v) {
        /*
        返回第一个邻接点
        这样的一个类，所代表的图，可能是图或者带权图，所以判断条件为不等于0且小于INFINITY
         */
        for (int j = 0; j < vexNum; j++) {
            if (arcs[v][j] != 0 && arcs[v][j] < INFINITY)
                return j;
        }
        return -1;
    }

    @Override
    public int nextAdjVex(int v, int w) {
        for (int j = w+1; j < vexNum; j++) {
            if (arcs[v][j] != 0 && arcs[v][j] < INFINITY)
                return j;
        }
        return -1;
    }
}
