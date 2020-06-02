package com.atguigu.binarysorttree;

/**
 * Created by ChengQian on 2020/6/1 16:35
 */
public class BinarySortTreeDemo2 {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9,2};
        BinarySortTree2 binarySortTree = new BinarySortTree2();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node2(arr[i]));
        }

        System.out.println("中序遍历~");
        binarySortTree.infixOrder();

        //删除
        System.out.println("删除结点");
        binarySortTree.delNode(1);

        System.out.println("删除后的中序遍历");
        binarySortTree.infixOrder();
    }
}
//二叉树
class BinarySortTree2{
    private Node2 root;

    public int delRightTreeMin(Node2 node){
        Node2 node2 = node;
        while(node2.left != null ) {
            node2 = node2.left;
        }

        delNode(node2.value);
        return node.value;
    }
    public Node2 search(int value){
        if (root == null ) {
            return null;
        } else {
            return root.search(value);
        }
    }
    public Node2 searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    /**
     * 删除
     * @param value 要删除的id
     * @return
     */
    public void delNode(int value) {
        if (root == null) {
            return;
        }

        Node2 targetNode = search(value);
        if (targetNode == null ) {
            return;
        }
        if (root.left == null && root.right == null) {
            root = null;
            return ;
        }

        Node2 parent = searchParent(value);
        //删除单叶子节点  判断结点左右是否为空
        if (targetNode.left == null && targetNode.right == null) {
            //左左子结点不为空 左子结点的值等于要修改的值
            if(parent.left != null && parent.left.value == value) {
                parent.left = null;
                //右子结点不为空 值相同
            } else if (parent.right != null && parent.right.value == value) {
                parent.right = null;
            }
            //左右结点都不为空
        } else if (targetNode.left != null && targetNode.right != null ) {
            int min = delRightTreeMin(targetNode);
            targetNode.value = min;
        } else { //只有一棵子树结点不为空
            //左子结点不为空
            if (targetNode.left != null ) {
                //targetNode是parent的左子结点
                if (parent.left.value == value) {
                    parent.left = targetNode.left;
                } else {
                    ////targetNode是parent的右子结点
                    parent.right = targetNode.left;
                }
            } else {
                //右子节点 不为空
                //targetNode是parent的左子结点
                if (parent.left.value == value) {
                    parent.left = targetNode.right;
                } else {//targetNode是parent的右子结点
                    parent.right = targetNode.right;
                }
            }
        }

    }

    //添加
    public void add(Node2 node){
        if (root == null) {
             root = node;
        }else{
            root.add(node);
        }
    }
    public void infixOrder(){
        if(root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }
}
//结点
class Node2{
    int value;
    Node2 left;
    Node2 right;

    public Node2(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node2{" +
                "value=" + value +
                '}';
    }

    //中序遍历
    public void infixOrder(){
        if (this.left != null) {
            this.left.infixOrder();
        }

        System.out.println(this);

        if (this.right != null) {
            this.right.infixOrder();
        }
    }
    //删除叶子结点
    /**
     * 查询要删除的结点
     * @param value 要查找的值
     * @return
     */
    public Node2 search(int value){
        if (value == this.value) {
            return this;
        } else if (value < this.value) { //搜索的值比当前值小 往左边递归查找
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);//向左递归查找
        } else {
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * 要删除结点的父结点
     * @param value
     * @return
     */
    public Node2 searchParent(int value){
        //如果要删除的结点的父结点 等于当前结点
        if ((this.left != null && this.left.value == value)
                || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //查找的值 小于当前值 向左递归
            if (value < this.value && this.left != null ) {
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;//无父结点
            }
        }
    }


    //添加数据
    public void add(Node2 node2){
        if (node2 == null) {
            return;
        }
        //要添加的值小于 当前的值 往左边添加
        if (node2.value < this.value) {
            if (this.left == null) {
                this.left = node2;
            }else{
                this.left.add(node2);//递归添加
            }
        } else {
            if (this.right == null) {
                this.right = node2;
            } else {
                this.right.add(node2);
            }
        }
    }
}