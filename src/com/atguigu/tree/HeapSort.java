package com.atguigu.tree;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by ChengQian on 2020/5/29 10:29
 *
 * 堆排序
 *  1.先将无序队列的转换成大堆顶或者小堆顶
 *  2.栈顶元素与末尾元素交换 将最大元素沉到数组末端
 *  3.重新调整结构 继续交换堆顶元素和当前末尾元素 反复执行操作 直到整个序列有序
 */
public class HeapSort {
    public static void main(String[] args) {
        //数组升序降序
        int[] arr = {4, 6, 8, 5, 9};

      /*  int[] arr = new int[800000];
        for (int i = 0; i<800000; i++){
            arr[i] =(int)(Math.random()*80000);
        }*/
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = dateFormat.format(date);

        System.out.println("开始时间=" + format);
        heapSort(arr);//交换式
//        shellSortFor(arr);//移位式
//        System.out.println("" + Arrays.toString(arr));

        Date date1 = new Date();
        String format1 = dateFormat.format(date1);
        System.out.println("结束时间=" + format1);
        //

    }
    public static void heapSort(int[] arr){
        int temp = 0;
        System.out.println("堆排序");
/*        adjustHeap(arr,1,arr.length);
        System.out.println("第一次="+ Arrays.toString(arr));

        adjustHeap(arr,0,arr.length);
        System.out.println("第二次="+ Arrays.toString(arr));*/
        //完成我们最终的代码
        //讲无序列构建成一个堆 根据升序降序选择大顶堆 或小顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        /**
         * 栈顶元素与末尾元素交换 将最大元素沉到数组末端
         * 重新调整结构
         */
        for (int j = arr.length - 1;j > 0; j--){
            //交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0,j);
        }
        System.out.println("数组=" + Arrays.toString(arr));
    }
    //将一个数组(二叉树) 调整成一个大顶堆
    /**
     *  功能 完成以i对应的非叶子结点 调整成大顶堆
     *  例子 arr[]
     * @param arr 待调整的数组
     * @param i 表示非叶子结点再数组中的索引
     * @param length 表示对多个元素继续调整，length逐渐在减少
     */
    public static void adjustHeap(int[] arr,int i,int length){
        //取出当前值保存在临时变量
        int temp = arr[i];
        //开始调整
        //k = i * 2 +1 k是i结点的左子结点
        for (int k = i * 2 + 1; k < length; k = k * 2+1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {//说明左子结点 的值小于右子节点的值
                k++;//k 指向右子结点
            }
            if (arr[k] > temp) {//子结点大于父结点
                arr[i] = arr[k];//较大值给当前结点
                i = k;//!! i指向k 继续循环比较
            } else {
                break;
            }
        }
        //当for结束 i为父节点的树的最大值 放在了最顶
        arr[i] = temp;//temp放到调整后的位置
    }
}