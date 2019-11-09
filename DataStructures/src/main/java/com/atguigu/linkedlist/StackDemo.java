package com.atguigu.linkedlist;

import java.util.Stack;

//演示栈stack的基本使用
public class StackDemo {
    public static void main(String[] args) {
        Stack<String> strings = new Stack<>();

        //入栈
        strings.add("广东");
        strings.add("上海");
        strings.add("北京");

        while (strings.size() > 0){
            System.out.print(strings.pop() + "\t");//出栈操作
        }
    }
}


































