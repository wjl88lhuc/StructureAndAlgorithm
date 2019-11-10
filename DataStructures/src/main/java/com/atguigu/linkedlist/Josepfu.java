package com.atguigu.linkedlist;

//约瑟夫问题
public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(125);
//        circleSingleLinkedList.showBoy();
        System.out.println("************");
        circleSingleLinkedList.countBoy(5,289,125);
    }
}

//创建一个环形的单向链表
class CircleSingleLinkedList{
    //创建一个first节点,默认是没有编号的
    private Boy first= new Boy(-1);
    //添加小孩节点，构建成一个环形链表
    public void addBoy(int nums){//参数nums 表示要添加几个小孩
        //对nums做一个校验,小于1 就没有任何的意义
        if (nums <1){
            return;
        }
        Boy curBoy = null;//辅助指针，帮助构建环形链表
        for (int i = 1; i <=nums ; i++) {
            //根据编号创建小孩节点
            Boy boy = new Boy(i);
            if(i == 1){
                //如果是第一个小孩
                first = boy;
                boy.setNext(boy);//构成环状
                curBoy = first;//让curBoy指向第一个小孩
            }else{
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy=boy;
            }
        }
    }

    //遍历当前的环形链表
    public void showBoy(){
        //判断当前链表是否是空的
        if (first == null){
            System.out.println("---没有任何小孩--");
            return;
        }
        Boy curBoy = first;//因为first不能动，因此使用辅助指针来遍历
        while(true){
            System.out.println("小孩的编号是 \t" + curBoy.getNo());
            if (curBoy.getNext() == first){
                break;// 说明已经遍历完毕
            }
            curBoy = curBoy.getNext();
        }
    }

    /**
     * 根据用户的输入，生成一个小孩出圈的顺序：
     *   1. 需要创建一个辅助指针(变量)helper，事先应该指向环形链表的最后这个节点
     *   2. 当小孩报数时，让first和helper指针同时的移动（m - 1）次 ，因为开始节点自己也数一下（从当前节点开始数数）
     *   3. 这时就让可以将first指针指向的小孩出圈
     * @param startNo 表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums 表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo,int countNum,int nums){
        //现对数据进行校验
        if (first == null || startNo < 1 || startNo > nums){
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        //创建一个辅助指针，帮助完成小孩出圈
        Boy helper = first;
        //需要创建一个辅助指针(变量)helper,事先应该指向环形链表的最后这个节点
        while (true){
            if (helper.getNext() == first){//说明helper指向了最后小孩这个节点
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前，先让first 和 helper 移动 startNo - 1 次
        for (int i = 0; i <  startNo - 1; i++) {
            first = first.getNext();
            helper= helper.getNext();
        }
        //当小孩报数时，让first 和helper 指针同时移动 countNum -1 次，然后出圈。这是一个循环操作，直到圈中只有一个节点为止
        while (true){
            if (helper == first){//当helper等于first的时候，说明圈中只有一个人（节点）
                break;
            }
            //让first 和 helper 指针同时的移动 countNum - 1
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这是first指向的节点就是要出圈的小孩节点
            System.out.println("小孩"+ first.getNo() +"要出圈");
            //这时将first指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        //当退出循环的时候，圈中只剩下一个节点
        System.out.println("最后留在圈中的小孩是 " + first.getNo());
    }
}


/**
 * 构建一个单向的环形链表思路：
 * 1. 先创建第一个节点，让first指向该节点，并形成环形
 * 2. 后面当我们每创建一个新的节点，就把该节点加入到已有的环形链表中即可
 */
//创建一个Boy类，表示一个节点
class Boy{
    private int no;//编号
    private Boy next;//指向下一个节点，默认null
    public Boy(int no){
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}



























