package com.atguigu.arrayStack;

import java.util.Scanner;

/**
 * Created by GuoChengQian on 2020/5/16 17:56
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        //测试一下ArrayStack是否正确
        //先创建一个ArrayStack对象 表示栈
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;//控制是否退出菜单
        Scanner sacnner = new Scanner(System.in);

        while (loop){
            System.out.println("show: 表示显示栈");
            System.out.println("ext: 退出程序");
            System.out.println("push: 表示添加数据到栈(入栈)");
            System.out.println("pop: 表示从栈取出数据(出栈)");
            key = sacnner.next();
            switch (key){
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = sacnner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try{
                        int res = stack.pop();
                        System.out.printf("出栈的数据是%d\n",res);
                }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case"exit":
                    sacnner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~~~");
    }
}
//定义一个ArrayStack 表示是一个栈
class ArrayStack{
    private int maxSize; //栈的大小
    private int[] stack;//数组，数组模拟栈 数据就放在数组中
    private int top = -1;//top表示栈顶 初始化为1
    //构造器
    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }
    //栈满了
    public boolean isFull(){
        return top == maxSize-1;
    }

    //栈空
    public boolean isEmpty(){
        return top == -1;
    }
    //入栈
    public void push(int value){
        //先判断栈是否为空
        if (isFull()){
            System.out.println("栈满了");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈  将栈顶的数据返回
    public int pop(){
        //先判断栈是否为空
        if (isEmpty()){
            throw new RuntimeException("栈空 没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }
    //显示栈的情况，遍历时 我们要从栈顶开始遍历
    public void list(){
        if (isEmpty()){
            System.out.println("栈为空, 没有数据");
            return;
        }
        //需要从栈顶输出数据
        for (int i=top; i>=0; i--){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }

    }
}