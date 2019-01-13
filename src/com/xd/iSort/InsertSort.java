package com.xd.iSort;

public class InsertSort extends iSort {


    public InsertSort(int num, int kind) {
        super(num, kind);
        startTime = System.currentTimeMillis();   //获取开始时间
        this.insertSort();
        endTime = System.currentTimeMillis(); //获取结束时间
    }

    public void insertSort() {

        // tmp作为暂存，i作为已经找好的，j作为剩下未排序的。
        int tmp, i, j;
        for (i = 1; i < data.length; i++) {
            // 从1开始是因为第0个没有对比的对象
            tmp = data[i];
            for (j = i - 1; j >= 0 && tmp < data[j]; j--, compareNum = compareNum + 2) {
                data[j + 1] = data[j];
                moveNum++;
            }
            // 需要加1是因为：
            // 如果数据需要到0的位置，则j=-1，必须+1
            // 如果数据不是到0的位置，在上一次j+1与j交换后，j又减1了，它需要到j的位置
            data[j + 1] = tmp;
        }

//        System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
    }
}
