package com.xd.iSort;


public class CompareSort {
    public static void main(String args[]) {
        for (int i :
                new int[]{20, 200, 2000}) {
            for (int j :
                    new int[]{-1, 0, 1}) {
                iSort t1 = new InsertSort(i, j);
                iSort t2 = new ShellSort(i, j);
                iSort t3 = new BubbleSort(i, j);
                iSort t4 = new QuickSort(i, j);
                iSort t5 = new SelectSort(i, j);
                iSort t6 = new HeapSort(i, j);
                iSort t7 = new MergeSort(i, j);
                System.out.println("不同排序方式的比较：数据量=>" + i + ",类型=>" + j);
                System.out.printf("%10s|%10s|%10s|%10s|%10s|%10s|%10s|%10s\n", "数据", "Insert", "Shell", "Bubble", "Quick", "Select", "Heap", "Merge");
                System.out.printf("%10s|%10s|%10s|%10s|%10s|%10s|%10s|%10s\n", "运行时间", t1.getTime(), t2.getTime(), t3.getTime(), t4.getTime(), t5.getTime(), t6.getTime(), t7.getTime());
                System.out.printf("%10s|%10s|%10s|%10s|%10s|%10s|%10s|%10s\n", "比较次数", t1.compareNum, t2.compareNum, t3.compareNum, t4.compareNum, t5.compareNum, t6.compareNum, t7.compareNum);
                System.out.printf("%10s|%10s|%10s|%10s|%10s|%10s|%10s|%10s\n", "交换次数", t1.moveNum, t2.moveNum, t3.moveNum, t4.moveNum, t5.moveNum, t6.moveNum, t7.moveNum);
                System.out.println();
            }

        }
    }
}
