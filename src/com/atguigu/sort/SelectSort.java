package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 选择排序
 * Created by GuoChengQian on 2020/5/20 16:12
 */
public class SelectSort {
    public static void main(String[] args) {
      //  int[] arr = new int[]{101,34,119,1};
        //测试80000
        int[] arr = new int[80000];
        for (int i = 0; i<80000; i++){
            arr[i] =(int)(Math.random()*80000);
        }
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = dateFormat.format(date);
        System.out.println("开始时间=" +format );

        selectSort(arr);

        Date date1 = new Date();
        String format1 = dateFormat.format(date1);
        System.out.println("结束时间=" + format1);

//        System.out.println("排序前");
//        selectSort(arr);
//        System.out.println("排序后");
//        System.out.println(Arrays.toString(arr));


    }

    //选择排序
    public static void selectSort(int[] arr){
        //使用逐步推导的方式来,讲解选择排序
        //第一轮
        /**
         * 原始的数组： 101,34,119,1
         * 第一轮排序: 1,34,119,101
         * 算法 先简单 =>做复杂 就是可以把一个复杂的算法 拆分成简单的问题，=>逐步解决
         */
        for (int i = 0; i < arr.length - 1; i++) {
            int min = arr[i];//定义一个最小数
            int minIndex = i;//最小数的索引
            //为什么要从0+1开始 因为假定第一位是最小的数
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {//说明我们假定最小的值 并不是最小
                    min = arr[j]; //重置min的值
                    minIndex = j;//重置index
                }
            }
            if (minIndex != 0) {
                //将最小值 放在arr[0] 交换
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
/*        System.out.println("第一轮");
        System.out.println(Arrays.toString(arr));//[1, 34, 119, 101]*/
        }
/*
        min = arr[1];//最小数
       minIndex = 1;//最小数的索引
        //第一轮
        //为什么要从0+1开始 因为假定第一位是最小的数
        for (int j=1+1; j<arr.length; j++){
            if (min > arr[j]){//说明我们假定最小的值 并不是最小
                min = arr[j]; //重置min的值
                minIndex = j;//重置index
            }
        }

        if (minIndex!=1) {
            //将最小值 放在arr[0] 交换
            arr[minIndex] = arr[1];arr[1]是之前定义最小的值
            arr[1] = min; 之前定义最小的值等于 当前找到最小数
        }
        System.out.println("第二轮");
        System.out.println(Arrays.toString(arr));//[1, 34, 119, 101]


        min = arr[2];//最小数
        minIndex = 2;//最小数的索引
        //第一轮
        //为什么要从0+1开始 因为假定第一位是最小的数
        for (int j=1+2; j<arr.length; j++){
            if (min > arr[j]){//说明我们假定最小的值 并不是最小
                min = arr[j]; //重置min的值
                minIndex = j;//重置index
            }
        }

        if (minIndex!=2) {
            //将最小值 放在arr[0] 交换
            arr[minIndex] = arr[2];
            arr[2] = min;
        }
        System.out.println("第三轮");
        System.out.println(Arrays.toString(arr));//[1, 34, 101, 119]*/
    }
}