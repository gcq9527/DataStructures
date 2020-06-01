package com.atguigu.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ChengQian on 2020/5/29 16:02
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = { 13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(arr);
        //测试
        preOrder(root);
    }
    public static void preOrder(Node node){
        if (node != null){
            node.preOrder();
        }else{
            System.out.println("空树 不能遍历~~");
        }
    }
    //创建赫夫曼树
    public static Node createHuffmanTree(int[] arr){
        //
        //1.遍历arr数组
        //2.将arr的每个元素构成一个Node
        //3.将node放入ArrayList中
        List<Node> nodes = new ArrayList<Node>();
        for (int value : arr){
            nodes.add(new Node(value));
        }
//        Collections.sort(nodes);
//        System.out.println(nodes);

        //最终数组里面一个结果
        while(nodes.size() > 1) {
            //排序 从小到大
            Collections.sort(nodes);//排序
            System.out.println("nodes=" + nodes);

            //取出根节点权值最小的两颗二叉树
            //1.取出权值最小的节点 二叉树
            Node leftNode = nodes.get(0);
            //2.取出权值第二小的节点 二叉树
            Node rightNode = nodes.get(1);

            //3.构建一颗新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            //4.从ArrayList中删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            //5.将parent加入到nodes
            nodes.add(parent); //添加到nodes后面

            System.out.println("第一次处理过程后" + nodes);
        }

        //返回赫夫曼树root节点
        return nodes.get(0);
    }

}
//创建结点类
class Node implements Comparable<Node>{
    int value;//结点权值
    Node left;//左子结点
    Node right;//右子结点
    public Node(int value){
        this.value = value;
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
      // System.out.println(this.value +"-" + o.value);
        //从小到大
        return this.value - o.value;
    }
}