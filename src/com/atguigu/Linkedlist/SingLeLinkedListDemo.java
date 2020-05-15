package com.atguigu.Linkedlist;

import java.sql.ParameterMetaData;
import java.util.Stack;

public class SingLeLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        //先创建
        HerNode herNode = new HerNode(1,"宋江","及时雨");
        HerNode herNode2 = new HerNode(2,"卢俊义","玉麒麟");
        HerNode herNode3 = new HerNode(3,"吴用","智多心星");
        HerNode herNode4 = new HerNode(4,"林冲","豹子头");

        //创建要给链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(herNode);
//        singleLinkedList.add(herNode4);
//        singleLinkedList.add(herNode2);
//        singleLinkedList.add(herNode3);

        //加入编号的顺序
        singleLinkedList.addbyOrder(herNode);
        singleLinkedList.addbyOrder(herNode4);
        singleLinkedList.addbyOrder(herNode2);
        singleLinkedList.addbyOrder(herNode3);

        singleLinkedList.list();

        //测试修改节点
//        HerNode newherNode = new HerNode(2,"小卢","玉麒麟");
//        singleLinkedList.update(newherNode);
//
     System.out.println("修改后");
        //显示
//        singleLinkedList.list();
//        //删除一个节点
//
//        singleLinkedList.del(1);
//        System.out.println("删除后!!");
//        singleLinkedList.del(4);
//        singleLinkedList.list();
//        //测试单链表有效节点个数
//        System.out.println("有效节点个数" + getLiength(singleLinkedList.getHead()));

        //测试一下 倒数第K个节点
       /* HerNode res = findLastIndexNode(singleLinkedList.getHead(),0);
        System.out.println("倒数第一个" + res);
*/
//        reversetList(singleLinkedList.getHead());
//        singleLinkedList.list();

        //逆序打印
        reverserPrint(singleLinkedList.getHead());
       // singleLinkedList.list();
    }

    //获取单链表节点个数 如果带头节点 不统计头节点
    public static int getLiength(HerNode herNode){
        if (herNode.next == null){
            return 0;
        }
        int length = 0;
        HerNode cur = herNode.next;
        while(cur.next != null){
            length++;
            cur = cur.next;
        }
        return  length;
    }


    //将单链表反转
    public static void reversetList(HerNode head){
        //如果当前链表为空 或者只有一个节点 就无需反转
        if(head.next == null || head.next.next == null){
            return ;
        }
        //辅助指针 帮助我们遍历原来的链表
        HerNode cur = head.next;
        HerNode next = null; //指向当前节点的下一个节点
        HerNode reverseHead = new HerNode(0,"","");
        //遍历原来的链表 每遍历一个节点 就将其取出 并放在新的链表的reverseHead 的前端
        //
        while(cur != null){
            next = cur.next; //先暂时保存当前节点的下一个节点
            cur.next = reverseHead.next;
            reverseHead.next = cur;
            cur = next; //让cr后移动
        }
        head.next = reverseHead.next;
    }

    //使用方式二栈 这个数据结构进行逆序
    public static void reverserPrint(HerNode head){
        if (head.next == null){ //空链表
            return; //不能打印
        }
        //先创建一个栈 将各个节点压入栈
        Stack<HerNode> stack = new Stack<>();
        HerNode cur = head.next;
        //将链表所有节点压入栈
        while (cur != null){
            stack.push(cur);
            cur = cur.next; //后移 这样就可以压入下一个节点
        }
        //遍历将 栈的节点打印 出找
        while (stack.size() > 0){
            System.out.println(stack.pop()); //逆序 先进后出
        }

    }

    //倒数个k节点

    //1.编写一个方 接收head节点 同时接收一个index
    //2.index表示倒数第index个节点
    //3.先把链表从头到尾遍历 得到链表总长度
    //4.得到size后 我们从链表的第一个开始遍历 size-index个 就可以得到
    //5.如果得到了则返回该节点 否则 返回null
    public static HerNode findLastIndexNode(HerNode head,int index){
        //判断链表为空
        if (head == null){
            return null;//没有找到
        }
        //第一次遍历得到链表长度
        int size = getLiength(head);
        //第二次遍历 size-index位置 就是我们倒数的第K个节点
        //先做一个index的校验
        if (index <= 0 || index>size){
            return null;
        }
        //定义一个辅助变量Her for 循环定位倒index
        HerNode cur = head.next;//3
        for (int i=0; i<size-index; i++){
            cur = cur.next;
        }
        return cur;
    }

}

