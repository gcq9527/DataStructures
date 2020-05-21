package com.atguigu.sort;

import org.omg.PortableInterceptor.INACTIVE;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
/**
 * Created by GuoChengQian on 2020/5/20 13:06
 */
public class BubbleSort {
    public static void main(String[] args) {
//        int[] arr = {3,9,-1,10,-2};
//        int[] arr = {1,2,3,4,5,6};

        /**
         * 测试
         * 8万个数排序大约需要11秒时间
         */
        int[] arr = new int[80000];
        for (int i = 0; i<80000; i++){
            arr[i] =(int)(Math.random()*80000);
        }
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = dateFormat.format(date);
        System.out.println("开始时间=" +format );

        BubbleSort(arr);

        Date date1 = new Date();
        String format1 = dateFormat.format(date1);
        System.out.println("结束时间=" + format1);
    }

  /*      //第一趟排序 将最大数放到最后
        int temp = 0;//临时变量
        for (int i=0; i<arr.length -1;i++){
            //如果前面的数比后面的大 就交换
            if (arr[i] > arr[i+1]){
                temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
            }
        }
        System.out.println("第一次排序");
        System.out.println(Arrays.toString(arr));

        //第二趟排序，就是将第二大得数放到第二位
        //arr.length-1-1就是他最后一位不参加排序
        for (int i=0; i<arr.length -1-1;i++){
            //如果前面的数比后面的大 就交换
            if (arr[i] > arr[i+1]){
                temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
            }
        }
        System.out.println("第二排序");
        System.out.println(Arrays.toString(arr));


        //第二趟排序，就是将第二大得数放到第二位
        //arr.length-1-1就是他最后一位不参加排序
        for (int i=0; i<arr.length -1-2;i++){
            //如果前面的数比后面的大 就交换
            if (arr[i] > arr[i+1]){
                temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
            }
        }
        System.out.println("第三排序");
        System.out.println(Arrays.toString(arr));

        //第二趟排序，就是将第二大得数放到第二位
        //arr.length-1-1就是他最后一位不参加排序
        for (int i=0; i<arr.length -1-3;i++){
            //如果前面的数比后面的大 就交换
            if (arr[i] > arr[i+1]){
                temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
            }
        }
        System.out.println("第四排序");
        System.out.println(Arrays.toString(arr));*/

  public static void BubbleSort(int[] arr){
      boolean flag = false;
      for (int j=0; j<arr.length-1; j++){
          int temp = 0;//临时变量
          //arr.length-1-1就是他最后一位不参加排序 因为已经排序了
          for (int i=0; i<arr.length - 1 - j;i++){
              //如果前面的数比后面的大 就交换
              if (arr[i] > arr[i+1]){
                  flag = true;
                  temp = arr[i];
                  arr[i] = arr[i+1];
                  arr[i+1] = temp;
              }
          }
          if (!flag){//说明一次交换都没有发生
              break;
          }else{
              flag = true;//重置flag 进行下次判断
          }
/*          System.out.println("第"+(j+1)+"次排序");
          System.out.println(Arrays.toString(arr));*/
      }
    }


}
