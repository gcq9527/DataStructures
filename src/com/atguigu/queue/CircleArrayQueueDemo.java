package com.atguigu.queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        //创建一个队列
        CircleArray queue = new CircleArray(3); //说明设置4 其最队列有效数据最大是3
        char key = ' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输入一个菜单
        while(loop){
            System.out.println("s(show) 显示队列");
            System.out.println("e(exit) 退出程序");
            System.out.println("a(add) 添加队列");
            System.out.println("g(get) 从队列中取出数据");
            System.out.println("h(head) 查看队列头的数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key){
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try{
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    }catch (Exception ex){
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 'h'://查看队列头的数据
                    try{
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n",res);
                    }catch (Exception ex){
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序推出");
    }
}
class CircleArray{
    private int maxSize; //表示数组最大容量
    //front变量的含义做一个调整 front 就指向队列的第一个元素 也就是arr[front]
    //front初始值为0
    private int front;
    //rear变量的含义做一个调整 rear 就指向队列的第后一个元素的最后一个位置 因为希望空出一个元素
    //rear初始值为0
    private int rear;
    private int[] arr;//该数组用于存放数据 模拟队列

    public CircleArray(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }
    public boolean isEmpty(){
        return rear == front;
    }
    public boolean isFull(){
        return (rear + 1 ) % maxSize == front;
    }

    //添加数据到队列
    public void addQueue(int n){
        //判断队列是否满
        if (isFull()){
            System.out.println("队列满，不能加入数据");
            return;
        }
        //直接将数据插入
        arr[rear] = n;
        //将rear后移 这里必须考虑取模
        rear = (rear+1)%maxSize;
    }

    //获取队列数据，出队列
    public int getQueue(){
        //判断队列是否为空
        if (isEmpty()){
            //通过抱出异常
            throw new RuntimeException("队列空，不能取出数据");
        }
        //这里分析出front是指向队列的第一个元素
        //1.先把front对应的值保存到一个临时变量
        //2.将front 后移,考虑取模
        //3.将临时保存的变量返回
        int value = arr[front];
        front = (front+1) % maxSize;
        return value;
    }

    //显示队列所有数据
    public void showQueue(){
        //遍历
        if (isEmpty()){
            System.out.println("队列空的");
            return;
        }
        //思路 从front开始遍历 遍历多少个元素
        //
        for (int i=front; i<front+size(); i++){
            System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]);
        }
    }
    //求出当前队列有效数据的个数
    public int size(){
        return (rear+maxSize-front)%maxSize;
    }
    //显示队列的头数据 注意不是取出数据
    public int headQueue(){
        //判断
        if (isEmpty()){
            throw new RuntimeException("队列空，不能取出数据");
        }
        return arr[front+1];
    }

}