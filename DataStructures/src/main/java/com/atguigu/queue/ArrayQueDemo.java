package com.atguigu.queue;

import java.util.Scanner;

public class ArrayQueDemo {
    public static void main(String[] args) {
        //测试一把
        //创建一个队列
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' ';//接受用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头部的数据");
            key = scanner.next().charAt(0);//接受一个字符
            switch (key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    arrayQueue.addQueue(scanner.nextInt());
                    break;
                case 'g': // 取出数据
                    try {
                        System.out.println("取出的数据是： " + arrayQueue.getQueue());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h'://"查看队列头的数据"
                    try {
                        System.out.println("队列头部的数据： " + arrayQueue.headQueue());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");

    }
}


/**
 * //使用数组模拟队列编写一个ArrayQueue类
 * 这种数组的问题及其优化：
 *      （1）目前数组使用一次就不能使用，没有达到复用的效果
 *      （2）将这个数组使用算法，改进成一个环形的队列 ，取模的方式来完成的
 */
class ArrayQueue {
    private int maxSize;//表示数组的最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;//该数组用于存放数据，模拟队列

    //创建队列的构造器

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1;//指向队列数据头部前面的一个位置,指向队列头部（队列第一个数据的前一个位置）
        rear = -1;//如果rear也是 -1 ，则表示队列是空的，rear指向队尾（就是队列的最后一个数据）
    }

    //判断队列是否是满的
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    //判断队列是否是空的
    public boolean isEmpty() {
        //如果front 等于 rear，那么队列就是空的
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列满，不能再加入数据了");
            return;
        }
        rear++;
        arr[rear] = n;
    }

    //获取队列的数据，出队列操作
    public int getQueue() {
        //判断队列是否是空的
        if (isEmpty()) {
            //如果队列是空的，就抛出异常
            throw new RuntimeException("队列是空的,不能取出数据");
        }
        return arr[++front];
    }

    //显示队列的所有数据
    public void showQueue() {
        //遍历
        if (isEmpty()) {
            System.out.println("队列是空的，没有数据");
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d] = %d", i, arr[i]);
        }
    }

    //显示数据的头部
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列是空的，没有数据");
        }
        return arr[front + 1];
    }

}



















