package com.atguigu.graph;

import sun.awt.image.ImageWatched;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by ChengQian on 2020/6/3 10:04
 */
public class Graph {

    private ArrayList<String> vertexList;//存储顺序点集合
    private int[][] edges; //存储图对应的邻结矩阵
    private int numOfEdges;//表示边的数目
    private boolean[] isVisited;//是否被访问

    public static void main(String[] args) {

        //图是否创建成功
        int n = 5; //结点的个数
        String VertexValue[] = {"A", "B", "C", "D", "E"};
        //创建图对象
        Graph graph = new Graph(n);
        //循环添加顶点
        for (String value : VertexValue) {
            graph.insertVertex(value);
        }
        //添加边
        //A-B A-C B-C B-D B-E
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);

        //显示一把邻结矩阵
        graph.showGraph();

        //测试一把 我们的dfs遍历是否ok
        System.out.println("深度遍历");
        graph.dfs();

        System.out.println("广度遍历");
        graph.bfs();
    }
    //构造器
    public Graph(int n) {
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);//定义好长度
        numOfEdges = 0; // 默认为0
    }

    //得到邻接结点的下标
    /**
     * index是行 i是列的值  循环找 找到就返回 当前数组的下标
     * @param index
     * @return 存在返回对应下标 不存在返回-1
     */
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    //根据前一个邻接结点的下标获取下一个邻接结点
    /**
     *
     * @param v1 行的值 从指定的行查找值
     * @param v2
     * @return
     */
    public int getNextNeighbor(int v1,int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0 ) {
                return j;
            }
        }
        return -1;
    }

    //对一个结点进行广度优先遍历
    public void bfs(boolean[] isVisited, int i) {
        int u;//表示队列的头结点对应下标
        int w;//邻接结点w
        //队列，记录结点访问的顺序
        LinkedList queue = new LinkedList<>();
        //访问结点 输出结点信息
        System.out.print(getValueByIndex(i) + "=>");
        //标记为已经访问
        isVisited[i] = true;
        //将结点加入队列
        queue.addLast(i);
        //队列不等于空
        while(!queue.isEmpty()) {
            //取出队列的头结点
            u = (Integer)queue.removeFirst();
            //得到结点u的邻结点
            w = getFirstNeighbor(u);
            while (w != -1) { //找到
                //没有被访问
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "=>");
                    //标记已经访问
                    isVisited[w] = true;
                    //入队
                    queue.addLast(w);
                }
                //查找结点u的 w的邻接结点后的下一个邻接结点w
                w = getNextNeighbor(u, w);//体现出广度优先
            }
        }
    }

    //遍历所有的结点 都进行广度优先搜索
    public void bfs() {
        isVisited = new boolean[5];
        for (int i = 0; i < getNumOfEdegs(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    //深度优先遍历算法
    /**
     * i 第一次就是0
     * @param isVisited
     * @param i
     */
    public void dfs(boolean[] isVisited,int i) {
        //1.首先我们访问该结点 输出
        System.out.print(getValueByIndex(i) + "->");
        //2.将结点设置成已经访问 true已经访问 false没有访问
        isVisited[i] = true;
        //3/查找结点i的第一个邻结点w
        int w = getFirstNeighbor(i);
        while(w != -1) { //有
            //4.w结点没有被访问过 对w结点进行深度优先遍历 再次走一次123步
            if (!isVisited[w]) {
                dfs(isVisited,w);
            }
            //5.如果w结点已经被访问过 查找结点v的w的邻接结点的下一个邻接结点
            w = getNextNeighbor(i,w);
        }
    }
    //对dfs 进行一个重载 遍历我们的所有的结点 进行dfs
    public void dfs() {

        isVisited = new boolean[5];
        //遍历所有的结点 进行dfs[回溯]
        for (int i = 0; i < getNumOfEdegs(); i++) {
            //没有被访问的
            if (!isVisited[i]) {
                dfs(isVisited,i);
            }
        }
    }


    //图的常用方法

    //返回他的个数
    public int getNumOfEdges() {
        return vertexList.size();
    }
    //显示图对应的矩阵
    public void showGraph() {
        for (int[] link : edges ) {
            System.out.println(Arrays.toString(link));
        }
    }
    //返回对应结点i(下标) 对应的数据 0->"A" 1->"B" 2->"C"
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }
    //返回v1和v2的值
    public int getWeight(int v1,int v2){
         return edges[v1][v2];
    }
    //显示图对应的绝症
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }
    //添加边的数目
    public int getNumOfEdegs(){
        return numOfEdges;
    }

    /**
     * 第一个是行 第二个是列
     * 无向图 返回来设置对应的边
     * @param v1 表示点的下标即使第几个顶点 A的结点就是0 B就是1 C就是2
     * @param v2 第二个顶点对应的下标
     * @param weight 对应的值
     */
    public void insertEdge(int v1,int v2,int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}