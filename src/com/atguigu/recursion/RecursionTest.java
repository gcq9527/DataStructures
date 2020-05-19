package com.atguigu.recursion;

/**
 * Created by GuoChengQian on 2020/5/19 9:48
 */
public class RecursionTest {
    public static void main(String[] args) {
//        test(4);
        System.out.println(factorial(3));
    }
    public static void  test(int n){
        if (n>2){
            //4>2  1
            //3>2  2
            test(n-1);
        }
        System.out.println("n="+n);
    }
    public static int factorial(int n){
        if (n==1){
            return 1;
        }else{
            return factorial(n-1)*n;//1*2*3
        }
    }
}