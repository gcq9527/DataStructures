package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 基数排序
 * Created by GuoChengQian on 2020/5/23 8:32
 */
public class RadixSort {
    public static void main(String[] args) {
//        int[] arr = {53, 3, 542, 748, 14, 214};
//        radixSortFor(arr);


        int[] arr = new int[8];//8百万的数据 时间两秒
        for (int i=0; i<8; i++){
            arr[i] = (int)(Math.random()*80000);
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = simpleDateFormat.format(date);
        System.out.println("当前时间是=" + time);

        radixSort(arr);

        Date date1 = new Date();
        String format = simpleDateFormat.format(date1);
        System.out.println("遍历时间是=" + format);


//        System.out.println("基数排序结果是=" + Arrays.toString(arr));
    }
    public static void radixSortFor(int[] arr){

        //根据前面的推导过程 可以得到最终的基数排序代码
        int max = arr[0];//假定 0位是最大的
        for (int i=1;i< arr.length; i++){
            if (arr[i] > max){
                max = arr[i];
            }
        }
        //得到最大数是几位数
        int maxLength = (max + "").length();

        //定义一个二维数组 表示10个桶
        //1.二维数组包含10个1维数组
        //2.为了防止在入数的时候，数据溢出，则每一个一维数组(桶) 大小定义为arr.length-1
        //3.明确 基数排序是空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中 实际存放了多少个数据，我们定义一个一维数组来记录各个桶的每次放入数据个数
        //比如 bucketElementCounts[0] ,记录的就是bucket[0]桶放入数据个数
        int[] bucketElementCounts = new int[10];

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            // 针对每个元素的对应的位进行 排序处理  第一次个位 第二次 十位 第三次 百位
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素的各位的值
                int digitOfElement = arr[j] / n % 10; //对十位数
                //放到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;//下一个后面 同时也是累计值
            }
            //按照这个桶的顺序(一维数组的下标依次取出数据 放入原来的数组)
            int index = 0;
            //遍历每一桶 并将桶中数据 放入到原数组
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中有数据 我们才放入到原数组中
                if (bucketElementCounts[k] != 0) {
                    //循环该桶即第k个桶 即第k个一维数组 放入
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        //取出元素放到arr  二维数组 k是行 l是列 l是每次从0开始取 而k是累加
                        arr[index++] = bucket[k][l];
                    }
                }
                //第i轮排序后 需要将每个bucketElementtCountsp[k] = 0!!!/
                bucketElementCounts[k] = 0;
            }
//            System.out.println("第" + (i + 1) + "轮对个数排序处理=" + Arrays.toString(arr));
        }

    }

    //基数排序的方法
    public static void radixSort(int[] arr){

        //第一轮排序 针对每个元素的个数进行排序

        //定义一个二维数组 表示10个桶
        //1.二维数组包含10个1维数组
        //2.为了防止在入数的时候，数据溢出，则每一个一维数组(桶) 大小定义为arr.length-1
        //3.明确 基数排序是空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中 实际存放了多少个数据，我们定义一个一维数组来记录各个桶的每次放入数据个数
        //比如 bucketElementCounts[0] ,记录的就是bucket[0]桶放入数据个数
        int[] bucketElementCounts = new int[10];

        //第一轮针对每个元素的个位进行 排序处理
        for (int j = 0;j < arr.length; j++){
            //取出每个元素的各位的值
            int digitOfElement = arr[j] % 10; //对十位数
            //放到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;//下一个后面 同时也是累计值
        }
        //按照这个桶的顺序(一维数组的下标依次取出数据 放入原来的数组)
        int index = 0;
        //遍历每一桶 并将桶中数据 放入到原数组
        for (int k = 0; k<bucketElementCounts.length;k++){
            //如果桶中有数据 我们才放入到原数组中
            if (bucketElementCounts[k] != 0){
                //循环该桶即第k个桶 即第k个一维数组 放入
                for (int l = 0; l<bucketElementCounts[k]; l++){
                    //取出元素放到arr  二维数组 k是行 l是列 l是每次从0开始取 而k是累加
                    arr[index++] = bucket[k][l];
                }
            }
            //第一轮排序后 需要将每个bucketElementtCount[k] = 0!!!/
            bucketElementCounts[k] = 0;
        }
        System.out.println("第一轮对个数排序处理="+ Arrays.toString(arr));

        //==================================================
        //第一轮针对每个元素的个位进行 排序处理
        for (int j = 0;j<arr.length; j++){
            //取出每个元素的十位的值
            int digitOfElement = arr[j] /10 %  10; //对十位数 748 / 10 ==》74 % 10 => 4
            //放到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;//下一个后面
        }
        //按照这个桶的顺序(一维数组的下标依次取出数据 放入原来的数组)
         index = 0;
        //遍历每一桶 并将桶中数据 放入到原数组
        for (int k = 0; k<bucketElementCounts.length;k++){
            //如果桶中有数据 我们才放入到原数组中
            if (bucketElementCounts[k] != 0){
                //循环该桶即第k个桶 即第k个一维数组 放入
                for (int l = 0; l<bucketElementCounts[k]; l++){
                    //取出元素放到arr
                    arr[index++] = bucket[k][l];
                }
            }
            //第二轮排序后 需要将每个bucketElementtCountsp[k] = 0!!!/
            bucketElementCounts[k] = 0;
        }
        System.out.println("第二轮对个数排序处理="+ Arrays.toString(arr));

        //==================================================
        //第一轮针对每个元素的个位进行 排序处理
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的百位的值
            int digitOfElement = arr[j] / 100 % 10; //对十位数 748 / 10 ==》74 % 10 => 4
            //放到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;//下一个后面
        }
        //按照这个桶的顺序(一维数组的下标依次取出数据 放入原来的数组)
        index = 0;
        //遍历每一桶 并将桶中数据 放入到原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据 我们才放入到原数组中
            if (bucketElementCounts[k] != 0) {
                //循环该桶即第k个桶 即第k个一维数组 放入
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放到arr
                    arr[index++] = bucket[k][l];
                }
            }
        }
        System.out.println("第三轮对个数排序处理=" + Arrays.toString(arr));
    }
}