package com.atguigu.tree;

import java.util.Arrays;

/**
 * Created by ChengQian on 2020/5/29 14:21
 * 堆排序
 * 1.先将无需队列 转换成 大顶堆或小顶堆
 */
public class HeapSort2 {
    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9};
        heapSort(arr);
    }

    public static void heapSort(int[] arr){
        int temp = 0;
        System.out.println("堆排序");

/*        System.out.println("第二轮排序");
        addJustHeap(arr,1,arr.length);
        System.out.println(Arrays.toString(arr));

        System.out.println("第一轮排序");
        addJustHeap(arr,0,arr.length);
        System.out.println(Arrays.toString(arr));*/

        //1.将无序队列转换成 大顶堆 小顶堆
        for (int i=arr.length/ 2 - 1; i >= 0 ;i--){
            addJustHeap(arr, i, arr.length);
        }
//        System.out.println("大顶堆排序=" + Arrays.toString(arr));
        //2.将栈顶元素和栈顶元素进行交换
        for (int j = arr.length - 1; j > 0; j--){
            //交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            addJustHeap(arr, 0, j);
        }
        System.out.println("数组为 = " + Arrays.toString(arr));
    }

    public static void addJustHeap(int[] arr,int i,int length){
        int temp = arr[i];//临时变量保存值

        //i*2+1是i结点的左节点
        for (int k = i * 2 + 1; k < length; k = i * 2 +1){
            if(arr[k] < arr[k+1]){ //左子节点小于右子节点
                k++;
            }
            if (arr[k] > temp){ //子节点小于父节点
                arr[i] = arr[k];//较大值给到当前结点
                i = k;//
            }else{
                break;
            }
        }
        //i为父节点最大值
        arr[i] = temp;
    }
}