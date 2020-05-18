package com.atguigu.arrayStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by GuoChengQian on 2020/5/17 19:20
 */
public class PolandNotation {
    public static void main(String[] args) {
        //完成中缀表达式转成后缀表达式的功能
        //1.1+((2+3)x4)-5 ==> 123+4x+5-
        //2.直接对str操作 不方便 先将表达式转换称成为 中缀表达式对应的List
        // 即 将 1+((2+3)x4)-5 => ArrayList [1,+,(,(,2,+,3,),*,,4,),5,-]
        //3.将得到的中缀表达式对应的list  => 后缀表达式对应的list
        //即 Arraylist [1, +, (, (, 2, +, 3, ), *, 4, ), -, 5]

        String expression = "1+((2+3)*4)-5";
        List<String> strings = toInfixExpressionList(expression);//字符串转换成为中缀表达式
        System.out.println("中缀表达式:" + strings);//[1, +, (, (, 2, +, 3, ), *, 4, ), -, 5]
        List<String> strings1 = parseSuffixExpressionList(strings);
        System.out.println("后缀表达式:"+strings1);//[1, 2, 3, +, 4, *, +, 5, -]

        System.out.println("后缀表达式计算的结果视" + calculate(strings1));
        /*
        String suffixExpression = "30 4 + 5 * 6 -";
        //思路
        //1.先将 表达式存放到 =>放到ArrayList中
        //2.将ArrayList 传递一个方法 遍历ArrayList 配合栈 完成计算
        List<String> list = getListString(suffixExpression);
        System.out.println("renList=" + list);

        int res = calculate(list);
        System.out.println("计算的结果是=" + res);

         */
    }

    //3.将得到的中缀表达式对应的list  => 后缀表达式对应的list
    //方法 将得到的中缀表达式对应的list => 后缀表达式对应的list
    public static List<String> parseSuffixExpressionList(List<String> ls){
        //定义两个栈
        Stack<String> s1 = new Stack<String>();//符号栈
        List<String> s2 = new ArrayList<String>();//数值栈

        //遍历ls
        for (String item : ls){
            //如果是一个数 加入s2
            if (item.matches("\\d+")){
                s2.add(item);//加入到数值栈中
            }else if (item.equals("(")){//匹配的是一个左符号
                s1.push(item);//放到符号栈中
            } else if (item.equals(")")) {
                //如果是右括号 则依次弹出s1栈顶的运算符 并压入s2，知道遇到左括号位置,此时将这一对括号丢弃
                //peek查看栈顶元素
                //这里是查看符号栈的元素 如果不等于左括号 就把s1栈中元素添加到数字栈中
                while(!s1.peek().equals("(")){
                    s2.add(s1.pop());//把s1的内容弹出 放到s2中 直到遇到了左括号
                }
                s1.pop();//将( 弹出s1栈 消除小括号
            }else{
                //当item的优先级小于s1栈顶运算符 将s1栈顶的运算符弹出并压入到s2中，再次转到(4.1)与s1中新的栈顶运算符相比较；
                while(s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                //将item入栈
                s1.push(item);
            }
        }
        //将s1中剩余的运算符依次弹出并加入s2
        while(s1.size() != 0){
            s2.add(s1.pop());
        }
        return s2;//存档到list中所以返回
    }

    //方法将中缀表达式转成对应的List
    //s = "1+((2+3)*4)-5"
    public static List<String> toInfixExpressionList(String s) {
        //定义一个List 存放中缀表达式 对应的内容
        List<String> ls = new ArrayList<String>();
        int i = 0;//指针 用于遍历 中缀表达式字符串
        String str;//多位数的拼接
        char c;//每遍历一个字符就放到c
        do {
            //通过charAt截取字符
            //如果c是一个非数字 需要加入到ls中 Ascll编码表中 小于48 大于57 就不是数字
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                i++;//往后移动
            } else {
                //如果是一个数 需要考虑多位数
                str = "";
                //编码表 大于48 小于57就是数字
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;//拼接
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }



    private static List<String> getListString(String suffixExpression) {
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String ele : split){
            list.add(ele);
        }
        return list;
    }
    //完成对逆波兰表达式的运算
    /**
     * 1)从左至右扫描，将3和4压入堆栈；
     * 2)遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈；
     * 3)将5入栈；
     * 4)接下来是×运算符，因此弹出5和7，计算出7×5=35，将35入栈；
     * 5)将6入栈；
     * 6)最后是-运算符，计算出35-6的值，即29，由此得出最终结果
     */
    public static int calculate(List<String> ls) {
        Stack<String> stack = new Stack<String>();
        //遍历ls
        for (String item : ls) {
            //使用正则表达式 取出数
            if (item.matches("\\d+")) {//匹配多位数
                //入栈
                stack.push(item);
            } else {
                //pop出两个数 并运算 在入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                //匹配对应符号 进行对应的操作
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                //把res入栈
                stack.push(res +"");
            }
        }
        //最后留在stack中的数据式运算结果
        return  Integer.parseInt(stack.pop());
    }
}//编写一个类 Operation 可以返回一个运算符 对应的优先级
class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;
    //写一个方法
    public static int getValue(String operation){
        int result = 0;
        switch (operation){
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "/":
                result = DIV;
                break;
            case "*":
                result = MUL;
                break;
            default:
                System.out.println("不存在改运行符");
                break;
        }
        return result;
    }
}