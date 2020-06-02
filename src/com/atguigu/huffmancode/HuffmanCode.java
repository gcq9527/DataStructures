package com.atguigu.huffmancode;

import java.io.*;
import java.util.*;

/**
 * Created by ChengQian on 2020/5/31 17:42
 */
public class HuffmanCode {
    public static void main(String[] args) {
//        String content = "i like like like java do you like a java";
//        byte[] contentBytes = content.getBytes();//转换成字节
//        System.out.println(content.length());
//
//        byte[] bytes = huffmanZip(contentBytes);
//        System.out.println("压缩后的结果=" + Arrays.toString(bytes)+"长度是=" + bytes.length);


        //测试
//        System.out.println(byteToBitString((byte)-1));

//        byte[] SourceBytes = decode(huffmanCodes, bytes);
//        System.out.println("原来的字符串"+ new String(SourceBytes));

        //测试压缩文件
        int i = 1;
        if (i == 1){ //压缩文件
            String srcFile = "E:\\桌面\\Uninstall.xml";
            String dstFile ="E:\\桌面\\Uninstall.zip";
            zipFile(srcFile, dstFile);
            System.out.println("压缩文件ok~~");
        }else{ //解压文件

            String zipFile =  "E:\\桌面\\Uninstall.zip";
            String dstFile = "E:\\桌面\\Uninstall2.xml";
            //解压文件
            unZipFile(zipFile,dstFile);
            System.out.println("解压成功");
        }

        /**
        //byte字符数组 转换成list
        List<Node> nodes = getNodes(contentBytes);
        System.out.println(nodes);

        //创建赫夫曼树
        System.out.println("赫夫曼树");
        Node huffmanTree = createHuffmanTree(nodes);

        //遍历
        System.out.println("前序遍历");
        preOrder(huffmanTree);

        //测试 是否生成根节点
        Map<Byte, String> huffmanCodes = getCodes(huffmanTree);
        System.out.println("生成的赫夫曼编码表"+huffmanCodes);

        byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);
        System.out.println("huffmanCodeBytes="+Arrays.toString(huffmanCodeBytes));
         **/

    }

