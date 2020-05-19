package com.atguigu.arrayStack;

import javafx.util.BuilderFactory;

/**
 * Created by GuoChengQian on 2020/5/17 11:52
 */
public class Calcuator {
    public static void main(String[] args) {
        //根据老师思路，完成表达式的运算
        String expression = "3+2*6-2";//13
        //创建两个栈,数栈 符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义需要的相关变量
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0; //字符
        int res = 0; //返回结果
        char ch = ' ';//将每次扫描得到的存到ch
        String keepNum = "";//用于拼接
//        使用while循环扫描expression
        while(true){
            //依次得到expression通过截取 然后转换成字符
            ch = expression.substring(index,index+1).charAt(0);
            //判断ch是什么 做相应的处理
            if (operStack.isOper(ch)){//如果是运算符
                //判断当前符号栈是否为空
                if (!operStack.isEmpty()){
                    //如果符号栈有操作符，就进行比较,如果当前的操作符的优先级小于或者等于栈中的操作符，
                    // 就需要从数栈中pop出两个数,在从符号栈中pop出一个符号，进行运算，将得到结果，入数栈，然后将当前的操作符入符号栈，
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())){//peek查看栈顶是的值
                        num1 = numStack.pop();//取出两个数
                        num2 = numStack.pop();
                        oper = operStack.pop();//取出符号
                        res = numStack.cal(num1,num2,oper);//进行计算
                        //把运算符的结果入数字栈
                        numStack.push(res);
                        //然后将当前的操作符入符号栈
                        operStack.push(ch);
                    }else{
                        //如果当前的操作符的优先级大于栈中的操作符，就直接入符号栈
                        operStack.push(ch);
                    }
                }else{
                    //如果为空直接入字符栈
                    operStack.push(ch);//1+3
                }
            }else{
                //如果是数字 直接入数栈
         /*       System.out.println(ch + "---" );
                System.out.println(ch-48);*/
                 //ASCII码 1为48 减48拿到数字
                //1.在处理多位数时，不能发现是一个数就立即入栈 因为他可能是多位数
                //2.在处理数，需要向expression的表达式的index后 再看一位 如果是数就进行扫描 如果是符号就入栈
                //3.因从我们需要定义一个变量字符串变量，用于拼接
                //处理多位数
                keepNum += ch; //临时存放值
                //如果ch已经是expression的最后一位 就直接入栈
                if (index == expression.length() -1){
                    numStack.push(Integer.parseInt(keepNum));
                }else{
                    //往后面看下 下一位字符是不是数字 如果是数字 就继续扫描 如果是运算符 则入栈
                    //往后看以为 不是index++
                    if (operStack.isOper(expression.substring(index+1,index+2).charAt(0))){
                        //后一位是运算符,则入栈
                        numStack.push(Integer.parseInt(keepNum));
                        //重要！！！ keepNum
                        keepNum = "";
                    }
                }
            }
            //让index + 1 并判断是否扫描到expression最后
            index++;//接着拆分字符串
            if (index >= expression.length()){//大于字符串的长度就结束
                break;
            }
        }
        //当扫描完毕
        while(true){
            //如果符号栈为空 不用计算
            // 否则计算到最后的结果 数栈中只有一个[数字]结果
            if (operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();//符号
            res = numStack.cal(num1,num2,oper);
            numStack.push(res);//入栈
        }
        //将数栈最后的数 pop出来 就是结果
        int res2 = numStack.pop();
        System.out.printf("表达式%s = %d",expression,res2);

    }
}
//定义一个ArrayStack 表示是一个栈
class ArrayStack2 {
    private int maxSize; //栈的大小
    private int[] stack;//数组，数组模拟栈 数据就放在数组中
    private int top = -1;//top表示栈顶 初始化为1

    //构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }
    //可以返回当前栈顶的值 不是真正的pop
    public int peek(){
        return stack[top];
    }

    //栈满了
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        //先判断栈是否为空
        if (isFull()) {
            System.out.println("栈满了");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈  将栈顶的数据返回
    public int pop() {
        //先判断栈是否为空
        if (isEmpty()) {
            throw new RuntimeException("栈空 没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //显示栈的情况，遍历时 我们要从栈顶开始遍历
    public void list() {
        if (isEmpty()) {
            System.out.println("栈为空, 没有数据");
            return;
        }
        //需要从栈顶输出数据
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }

    }

    //返回运算符的优先级 优先级是程序员来确定的 优先级使用数字来表示
    //数字越大 则优先级越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1; //假定目前的表达式值只有 +,-,*,/
        }
    }
    //判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }
    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0;//res 用于存放返回结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;//注意顺序
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }

}
