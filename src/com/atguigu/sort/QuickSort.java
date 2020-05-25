package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 快速排序
 * Created by GuoChengQian on 2020/5/22 12:51
 */
public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = {-9,78,0,23,-567,70};
        int[] arr = new int[8000000];//8百万的数据 时间两秒
        for (int i=0; i<8000000; i++){
            arr[i] = (int)(Math.random()*80000);
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = simpleDateFormat.format(date);
        System.out.println("当前时间是=" + time);

        quickSort(arr,0,arr.length-1);

        Date date1 = new Date();
        String format = simpleDateFormat.format(date1);
        System.out.println("遍历时间是=" + format);
        quickSort(arr,0,arr.length-1);
//        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr,int left,int right){
        int le = left;//左下标
        int r = right;//右下标
        //pivot中轴值
        int pivot = arr[(left+right)/2];//取中间值
        int temp = 0;
        //while循环目的是让 比pivot值小放到左边
        //比prvot值大放到右边
        while(le < r){//左边小于右边
            //在pivot的左边一直找 找到大于等于 pivot的值 才退出
            //左边小于pivot就加 右边大于pivot就减
            while(arr[le] < pivot){//左边值小于中间值
                le+=1;//左加一
            }
            //在pivot的右边一直找 找到小于pivot值 才退出
            while(arr[r] > pivot){//右边值大于中间值 就加一
                r-=1;
            }
            //如果1 >=r 说明pivot 的左右俩的值 已经按照
            // 左边全部是小于pivot的值 右边全部是大于等于pivot的值
            if (le >= r) {
                break;
            }
            //交换值
            temp = arr[le];
            arr[le] = arr[r];
            arr[r] = temp;

            //如果交换完后 发现这个arr[1] == pivot值 相等 r-- 前移
            if (arr[le] == pivot){
                r -= 1;
            }
            //如果交换完 发现这个arr[r] = pivot值 相等 l++ 后移
            if (arr[r] == pivot){
                le += 1;
            }
        }
        //如果l = r 必须 l++ r-- 否则出现栈溢出
        if (le == r ){
            le += 1;
            r -= 1;
        }
        //向左递归
        if (left < r ){
            quickSort(arr,left,r);
        }
        //向右递归
        if (right > le){
            quickSort(arr,le,right);
        }
    }

}













