package com.atguigu.recursion;

/**
 * Created by GuoChengQian on 2020/5/19 16:04
 */
public class Queue8 {
    //定义一个max表示多少个皇后
    int max = 8;
    //定义数组array 保存皇后位置的结果
    int[] array = new int[max];
    static int count = 0;
    static int judge = 0;
    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);//第一行第一列
        System.out.printf("打印了%d次\n",count);
        System.out.printf("judge方法调用%d次",judge);
    }

    //特别注意，check是每一次递归时，进入到check中都有  for (int i=0; i<max; i++) 因此会有回溯
    public void check(int n){
        if(n==max){//n = 8 其实8个皇后就依然放好
            print();
            return;
        }
        //依次放入皇后 并判断是否冲突
        for (int i=0; i<max; i++){
            //先把当前这个皇后n,放到该行的第1列
            array[n] = i;
            //判断当放置第n个皇后到i列时，是否冲突
            if (judge(n)){//不冲突
                //接着放n+1皇后，即开始递归
                check(n+1);
            }
            //如果冲突，就继续执行array[n] = i; 即将第n个皇后放置在本行的后移的一个位置
        }
    }
   //查看当我们放置第n个皇后,就去检测该皇后是否和前面以及摆放的皇后冲突
    /**
     *
     * @param n 表示第n个皇后
     * @return
     */
    public boolean judge(int n){
        judge++;
       for (int i=0; i<n; i++){
           //Math.abs绝对值
           //1.array[i] == array[n] 表示判断 第n个黄后 是否和前面n-1皇后在同一列
           //2.Math.abs(n-i) = Math.abs(array[n] - array[i]) 表示判断第n个皇后是否和第i皇后是否在同一斜线
           //n = 1 放置第2列 n = 1 array[1] = 1
           //Math.abs(1-0) == 1 Math.abs(array[n] - array[i]) = Math.abs(1-0) =1
           //判断是否在同一行 没有必要 n每次都在递增
           if (array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n] - array[i])){
               return false;
           }
       }
       return true;
    }

    //将皇后的位置打印出来
    public void print(){
        count++;
        for (int i=0; i<array.length; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
