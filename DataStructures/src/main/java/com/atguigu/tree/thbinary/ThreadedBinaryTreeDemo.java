package com.atguigu.tree.thbinary;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //测试一把 线索化二叉树的 中序线索化
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dmi");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setRight(node5);
        node2.setLeft(node4);
        node3.setLeft(node6);

        //测试线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree(root);
        threadedBinaryTree.threadedNodesDefault();

        //测试： 以10 节点测试
        System.out.println(node5.getLeft());

        //遍历线索化的二叉树
        System.out.println("*********************************");
        threadedBinaryTree.threadedList();
    }
}

//定义一个 ThreadedBinaryTree 实现了线索化功能的二叉树
class ThreadedBinaryTree{
    private HeroNode root;

    //为了实现线索化，需要创建给指向当前节点的前驱节点的指针
    //在递归进行线索化时， pre 总是保留前一个节点
    private HeroNode pre = null;

    public ThreadedBinaryTree(HeroNode root){
        this.root = root;
    }

    //遍历线索化的二叉树
    public void threadedList(){
        //定义一个变量，临时存储当前遍历的节点,遍历是从 root 开始的
        HeroNode node = root;
        while (node != null){
            //循环的找到 leftType = 1 的这个节点 ，后面随遍历的进行 node 也变化，因为当 leftType = 1 时，说明该节点是按照线索化 处理后的有效节点
            while (node.getLeftType() == 0){
                node = node.getLeft();
            }
            //打印当前这个节点
            System.out.println(node);

            //如果当前节点的右指针指向的后继节点，就一直输出
            while (node.getRightType() == 1){
                node = node.getRight();//获取当前节点的后继节点
                System.out.println(node);
            }
            //替换这个遍历的节点
            node = node.getRight();
        }
    }

    public void threadedNodesDefault(){
        this.threadedNodes(root);
    }

    /**
     * 编写一个方法进行线索化
     * @param node  就是当前需要线索化的节点
     */
    public void threadedNodes(HeroNode node){
        if (node == null){//如果 node == null,不能线索化
            return;
        }
        //1. 先线索化左子树
        threadedNodes(node.getLeft());
        //2. 线索化当前节点
        // 2.1 先处理当前节点的前驱节点
        if (node.getLeft() == null){
            //让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针类型
            node.setLeftType(1);
        }
        //2.2 处理后继节点
        if (pre != null && pre.getRight() == null){
            //让前驱节点的右指针指向当前节点
            pre.setRight(node);
            //修改前驱节点的右指针类型
            pre.setRightType(1);
        }
        // !!! 非常重要： 每处理一个节点后，让当前节点是下一个节点的前驱节点
        pre = node;

        //3. 线索化右节点
        threadedNodes(node.getRight());
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

//创建 HeroNode
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;//null
    private HeroNode right;//null
    public HeroNode(int no,String name){
        this.no = no;
        this.name = name;
    }
    //如果leftType == 0,表示指向左子树，如果是1 表示前驱节点
    private int leftType;

    //如果rightType == 0,表示指向右子树，如果是1 表示后继节点
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
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


















































































































