package com.atguigu.tree;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by ChengQian on 2020/5/27 20:45
 */
public class Test {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
 /*       int number;
        int temp = 0;
        Scanner in = new Scanner(System.in);
        for (int i = 1; i <= 10; i++) {
            System.out.print("输入第" + i + "个数：");
            number = in.nextInt();
            if (number > temp ){
                temp = number;
            }
        }
        System.out.print("这10个数中最大的是：" + temp);*/
//        function();
//        fun2();

     /*   Scanner scan = new Scanner(System.in);
        System.out.println("请输入一个数字");
        int num = scan.nextInt();
        num += num * 0.2;
        System.out.println(num);*/
        fun3();
    }

    public static void function(){
        /**
         * 建立一个Guess类。建立getGuessData方法。
         *
         * 随机生成一个数。在通过键盘输入一个数。
         *
         * 如果比随机生成的数大，显示“猜错了，比这个数小”。
         *
         * 如果比随机生成的数小，显示“猜错了，比这个数大”。
         *
         * 如果相等，显示“恭喜你，答对了”
          */
        Random random = new Random();
        int randomNumber = random.nextInt(100); //获取 0-100之间的随机数
        Scanner scanner = new Scanner(System.in);
        System.out.println("随机数="+ randomNumber);

        System.out.printf("请输入一个数");
        int number = scanner.nextInt();
        if(number == randomNumber){
            System.out.println("数相等 答对啦");
        }else if (number > randomNumber){
            System.out.printf("答错啦 数大了");
        }else{
            System.out.printf("答错啦 数小了");
        }
    }

    public static void fun2(){
        /**
         * 张刚想买一部手机，他询问了5家店的电脑价格，分别是4000、3800、5000、6000、10000。请用数组编程求出最低的电脑价格
         */
        int[] arr = new int[]{4000,3800,5000,6000,10000};
        int min = arr[0];
        for (int i = 1; i<arr.length; i++){
           if(min > arr[i]){
               min = arr[i];
           }
        }
        System.out.printf("最小值为%s",min);
    }
    public static void fun3(){
        /**
         * 通过键盘输入一个url网址。获得地址中的协议名：如网址是：http://www.bdqn.com 则获得http,如果是ftp://127.0.0.1,则获得ftp
         */
       /* Scanner input = new Scanner(System.in);
        System.out.println("请输入网址");
        String str = input.next();
        String res = str.substring(0,4);
        System.out.println("前4位是:"+res);*/


        Scanner sc = new Scanner(System.in);
        System.out.println("请输入购物金额");
        double shop = sc.nextDouble();
        System.out.println("请输入积分");
        int fen = sc.nextInt();
        if (fen < 2000) {
            shop *= 0.9;
            System.out.println("目前消费" + shop + "元");
        } else if (fen >= 2000 && fen <= 4000) {
            shop *= 0.8;
            System.out.println("目前消费" + shop + "元");
        } else if (fen <= 8000 && fen > 4000) {
            shop *= 0.75;
            System.out.println("目前消费" + shop + "元");
        } else if (fen > 8000) {
            shop *= 0.7;
            System.out.println("目前消费" + shop + "元");
        } else {
            System.out.println("抱歉没有折扣");
        }
    }


}
