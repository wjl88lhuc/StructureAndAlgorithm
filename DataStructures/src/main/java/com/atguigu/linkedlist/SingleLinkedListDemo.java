package com.atguigu.linkedlist;

import java.awt.*;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        //1. 先创建几个节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);
        //显示
        singleLinkedList.showLinkedList();

        //修改林冲称号
        HeroNode heroNode5 = new HeroNode(4,"林冲","八十万禁军总教头");
        singleLinkedList.update(heroNode5);
        System.out.println("==========");
        singleLinkedList.showLinkedList();

        //删除节点
        singleLinkedList.delete(hero2);
        singleLinkedList.delete(hero1);
        singleLinkedList.delete(hero3);
        singleLinkedList.delete(hero4);
        System.out.println("*************");
        singleLinkedList.showLinkedList();
    }
}
//定义SingleLinkedList管理我们的英雄
class SingleLinkedList {
    //先初始化一个头节点，头节点不能动，头节点不存储数据，只存储下一个元素的地址
    private HeroNode head = new HeroNode(0,"","");

    //添加节点到单向链表
    //思路：当不考虑编号顺序时，1，找到当前链表的最后节点 2，将最后这个节点的next 指向新的节点
    public void add(HeroNode heroNode){
        //因为头节点不能动，因此我们需要一个辅助遍历temp
        HeroNode temp = head;
        //遍历链表，找到最后
        while(true){
            //找到链表的最后
            if(temp.next == null){
                break;//退出循环
            }
            //如果没有找到最后，将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后,将最后这个节点的next 指向新的节点
        temp.next = heroNode;
    }

    //考虑顺序添加英雄
    public void addByOrder(HeroNode heroNode){
        //因为头节点不能动，因此我们仍然通过一个辅助指针（变量）来帮助找到添加的位置
        //因为是单链表，因为我们找的temp 是位于添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        boolean flag = false;//标识添加的编号是否存在，默认为false
        while (true){
            if (temp.next == null){//说明temp已经再链表的最后
                break;
            }
            if (temp.next.no > heroNode.no){//位置找到了,就在temp的后面插入
                break;
            }
            if (temp.next.no == heroNode.no){//说明希望添加的heroNode已经存在了
                flag = true;
                break;
            }
            temp = temp.next;//后移，遍历当前链表
        }
        //判断flag的值
        if(flag){//不能添加，说明编号已经存在
            System.out.printf("准备插入的英雄编号存在了%d，不能加入",heroNode.no);
        }else {
            //插入到链表中,temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //显示链表[遍历]
    public void showLinkedList(){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("单链表是空的，没有数据");
            return;
        }
        //因为头节点不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head;
        while (true){
            //判断是否到链表的最后
            if(temp.next == null){
                break;
            }
            //输出节点信息
            temp = temp.next;
            System.out.println(temp);
        }
    }

    //修改节点的信息，根据no来修改，节点的no不能修改
    public void update(HeroNode heroNode){
        //判断是否为空
        if (head.next == null){
            System.out.println("链表为空----");
            return;
        }
        //找到需要修改的节点，根据编号no
        HeroNode temp = head.next;
        boolean flag = false;//标识是否找到该节点
        while (true){
            if (temp == null){
                break;//到了链表的尾部，已经遍历完该链表
            }
            if (temp.no == heroNode.no){
                //找到
                flag =true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if (flag){
            temp.name = heroNode.name;
            temp.nickName = heroNode.nickName;
        }else {//没有找到
            System.out.printf("没有找到编号%d 的节点，不能修改",heroNode.no);
        }
    }

    //节点修改,head节点不能删除
    public void delete(HeroNode deleteNode){
        HeroNode temp = head;
        while (true){
            if (temp.next == null){
                break;//已经遍历完该
            }
            if (temp.next.no == deleteNode.no){//找到了
                temp.next = temp.next.next;
                break;
            }
            temp = temp.next;
        }
    }

}



//定义一个HeroNode,每一个HeroNode 对象就是一个节点
class HeroNode{
    public int no;//英雄编号
    public String name;
    public String nickName;
    public HeroNode next;//指向下一个节点

    //构造器
    public HeroNode(int hNo,String name,String nickName){
        this.no = hNo;
        this.name = name;
        this.nickName=nickName;
    }
    //为了显示方便，重写toString方法

    //定义一个Sing

    @Override
    public String toString() {
        return "HeroNode{" + "no=" + no + ", name='" + name + '\'' + ", nickName='" + nickName + '}';
    }
}
