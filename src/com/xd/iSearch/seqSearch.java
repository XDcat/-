package com.xd.iSearch;

public class seqSearch extends iSearch {


    public seqSearch(int count) {
        super(count);
        cmpKind = "顺序查找";
    }

    @Override
    public int search(int x, int j) {
        /*
        x: 查找的数字
        i: 这是第几次查找
         */
        int count=0;  // 计算比较次数
        int i, n = data.length;
        // 寻找x
        for (i = 0; i < n; i++, count++) {
            count++;
            if (data[i] == x) {
                break;
            }
        }
        // 判断是否找到
        count++;
        compareNum[j] = count;
//        System.out.println(" 比较次数：" + count + " 查找的数字：" + x);
        if (i<n)
            return i;
        else
            return -1;
    }
}
