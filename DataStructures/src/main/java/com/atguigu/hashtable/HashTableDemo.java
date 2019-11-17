package com.atguigu.hashtable;

import java.util.Scanner;

public class HashTableDemo {
    public static void main(String[] args) {
        //创建哈希表
        MyHashTable myHashTable = new MyHashTable(7);

        //写一个简单菜单来测试
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("add :添加雇员 ");
            System.out.println("list :显示雇员 ");
            System.out.println("find :查找雇员 ");
            System.out.println("exit :退出雇员 ");
            key = scanner.next();
            switch (key){
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    myHashTable.add(emp);
                    break;
                case "list":
                    myHashTable.list();
                    break;
                case "find":
                    System.out.println("输入id");
                    myHashTable.findEmpById(scanner.nextInt());
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }

    }
}

class Emp{//表示一个雇员
    public int id;
    public String name;
    public Emp next;//next默认位null
    public Emp(int id,String name){
        this.id = id;
        this.name=name;
    }
}

//创建一个 EmpLinkedList  表示链表
class EmpLinkedList{
    //头指针,指向第一个Emp,因此我们这个链表的head 是直接指向第一个Emp
    private Emp head;//默认是 null

    /**
     * 添加雇员到链表
     * 假定，当添加雇员时，id 是自增长的，即 id 的分配总是从小到大
     * ，因此，我将该雇员直接加入到本链表的最后即可
     * @param emp
     */
    public void add(Emp emp){
        //如果添加第一个雇员
        if (head == null){
            head = emp;
            return;
        }
        //如果不是添加第一个雇员，则使用辅助的指针，帮助我们定位到最后
        Emp curEmp = head;
        while (true){
            if (curEmp.next == null){
                //说明到了链表的最后
                break;
            }
            curEmp =curEmp.next;
        }
        //退出时直接将emp加入到链表的最后
        curEmp.next = emp;
    }

    //写一个遍历链表雇员的信息
    public void list(int no){
        if (head == null){
            System.out.println("第 "+ (no + 1) +"前链表位空");
            return;//说明链表是空的，无需遍历了
        }
        System.out.println("当前链表的信息位： ");
        Emp curEmp = head;
        while (true){
            System.out.printf("=> id = %d  name = %s \t",curEmp.id,curEmp.name);
            if (curEmp.next == null){//说明已经到了链表的最后
                break;
            }
            curEmp= curEmp.next;
        }
        System.out.println();
    }

    /**
     * 根据id 查找雇员
     * 如果查找到就返回 Emp,如果没有找到就返回 null
     * @param id
     * @return
     */
    public Emp findByEmpById(int id){
        //判断链表是否为 空
        if (head == null){
            System.out.println("该链表为空");
            return null;
        }
        //辅助指针
        Emp curEmp = head;
        while (true){
            if (curEmp.id == id){
                break;
            }
            if (curEmp.next == null){
                curEmp = null;//说明遍历当前链表没有找到该雇员。直接将结果置为 null
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }

}

//编写一个HashTable类，该类管理多个链表
class MyHashTable{
    private EmpLinkedList[] empLinkedListArray;
    private int size ;//表示共有多少条链表

    public MyHashTable(int size) {
        //初始化empLinkedListArray
        empLinkedListArray = new EmpLinkedList[size];
        this.size = size;
        //不要忘了，要记得初始化每一条链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp){
        //根据员工的id得到该员工应该加入哪一条链表
        int empLinkedListNo = hashFun(emp.id);
        //将emp 添加到对应的链表中
        empLinkedListArray[empLinkedListNo].add(emp);
    }

    //遍历所有的链表,遍历所有的hashtable
    public void list(){
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    //根据输入的id查找雇员
    public void findEmpById(int id){
        int empLinkedListNo = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNo].findByEmpById(id);
        if (emp != null){
            System.out.printf("在第%d条链表中找到该雇员 id = %d \n",(empLinkedListNo + 1),id);
        }else{
            System.out.println("在哈希表中没有找到该雇员");
        }
    }

    //编写一个散列函数，使用一个简单的取模法
    public int hashFun(int id){
        return id % size;
    }



}




























































