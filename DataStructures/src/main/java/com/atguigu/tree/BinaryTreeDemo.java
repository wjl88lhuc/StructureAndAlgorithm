package com.atguigu.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        //先需要创建一颗二叉树
        BinaryTree binaryTree = null;
        //创建需要的节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");


        binaryTree = new BinaryTree(root);
        //说明：我们先手动创建该二叉树，后面学习递归的方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
//        binaryTree.deleteNode(1);
        //测试
        System.out.println("前序遍历");
        binaryTree.preOrder();
        System.out.println("中序遍历");
        binaryTree.infixOrder();
        System.out.println("后序遍历");
        binaryTree.postOrder();

        System.out.println("***********************");
        System.out.println("前序遍历方式查找------------------");
        HeroNode resNode = binaryTree.preOrderSearch(15);
        if (resNode != null){
            System.out.printf("找到了，信息为 no = %d  name = %s",resNode.getNo(),resNode.getName());
        }else{
            System.out.println("没有找到");
        }
    }
}

//定义一个BinaryTree 二叉树
class BinaryTree{
    private HeroNode root;

    public BinaryTree(HeroNode root){
        this.root = root;
    }

    //前序遍历
    public void preOrder(){
        if (this.root != null){
            this.root.preOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }
    //中序遍历
    public void infixOrder(){
        if (this.root != null){
            this.root.infixOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //后续遍历
    public void postOrder(){
        if (this.root != null){
            this.root.postOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no){
        if (this.root != null){
            return root.preOrderSearch(no);
        }
        return null;
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no){
        if (this.root != null){
            return this.root.infixOrderSearch(no);
        }
        return null;
    }

    //后续遍历查找
    public HeroNode postOrderSearch(int no){
        if (this.root != null){
            return this.root.postOrderSearch(no);
        }
        return null;
    }

    //删除节点
    public void deleteNode(int no){
        if (this.root == null){
            System.out.println("这是一颗空树，不能删除");
            return;
        }
        if (this.root.getNo() == no){
            this.root = null;
            return;
        }
        this.root.deleteNode(no);
    }


}

class HeroNode{
    private int no;
    private String name;
    private HeroNode left;//null
    private HeroNode right;//null
    public HeroNode(int no,String name){
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode[" + "no=" + no + ", name='" + name + ']';
    }

    //编写前序节点
    public void preOrder(){
        System.out.println("先输出父节点" + this);
        //递归向左子树前序遍历
        if (this.left != null){
            this.left.preOrder();
        }
        //递归向右节子树前序遍历
        if (this.right != null){
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder(){
        //递归向做子树中序遍历
        if (this.left != null){
            this.left.infixOrder();
        }
        //输出父节点
        System.out.println(this);
        //递归向右中序遍历
        if (this.right != null){
            this.right.infixOrder();
        }
    }

    //后续遍历
    public void postOrder(){
        if (this.left != null){
            this.left.postOrder();
        }
        if (this.right != null){
            this.right.postOrder();
        }
        System.out.println(this);
    }

    /**
     * 前序遍历查找
     * @param no  传入的雇员的编号 no
     * @return 如果查找到就返回 HeroNode ,否则就返回 Null
     */
    public HeroNode preOrderSearch(int no){
        //比较当前节点，如果是就返回
        if (this.no == no){
            return this;
        }
        HeroNode resNode = null;
        if (this.left != null){
            resNode =this.left.preOrderSearch(no);
        }
        if (resNode != null){// 说明 我们在左子树找到了
            return resNode;
        }
        if (this.right != null){//如果做节点没有找到，那么判断当前节点的右节点是否是空的，如果不是空的，那么向右递归前序遍历查找
            resNode =this.right.preOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 中序遍历查找
     * @param no
     * @return
     */
    public HeroNode infixOrderSearch(int no){
        HeroNode resNode = null;
        if (this.left != null){
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null){
            return  resNode;
        }
        if (this.no == no){
            return this;
        }
        if (this.right != null){
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    public HeroNode postOrderSearch(int no ){
        HeroNode resNode = null;
        if (this.left != null){
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        if (this.right != null){
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        //如果左右子树都没有找到，那么判断当前节点是否我们要找的节点，如果是，那么就返回当前节点
        if (this.no == no){
            return this;
        }
        return resNode;
    }

    /**
     * 删除节点：
     * （1）如果删除的节点是叶子节点，则删除该节点即可
     * （2）如果删除的节点是非叶子节点，则删除该子树
     * @param no
     */
    public void deleteNode(int no){
        if (this.left != null && this.left.no == no){
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no){
            this.right = null;
            return;
        }
        if (this.left != null){
            this.left.deleteNode(no);
        }
        if (this.right != null){
            this.right.deleteNode(no);
        }
    }

}




































































































