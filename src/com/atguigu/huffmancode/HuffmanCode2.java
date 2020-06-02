package com.atguigu.huffmancode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * Created by ChengQian on 2020/6/1 8:41
 */
public class HuffmanCode2 {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();//转成字符数组
        System.out.println(contentBytes.length);//40

        //bytes 转成list
        List<Node2> nodes = getNodes(contentBytes);
        System.out.println(nodes);

        //创建赫夫曼数
        Node2 huffmanTree = createHuffmanTree(nodes);
        System.out.println(huffmanTree);

        Map<Byte, String> codes = getCodes(huffmanTree);
        System.out.println(codes);

        //根据赫夫曼编码
        byte[] huffmanCodeBytes = zip(contentBytes,codes);
        System.out.println("压缩后的效果="+Arrays.toString(huffmanCodeBytes));

        byte[] decode = decode(huffmanCodes, huffmanCodeBytes);
        System.out.println("原来的字符串="+new String(decode));
    }

   /* public static void zipFile(String srcFile,String dstFile){

        OutputStream os = null;
        ObjectOutputStream oos = null;
        //文件的输入流
        FileInputStream is = null;
        try{
            is = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的byte[]
            byte[] b =
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * 压缩数据解码
     * @param huffmanCodes 赫夫曼编码
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 原来字符串对应的数组
     */
    public static byte[] decode(Map<Byte,String> huffmanCodes,byte[] huffmanBytes){

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i<huffmanBytes.length; i++){//将byte数组转换成二进制字符串
            byte b = huffmanBytes[i];
            boolean flag = (i == huffmanBytes.length - 1);//字符是否是最后一个
            stringBuilder.append(byteToBitString(!flag,b));
        }
        //按照指定赫夫曼编码解码
        Map<String,Byte> map = new HashMap<>();
        for (Map.Entry<Byte,String> entry : huffmanCodes.entrySet()){
            map.put(entry.getValue(),entry.getKey());
        }
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i<stringBuilder.length();){
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while(flag){
                String key = stringBuilder.substring(i,i+count);
                b = map.get(key);
                if (b == null){
                    count++;
                }else{
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }
        byte[] b = new byte[list.size()];
        for (int i = 0; i<b.length; i++){
            b[i] = list.get(i);
        }
        return b;
    }

    /**
     *
     * @param flag
     * @param b
     * @return
     */
    private static String byteToBitString(boolean flag ,byte b){
        int temp = b;
        if (flag){
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);
        if (flag){
            return str.substring(str.length() - 8);
        }else{
            return str;
        }
    }


    /**
     *
     * @param bytes
     * @param huffmanCodes
     * @return
     */
    public static byte[] zip(byte[] bytes,Map<Byte,String> huffmanCodes){
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes){//遍历bytes 拿到数据添加到 stringbuilder中
            stringBuilder.append(huffmanCodes.get(b));
        }
        int len;
        if (stringBuilder.length() % 8 == 0){
            len = stringBuilder.length() / 8;
        }else{
            len = stringBuilder.length() / 8 +1;
        }
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;

        for (int i = 0;  i<stringBuilder.length(); i += 8){
            String strByte;
            if (i + 8 > stringBuilder.length()){
                strByte = stringBuilder.substring(i);
            }else{
                strByte = stringBuilder.substring(i,i+8);
            }
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte,2);
            index++;
        }
        return huffmanCodeBytes;
    }
    static Map<Byte,String> huffmanCodes = new HashMap<>();

    static StringBuilder stringBuilder = new StringBuilder();

    public static Map<Byte,String> getCodes(Node2 root){
        if (root == null){
            return null;
        }
        //左
        getCodes(root.left,"0",stringBuilder);
        //右
        getCodes(root.right,"1",stringBuilder);
        return huffmanCodes;

    }

    /**
     * 功能 将传入的node结点的所有叶子结点和赫夫曼树编码得到 放入到huffmanCodes集合
     * @param node2
     * @param code
     * @param stringBuilder
     */
    public static void getCodes(Node2 node2,String code,StringBuilder stringBuilder){
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);

        stringBuilder1.append(code);
        if (node2 != null){
            if (node2.data == null){
                //左递归
                getCodes(node2.left,"0",stringBuilder1);
                //右递归
                getCodes(node2.right,"1",stringBuilder1);
            }else{
                huffmanCodes.put(node2.data,stringBuilder1.toString());
            }
        }
    }
    /**
     *
     * @param nodes 传入集合
     * @return 返回创建的赫夫曼树
     */
    public static Node2 createHuffmanTree(List<Node2> nodes){
        while (nodes.size() > 1){
            //排序 从小到大
            Collections.sort(nodes);

            //取出一棵最小的二叉树
            Node2 leftNode = nodes.get(0);
            Node2 rightNode = nodes.get(1);

            Node2 parent = new Node2(null,leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            
            //删除原先
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            
            //添加到
            nodes.add(parent);
        }
        return nodes.get(0); 
    }

    /**
     *
     * @param bytes  接收一个byte数组
     * @return 返回一个list
     */
    private static List<Node2> getNodes(byte[] bytes){

        ArrayList<Node2> list = new ArrayList<Node2>();
        //遍历bytes 统计每一个byte出现次数
        Map<Byte,Integer> counts = new HashMap<>();

        for (byte b : bytes){
            Integer count = counts.get(b);
            if (count == null){ //相同元素添加
                counts.put(b,1);
            }else {
                counts.put(b,count+1);
            }
        }
        //counts={32=9, 97=5, 100=1, 101=4, 117=1, 118=2, 105=5, 121=1, 106=2, 107=4, 108=4, 111=2}
        //记录对应的编码的次数 32 对应的就是空格 所以就有9个
        System.out.println("counts=" + counts);

        //遍历map
        for (Map.Entry<Byte,Integer> entry : counts.entrySet()){
            list.add(new Node2(entry.getKey(),entry.getValue()));
        }
        //[Node2{data=32, weight=9}, Node2{data=97, weight=5}
        System.out.println("list=" + list);
        return list;

    }
}
class Node2 implements Comparable<Node2>{
    Byte data;//数据本身
    int weight; //取值
    Node2 left;
    Node2 right;
    @Override
    public int compareTo(Node2 o) {
        return 0;
    }
    public Node2(Byte data,int weight){
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node2{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
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
}
