package com.xd.iSearch;

public class CompareSearch {
    public static void main(String args[]) {
        // 顺序
        iSearch s1 = new seqSearch(10000);
        // 二分
        iSearch s2 = new binarySearch(10000);
        // 二分且递归
        iSearch s3 = new binarySearchRe(10000);
        // 比较
        System.out.println("比较在有序表和无序表中进行顺序查找:");
        s1.run(0);
        s1.run(1);
        System.out.println("比较在同一有序表中进行顺序查找和二分查找时的时间性能:");
        s1.run(1);
        s2.run(1);
        System.out.println("比较在同一有序表中进行非递归二分查找和递归二分查找时的时间性能:");
        s2.run(1);
        s3.run(1);
    }
}
