package com.atguigu.arrayStack;

/**
 * Created by GuoChengQian on 2020/5/18 13:26
 */
public class Calcuator2 {
    public static void main(String[] args) {
        //
        String expression = "3+2*6-2";
        ArrayStack4 numStack = new ArrayStack4(10);//数字栈
        ArrayStack4 operStack = new ArrayStack4(10);//符号栈

        int index = 0;//返回结果
        int num1 = 0;//用于计算
        int num2 = 0;
        int oper = 0;//字符
        int res = 0;//计算结果
        char ch = ' ';
        String keepNum = "";//用于辅助变量相加
        while (true){
            ch = expression.substring(index,index+1).charAt(0);
            //判断是不是运算符
            if (operStack.isOper(ch)){
                if (!operStack.isEmpty()){
                    //如果符号栈有操作符，就进行比较,如果当前的操作符的优先级小于或者等于栈中的操作符，
                    // 就需要从数栈中pop出两个数,在从符号栈中pop出一个符号，进行运算，将得到结果，入数栈，然后将当前的操作符入符号栈，
                    // 如果当前的操作符的优先级大于栈中的操作符， 就直接入符号栈.
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1,num2,oper);
                        //将计算结果添加
                        numStack.push(res);
                        //将当前操作符入栈
                        operStack.push(ch);
                    }else{
                        //如果当前的操作符的优先级大于栈中的操作符，就直接入符号栈
                        operStack.push(ch);
                    }
                }else{//栈为空直接添加
                    operStack.push(ch);
                }
            }else{//是数字
                keepNum+=ch;
                //到了最后一位的话直接添加
                if (index == expression.length()-1){
                    numStack.push(Integer.parseInt(keepNum));
                }else{
                    //往后看一位是否是数字
                    if (operStack.isOper(expression.substring(index+1,index+2).charAt(0))){
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum="";
                    }
                }
            }
            index++;//接着拆分字符串
            if (index >= expression.length()){//大于字符串的长度就结束
                break;
            }
        }
        //当扫描完毕
       while (true){
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
class ArrayStack4{
    private int maxSize;
    private int[] stack;
    private int top = -1;

    public ArrayStack4(int maxSize){
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //验证是否为空
    public boolean isEmpty(){
        return top == -1;
    }
    //验证是否为满
    public boolean isFull(){
        return top == maxSize-1;
    }
    //入栈
    public void push(int data){
        if (isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = data;
    }
    //出栈
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //显示栈顶
    public int peek(){
        return stack[top];
    }
    //显示栈
    public void list(){
        if (isEmpty()){
            throw new RuntimeException("栈空");
        }
        for (int i=top; i<=0;i--){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
    //运算符优先级
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }
    //判断是不是运算符
    public boolean isOper(char var) {
        return var == '*' || var == '/' || var == '+' || var == '-';
    }

    public int cal(int num1,int num2,int oper){
        int res = 0;//总结果
        switch (oper){
            case '+':
                res = num1+num2;
                break;
            case '-':
                res = num2-num1;
                break;
            case '*':
                res = num1*num2;
                break;
            case '/':
                res = num2/num1;
                break;
        }
        return res;
    }
}