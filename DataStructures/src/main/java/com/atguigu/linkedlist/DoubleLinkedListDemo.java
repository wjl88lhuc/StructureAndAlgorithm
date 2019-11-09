package com.atguigu.linkedlist;

public class  DoubleLinkedListDemo{
    public static void main(String[] args) {
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero4);
        doubleLinkedList.add(hero3);
        doubleLinkedList.showLinkedList();
//        doubleLinkedList.delete(hero3);
        HeroNode2 update = new HeroNode2(4, "林教头", "八十万禁军总教头");
        doubleLinkedList.update(update);
        System.out.println("=======");
        doubleLinkedList.showLinkedList();
    }
}

 class DoubleLinkedList {

    //先初始化一个头节点，头节点不能动，头节点不存储数据，只存储下一个元素的地址
    private HeroNode2 head = new HeroNode2(0,"","");

    //返回头节点
    public HeroNode2 getHead(){
        return head;
    }

    //遍历双向链表
    public void showLinkedList(){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("单链表是空的，没有数据");
            return;
        }
        //因为头节点不能动，因此我们需要一个辅助变量来遍历
        HeroNode2 temp = head;
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

    //添加节点到单向链表
    //思路：当不考虑编号顺序时，1，找到当前链表的最后节点 2，将最后这个节点的next 指向新的节点
    // 3.将新节点的前一个节点指向这一个
    public void add(HeroNode2 heroNode){
        //因为头节点不能动，因此我们需要一个辅助遍历temp
        HeroNode2 temp = head;
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
        heroNode.pre = temp;//将新节点的前一个节点指向这一个。形成一个双向链表
    }

    //修改节点的信息，根据no来修改，节点的no不能修改。可以看到双向链表的修改与单向链表的修改一样，只是节点的类型不一样
    public void update(HeroNode2 heroNode){
        //判断是否为空
        if (head.next == null){
            System.out.println("链表为空----");
            return;
        }
        //找到需要修改的节点，根据编号no
        HeroNode2 temp = head.next;
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

    //从双向链表中直接删除一个链表，直接找到这个节点，而不需要借助一个临时变量来寻找
    public void delete(HeroNode2 heroNode){
        if (head.next == null){
            return;
        }
        HeroNode2 temp = head.next;
        while (temp != null){
            if(temp.no == heroNode.no){
                temp.pre.next =temp.next;
                if(temp.next != null){
                    //如果是最后一个节点的话就不需要，不是最后一个节点才需要，要不然会抛出空指针异常
                    temp.next.pre = temp.pre;
                }
                break;
            }
            temp = temp.next;
        }
    }

    //按照编号顺序添加节点。升序
     public void addByOrder(HeroNode2 heroNode){

     }

}

//定义一个HeroNode,每一个HeroNode 对象就是一个节点
class HeroNode2{
    public int no;//英雄编号
    public String name;
    public String nickName;
    public HeroNode2 next;//指向下一个节点，默认为 null
    public HeroNode2 pre;//指向前一个节点，默认为  null

    //构造器
    public HeroNode2(int hNo,String name,String nickName){
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

































