package com.atguigu.search;

import java.util.Arrays;

/**
 * Created by GuoChengQian on 2020/5/26 13:13
 */
public class InSertSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i=0; i<100;i++){
            arr[i]=i+1;
        }
        System.out.println(Arrays.toString(arr));
        int i = insertValueSearch(arr, 0, arr.length - 1, 10);
        System.out.println(i);
    }

    /**
     *
     * @param arr 数组
     * @param left 左边下标
     * @param right 右边下标
     * @param findVal 查找的值
     * @return 没有找到返回-1
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
        System.out.println("查找次数~~~");
        //findVal < arr[0] 比最小的还小
        // findVal > arr[arr.length-1] 比最大的大
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }
        //求出mid
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal) {//右边递归
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) { //左边递归
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }
}