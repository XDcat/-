package com.xd.iSort;

public class MergeSort extends iSort {
    public MergeSort(int num, int kind) {
        super(num, kind);
        startTime = System.currentTimeMillis();   //获取开始时间
        mergeSort();
        endTime = System.currentTimeMillis(); //获取结束时间
    }

    private void merge(int[] a, int[] swap, int k) {
        int n = a.length;
        int m = 0, u1, l2, i, j, u2;
        int l1 = 0;                                //第一个有序子数组下界为0
        while (l1 + k <= n - 1) {
            compareNum++;
            l2 = l1 + k;                            //计算第二个有序子数组下界
            u1 = l2 - 1;                            //计算第一个有序子数组上界
            u2 = (l2 + k - 1 <= n - 1) ? l2 + k - 1 : n - 1;        //计算第二个有序子数组上界

            for (i = l1, j = l2; i <= u1 && j <= u2; m++, compareNum += 2) {
                compareNum++;
                if (a[i] <= a[j]) {
                    swap[m] = a[i];
                    i++;
                    moveNum++;
                } else {
                    swap[m] = a[j];
                    j++;
                    moveNum++;
                }
            }

            //子数组2已归并完，将子数组1中剩余的元素存放到数组swap中
            while (i <= u1) {
                compareNum++;
                swap[m] = a[i];
                moveNum++;
                m++;
                i++;
            }

            //子数组1已归并完，将子数组2中剩余的元素存放到数组swap中
            while (j <= u2) {
                compareNum++;
                swap[m] = a[j];
                moveNum++;
                m++;
                j++;
            }

            l1 = u2 + 1;
        }

        //将原始数组中只够一组的数据元素顺序存放到数组swap中
        for (i = l1; i < n; i++, m++, compareNum++) {
            swap[m] = a[i];
            moveNum++;
        }
    }

    private void mergeSort() {
        int i;
        int n = data.length;
        int k = 1;                                    //归并长度从1开始
        int[] swap = new int[n];

        while (k < n) {
            merge(data, swap, k);                        //调用函数merge()

            for (i = 0; i < n; i++, compareNum++) {
                data[i] = swap[i];            //将元素从临时数组swap放回数组a中
                moveNum++;
            }
            k = 2 * k;                                //归并长度加倍
        }
    }
}
