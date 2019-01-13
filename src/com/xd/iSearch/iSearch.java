package com.xd.iSearch;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public abstract class iSearch {
    int[] compareNum;  // 记录比较次数
    private long[] runTime;  // 记录比较时间
    int count;
    int dataSize = 20000;
    int[] data;
    String cmpKind;

    public iSearch(int count) {
        /*
        比较count次
        默认数据：在2000个数据中查找
         */
        // 根据次数来决定两个数组大小
        this.count = count;
        compareNum = new int[count];
        runTime = new long[count];
    }

    public void run(int kind) {
        /*
        运行的函数
        为了使类可以多次使用,可以来一次顺序，又来一遍逆序
         */
        int res;  // 是否成功
        long startTime, endTime;  // 运行时间
        data = readData(dataSize, kind);
        for (int i = 0; i < count; i++) {
//            System.out.print("次数：" + i);
            startTime = getTime();
            res = search(getRandom(1, dataSize), i);
            endTime = getTime();
            runTime[i] = endTime - startTime;
//            System.out.print(" 用时：" + runTime[i]);
//            System.out.println(compareNum[i]);
        }
        // 数据呈现
        display(kind);
    }

    // 查找的函数
    public abstract int search(int x, int i);

    // 读取文件，获取数组
    private int[] readData(int num, int kind) {
        /*
        读取data中的随机数：
            1. num：数目（20、200、2000）
            2. kind：类型（0-随机、1-正序、-1-逆序）
         */
        // 更具类型形成路径
        String path = "";
        switch (kind) {
            case 0:
                path = String.format("%s\\src\\com\\xd\\iSearch\\data\\%s个%s数据", System.getProperty("user.dir"), num, "随机");
                break;
            case 1:
                path = String.format("%s\\src\\com\\xd\\iSearch\\data\\%s个%s数据", System.getProperty("user.dir"), num, "正序");
                break;
            case -1:
                path = String.format("%s\\src\\com\\xd\\iSearch\\data\\%s个%s数据", System.getProperty("user.dir"), num, "逆序");
                break;
        }
        //  读取文件
        ArrayList<String> arrayList = new ArrayList<>();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(path);
            BufferedReader bf = new BufferedReader(fileReader);
            String string;
            while ((string = bf.readLine()) != null) {
                arrayList.add(string);
            }
            bf.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对ArrayList中存储的字符串进行处理
        int length = arrayList.size();
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            String s = arrayList.get(i);
            array[i] = Integer.parseInt(s);
        }
        // 返回数组
        return array;
    }

    // 返回平均值
    private long getAvg(int[] l) {
        int sum = 0;
        for (int i :
                l) {
            sum += i;
        }
        return sum / l.length;
    }
    private long getAvg(long[] l) {
        long sum = 0;
        for (long i :
                l) {
            sum += i;
        }
        return sum / l.length;
    }

    private int getRandom(int min, int max) {
        int res = (int) (1 + Math.random() * (max - min + 1));
        return res;
    }

    private void setData(int num, int kind) {
        this.data = readData(num, kind);
    }

    private long getTime() {
        return System.nanoTime();   //获取时间
    }
    private void display(int kind) {
        /*
        简明的叙述结果
         */
        String type = (kind == 1) ? "有序" : "无序";
        System.out.printf("\t| %s：%s序列\t平均时间%s(纳秒)\t平均比较次数%s\n", cmpKind, type, getAvg(runTime), getAvg(compareNum));
    }
}
