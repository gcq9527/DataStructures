package com.atguigu.arrayStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Guo
 * Created by GuoChengQian on 2020/5/18 15:59
 */
public class PolandNotation2 {
    public static void main(String[] args) {
        //中缀表达式
        String expression = "1+((2+3)*4)-5";
        //字符串装换成对应的中缀表达式以list格式返回
        List<String> list = toInfixExpressionList(expression);
        System.out.printf("中缀表达式"+list);
        List<String> list2 = parseSuffixExpressionList(list);
        System.out.printf("后缀表达式"+list2);
        int calculate = calculate(list2);
        System.out.printf("计算结果为%d",calculate);

    }

    //后缀表达式
    private static List<String> parseSuffixExpressionList(List<String> list) {
        Stack<String> s1 = new Stack<String>();//符号栈
        List<String> s2 = new ArrayList<String>();//数值栈

        for (String item : list){
            if (item.matches("\\d")){//多个数字
                s2.add(item);//将数值添加到集合中
            }else if (item.equals("(")){//左括号
                s1.push(item);
            }else if (item.equals(")")){
                //如果是右括号“)”，则依次弹出s1栈顶的运算符，
                // 并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());//添加s1弹出的栈顶元素
                }
                //弹出栈顶元素 为括号的
                s1.pop();
            }else{
                //当item的优先级小于s1栈顶运算符 将s1栈顶的运算符弹出并压入到s2中，再次转到(4.1)与s1中新的栈顶运算符相比较；
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                //item入栈
                s1.push(item);
            }
        }
        //遍历完成后 符号栈还有值 弹出 放到 数值栈中
        while (s1.size()!=0){
            s2.add(s1.pop());
        }
        return s2;

    }

    private static List<String> toInfixExpressionList(String s) {
        List<String> list = new ArrayList<String>();
        int i = 0;//辅助遍历
        String str = "";//用于拼接
        char c;//用于接收字符判断
        do {
            //使用charat截取字符0
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                list.add("" + c);
                i++;
            } else {
                //考虑多个数
                str = "";
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str+=c;
                    i++;
                }
                list.add(str);
            }
        } while (i < s.length());
        return list;
    }

    /**
     * 用于计算
     * @param ls
     * @return
     */
    public static int calculate(List<String> ls) {
        int res = 0;//结果
        Stack<String> stack = new Stack<String>();

        for (String item : ls) {
            if (item.matches("\\d+")) {//匹配为多个数字
                stack.push(item);
            } else {//匹配为符号
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                switch (item) {
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num1 - num2;
                        break;
                    case "*":
                        res = num1 * num2;
                        break;
                    case "/":
                        res = num1 / num2;
                        break;
                    default:
                        System.out.println("运算符有误");
                        break;
                }
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
class Operation2{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getValue(String operation){
        int res = 0;
        switch (operation){
            case "-":
                res = SUB;
                break;
            case "+":
                res = ADD;
                break;
            case "*":
                res = MUL;
                break;
            case "/":
                res = DIV;
                break;
            default:
                System.out.println("没有这个操作符");
                break;
        }
        return res;
    }
}