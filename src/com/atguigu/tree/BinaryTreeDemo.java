package com.atguigu.tree;

/**
 * Created by ChengQian on 2020/5/27 14:31
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        //二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的结点
        HeroNode root = new HeroNode(1,"宋江");
        HeroNode node2 = new HeroNode(2,"吴用");
        HeroNode node3 = new HeroNode(3,"卢俊义");
        HeroNode node4 = new HeroNode(4,"林冲");
        HeroNode node5 = new HeroNode(5,"关胜");

        //说明 我们先手创建二叉树 后面我们学习递归方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);//设置左节点
        node3.setLeft(node5);
        binaryTree.setRoot(root);//设置结点
        //测试
        System.out.println("前序遍历"); //12354 父左右
        binaryTree.preOrder();

        System.out.println("中序遍历");//21534 左父右
        binaryTree.infixOrder();

        System.out.println("后序遍历");//25431 左右父
        binaryTree.postOrder();

        //前序遍历
        System.out.println("前序遍历方式~~~");
        HeroNode resNode = binaryTree.preOrderSearch(5);
        if (resNode != null){
            System.out.printf("找到了 信息为no=%d name %s\n",resNode.getNo(),resNode.getName());
        }else{
            System.out.printf("没有找到 no = %d 的英雄",5);
        }
    }
}
//数
class  BinaryTree{
    private HeroNode root;

    public void setRoot(HeroNode root){
        this.root = root;
    }

    //前序遍历
    public void preOrder(){
        if (this.root != null){
            this.root.preOrder();
        }else{
            System.out.println(" 二叉树为空，无法遍历");
        }
    }
    //前序遍历
    public void infixOrder(){
        if (this.root != null){
            this.root.infixOrder();
        }else{
            System.out.println(" 二叉树为空，无法遍历");
        }
    }
    //前序遍历
    public void postOrder(){
        if (this.root != null){
            this.root.postOrder();
        }else{
            System.out.println(" 二叉树为空，无法遍历");
        }
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no){
        if (root != null){
            return root.preOrderSearch(no);
        }else{
            return null;
        }
    }
    //中序遍历
    public HeroNode infixOrderSearch(int no){
        if (root != null){
            return root.infixOrderSearch(no);
        }else{
            return null;
        }
    }
    //后序遍历
    public HeroNode postOrderSearch(int no){
        if (root != null){
            return root.preOrderSearch(no);
        }else{
            return null;
        }
    }


}
//链表
class HeroNode{
    private int no;
    private String name;
    private HeroNode left; //左节点
    private HeroNode right;//右节点

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
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //前置遍历
    public void preOrder(){
        System.out.println(this);//输出父节点
        //递归向左子树前序遍历
        if (this.left != null){
            this.left.preOrder();
        }
        //遍历向右子树前序遍历
        if (this.right != null){
            this.right.preOrder();
        }
    }
    //中序遍历
    public void infixOrder(){
        //递归向左子树中序遍历
        if (this.left != null){
            this.left.infixOrder();
        }
        //输出父节点
        System.out.println(this);
        //递归向右子树中序遍历
        if (this.right != null){
            this.right.infixOrder();
        }
    }
    //后序遍历
    public void postOrder(){
        if (this.left!= null){
            this.left.postOrder();
        }
        if (this.right!= null){
            this.right.postOrder();
        }
        System.out.println(this);
    }

    //前序遍历查找

    /**
     *
     * @param no 查找no
     * @return 如果找到就返回该Node 如果没有找到就返回null
     */
    public HeroNode preOrderSearch(int no){
        System.out.println("进入前序遍历~");//主要遍历是这条语句
        //比较当前结点是不是
        if (this.no == no){
            return this;
        }
        //1.则判断当前左子节点是否为空，如果不为空，则递归前序查找
        //2.如果左递归前序 查找 找到结点 则返回
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        //1.则判断当前右子节点是否为空，如果不为空，则递归前序查找
        //2.如果左递归前序 查找 找到结点 则返回
        if (this.right != null){
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no){
        //则判断当前左子节点是否为空，如果不为空，则递归前序查找
        HeroNode resNode = null;
        if (this.left != null){
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        //如果找打,则返回 如果没有找到 就和当前结点比较 如果是则返回当前结点
        if (this.no == no){
            return this;
        }
        //否则继续进行左递归 中序查找
        if (this.right!=null){
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }
    //后序遍历查找
    public HeroNode postOrderSearch(int no) {
        //则判断当前左子节点是否为空，如果不为空，则递归前序查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
     //如果左子树没有找到 则向右子树递归进行后序遍历查找
        if(this.right != null){
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        //左右都没有找到 比较当前结点
        if (this.no == no){
            return this;
        }
        return resNode;
    }
}
