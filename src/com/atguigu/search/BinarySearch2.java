package com.atguigu.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GuoChengQian on 2020/5/25 18:43
 */
public class BinarySearch2 {
    public static void main(String[] args) {
        int[] arr = {1,8,10,89,1000,1000,1234};
//        int i = BinarySearch1(arr, 0, arr.length-1, 10);
//        System.out.println(i);
        List<Integer> integers = BinarySearch2(arr, 0, arr.length - 1, 1000);
        System.out.println(integers);
    }

    public static int BinarySearch1(int[] arr,int left,int right,int findVal){
        if (left>right){
            return -1;
        }
        int mid = (left+right)/2;//拿到中间内容
        int midVal = arr[mid];

         if (findVal > midVal){//向右递归
             return BinarySearch1(arr,mid+1,right,findVal);
         }else if (findVal < midVal){//向左移动
             return BinarySearch1(arr,left,mid-1,findVal);
         }else{
             return mid;
         }
    }

    public static List<Integer> BinarySearch2(int[] arr, int left, int right, int findVal){
        if (left>right){ //左大于右 结束程序
            return new ArrayList<Integer>();
        }
        int mid = (left+right)/2;//拿到中间内容
        int midVal = arr[mid];

        if (findVal > midVal){//向右递归
            return BinarySearch2(arr,mid+1,right,findVal);
        }else if (findVal < midVal){//向左移动
            return BinarySearch2(arr,left,mid-1,findVal);
        }else{
            /**
             * 找到mid值 不直接马上返回
             * 向mid 左边扫描
             * 向mid右边扫描
             * 返回list
             */
            List<Integer> resIndexList = new ArrayList<Integer>();
            //向mid左边扫描
            int temp = mid-1;
            while (true){
                //退出
                if (temp <0 || arr[temp]!=findVal){
                    break;
                }
                //否则放到list中
                resIndexList.add(temp);
                temp -= 1;//左移动
            }

            resIndexList.add(mid);

            temp = mid+1;
            while (true){
                if (temp >arr.length-1 || arr[temp]!=findVal){
                    break;
                }
                //否则放到list中
                resIndexList.add(temp);
                temp += 1;//右动
            }
            return resIndexList;
        }
    }

}