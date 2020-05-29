package com.atguigu.tree.threadbinaryTree;


/**
 * Created by ChengQian on 2020/5/28 17:15
 */
public class ThreadBinaryTreeDemo {
    public static void main(String[] args) {

        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        BinaryTree binaryTree = new BinaryTree();
        binaryTree.setRoot(root);
        binaryTree.threadNodes();

        HeroNode heroNode = node5.getLeft();
        HeroNode heroNode2 = node5.getRight();
        System.out.println("前置结点为=" + heroNode);
        System.out.println("后置结点为=" + heroNode2);

        System.out.println("中序线索化遍历");
        binaryTree.threadedList();

        System.out.println("前序线索化遍历");
        binaryTree.infixThreadList();


    }

}
//线索化二叉树
class  BinaryTree{
    private HeroNode root;

    //为了实现线索化 需要创建要给指向当前结点的前驱结点的指针
    //pre 在递归进行线索化 pre 总是保留前一个结点
    private HeroNode pre = null;

    public void setRoot(HeroNode root){
        this.root = root;
    }

    //重载方法
    public void threadNodes(){
        this.threadNodes(root);
    }

    //遍历线索化二叉树方法
    /**
     * 中序遍历
     * leftType =1
     * RightType=1 就说明已经线索化
     */
    public void threadedList(){
        //定义一个变量，存储当前遍历得结点从root开始
        HeroNode node = root;
        while(node != null){
            //循环找到leftType == 1得结点第一个找到的就是8结点
            //后面随着遍历而变换 因为从leftType=1时 说明该结点是按照线索化
            //处理后的有效结点
            while(node.getLeftType() == 0){
                node = node.getLeft();
            }
            //输出当前结点
            System.out.println(node);
            //如果当前接结点的右指针指向的是后继结点 就一直输出
            while(node.getRightType() == 1){
                //拿到该节点的后继结点
                node = node.getRight();
                System.out.println(node);
            }
            //替换这个后继结点
            node = node.getRight();
        }
    }

    /**
     * 前序遍历
     */
    public void infixThreadList(){
        //遍历结点从root结点开始
        HeroNode node = root;
        while(node != null){
            //leftType=0就是没有线索化的 找到 leftType=1 的就是线索化的
            if (node.getLeftType() == 0){
                node = node.getLeft();
            }
            System.out.println(node);
            //往右遍历 找到后继结点
            while(node.getRightType() == 1){
                node = node.getRight();
                System.out.println(node);
            }
            //替换后继结点
            node = node.getRight();
        }
    }

    //还需要思考
    //编写对二叉树j进行中序线索化方法
    /**
     *
     * @param node 当前需要处理线索化的节点
     */
    public void threadNodes(HeroNode node){
        //结点为空 无法线索化
        if (node == null){
            return;
        }

        //1.先线索化左子树
        threadNodes(node.getLeft());
        //2.线索化当前节点
        //处理当前结点的前驱结点
        if(node.getLeft() == null){
            //让当前结点的左指针
            node.setLeft(pre);
            //修改当前结点的左指针类型,指向前驱结点
            node.setLeftType(1);
        }
        //处理后继结点
        if (pre !=null && pre.getRight() == null){
            //让前驱结点的右指针指向当前结点
            pre.setRight(node);
            //修改前驱结点的右指针类型
            pre.setRightType(1);
        }
        pre = node;

        //!!! 没处理一个结点后 让当前结点是下一个结点的前驱结点
    //3.线索化右子树
        threadNodes(node.getRight());

    }

    //删除节点
    public void delNode(int no){
        if (root != null){
            //如果只有一个root结点 这里立即判断root是不是要删除结点
            if (root.getNo() == no){//是当前结点直接删除
                root = null;
            }else{
                //递归删除
                root.delNode(no);
            }
        }else{
            System.out.println("空树 不能删除");
        }
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

    //说明
    //leftType == 0 指向左子树 如果为1则指向 前驱节点
    //rightType == 1 表示是指向的是右子树 如果是1表示指向 后继节点
    private int leftType;
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

    public HeroNode(int no, String name){
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
    //递归删除节点
    //1.如果删除的节点是叶子节点 则删除改节点
    //2.如果删除的节点是非叶子节点，则删除改子树
    public void delNode(int no){
        //当前节点的子节点b不为空 并且子节点的表编号 等于要删除的编号 就进行删除
        if (this.left != null && this.left.no == no){
            this.left = null;
            return;
        }
        //当前结点的右子结点不为空 并且子结点 就是要删除的结点 就将this.right = null
        if (this.right != null && this.right.no == no){
            if (this.right.right != null){ //改结点下的右子结点不为空
                if (this.right.left != null){//左子结点不为空
                    this.right = this.right.left;//当前结点设置为左子节点
                    return;
                }else{ //左子节点为空
                    this.right = this.right.right;
                }

            }else{//没有子节点
                this.right = null;
            }

            return;
        }
        //左子树递归删除
        if (this.left != null){
            this.left.delNode(no);
        }
        //5.应当向右子树递归删除
        if (this.right != null){
            this.right.delNode(no);
        }
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
