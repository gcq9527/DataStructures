package com.atguigu.tree;

/**
 * Created by ChengQian on 2020/5/28 16:02
 */
public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2,  3, 4, 5, 6, 7};
        //创建一个ArrBinaryTree
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
//        arrBinaryTree.infixOrder(0);//2451367
        arrBinaryTree.postOrder(0);//
    }

}
//编写一个ArrayBinaryTree 实现顺序存储二叉树遍历
class ArrBinaryTree{
    private int[] arr;//存储数据结点的数组
    public ArrBinaryTree(int[] arr){
        this.arr = arr;
    }
    //重载preOrder
    public void preOrder(){
        this.preOrder();
    }
    //编写一个方法
    /**
     * 第n个元素的左子节点为 2 * n +1  3节点下面的左边的 4  2*2+1 4的位置再数组中就是 3
     * 第n个元素的右子节点为 2 * n + 2
     * @param index 数组下标
     */
    public void preOrder(int index){
        if (arr == null || arr.length == 0){
            System.out.println("数组为空 不能按照二叉树前序遍历");
        }
        //输出当前这个元素
        System.out.println(arr[index]);
        //向左边递归
        if ((index * 2 + 1) < arr.length){
            preOrder(2 * index + 1);
        }
        //右递归
        if ((index * 2 + 2) < arr.length){
            preOrder(2 * index + 2);
        }
    }

    /**
     * 中置排序
     * @param index
     */
    public void infixOrder(int index){
        if (arr == null || arr.length == 0){
            System.out.println("数组为空 不能按照二叉树前序遍历");
        }

        //向左边递归
        if ((index * 2 + 1) < arr.length){
            preOrder(2 * index + 1);
        }
        //输出当前这个元素
        System.out.println(arr[index]);
        //右递归
        if ((index * 2 + 2) < arr.length){
            preOrder(2 * index + 2);
        }
    }
    /**
     * 后置排序
     * @param index
     */
    public void postOrder(int index){
        if (arr == null || arr.length == 0){
            System.out.println("数组为空 不能按照二叉树前序遍历");
        }

        //向左边递归
        if ((index * 2 + 1) < arr.length){
            preOrder(2 * index + 1);
        }

        //右递归
        if ((index * 2 + 2) < arr.length){
            preOrder(2 * index + 2);
        }
        //输出当前这个元素
        System.out.println(arr[index]);
    }
}