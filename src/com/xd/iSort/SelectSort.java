package com.xd.iSort;

public class SelectSort extends iSort {

    public SelectSort(int num, int kind) {
        super(num, kind);
        startTime = System.currentTimeMillis();   //获取开始时间
        selectSort();
        endTime = System.currentTimeMillis(); //获取结束时间
    }

    private void selectSort() {
        for (int i = 0; i < data.length; i++, compareNum++) {
            int min = i;
            for (int j = i; j < data.length; j++, compareNum++) {
                compareNum++;
                if (data[j] < data[min]) {
                    min = j;
                }
            }
            compareNum++;
            if (min != i) {
                int tmp = data[i];
                data[i] = data[min];
                data[min] = tmp;
                moveNum +=2;
            }
        }
    }
}
