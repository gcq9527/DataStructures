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
        node.preOrder();

        /**
         * 中序遍历思路
         *  1.先遍历左节点
         *  2.在输出父节点
         *  3.再遍历右节点
         */
        System.out.println("中序遍历");
        node.infixOrder();

        /**
         * 后序遍历思路
         *  1.先遍历左节点
         *  2.再遍历右节点
         *  3.在输出父节点
         */
        System.out.println("后序遍历");
        node.infixOrder();



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
}