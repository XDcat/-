package com.xd.iSearch;

public class binarySearch extends iSearch {

    public binarySearch(int count) {
        super(count);
        cmpKind = "二分查找";
    }

    @Override
    public int search(int x, int i) {
        int count=0;
        int low = 0, high = data.length - 1;  // 边界范围
        while (low < high) {
            count++;
            int mid = (high + low) / 2;
//            System.out.println("" + low + " " + mid + " " + high);
            if (data[mid] == x) {
                count++;
                compareNum[i] = count;
                return mid;
            } else if (data[mid] > x) {
                count += 2;
                high = mid - 1;
            } else {
                count += 3;
                low = mid + 1;
            }
        }
        compareNum[i] = count;
        return -1;
    }
}
