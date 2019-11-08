package com.atguigu.queue;

import org.junit.Test;

import java.util.Scanner;

public class CycleArrayQueue {
    public static void main(String[] args) {
        //测试一把
        //创建一个队列
        CircleArray circleArray = new CircleArray(5);
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
                    circleArray.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    circleArray.addQueue(scanner.nextInt());
                    break;
                case 'g': // 取出数据
                    try {
                        System.out.println("取出的数据是： " + circleArray.getQueue());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h'://"查看队列头的数据"
                    try {
                        System.out.println("队列头部的数据： " + circleArray.headQueue());
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

    @Test
    public void test1(){
        int sb = 2;
        System.out.println(sb++ % 3);
    }
}

/**
 * 思路：
 * 	1. front变量的含义调整： front指向队列的第一个元素，也就是说arr[front]就是队列的第一个元素， 初始值为0
 * 	2. rear变量的含义做一个调整： rear指向队列的最后一个元素的后一个位置，因为希望空出一个空间作为约定，初始值为 0。
 * 	3. 当队列满时，条件是(rear + 1) % maxSize = front时候，就满了
 * 	4.当队列为空的条件，rear = front
 * 	5. 队列中的有效数据个数是： (rear + maxSize -front ) % maxSize
 */
class CircleArray{
    private int maxSize;//表示数组的最大容量
    private int front;//front指向队列的第一个元素,初始值为 0
    private int rear;//rear指向队列的最后一个元素的后一个位置,初始值为 0
    private int[] arr;//该数组用于存放数据，模拟队列

    public CircleArray(int maxSize){
        this.maxSize =maxSize;
        front =0;
        rear = 0;
        arr = new int[maxSize];
    }

    //判断是否是满的
    public boolean isFull(){
        return (rear + 1) % maxSize == front;
    }

    //是否为空的
    public boolean isEmpty(){
        return rear == front;
    }

    //添加数据
    public void addQueue(int data){
        //判断数据是否是满的
        if(isFull()){
            System.out.println("队列已满，不能再添加数据");
            return;
        }
        //因为rear是指向最后一个数据的后一个位置
        arr[rear] = data;
        //rear后移，这里必须考虑取模
        rear = (rear + 1) % maxSize;
    }

    //取出数据，出列操作
    public int getQueue(){
        //判断队列是否是空的
        if(isEmpty()){
            throw  new RuntimeException("队列是空的，没有数据");
        }
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //显示队列数据的方法
    public void showQueue(){
        //遍历数据
        if(isEmpty()){
            System.out.println("队列是空的，没有数据");
            return;
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d] = %d\t",i % maxSize,arr[i % maxSize]);
        }
    }

    //求出队列当前有效数据的个数
    public int size(){
        return (rear + maxSize -front) % maxSize;
    }

    //显示头元素
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列是空的，没有数据");
        }
        return arr[front];
    }

}























