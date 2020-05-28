package com.atguigu.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找 前提是 数组有序
 * Created by GuoChengQian on 2020/5/25 13:35
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1,8,10,89,1000,1000,1234};
       // List<Integer> integers = binarySearch2(arr, 0, arr.length - 1, 1000);
        int i = binarySearch(arr, 0, arr.length - 1, 1);
        System.out.println(i);
    }

    /**
     *
     * @param arr 数组
     * @param left 左边索引
     * @param right 右边索引
     * @param findVal 要查找的值
     * @return 找到就返回下标 没找到就返回-1
     */
    public static int binarySearch(int[] arr,int left,int right,int findVal){
        //当left小于right 说明递归整个数组但是还没有找到
        if (left > right){
            return -1;
        }
        int mid = (left + right)/2;//中间值
        int midVal = arr[mid];

        //查找的值大于中间值
        if (findVal > midVal){//向右递归
            return binarySearch(arr,mid+1,right,findVal);//mid 加一往后移动一位
        }else if (findVal < midVal) {//小于中间值 向左递归
            return binarySearch(arr,left,mid-1,findVal);//减一 往左边移动
        }else{
            return mid;
        }
    }
    /**
     * 课后思考题
     * {1,8,10,89,1000,1000,1000,1234};
     * 有更多个相同的数值时，如何将所有的数值都查找到 比如1000
     *
     *  思路分析
     *  1.在 找到mid 的索引值  不要马上返回
     *  2.向mid索引值的左边扫描 将所有满足1000 的元素 加入到集合ArrayList
     *  3.向mid索引值的右边扫描 将所有满足1000 的元素 加入到集合ArrayList
     *  4.将ArrayList返回
     */
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {

        System.out.println("hello~");
        // 当 left > right 时，说明递归整个数组，但是没有找到
        if (left > right) {
            return new ArrayList<Integer>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) { // 向 右递归
            return binarySearch2(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) { // 向左递归
            return binarySearch2(arr, left, mid - 1, findVal);
        } else {
//			 * 思路分析
//			 * 1. 在找到mid 索引值，不要马上返回
//			 * 2. 向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
//			 * 3. 向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
//			 * 4. 将Arraylist返回

            List<Integer> resIndexlist = new ArrayList<Integer>();
            //向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
            int temp = mid - 1;
            while(true) {
                if (temp < 0 || arr[temp] != findVal) {//退出
                    break;
                }
                //否则，就temp 放入到 resIndexlist
                resIndexlist.add(temp);
                temp -= 1; //temp左移
            }
            resIndexlist.add(mid);  //

            //向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
            temp = mid + 1;
            while(true) {
                if (temp > arr.length - 1 || arr[temp] != findVal) {//退出
                    break;
                }
                //否则，就temp 放入到 resIndexlist
                resIndexlist.add(temp);
                temp += 1; //temp右移
            }

            return resIndexlist;
        }

    }
}