package com.atguigu.linkedlist;

/**
 * Created by GuoChengQian on 2020/5/15 15:34
 */
public class Josefu {
    public static void main(String[] args) {
        //测试 看看构建环形链表 喝遍历的是否ok
        CircleSingLeLinkedList circleSingLeLinkedList = new CircleSingLeLinkedList();
        circleSingLeLinkedList.addBoy(5);//添加五个小孩节点
        circleSingLeLinkedList.showBoy();//显示出五个小孩

        circleSingLeLinkedList.courBoy(1,2,5);
    }
}
class CircleSingLeLinkedList{
    //创建一个first节点 当前没有编号
    private Boy first = new Boy(-1);

    //添加小孩节点 构建成一个环形链表
    public void addBoy(int nums){
        if (nums < 1){
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null;//辅助指针 帮助构建环形链表
        //用for循环创建环形链表
        for (int i=1; i<=nums; i++){
            //根据编号创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if (i == 1){
                first = boy;
                first.setNext(first);//构成环
                curBoy = first;//让curboy指向第一个小孩子
            }else{
                curBoy.setNext(boy);//临时变量的next等于新的boy
                boy.setNext(first);//新的boy设置他的next等于头节点 从而形成一个环形链表
                curBoy = boy;//boy的值给临时变量curBoy
            }
        }
    }

    //遍历当前环形链表
    public void showBoy(){
        //判断链表是否为空
        if (first == null){
            System.out.println("没有任何小孩~");
            return;
        }
        //因为first不能动 我们需要一个辅助指针完成遍历
        Boy curBoy = first;
        while(true){
            System.out.printf("小孩的编号 %d \n",curBoy.getNo());
            //环形链表的next等于 头节点 说明已经到头了
            // 因为在插入的时候 first是头节点 插入后都会指向头节点
            if (curBoy.getNext() == first){//说明链表遍历完毕
                break;
            }
            //往后移动再次打印
            curBoy = curBoy.getNext();
        }
    }

    //根据用户的输入 计算出圈的顺序
    /**
     *
     * @param startNo //表示从第几个小孩开始数数
     * @param coutnNum //表示数几下
     * @param nums //表示最初有多少个小孩在圈中
     */
    public void courBoy(int startNo, int coutnNum, int nums) {
        //先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数有问题");
            return;
        }
        //创建一个辅助变量
        Boy helper = first;
        //1.需求创建一个辅助指针(变量)helper 指向环形链表最后节点 此处通过whil循环实现
        while (true) {
            if (helper.getNext() == first) { //说明helper指向最后小孩的节点
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前 让first和helper移动k-1次
        for (int j = 0; j < startNo - 1; j++) {
            first = first.getNext();
            helper = helper.getNext(); //环形链表 指向first
        }
        //当小孩报数的时候 让first和helper指针同时移动m-1次 然后出圈
        //这里是一个循环操作，知道圈中有这么一个节点
        while (true) {
            if (helper == first) {//说明圈中只有一节点 一个人
                break;
            }
            //让first和helper指针同时移动conutNum-1
            for (int j = 0; j < coutnNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的节点 就是要出圈的小孩节点
            System.out.printf("小孩%d出圈\n", first.getNo());
            //将first指向的小孩的节点出圈
            //让first往前移动 指向他的前一个
            // 然后设置helper的next为first 构成环形 之前的节点就会被垃圾回收机制给回收
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中额小孩编号%d \n", helper.getNo());
    }

}

//创建一个Body类 表示一个节点
class Boy{
    private int no;///编号
    private Boy next;//指向下一个节点 默认是null
    public Boy(int no){
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}