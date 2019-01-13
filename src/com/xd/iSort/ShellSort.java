package com.xd.iSort;

public class ShellSort extends iSort{

    public ShellSort(int num, int kind) {
        super(num, kind);
        startTime = System.currentTimeMillis();   //获取开始时间
        shellSort();
        endTime = System.currentTimeMillis(); //获取结束时间
    }
    public void shellSort(){
        /*
        希尔排序
        k = n / 2 向下取整
         */
        int i, j, k, tmp;
        for (k = data.length / 2; k > 0; k = k / 2) {
            // 类似的直接插入排序
            for (i = k; i < data.length; i ++) {
                /*
                在看算法的时候，的确有分组的思想，但是在实际操作过程中，只需要在一次循环中，跨组全部运行就可以了。
                为什么从K开始：与直接插入排序类似，不考虑0；
                其余的部分完全参考直接插入排序；
                 */
                tmp = data[i];
                for (j = i - k; j >= 0 && tmp < data[j]; j -= k, compareNum += 2) {
                    data[j + k] = data[j];
                    moveNum ++;
                }
                data[j + k] = tmp;
            }
        }
    }
}
