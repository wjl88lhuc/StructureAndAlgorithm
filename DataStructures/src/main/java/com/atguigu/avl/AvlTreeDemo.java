package com.atguigu.avl;

public class AvlTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {4,3,6,5,7,8};
//        int[] arr = {10,12,8,9,7,6};
        int[] arr = {10,11,7,6,8,9};
        AvlTree avlTree = new AvlTree();
        //添加节点
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        //中序遍历
        avlTree.infixOrderList();

        //在没有做平衡处理之前，
        System.out.println("在没有做平衡处理之前---");
        System.out.println("树的高度 ； " + avlTree.getRoot().height());
        System.out.println("树的左子树的高度" + avlTree.getRoot().leftHeight());
        System.out.println("树的右子树的高度" + avlTree.getRoot().rightHeight());
        System.out.println(avlTree.getRoot());
        System.out.println(avlTree.getRoot().left.left + " \t " + avlTree.getRoot().right);
    }
}

//创建AVLTree
class  AvlTree{
    public Node getRoot(){
        return this.root;
    }
    private Node root;
    //添加节点的方法
    public void add(Node node){
        if (root == null){//如果root是空的
            root = node;
            return;
        }
        root.add(node);
    }

    //中序遍历
    public void infixOrderList(){
        if (root == null){
            System.out.println("当前二叉树是空的，不能遍历");
        }else{
            root.infixOrderList();
        }
    }

    //查找要删除的节点
    public Node search(int value){
        if (root == null){
            return null;
        }
        return root.search(value);
    }

    //查找父节点
    public Node searchParent(int value){
        if (root == null){
            return null;
        }
        return root.searchParent(value);
    }

    /**
     * 功能两个
     * （1） 返回 以node 为根节点的二叉排序树的最小节点的值
     * （2）删除node 为根节点的二叉树的最小节点
     * @param node  传入的节点（当作一颗二叉排序树的根节点）
     * @return   返回的是以 node 为 根节点的二叉排序树的最小节点的值
     */
    public int delRightTreeMin(Node node){
        Node target = node;
        //循环的查找左节点，就会找到最小值
        while (target.left != null){
            target = target.left;
        }
        //这时，target 就指向了 最小节点
        //删除最小节点
        delteNode(target.value);
        return target.value;
    }

    //删除节点
    public void delteNode(int value){
        if (root == null){
            return;
        }
        //查找要删除的节点
        Node targetNode = search(value);
        if (targetNode == null){
            return;
        }
        //如果我们发现当前这颗二叉排序树只有一个节点
        if (root.left == null && root.right == null){
            root = null;
            return;
        }
        //查找targetNode的父节点
        Node parentNode = searchParent(value);
        //如果我们要删除的节点是叶子节点
        if (targetNode.left == null && targetNode.right == null) {
            if (parentNode.left != null && parentNode.left == targetNode){
                parentNode.left = null;
            }
            if (parentNode.right != null && parentNode.right == targetNode){
                parentNode.right = null;
            }
        } else if (targetNode.left != null && targetNode.right != null){//删除有两颗子树的节点
            int minValue = delRightTreeMin(targetNode.right);
            targetNode.value = minValue;
        }else{//删除只有一颗子树的节点
            //如果要删除的节点有左子节点
            if (targetNode.left != null){
                if (parentNode != null) {
                    if (parentNode.left != null && parentNode.left.value == value) {
                        parentNode.left = targetNode.left;
                    } else {
                        //targetNode 是parentNode的右子节点
                        parentNode.right = targetNode.left;
                    }
                }else{
                    root =targetNode.left;
                }
            }else{//要删除的节点有右子节点
                if (parentNode != null) {
                    if (parentNode.left != null && parentNode.left.value == value) {
                        parentNode.left = targetNode.right;
                    } else {
                        //targetNode是parentNode的右子节点
                        parentNode.right = targetNode.right;
                    }
                }else{
                    root = targetNode.right;
                }
            }
        }

    }
}

