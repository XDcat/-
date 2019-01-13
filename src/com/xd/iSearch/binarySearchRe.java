package com.xd.iSearch;

public class binarySearchRe extends iSearch {
    int countCompare;
    public binarySearchRe(int count) {

        super(count);
        cmpKind = "二分查找（递归）";
    }

    @Override
    public int search(int x, int i) {
        // 递归查找
        countCompare = 0;
        int res = searchRe(0, data.length - 1, x);  // 调用递归
        compareNum[i] = countCompare;
        return res;
    }

    private int searchRe(int min, int max, int x) {
        /*
        如果不存在，即min>=max，直接跳出；
        如果存在，继续递归；
         */
        countCompare++;
        if (min >= max) {
            return -1;  // 不存在，则递归
        } else {
            countCompare++;
            int mid = (min + max) / 2;
            if (data[mid] == x) {
                return mid;  // 找到了，返回
            } else {
                // 没有找到，继续递归
                countCompare++;
                if (data[mid] > x) {
                    return searchRe(min, mid - 1, x);
                } else {
                    return searchRe(mid + 1, max, x);
                }
            }

        }
    }
}
