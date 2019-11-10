package com.atguigu.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰表达式(后缀表达式)（逆波兰计算器）
 */
public class PolandNotation {
    public static void main(String[] args) {
        //先定义逆波兰表达式
        // (3+4)x5-6 => 3 4 + 5 x 6 -
        String suffixExpression ="3      4 + 5 x 6 -";
        /*
        思路：
            1. 先将 "3 4 + 5 x 6 -" => 放到ArrayList中
            2. 将ArrayList传递给一个方法，遍历ArrayList配合栈完成计算
         */
        List<String> rpnList = getListString(suffixExpression);
//        System.out.println(rpnList);
//        System.out.println(rpnList.size());
        System.out.println("运算结果： " + calculate(rpnList));
    }

    //将一个逆序波兰表达式，依次将数据和运算符放到ArrayList中
    public static List<String> getListString(String suffixExpression){
        //将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String element : split) {
            if( element.length() != 0){//过滤掉空值
                list.add(element.trim());
            }
        }
        return list;
    }

    //完成对逆波兰表达式的运算
    public static int calculate(List<String> list){

        //创建栈，只需要一个栈即可
        Stack<String> stack = new Stack<>();
        //遍历
        for (String item : list) {
            //使用正则表达式来验证数字并取出
            if(item.matches("\\d+")){//匹配的多位数
                //入栈
                stack.push(item);
            }else{
                //pop出两个数，并运算，再将结果入栈
                int num2 = Integer.parseInt(stack.pop());//先出栈的当作第二个数字
                int num1 = Integer.parseInt(stack.pop());//先出栈的当作第一个数字
                int res = 0;
                switch(item){
                    case "+":
                        res = num1 + num2;
                        break;
                    case "x":
                        res=num1 * num2;
                        break;
                    case "/":
                        res= num1 / num2;
                        break;
                    case "-":
                        res=num1 -num2;
                        break;
                    default:
                        throw new RuntimeException("输入运算符号有误");

                }
                //运算完毕，把运算结果入栈操作
                stack.push(String.valueOf(res));
            }
        }
        //最后留在数栈中的数据就是整个表达式总的运算结果
        return Integer.parseInt(stack.pop());
    }

}


