//定义SingLeLinkeList来管理英雄
class  SingleLinkedList {
    //先初始化一个头节点 头节点不要动，不存放具体数据
    private HerNode head = new HerNode(0, "", "");
    //返回头节点
    public HerNode getHead(){
        return head;
    }
    //添加节点到单项链表
    //当不考虑编号顺序时
    //1.找到当前链表的最后节点
    public void add(HerNode herNode) {
        //辅助变量 应为head节点不能动
        HerNode temp = head;
        //遍历链表 找到最后
        while (true) {
            //找到链表的最后
            if (temp.next == null) {
                break;
            }
            //如果没有找到最后,tmep后移
            temp = temp.next;
        }
        //当推出while循环时 temp就指向链表最后
        temp.next = herNode;
    }

    //修改节点信息 根据no编号来修改 即编号不能更改
    //根据newHerNode 的 no 来修改
    public void update(HerNode newHernode) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点 根据no编号
        //定义一个辅助变量
        HerNode temp = head.next;
        boolean flag = false; //表示是否找到
        while (true) {
            if (temp == null) {
                break;//已经遍历完链表
            }
            if (temp.no == newHernode.no) {
                //扎到
                flag = true;
                break;
            }
            temp = temp.next;
        }

        //根据flag判断是否找到要修改的节点
        if (flag) {
            temp.name = newHernode.name;
            temp.nickname = newHernode.nickname;
        } else { //没有找到
            System.out.printf("没有找到 %d 编号为的节点不能修改\n", newHernode.no);
        }
 }



    //第二种在添加英雄时，根据排名将英雄插入到指定位置
    //如果有这个排名 添加失败 给出提示
    public void addbyOrder(HerNode herNode) {
        ////因为头节点不能动 因此我们任然通过一个辅助指针 变量 来帮助我们找到添加的位置
        //因为单链表 因为我们找到temp 是位于 添加位置的前一个节点 否则插入不了
        HerNode temp = head;
        boolean flag = false;//标志添加的编号是否存在 默认为false
        while (true) {
            if (temp.next == null) {//说明temp已经在链表的最后
                break;
            }
            if (temp.next.no > herNode.no) { //位置找到 temp后面插入
                break;
            } else if (temp.next.no == herNode.no) { //希望添加的hernode的编号已经存在
                flag = true;//说明编号存在
                break;
            }
            //后移 遍历当前链表
            temp = temp.next;
        }
        //判断flag的值
        if (flag) { //不能添加 说明编号存在
            System.out.printf("准备插入的英雄编号，已经存在 不能加入\n", herNode.no);
        } else {
            //插入到链表中 temp的后面
            herNode.next = temp.next;
            temp.next = herNode;
        }
    }

    //显示链表(遍历)
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能为空 因此我们需要一个辅助变量来遍历
        HerNode temp = head.next;
        while (true) {
            //判断是偶到链表最后
            if (temp == null) {
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //将temp后移,一定要后移
            temp = temp.next;
        }
    }


    //删除节点
    //思路
    //1.head 不能动 因此我们需要一个temo辅助节点找到待删除节点的前一个节点
    //2.说明我们在比较时 是temp.next.no 和需要删除的节点no比较
    public void del(int no) {
        HerNode temp = head;
        boolean flag = false; //标志是否找到待删除节点
        while (true) {
            if (temp.next == null) { //已经到链表最后
                break;
            }
            if (temp.next.no == no) {
                //找到待删除节点的前一个节点
                flag = true;
                break;
            }
            temp = temp.next;//往后移
        }
        //判断flag
        if (flag) {
            //可以删除
            temp.next = temp.next.next;
        } else {
            //说明没有找到
            System.out.printf("要删除的节点%d 不存在啊", no);
        }
    }
}

//定义HeroNode 每个HerNode 对象就是一个节点
class HerNode{
    public int no;
    public String name;
    public String nickname;
    public HerNode next;//指向下一个节点

    //构造器
    public HerNode(int hNo,String hName,String hNickname){
        this.no=hNo;
        this.name = hName;
        this.nickname = hNickname;
    }
    //重写tostring

    @Override
    public String toString() {
        return "HerNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
    //

}