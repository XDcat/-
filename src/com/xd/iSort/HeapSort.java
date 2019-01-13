package com.xd.iSort;

public class HeapSort extends iSort {

    public HeapSort(int num, int kind) {
        super(num, kind);
        startTime = System.currentTimeMillis();   //获取开始时间
        heapSort();
        endTime = System.currentTimeMillis(); //获取结束时间
    }

    private void bulidMaxHeap(int low, int high) {
        /*
        build大顶堆；
        采用逐层逐个比较，从前往后取，从后往前比较；
        两个循环：
            1. 从前往后取：遍历所有的节点
            2. 从后往前比较：看时候需要交换
         */
        for (int i = low + 1; i <= high; i++) {
            // 1. 从前往后取：遍历所有的节点
            for (int j = i; j > low; j = (j-1) / 2, compareNum++) {
                // 2. 从后往前比较：看时候需要交换
                // 如果：叶节点小于父节点，则跳出循环
                // 否则：交换
                compareNum++;
                if (data[j] < data[(j - 1) / 2]) {
                    break;
                }
                int tmp = data[j];
                data[j] = data[(j - 1) / 2];
                data[(j - 1) / 2] = tmp;
                moveNum +=2;
            }
        }
    }

    private void heapSort() {
        /*
        堆排序
        每次建造一个大顶堆，就可以得到最大值，然后与最后的值交换位置，缩小范围；
        重复这样做，最后将得到递增序列；
         */
        int low = 0, high = data.length - 1, tmp;
        while (high > 0) {
            bulidMaxHeap(low, high);  // 建堆
            // 交换排序
            tmp = data[low];
            data[low] = data[high];
            data[high] = tmp;
            high--;  // 缩小范围
            moveNum += 2;
        }
    }
}
