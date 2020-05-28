package com.atguigu.search;

/**
 * 线性查找
 * 直接通过循环查找你匹配到就返回结果
 * Created by GuoChengQian on 2020/5/25 13:21
 */
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {1,9,11,-1,34,89};
        int i = OrderFind(arr, 111);
        System.out.println(i);
    }
    public static int OrderFind(int[] arr,int value){
        for (int i=0; i<arr.length;i++){
            if (arr[i] == value){
                return i;
            }
        }
        return -1;
    }
}