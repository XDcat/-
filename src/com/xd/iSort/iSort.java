package com.xd.iSort;

import java.io.*;
import java.util.ArrayList;

public abstract class iSort {
    // 基本的信息
    public int num;
    public String type;
    public int[] data;
    // 算法比较的信息
    public long startTime;  // 开始时间
    public long endTime;  // 结束时间
    public int compareNum = 0;  // 比较次数
    public int moveNum = 0;

    public iSort(int num, int kind) {
        this.num = num;
        this.data = readData(num, kind);
        switch (kind) {
            case 0:
                type = "随机";
                break;
            case 1:
                type = "正序";
                break;
            case -1:
                type = "逆序";
                break;
        }
    }

    public void display() {
        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i]);
        }
        System.out.println(String.format("move:%s, compare:%s", moveNum, compareNum));
        System.out.println("运行时间：" + getTime());
        cheack();
    }
    public long getTime(){
        return endTime - startTime;
    }

    protected void cheack() {
        for (int i = 0; i < data.length - 1; i++) {
            if (data[i] > data[i + 1]) {
                System.out.println("不满足升序要求！");
                return;
            }
        }
        System.out.println("满足升序要求···");
    }

    public int[] readData(int num, int kind) {
        /*
        读取data中的随机数：
            1. num：数目（20、200、2000）
            2. kind：类型（0-随机、1-正序、-1-逆序）
         */
        // 更具类型形成路径
        String path = "";
        switch (kind) {
            case 0:
                path = String.format("%s\\src\\com\\xd\\iSort\\data\\%s个%s数据", System.getProperty("user.dir"), num, "随机");
                break;
            case 1:
                path = String.format("%s\\src\\com\\xd\\iSort\\data\\%s个%s数据", System.getProperty("user.dir"), num, "正序");
                break;
            case -1:
                path = String.format("%s\\src\\com\\xd\\iSort\\data\\%s个%s数据", System.getProperty("user.dir"), num, "逆序");
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
}
