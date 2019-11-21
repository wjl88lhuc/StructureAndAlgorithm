package com.atguigu.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr ={13,7,8,3,29,6,1};
        Node huffmanTree = createHuffmanTree(arr);//返回的就是赫夫曼树的根节点
        System.out.println(huffmanTree);
        preOrderList(huffmanTree);
    }

    /**
     *创建赫夫曼树的方法
     * @param arr
     */
    public static  Node createHuffmanTree(int[] arr){
        //第一步 为了操作方便
        //1. 遍历数组 arr 数组
        //2. 将arr 的每一个元素构成一个 Node
        //3. 将Node 放入到 ArrayList中
        List<Node> nodes = new ArrayList<Node>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }
        while (nodes.size() > 1) {
            // 排序： 从小到大
            Collections.sort(nodes);
//        System.out.println(nodes);
            //取出根节点权值最小的两颗二叉树
            //(1) 取出权值最小的节点（二叉树）
            Node leftNode = nodes.get(0);
            //(2) 取出权值第二小的节点（二叉树）
            Node rightNode = nodes.get(1);

            //(3) 构建一颗新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //(4) 从ArrayList中删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //(4)将parent加入到nodes 中
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    public static void preOrderList(Node root){
        if (root != null){
            root.preOrderList();
        }else{
            System.out.println("这是一个空树，不能遍历---------");
        }
    }
}

//创建节点类
class Node implements Comparable<Node>{
    int value;//节点权值
    Node left;//指向左节点
    Node right;//指向右节点

    public Node(int value){
        this.value = value;
    }

    public void preOrderList(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrderList();
        }
        if (this.right != null){
            this.right.preOrderList();
        }
    }

    @Override
    public String toString() {
        return "Node{" + "value=" + value + '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;//从小到大排序
    }

}






















































