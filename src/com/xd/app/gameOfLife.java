package com.xd.app;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class gameOfLife {
    private int rowSize;
    private int[][] nowLife;

    // 构造方法
    public gameOfLife() {
        // 随机构造
        this(30);
    }

    public gameOfLife(int rowSize) {
        // 确定格子大小
        this(rowSize, rowSize);
    }
    public gameOfLife(int rowSize, int randomNum) {
        // 确定点的数目
        this.rowSize = rowSize;
        nowLife = new int[rowSize][rowSize];
        try{
            // 随机填充
            int[][] randomX = randomCommon(randomNum);  // 二维数组[x, y]
            for (int[] is : randomX) {
                this.nowLife[is[0]][is[1]] = 1;
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    // 获取单个邻居是否存活
    private int getNeighbour(int x, int y) {
        if (x < 0 || y < 0 || x > rowSize - 1 || y > rowSize - 1) {
            return 0;
        } else {
            return nowLife[x][y];
        }
    }

    // 获取邻居存活个数
    private void nextLife() {
        int[][] tmp = new int[rowSize][rowSize];
        for (int x = 0; x < rowSize; x++) {
            // 控制行
            for (int y = 0; y < rowSize; y++) {
                // 控制列
                int sum = 0; // 统计（x, y）有几个存活的邻居
                for (int i = -1; i <= 1; i++) {
                    // 控制行的变化
                    for (int j = -1; j <= 1; j++) {
                        // 控制列的变化
                        if (!(i == 0 && j == 0))
                            sum = sum + getNeighbour(x + i, y + j);
                    }
                }
                // 变化（x, y）
                switch (sum) {
                    case 2: // 若是2， 不变
                        tmp[x][y] = nowLife[x][y];
                        break;
                    case 3: // 若是3， 活
                        tmp[x][y] = 1;
                        break;
                    default: // 其余， 死
                        tmp[x][y] = 0;

                }
            }
        }
        nowLife = tmp;
    }
    // 生成n个随机坐标
    private int[][] randomCommon(int n)throws Exception{
        if (n > rowSize * rowSize){
            // 不能超出格子的数目
            throw new Exception("数目过多");
        }
        int[][] result = new int[n][2]; // 二维数组
        Random random = new Random();
        int count = 0;
        while(count < n){
            int x = random.nextInt(rowSize);
            int y = random.nextInt(rowSize);
            int[] t = new int[2]; t[0] = x; t[1] = y;
            boolean flag = true;
            for (int i = 0; i<count; i++) {
                if (Arrays.equals(t, result[i])){
                    flag = false;
                    break;
                }
            }
            if (flag){
                result[count] = t;
                count ++;
            }
        }
        return result;
    }
    // 是否全死
    public boolean isDie(){
        int t = 0;
        for (int x = 0; x < rowSize; x++) {
            for (int y = 0; y < rowSize; y++) {
                t += nowLife[x][y];
            }
        }
        return t == 0;
    }
    // 打印输出
    public void display() {
        do{
            System.out.println("--------------------------------------------");
            for (int x = 0; x < rowSize; x++) {
                for (int y = 0; y < rowSize; y++) {
                    int t = nowLife[x][y];
                    if (t == 0) {
                        System.out.print(" ");
                    } else {
                        System.out.print("*");
                    }
                }
                System.out.println();
            }
//		System.out.println("--------------------------------------------");
            this.nextLife();
        } while(!isDie());
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("边界大小：");
        int m = sc.nextInt();
        System.out.println("生命个数：");
        int n = sc.nextInt();
        gameOfLife game = new gameOfLife(m, n);
//		while(!input.equals("q")){
        game.display();
//			gameOfLife.nextLife();
//			input = sc.next();
//		}
        sc.close();
    }

}

