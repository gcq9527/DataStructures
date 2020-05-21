package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
/**
 * Created by GuoChengQian on 2020/5/21 9:13
 */
public class InsertSort {
    public static void main(String[] args) {
//        int[] arr = new int[]{101,34,119,1,-1};
        int[] arr = new int[8];
        for (int i=0; i<8; i++){
            arr[i] = (int)(Math.random()*80000);
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = simpleDateFormat.format(date);
        System.out.println("当前时间是=" + time);

        insertSortFor(arr);

        Date date1 = new Date();
        String format = simpleDateFormat.format(date1);
        System.out.println("遍历时间是=" + format);

//            System.out.println(Arrays.toString(arr));

    }

    /**
     * 默认是从小到大 从大到小的话 该成 >
     * @param arr
     */
    public static void insertSortFor(int[] arr){
        int insertVal;//待插入的数
        int insertIndex;//待插入的前一个下标

        for (int i=1; i<arr.length; i++){
             insertVal = arr[i];//待插入的数
             insertIndex = i-1;//待插入的前一个下标

            while (insertIndex >= 0 && insertVal > arr[insertIndex]){
                arr[insertIndex+1] = arr[insertIndex];//往后移动一位
                insertIndex--;
            }

            //判断我们是否要赋值
            if (insertIndex+1 != i){
                //位置找到 将临时保存的值 插入
                arr[insertIndex+1] = insertVal;
            }
//            System.out.println("第"+(i+1)+"轮插入");
//            System.out.println(Arrays.toString(arr));
        }
    }
    //插入排序
    public static void insertSort(int[] arr){
        //使用逐步推导的方式 便于理解
        //第一轮{101,34,119,1} =>{34,101,119,1}

        //定义待插入的数
        int insertVal = arr[1];
        int insertIndex = 1-1;//即arr[1]前面的这个数的下标

        //给insertVal 找到插入的位置
        //1.insertIndext >=0 保证在给inserVal找到插入位置 不越界
        //2.insertVal < arr[InsertIndex] 待插入的数 还有没有找到插入的位置
        //3.就需要将arr[insertIndex]后移
        while (insertIndex >= 0 && insertVal < arr[insertIndex]){
            arr[insertIndex + 1] = arr[insertIndex];//往后移动一位
            insertIndex--;
        }
        //当退出while循环 说明插入的位置找到 InsertIndex+1
        arr[insertIndex+1] = insertVal;

        System.out.println("第一轮插入");
        System.out.println(Arrays.toString(arr));

        //第二轮
        insertVal = arr[2];
        insertIndex = 2-1;
        while (insertIndex >= 0 && insertVal < arr[insertIndex]){
            arr[insertIndex + 1] = arr[insertIndex];//往后移动一位
            insertIndex--;
        }

        arr[insertIndex+1] = insertVal;
        System.out.println("第二轮插入");
        System.out.println(Arrays.toString(arr));


        //第二轮
        insertVal = arr[3];
        insertIndex = 3-1;
        while (insertIndex >= 0 && insertVal < arr[insertIndex]){
            arr[insertIndex + 1] = arr[insertIndex];//往后移动一位
            insertIndex--;
        }

        arr[insertIndex+1] = insertVal;
        System.out.println("第三轮插入");
        System.out.println(Arrays.toString(arr));
    }
}
