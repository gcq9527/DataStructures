package com.atguigu.sparsearray;

import java.io.*;

public class SpareArray {

    public static void main(String[] args) throws IOException {
        //创建一个原始的二维数组11*11
        //0:表示没有棋子 1 表示黑子 2表示篮子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;

        //输出原始二维数组
        for (int[] row : chessArr1){
            for (int data : row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        //将二维数组 转 稀疏数组
        //1.先遍历二维数组 得到有效数据的个数
        int sum = 0;
        for (int i = 0; i<11; i++){
            for (int j = 0; j<11; j++){
                if (chessArr1[i][j] != 0){
                    sum++;
                }
            }
        }
        System.out.println(sum);
        //创建对应的稀疏数组  多少行等于有效数据个数+1
        int sparseArr[][]  = new int[sum+1][3];   //列是固定的 第一个值行 第二个值 列 第三个值 有效值
        // 给稀疏数组赋值
        sparseArr[0][0] = 11; //行为11
        sparseArr[0][1] = 11;  //列为11
        sparseArr[0][2] = sum; //第一行第三列值为有效个数

        //遍历二维数组 将非0的值存放(有效) sparseArr 稀疏数组中
        int count = 0;//用于·记录第几个非0数据
        for (int i = 0; i<11; i++){//11行
            for (int j = 0; j<11; j++){
                if (chessArr1[i][j] != 0){ //二维数组中值不等于0
                    count++;
                    sparseArr[count][0] = i;//行
                    sparseArr[count][1] = j;//列
                    sparseArr[count][2] =  chessArr1[i][j]; //第三列就是值
                }
            }
        }
    //输入稀疏数组的形式
        System.out.println();
        System.out.println("得到的稀疏数组为~~~~~");
        for (int i = 0; i<sparseArr.length; i++){
            //第一个列值为行 第二 列   第三个值为有效对应值
            System.out.printf("%d\t%d\t%d\t\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }
        //遍历第二种方式
      /*  for (int[] row2 : sparseArr){
            for (int data : row2){
                System.out.print(data + "\t");
            }
            System.out.println();
        }*/

        //将稀疏数组--->恢复成原始的二维数组
        /**
         * 1.先读取稀疏数组的第一行根据第一行的数据 创建原始的二维数组 比如上面 chessArr2
         * 2.在读取稀疏数组后几行的数据 并赋给原始的二维数组即可
         */
       // 1.先读取稀疏数组的第一行根据第一行的数据 创建原始的二维数组 比如上面 chessArr2
        /** 稀疏数组结构是这样
         * 11 11 2
         * 1 2 1
         * 2 3 2
         */
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]]; //第一个值 行 第二个值 列
        //2.在读取稀疏数组后几行的数据 从第二行开始 并赋值给 原始二维数组
        //从二行开始 为什么？ 因为第一行是行和值
        for(int i=1;i<sparseArr.length; i++){
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        //3.输出恢复后的二维数组
        System.out.println();
        System.out.println("恢复后的二维数组");

        for (int[] row : chessArr2){
            for (int data : row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        FileWriter writer = null;
        try {
            writer = new FileWriter("D:\\java\\尚硅谷Java数据结构和算法\\笔记、代码、课件、资料\\WorkSpace\\map.txt");
            for(int i =0; i < sparseArr.length; i++) {
                for(int j = 0; j < 3; j++) {
                    writer.write(sparseArr[i][j]);
                }
//				writer.write("\n");
//				写入的时候不需要换行！！我在这里摔倒了就不希望有人再在同一个地方摔倒。
//				如果你发现写入和读取的数字不对，第一件事情请看看你有没有把换行符之类的也写入了
//				导致reader把你的换行符也读取了。
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(writer != null) {
                    writer.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        //恢复文件

        FileReader reader = null;
        int[][] sparseArr2 = new int[sum+1][3];
        int getNum = 0;
        try {
            reader = new FileReader("D:\\java\\尚硅谷Java数据结构和算法\\笔记、代码、课件、资料\\WorkSpace\\map.txt");

            for(int i =0; i < sparseArr2.length; i++) {
                for(int j =0; j < 3; j++) {
                    getNum = reader.read(); //给列赋值
                    sparseArr2[i][j] = getNum;
                 /*   System.out.println(getNum + "----------");
                    System.out.println(sparseArr[i][j]+ "_______________________");*/
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(reader != null) {
                    reader.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        //打印数组
        System.out.println("打印读取出来的二维数组");
        for (int[] rows : sparseArr2){
            for (int data : rows ){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }
}