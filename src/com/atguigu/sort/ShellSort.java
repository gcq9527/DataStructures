package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 希尔排序
 * Created by GuoChengQian on 2020/5/21 13:09
 */
public class ShellSort {
    public static void main(String[] args) {
//        int[] arr ={8,9,1,7,2,3,5,4,6,0};
//        System.out.println("原始数组=" + Arrays.toString(arr));


        int[] arr = new int[8];
        for (int i = 0; i<8; i++){
            arr[i] =(int)(Math.random()*80000);
        }
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = dateFormat.format(date);

        System.out.println(Arrays.toString(arr));
        ShellSort3(arr);//交换式
//        shellSortFor(arr);//移位式
//        System.out.println("" + Arrays.toString(arr));

        Date date1 = new Date();
        String format1 = dateFormat.format(date1);
        System.out.println("结束时间=" + format1);

    }

    /**
     * 思路(算法) ====> 代码
     * 每次对一整组没有顺序的数据 把他分成两组
     * @param arr
     */
    public static void shellSortFor(int[] arr) {
        int temp = 0;
        int count = 0;
        //每次除2 10/2=5  5/2 =2  2/1 =1
        //每次除2
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    //如果当前元素大于加上步长后的那个元素，说明交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
//            System.out.println("希尔排序第" + (++count) + "轮后=" + Arrays.toString(arr));
        }
    }
    //使用逐步推导方式来编写希尔排序

    /**
     * 外层执行一遍 里层执行多遍去遍历和相对的数去比较
     * @param arr
     */
    public static void shellSort(int[] arr) {
        int temp = 0;
        //希尔排序第一轮
        //因为第一轮排序是将10组数据分成5组
        for (int i= 5; i < arr.length; i++) {
            //遍历各组中的所有元素，(共五组，每5组2个元素) 步长5
            for (int j = i - 5; j >= 0; j -= 5) {
                //如果当前元素大于加上步长后的那个元素，说明交换
                if (arr[j] > arr[j + 5]) {
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }
        System.out.println("希尔排序一轮后" + Arrays.toString(arr));


        //希尔排序第二轮
        //因为第一轮排序是将5组分成2组
        for (int i = 2; i < arr.length; i++) {
            //遍历各组中的所有元素，(共五组，每5组2个元素) 步长5
            for (int j = i - 2; j >= 0; j -= 2) {
                //如果当前元素大于加上步长后的那个元素，说明交换
                if (arr[j] > arr[j + 2]) {
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }
        System.out.println("希尔排序二轮后" + Arrays.toString(arr));
        //希尔排序第三轮
        //因为第一轮排序是将2组分成一组
        for (int i = 1; i < arr.length; i++) {
            //遍历各组中的所有元素，(共五组，每5组2个元素) 步长5
            for (int j = i - 1; j >= 0; j -= 1) {
                //如果当前元素大于加上步长后的那个元素，说明交换
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("希尔排序三轮后" + Arrays.toString(arr));
    }

    //对交换式希尔排序的优化--》移位法
    public static void ShellSort(int[] arr) {
        //增量gap 逐步缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素 逐步对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];//数组中的结果占时放到临时变量 要插入的数
                if (arr[j] < arr[j - gap]) { //j的位置小于 j-gap的位置就交换
                    //temp是j的临时变量 在循环条件中判断是不是小于要转换的值
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        /**
                         * 0和5 j =5 gap = 5  数组的0位和五位进行比较并替换
                         */
                        arr[j] = arr[j - gap];//交换位置 将j的位置变成j-gap的值
                        j -= gap;//j = j-gap 为j找好插入的位置
                    }
                    //退出while循环后 就给temp找到插入的位置
                    arr[j] = temp;
                }
            }
        }
    }
    public static void ShellSort3(int[] arr){
        int temp = 0;
        for (int i=5; i<arr.length;i++){
            for (int j=i-5; j>=0; j-=5){
                if (arr[j] > arr[j+5]){
                    temp = arr[j];
                    arr[j] = arr[j+5];
                    arr[j+5] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}