//节点
class Node{
    int value;
    Node left;//左节点
    Node right;//右节点

    public Node(int value) {
        this.value = value;
    }

    //返回左子树的高度
    public int leftHeight(){
        if (left == null){
            return  0;
        }
        return left.height();
    }

    //返回右子树的高度
    public int rightHeight(){
        return right == null ? 0 : right.height();
    }

    //返回以该节点为根节点的的树的高度
    public int height(){
        return Math.max(left == null ? 0 : left.height(),right == null ? 0 :right.height()) + 1;
    }

    //左旋转方法
    public void leftRotate(){
        //创建新的节点，以当前根节点的值
        Node newNode = new Node(value);
        //把新的节点的左子树设置为当前节点的左子树
        newNode.left=left;
        //把新的节点的右子树的左子树
        newNode.right = this.right.left;
        //把当前节点的值替换成 右子节点的值
        this.value = this.right.value;
        // 把当前节点的右子树设置成为 右子树的右子树
        this.right = this.right.right;
        //把当前节点的左子树设置成为 新节点
        this.left = newNode;
    }

    /**
     * 查找要删除的节点
     * @param value  希望删除的节点的值
     * @return  如果找到返回该节点，否则返回null
     */
    public Node search(int value){
        if (value == this.value){
            return this;
        }
        if (value < this.value){
            if (this.left == null){
                return null;
            }else{
                return this.left.search(value);
            }
        }else{
            if (this.right == null){
                return null;
            }
            return this.right.search(value);
        }

    }

    /**
     * 查找要删除的节点的父节点
     * @param value 要查找的节点的值
     * @return  返回的是要删除的节点的父节点，如果没有就返回null
     */
    public Node searchParent(int value){
        //如果当前节点就是要删除的节点的父节点，就直接返回即可
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)){
            return this;
        }else {
            //如果要查找的值小于当前节点的值，并且当前节点的左节点不为空
            if (value < this.value && this.left != null){
                return this.left.searchParent(value);
            }
            if (value >= this.value && this.right != null){
                return this.right.searchParent(value);//向右子树递归查找
            }
        }
        return null;//没有找到父节点，就直接返回null
    }

    //右旋转
    public void rightRotate(){
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }

    /**
     *添加节点的方法
     * 递归的形式添加节点，注意需要满足二叉排序树的要求
     * @param node
     */
    public void add(Node node){
        if (node == null){
            return;
        }
        //判断传入的节点的值与当前节点的根节点的值的关系
        if (node.value < this.value){
            //如果当前节点的左子节点位 null
            if (this.left == null){
                this.left = node;
            }else{
                //递归的向左子树添加
                this.left.add(node);
            }
        }else{//如果添加的节点的值大于当前节点的值
            if (this.right == null){
                this.right = node;
            }else{
                this.right.add(node);//递归的向右子树添加
            }
        }

        //当添加完一个节点后，如果：右子树的高度 - 左子树的高度 > 1 ,左旋转
        if (rightHeight() - leftHeight() > 1){
            if (right != null && right.leftHeight() - right.rightHeight() > 1){
                //如果他的右子树的左子树的高度大于他的右子树的高度，先右旋转
                right.rightRotate();
            }
            leftRotate();//左旋转
            return;//已经平衡了，直接返回即可
        }

        //当添加完一个节点之后，如果左子树的高度 - 右子树的高度 > 1 , 右旋转
        if (leftHeight() - rightHeight() > 1){
            //如果他的左子树的右子树的高度大于他的左子树的高度
            if (left != null && left.rightHeight() > left.leftHeight()){
                //先对 当前节点的左子树 进行左旋转
                left.leftRotate();
            }
            //右旋转
            rightRotate();
        }
    }

    //中序遍历
    public void infixOrderList(){
        if (this.left != null){
            this.left.infixOrderList();
        }
        System.out.println(this);
        if (this.right != null){
            this.right .infixOrderList();
        }
    }

    @Override
    public String toString() {
        return "Node{value=" + value + " }";
    }
}












































