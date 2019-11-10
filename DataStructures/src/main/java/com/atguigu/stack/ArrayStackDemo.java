package com.atguigu.stack;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        //测试一下ArrayStack 是否正确
        //先创建一个ArrayStack对象 -> 表示栈
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;//表示是否退出菜单
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 表示退出程序");
            System.out.println("push: 表示添加数据到栈(入栈)");
            System.out.println("pop: 表示栈取出数据(出栈操作)");
            System.out.println("请输入你的选择: ");
            key = scanner.nextLine();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数字");
                    stack.push(scanner.nextInt());
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                case "pop":
                    try {
                        System.out.println(stack.pop());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
        System.out.println("--------程序退出--------");
    }
}

//定义一个ArrayStack表示栈
class ArrayStack {
    private int maxSize;//栈的大小
    private int[] stack;//数组模拟栈，数据就存放在该数组中
    private int top = -1;//top表示栈顶，初始化为 -1

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈操作
    public void push(int value) {
        if (isFull()) {
            return;//栈满了，退出，入栈失败
        }
        stack[++top] = value;
    }

    //出栈操作
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空,没有数据");
        }
        return stack[top--];
    }

    //遍历栈
    public void list() {
        if (isEmpty()) {//栈是空的，就不用遍历了
            System.out.println("------栈是空的，没有数据------");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d\t", i, stack[i]);
        }
    }
}

