    /**
     *
     * @param zipFile 解压的文件
     * @param dstFile 解压文件存放的目录
     */
    public static void unZipFile(String zipFile,String dstFile){
        //文件输入流
        InputStream is = null;
        //定义一个对象输出流
        ObjectInputStream ois = null;
        OutputStream os = null;
        try{
            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);
            byte[] huffmanBytes = (byte[]) ois.readObject();
            //读取赫夫曼编码表
            Map<Byte,String> huffmanCodes = (Map<Byte, String>) ois.readObject();

            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            //写入数据到文件
            os = new FileOutputStream(dstFile);
            os.write(bytes);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                os.close();
                ois.close();
                is.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 压缩文件
     * @param srcFile  要压缩的文件
     * @param dstFile 压缩后要存放目录
     */
    public static void zipFile(String srcFile,String dstFile){

        //文件输出+流
        OutputStream os = null;
        ObjectOutputStream oos = null;
        //文件的输入流
        FileInputStream is = null; //读取文件
        try{
            is = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的byte[]
            byte[] b = new byte[is.available()];//is.available 返回源文件大小
            //读取文件
            is.read(b);
            //对源文件压缩
            byte[] huffmanBytes = huffmanZip(b);
            //创建文件输出流 存放压缩文件
            os = new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            //赫夫曼编码字节数组写入压缩文件
            oos.writeObject(huffmanBytes);
            //对象流的方式写入 赫夫曼编码 以后恢复源文件时使用 所以要把赫夫曼编码写入压缩文件
            oos.writeObject(huffmanCodes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                is.close();
                oos.close();
                os.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }



    //数据解压
    //思路
    //1.将huffmanCodeBytes [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
    //先转成 赫夫曼编码对应的二进制字符串
    //2.赫夫曼编码对应的二进制字符串 对照赫夫曼编码 转成 字符串

    //编写一个方法 完成对压缩数据的解码
    /**
     *
     * @param huffmanCodes 赫夫曼编码表 map
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 原来的字符串对应数组
     */
    private static byte[] decode(Map<Byte,String> huffmanCodes,byte[] huffmanBytes){

        //1.先得到huffmanBytes 对应的二进制字符串
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制字符串
        for (int i = 0; i < huffmanBytes.length; i++){
            byte b = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }

        //字符串按照指定赫夫曼编码进行解码
        // 赫夫曼编码进行调换 反向查询 a->100 100->a
        Map<String,Byte> map = new HashMap<String,Byte>();
        for (Map.Entry<Byte,String> entry : huffmanCodes.entrySet()){
            map.put(entry.getValue(),entry.getKey());
        }
//        System.out.println("map=" + map);

        //集合存放byte
        List<Byte> list = new ArrayList<>();
        //i 就是索引 扫描stringbuilder
        for (int i = 0; i<stringBuilder.length();){
            int count = 1;//小的计数器
            boolean flag = true;
            Byte b = null;

            while(flag){
                //取出一个 1 0 递增取出key
                String key = stringBuilder.substring(i,i+count);//i 不动 count移动 直到匹配到一个字符
                b = map.get(key);
                if (b == null){//没有匹配到
                    count++;
                }else{//匹配到
                    flag = false;
                }
            }
            list.add(b);
            i += count;//i移动到count
        }
        //for结束 list中存放所有字符
        //把list数据放入到byte[] 并返回
        byte b[] = new byte[list.size()];
        for (int i = 0; i < b.length; i++){
            b[i] = list.get(i);
        }
        return b;
    }

    /**
     * 将一个byte 转成一个二进制字符串
     * @param b
     * @param flag 标志是否需要补位
     * @return
     */
    private static String byteToBitString(boolean flag,byte b){
        //保存b
        int temp = b;
        //正数 存在补高位
        if (flag) {
            temp |= 256;
        }

        String str = Integer.toBinaryString(temp);
        if (flag) {
//            System.out.println(str);
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }


    //封装方法
    /**
     *
     * @param bytes 原始字符串对应的字符数组
     * @return 经过赫夫曼编码处理后的字节数组
     */
    private static byte[] huffmanZip(byte[] bytes){
        //byte转换成 list
        List<Node> nodes = getNodes(bytes);

        //创建赫夫曼树
        Node huffmanTree = createHuffmanTree(nodes);
        System.out.println(huffmanTree);
        //对应赫夫曼编码(根据 赫夫曼树)
        Map<Byte, String> huffmanCodes = getCodes(huffmanTree);

        //根据生成的赫夫曼编码，压缩 得到压缩后的赫夫曼编码数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);

        return huffmanCodeBytes;
    }


    /**
     * 1将赫夫曼树 存放在Map<Byte,String>形式**/
    static Map<Byte,String> huffmanCodes = new HashMap<Byte,String>();
    /**
     * 2.生成赫夫曼树编码表示 拼接路径 定义一个StringBuffer 存储某个叶子结点堆顶路径*/
    static StringBuilder stringBuilder = new StringBuilder();
    //调用方便
    private static Map<Byte,String> getCodes(Node root){
        if (root == null){
            return null;
        }
        //处理root左子树
        getCodes(root.left,"0",stringBuilder);
        //处理root右子树
        getCodes(root.right,"1",stringBuilder);
        return huffmanCodes;
    }

    /**
     *  * 编写一个方法 将字符串byte数组 通过生成的赫夫曼编码表 返回一个赫夫曼编码 压缩后的byte[]
     * @param bytes 原始字符串对应的byte[]
     * @param huffmanCodes 生成赫夫曼编码map
     * @return 返回赫夫曼编码处理后的byte[]
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {

        //1.利用 huffmanCodes 将bytes 转成 赫夫曼编码对应字符串
        StringBuilder stringBuilder = new StringBuilder();
        //遍历bytes 从huffmanCodes中拿出数据向strbuilder中添加
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
//        System.out.println(stringBuilder.toString());
        //将字符串转成 byte[]
        //统计返回 byte[] huffmanCodeBytes 长度
        //int len = (stringbuffer.length()+7)/8
        int len;
        if (stringBuilder.length() % 8 == 0) {//8的整数
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        //创建 存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;//记录是第几个byte

        for (int i = 0; i < stringBuilder.length(); i += 8) {//每8位对应一个byte 步长+8
            String strByte;
            if (i + 8 > stringBuilder.length()) {//不够8位
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }

            //strbyte转成 byte 放入到huffmanCodeBytes
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    /**
     *  功能 将传入的node结点的所有叶子结点和赫夫曼树编码得到 放入到huffmanCodes集合
     * @param node 传入结点
     * @param code 路径 左子节点0 右子结点 1
     * @param stringBuilder 用于拼接路径
     */
    public static void getCodes(Node node,String code,StringBuilder stringBuilder){
        StringBuilder stringBuffer2 = new StringBuilder(stringBuilder);

        //将code加入到stringbuffer2
        stringBuffer2.append(code);
        if (node != null){
            //当前node 是叶子结点还是非叶子结点
            if (node.data == null){//非叶子结点
                //递归
                //向左递归
                getCodes(node.left,"0",stringBuffer2);
                //向右递归
                getCodes(node.right,"1",stringBuffer2);
            }else{//说明是一个叶子结点
                //就表示找到某个叶子结点的最后
                huffmanCodes.put(node.data,stringBuffer2.toString());
            }
        }
    }

    /**
     *
     * @param bytes 接受一个字节数组
     * @return 返回List形式
     */
    public static List<Node> getNodes(byte[] bytes){

        //1.创建一个ArrayList
        ArrayList<Node> nodes = new ArrayList<Node>();

        //遍历bytes 统计每一个byte出现的次数-->map[key,value]
        Map<Byte,Integer> counts = new HashMap<>();
        //循环遍历bytes数组
        for (byte b : bytes){
            //拿到第一个值 判断
            Integer count = counts.get(b);
            if (count == null){//Map还没有这个字符数据 第一次
                counts.put(b,1);
            }else{
                counts.put(b,count+1);
            }
        }

        //每一个键值对 转成node对象 加入到nodes集合
        //遍历map
        for (Map.Entry<Byte,Integer> entry : counts.entrySet()){
            nodes.add(new Node(entry.getKey(),entry.getValue()));
        }
        return nodes;
    }
    //前序遍历
    public static void preOrder(Node root){
        if (root != null){
            root.preOrder();
        }else{
            System.out.println("赫夫曼树");
        }
    }



    /**
     *  通过list 创建对应赫夫曼殊
     *
     * @param nodes
     * @return
     */
    public static Node createHuffmanTree(List<Node> nodes){
        while(nodes.size() > 1){
            //排序 小到大
            Collections.sort(nodes);
            //取出一棵最小的二叉树
            Node leftNode = nodes.get(0);
            //取出二棵最小的二叉树
            Node rightNode = nodes.get(1);
            //新的二叉树 根节点没有data 只有权值
            Node parent = new Node(null,leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            //已经处理的两颗二叉树从nodes删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //新的二叉树
            nodes.add(parent);
        }
        //返回的结点 赫夫曼树 根节点
        return nodes.get(0);
    }
}
//创建Node 数据和取值
class Node implements Comparable<Node> {
    Byte data;//存放数据本身
    int weight;//取值
    Node left;
    Node right;

    public Node(Byte data,int weight){
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        //从小到大
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }
    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.right.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }
}
