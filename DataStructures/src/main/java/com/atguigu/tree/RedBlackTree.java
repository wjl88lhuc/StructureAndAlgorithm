package com.atguigu.tree;

public class RedBlackTree {
    private final int R = 0;//红色
    private final int B = 1;//黑色

    private Node root = null;//红黑树的根节点

    class Node{
        int data;  //存放数据
        int color = R;//所有的插入值都是红色
        Node left;//左节点
        Node right;//右节点
        Node parent;//父节点，如果是根节点的话，那么 parent 就是 null

        public Node(int data) {
            this.data = data;
        }

        /**
         * 功能：设置新节点的颜色，同时设置新节点的父节点
         * @param newNode
         */
        public void setColorAndParent(Node newNode){
            if (color == R){//如果当前节点的颜色是红色的，那么就将新节点的颜色设置为 黑色的
                newNode.color = B;
            }
            newNode.parent = this;
        }

        //数据的插入
        public void insert(int newData){
            if (newData < data){
                if (left == null){
                    left=new Node(newData);
                    setColorAndParent(left);// 设置新节点颜色与父节点
                }else{
                    left.insert(newData);
                }
            }else{
                if (right == null){
                    right = new Node(newData);
                    setColorAndParent(right);// 设置新节点颜色与父节点
                }else{
                    right.insert(newData);
                }
            }
        }



    }

    /**
     * 插入新节点
     * @param data
     */
    public void insert(int data){
        if (root == null){
            root = new Node(data);
            root.parent = null;
            root.color = B;//红黑树的根节点的颜色是黑色的
        }else{
            root.insert(data);
        }
    }
}
