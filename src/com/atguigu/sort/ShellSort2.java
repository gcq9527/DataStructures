package com.atguigu.sort;

import java.util.Arrays;

/**
 * Created by GuoChengQian on 2020/5/22 8:50
 */
public class ShellSort2 {
    public static void main(String[] args) {
        int[] arr ={8,9,1,7,2,3,5,4,6,0};
        ShellSort2(arr);
        System.out.println(   Arrays.toString(arr));
    }

    public static void ShellSortFor(int[] arr) {
        int temp = 0;
        int count = 0;
        //进行分组 每组除2 记录下标一定增量分组
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    //当前元素大于加上步长后的元素 就交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            System.out.println("希尔排序 第" + (++count) + "轮结果为" + Arrays.toString(arr));

        }
    }

    public static void ShellSort2(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;//
                int temp = arr[j];//临时存储arr[j]
                if (arr[j] < arr[j - gap]) {//arr[j]小于 arr[j-gap] gap是外层循环的值
                    while (j - gap >= 0 && temp < arr[j - gap]) {//用临时变量temp 小于 arr[j-gap] 就交换位置
                        arr[j] = arr[j - gap];
                        j -= gap;//位置改变
                    }
                    //循环结束后
                    arr[j] = temp;
                }
            }
        }
    }

}