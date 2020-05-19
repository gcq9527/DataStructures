package com.atguigu.recursion;

/**
 * Created by GuoChengQian on 2020/5/19 11:07
 */
public class MiGong {
    public static void main(String[] args) {
        //创建一个二维数组 模拟迷宫
        //int[] arr = new int[10]; //0....9
        int[][] map = new int[8][7];//第一个是 行 第二个是列
        //使用1表示墙
        //上下全部置成1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;//第0行 上面开始
            map[7][i] = 1;//最下面一行 第7行开始变1
        }
        //左右全部置成1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;//每一行的第0个设置为1
            map[i][6] = 1;//每一行的第6个(最后一个)设置为1
        }
        //挡板
        map[3][1]=1;
        map[3][2]=1;
        //输出地图
        System.out.println("地图的情况");
        for (int i=0; i<8; i++){//行
            for(int j=0; j<7; j++){//列
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }

        //使用递归回溯给小球找路
        setWay(map,1,1);//下 右 上 左
//        setWay2(map,1,1);
        //输出新的地图,小球走过并标识过的递归
        System.out.println("小球走过，并标识过的 地图的情况");
        for (int i=0; i<8; i++){//行
            for(int j=0; j<7; j++){//列
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }

    /**
     * 使用递归回溯给小球找路
     * 1.map 表示地图
     * 2.i,j表示从地图的那个位置开始触发(1,1)
     * 3。如果小球能到map[6][5] 位置 则说明通路找到
     * 4.当map[i][j] 为0 表示该点没有走过 为1表示墙 2表示通路可以走，3 表示已经走过 但是走不通
     * 5.在走迷宫时，需要确定一个策略 (方法) 下->右->
     * @param map 表示地图
     * @param i 从那个位置开始找
     * @param j
     * @return 如果找到路 就返回true 否则返回false
     */
    public static boolean setWay(int[][]map,int i,int j){
        //arr[i][j];
        if (map[6][5] == 2){//通路已经找到
            return true;
        }else{
            if (map[i][j] == 0){//如果当 前这个点还没有走过
                //按照策略 下->右->上->左 走
                map[i][j] = 2;//假定该点可以走通
                if (setWay(map,i+1,j)){//向下走 i是行 加一就是往下
                    return true;
                }else if (setWay(map,i,j+1)){//向右走 j+1 列加1就是往右走
                    return true;
                }else if (setWay2(map,i-1,j)){//向上 //i-1就是向上
                    return true;
                }else if (setWay2(map,i,j-1)){//向左走//
                    return true;
                }else{
                    //说明该点是走不通的 死路
                    map[i][j]=3;
                    return false;
                }
            }else {//如果map[i][j]!=0 可能是1 2 3
                return false;
            }
        }
    }
    //修改策略 改成上->右->下->左
    public static boolean setWay2(int[][]map,int i,int j){

        if (map[6][5] == 2){//通路已经找到
            return true;
        }else{
            if (map[i][j] == 0){//如果当 前这个点还没有走过
                //按照策略 下->右->上->左 走
                map[i][j] = 2;//假定该点可以走通
                if (setWay2(map,i-1,j)){//向上走
                    return true;
                }else if (setWay2(map,i,j+1)){//向右走
                    return true;
                }else if (setWay(map,i+1,j)){//向下
                    return true;
                }else if (setWay(map,i,j-1)){//向左走
                    return true;
                }else{
                    //说明该点是走不通的 死路
                    map[i][j]=3;
                    return false;
                }
            }else {//如果map[i][j]!=0 可能是1 2 3
                return false;
            }
        }
    }
}