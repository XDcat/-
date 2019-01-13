package com.xd.iSort;

public class BubbleSort extends iSort{

    public BubbleSort(int num, int kind) {
        super(num, kind);
        startTime = System.currentTimeMillis();   //获取开始时间
        bubbleSort();
        endTime = System.currentTimeMillis(); //获取结束时间
    }

    private void bubbleSort() {
        /*
        冒泡排序
        设施flag: 如果在一次序列中没有比较了，则可以提前结束了。
         */
        boolean flag = true;
        for (int i = 1; i < data.length && flag; i++, compareNum += 2) {
            flag = false;
            for (int j = 0; j < data.length - i; j++, compareNum++) {
                if (data[j] > data[j + 1]) {
                    int tmp = data[j];
                    data[j] = data[j+1];
                    data[j + 1] = tmp;
                    flag = false;
                    moveNum += 2;
                }
            }
        }
    }
}
