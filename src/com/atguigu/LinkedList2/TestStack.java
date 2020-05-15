package com.atguigu.LinkedList2;

/**
 * Created by GuoChengQian on 2020/5/15 8:21
 */

import java.util.Stack;

/**
 * 栈的基本使用
 */
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack();
        //入栈
        stack.add("jack");
        stack.add("tom");
        stack.add("smith");
        //出栈
        while (stack.size() > 0){
            System.out.println(stack.pop()); //先进后出
        }
    }
}