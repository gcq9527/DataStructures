package com.atguigu.linkedlist;

public class SingLeLinkedListDemo2 {
    public static void main(String[] args) {
        //进行测试
        //先创建
        HerNode1 herNode = new HerNode1(1,"宋江","及时雨");
        HerNode1 herNode2 = new HerNode1(2,"卢俊义","玉麒麟");
        HerNode1 herNode3 = new HerNode1(3,"吴用","智多心星");
        HerNode1 herNode4 = new HerNode1(4,"林冲","豹子头");

        //创建要给链表
        SingleLinkedList1 singleLinkedList = new SingleLinkedList1();
        singleLinkedList.add(herNode);

        singleLinkedList.add(herNode3);
        singleLinkedList.add(herNode2);
        singleLinkedList.add(herNode4);

        //显示
        System.out.println("显示一把");
        singleLinkedList.list();
        HerNode1 newherNode = new HerNode1(2,"小卢","玉麒麟");
        singleLinkedList.update(newherNode);
        System.out.println("修改后");
        System.out.println("----------删除前");
        singleLinkedList.list();
        singleLinkedList.delte(2);
        System.out.println("----------删除后");
        singleLinkedList.list();
        System.out.println("====================分割");
        //倒数第k个节点
        System.out.println(findLastIndexNode(singleLinkedList.getHead1(),1));

        System.out.println("链表有效个数" + getLine(singleLinkedList.getHead1()));
    }
    //获取单链表节点个数
    public static int getLine(HerNode1 head){
        if (head.next == null){
            return 0;
        }
        HerNode1 temp = head.next;
        int num = 0;
        while (temp != null){
            num++;
            //加一后 往后移动
            temp = temp.next;
        }
        return num;
    }

    //倒数第K个节点
    //1.编写一个方法 接收head节点 同时接收一个index(倒数的节点)
    //2.index表示倒数第index个节点
    //3.先把链表从头到尾遍历 得到链表总长度
    //4.得到size后 我们从链表的第一个开始遍历 size-index 个 就可以得到
    //5.如果得到了则返回该节点 否则 返回null
    public static HerNode1 findLastIndexNode(HerNode1 head1, int index){
        if (head1 == null){ //链表下一个节点为空 说明没有值
            return null;
        }
        HerNode1 temp = head1.next;
        int size = getLine(head1); //得到链表个数
        //验证数据
        if (index <= 0 || index > size){
            return null;
        }
         //3 - 1 2 循环走两次
        for (int i = 0; i<size-index; i++){
            temp = temp.next;
        }
        return temp;
    }


}
class SingleLinkedList1{
    private HerNode1 head1 = new HerNode1(0,"","");

    public HerNode1 getHead1() {
        return head1 ;
    }

    public void setHead1(HerNode1 head1) {
        this.head1 = head1;
    }

    public void add(HerNode1 head11){
        //辅助变量
        HerNode1 temp = head1;
        //循环遍历 进入首次都是null 然后往 后面的next域去查找
        while(true){
            if (temp.next == null){
                break;
            }
            //没找到往后移动
            temp = temp.next;
        }
        //把传递过来的值添加到next域中
        temp.next = head11;
    }
    //遍历
    public void list(){
        if (head1.next == null){
            System.out.println("链表为空");
            return;
        }
        HerNode1 temp = head1.next;
        while (true){
            if (temp == null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }
    //修改
    public  void update(HerNode1 herNode1){
        if (head1.next == null){
            System.out.println("链表为空");
            return;
        }
        boolean flag = false;
        HerNode1 temp = head1.next;
        while (true){
            if (temp.next == null){
                break; //说明已经遍历完例表
            }
            if (temp.no == herNode1.no){
                flag = true;
                break; //找到了
            }
            temp = temp.next;
        }
        if (flag){
            temp.name=herNode1.name;
            temp.nickname = herNode1.nickname;
        }else{
            System.out.println("没有找到no为" + herNode1.no);
        }
    }
    //删除单链表节点
    public void delte(int no1){
        if (head1.next == null){
            System.out.println("链表为空");
        }
        HerNode1 temp = head1.next;
        boolean falg = false;
        while(true){
            if (temp == null){
                break;//链表已经遍历完成
            }
            if (temp.next.no == no1){
                falg = true;
                break;
            }
            //temp往后移动
            temp = temp.next;
        }
        if (falg){
            temp.next = temp.next.next;
        }else{
            System.out.println("没有这个" + no1);
        }
    }
}


class HerNode1{
    public int no;
    public String name;//名字
    public String nickname;//昵称
    public HerNode1 next; //指向下一个节点


    public HerNode1(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HerNode1{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}