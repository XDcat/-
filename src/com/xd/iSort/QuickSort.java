package com.xd.iSort;

public class QuickSort extends iSort {

    public QuickSort(int num, int kind) {
        super(num, kind);
        startTime = System.currentTimeMillis();   //获取开始时间
        quickSort();
        endTime = System.currentTimeMillis(); //获取结束时间
    }

    private void quickSort() {
        qSort(0, data.length - 1);
    }

    private void qSort(int low, int high) {
        /*
        快速排序法的递归：
            如果low < high，则
                确定支点位置，对前面，后面都再递归；
         */
        if (low < high) {
            int middle = partition(low, high);
            qSort(low, middle - 1);
            qSort(middle + 1, high);
        }
    }

    private int partition(int low, int high) {
        /*
        将交换数组中的数组，分为两个部分，并且返回中间值
        low与high确定数组的边界。
         */
        int middle = data[low];  // 记录支点的位置
        while (low < high) {
            /*
            先交换大的，再交换小的。
             */
            compareNum ++;
            while (low < high && middle < data[high]) {
                compareNum += 2;
                high--;
            }
            compareNum ++;
            if (low < high) {
                data[low] = data[high];
                moveNum ++;
                low++;
            }
            while (low < high && middle > data[low]) {
                low++;
                compareNum ++;
            }
            compareNum++;
            if (low < high) {
                data[high] = data[low];
                high--;
                moveNum++;
            }
        }
        /*
        先交换大的后交换小的，所以最后一步和小的有关；
            如果是因为middle < data[low]，则会交换，low则是空的；
            如果是因为low = high, 则空出来的位置就是专门给middle的。
         */
        data[low] = middle;
        return low;
    }
}
