package com.atguigu.tree;

public class ArraBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        new ArraBinaryTree(arr).preOrderDefault();
    }
}

//编写一个 ArraBinaryTree ，实现顺序存储二叉树遍历
class ArraBinaryTree{
    private int[] arr;//存储数据节点的数据
    public ArraBinaryTree(int[] arr){
        this.arr = arr;
    }

    public void preOrderDefault(){
        preOrder(0);
    }

    /**
     * 编写一个方法，完成顺序存储二叉树的前序遍历
     * @param index  数组元素的下标， 从 0 开始
     */
    public void preOrder(int index){
        if (arr == null || arr.length == 0){
            System.out.println("数组为空，不能按照二叉树的前序遍历");
            return;
        }
        //输出当前这个元素
        System.out.println(arr[index]);
        if (index * 2 + 1 < arr.length){
            //向左递归遍历
            preOrder(index * 2 + 1);
        }
        if (index * 2 + 2 < arr.length){
            preOrder(index * 2 + 2);
        }
    }
}














































