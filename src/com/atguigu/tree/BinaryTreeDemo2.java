package com.atguigu.tree;

/**
 * Created by ChengQian on 2020/5/28 8:56
 */
public class BinaryTreeDemo2 {
    public static void main(String[] args) {
        BinaryTree2 binaryTree2 = new BinaryTree2();
        //测试
        HeroNode2 node = new HeroNode2(1,"宋江");
        HeroNode2 node2 = new HeroNode2(2,"吴用");
        HeroNode2 node3 = new HeroNode2(3,"卢俊");
        HeroNode2 node4 = new HeroNode2(4,"林冲");
        HeroNode2 node5 = new HeroNode2(5,"关胜");

        node.setLeft(node2);
        node.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);

        binaryTree2.setNode(node);

        /**
         * 前序遍历思路
         *  1.先输出父节点
         *  2.再遍历左节点
         *  3.再遍历右节点
         */
        System.out.println("前序遍历");
        binaryTree2.preOrder();

        /**
         * 中序遍历思路
         *  1.先遍历左节点
         *  2.在输出父节点
         *  3.再遍历右节点
         */
        System.out.println("中序遍历");
        binaryTree2.infixOrder();

        /**
         * 后序遍历思路
         *  1.先遍历左节点
         *  2.再遍历右节点
         *  3.在输出父节点
         */
        System.out.println("后序遍历");
        binaryTree2.postOrder();

        //========================================
        System.out.println("前序遍历方式~~~");
        HeroNode2 resNode = binaryTree2.preOrderSearch(5);
        if (resNode != null){
            System.out.printf("找到了 信息为no=%d name %s\n",resNode.getNo(),resNode.getName());
        }else{
            System.out.printf("没有找到 no = %d 的英雄",5);
        }


        System.out.println("中序遍历方式~~~");
        HeroNode2 resNode2 = binaryTree2.infixOrderSearch(5);
        if (resNode2 != null){
            System.out.printf("找到了 信息为no=%d name %s\n",resNode2.getNo(),resNode2.getName());
        }else{
            System.out.printf("没有找到 no = %d 的英雄",5);
        }

        System.out.println("后序遍历方式~~~");
        HeroNode2 resNode3 = binaryTree2.postOrderSearch(5);
        if (resNode3 != null){
            System.out.printf("找到了 信息为no=%d name %s\n",resNode3.getNo(),resNode3.getName());
        }else{
            System.out.printf("没有找到 no = %d 的英雄",5);
        }
    }
}

//树
class BinaryTree2{
    private HeroNode2 node;

    public void setNode(HeroNode2 node) {
        this.node = node;
    }
    //前置遍历
    public void preOrder(){
        if (node != null){
            this.node.preOrder();
        }else{
            System.out.println("二叉树为空");
        }
    }
    //前置遍历
    public void infixOrder(){
        if (node != null){
            this.node.infixOrder();
        }else{
            System.out.println("二叉树为空");
        }
    }
    //前置遍历
    public void postOrder(){
        if (node != null){
            this.node.postOrder();
        }else{
            System.out.println("二叉树为空");
        }
    }

    //====================
    //前序查找
    public HeroNode2 preOrderSearch(int no){
        if(this.node != null){
            return node.preOrderSearch(no);
        }else{
            return null;
        }
    }
    //中序查找
    public HeroNode2 infixOrderSearch(int no){
        if(this.node != null){
            return node.infixOrderSearch(no);
        }else{
            return null;
        }
    }
    //后序查找
    public HeroNode2 postOrderSearch(int no){
        if(this.node != null){
            return node.postOrderSearch(no);
        }else{
            return null;
        }
    }
}
//节点
class HeroNode2{
    private int no;
    private String name;
    private HeroNode2 left;
    private HeroNode2 right;
    public HeroNode2(int no,String name){
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

    public HeroNode2 getLeft() {
        return left;
    }

    public void setLeft(HeroNode2 left) {
        this.left = left;
    }

    public HeroNode2 getRight() {
        return right;
    }

    public void setRight(HeroNode2 right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 前置遍历
     *  1.先输出父节点
     *  2.在遍历左子树
     *  3.再遍历右子树
     */
    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();//向左子树遍历
        }
        if (this.right != null){
            this.right.preOrder();//向右子树遍历
        }
    }

    /**
     * 中置遍历
     * 1.先遍历左子树
     * 2.然后输出父节点
     * 3.再遍历右子树
     */
    public void infixOrder(){
        if (this.left != null){//左子树遍历
            this.left.infixOrder();
        }
        System.out.println(this);//输出父节点
        if (this.right != null){//向右子树遍历
            this.right.infixOrder();
        }
    }
    /**
     * 后置遍历
     * 1.先遍历左子树
     * 2.再遍历右子树
     * 3.再输出父节点
     */
    public void postOrder(){
        if (this.left != null){
            this.left.postOrder();
        }
        if (this.right != null){
            this.right.postOrder();
        }
        System.out.println(this);
    }


    //======================================================
    /**
     * 前序查找
     * 1.当前节点no是否和传入参数no是否相等
     * 2.相等返回节点
     * 3.不等 判断当前节点 左子节点是否为空 不为空递归查找
     * 3.如果左子节点前序查找 找到节点 则返回 否则继续判断
     * 4.当前节点的右节点是否为空 不为空向右递归查找
     */
    public HeroNode2 preOrderSearch(int no){
        //当前节点向同 直接返回
        System.out.println("前序查找！！");
        if (this.no == no){
            return this;
        }
        //当前节点不相等 判断左是否为空 进行递归
        HeroNode2 node2 = null;
        if (this.left != null){
            node2 = this.left.preOrderSearch(no);
        }
        //判断节点是否为空
        if (node2 != null){
            return node2;
        }
        //继续向右递归查找
        if (this.right != null){
            node2 = this.right.preOrderSearch(no);
        }
        return node2;
    }
    /**
     * 中序查找
     * 1.先遍历左子树 是否为空 进行递归查找
     * 2.没有找到 就和当前节点比较 如果是则返回当前节点
     * 3.然后往右节点进行比较 没找到进行递归 查找
     */
    public HeroNode2 infixOrderSearch(int no){
        //先判断当前左子树是否相等 相等返回 不相等继续递归查找
        HeroNode2 node2 = null;
        if (this.left != null){
            node2 = this.left.infixOrderSearch(no);
        }
        //判断节点是否为空
        if (node2 != null){
            return node2;
        }
        //判断当前节点
        System.out.println("中序查找！！");
        if (this.no == no){
            return this;
        }
        //继续向右递归查找
        if (this.right != null){
            node2 = this.right.infixOrderSearch(no);
        }
        return node2;
    }
    /**
     * 后序查找
     * 1.先查找左子树 相等返回 不为空 继续递归查找
     * 2.再查找右子树 相等返回 不为空继续查找
     * 3.然后用当前节点比较 相同返回
     */
    public HeroNode2 postOrderSearch(int no){

        //当前节点不相等 判断左是否为空 进行递归
        HeroNode2 node2 = null;
        if (this.left != null){
            node2 = this.left.postOrderSearch(no);
        }
        //判断节点是否为空
        if (node2 != null){
            return node2;
        }
        //继续向右递归查找
        if (this.right != null){
            node2 = this.right.postOrderSearch(no);
        }
        if (node2 != null){
            return node2;
        }
        System.out.println("后序查找！！");
        if (this.no == no){
            return this;
        }
        return node2;
    }
}