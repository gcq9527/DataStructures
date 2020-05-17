package com.atguigu.linkedList2;


import java.util.Stack;

/**
 * @author yd
 * @version 1.0
 * @date 2020/4/27 22:07
 */
public class SingLinkedList {
    public static void main(String[] args) {
        HeroNode herNode = new HeroNode(1,"宋江","及时雨");
        HeroNode herNode2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode herNode3 = new HeroNode(3,"吴用","智多心星");
        HeroNode herNode4 = new HeroNode(4,"林冲","豹子头");
        SingleLinkedList singLinkedList = new SingleLinkedList();
        SingleLinkedList singLinkedList2 = new SingleLinkedList();

        singLinkedList.addByOrder(herNode);
        singLinkedList.addByOrder(herNode4);
        singLinkedList.addByOrder(herNode3);
        singLinkedList.addByOrder(herNode2);
        singLinkedList2.addByOrder(herNode4);
        singLinkedList2.addByOrder(herNode3);
        singLinkedList2.addByOrder(herNode2);
        System.out.println("单链表1");
        singLinkedList.list();
        System.out.println("单链表2");
        singLinkedList2.list();
        //显示出来
        HeroNode heroNode = mergeList(singLinkedList.getHead(), singLinkedList2.getHead());
        System.out.println("显示合并的" + heroNode);
        singLinkedList2.list();
        /*System.out.println("修改前显示");
        singLinkedList.list();
        //修改后显示
        HeroNode newHeoNode = new HeroNode(2,"123123123123123","玉麒麟");
        //进行修改
        singLinkedList.update(newHeoNode);
        System.out.println("修改后显示");
        singLinkedList.list();

        singLinkedList.del(1);

        System.out.println("删除后显示");
        singLinkedList.list();
*/
       /* int length = getLength(singLinkedList.getHead());//3
        System.out.println("单链表节点的个数" + length  );
        //测试倒数第k个节点
        HeroNode lastIndexNode = findLastIndexNode(singLinkedList.getHead(), 1);
        System.out.println("temp="+lastIndexNode );*/

     //   System.out.println("显示前的数据");
       // singLinkedList.list();
      /*  System.out.println("显示后的数据");
        reversetList(singLinkedList.getHead());
        singLinkedList.list();*/

      /*  System.out.println("利用栈进行反转");
        reversetPrint(singLinkedList.getHead());*/


    }

    /**
     *  合并两个有序的单链表 ,合并之后的链表依然有效
     * @param head1
     * @param head2
     * @return
     */
    public static HeroNode mergeList(HeroNode head1 ,HeroNode head2){
        if (head1 == null && head2 == null) {
            // 如果两个链表都为空 return null;
        }
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        HeroNode head; // 新链表的头结点
        HeroNode current; // current结点指向新链表
        // 一开始，我们让current结点指向head1和head2中较小的数据，得到head结点
        if (head1.no < head2.no) {
            head = head1;
            current = head1;
            head1 = head1.next;
        } else {
            head = head2;
            current = head2;
            head2 = head2.next;
        }
        while (head1 != null && head2 != null) {
            if (head1.no < head2.no) {
                current.next = head1;
                // 新链表中，current指针的下一个结点对应较小的那个数据
                current = current.next; // current指针下移
                head1 = head1.next;
            } else {
                current.next = head2;
                current = current.next;
                head2 = head2.next;
            }
        }
        // 合并剩余的元素
        if (head1 != null) {
            // 说明链表2遍历完了，是空的
            current.next = head1;
        }
        if (head2 != null) {
            // 说明链表1遍历完了，是空的
            current.next = head2;
        }
        return head;
    }





    //计算出单链表节点的个数 带头节点 不统计头节点
    public static int getLength(HeroNode head){
        if (head.next == null){
            return 0 ;//空链表
        }
        int length = 0;
        //辅助变量
        HeroNode temp = head.next;
        while(temp !=null){
            length++;
            temp = temp.next;//遍历
        }
        return length;
    }

    /**
     * 查找单链表中倒数第k个节点  新浪面试题
     * 1.接收一个head节点和一个 index
     * 2.index表示倒数第index个节点
     * 3.把链表从头到尾遍历 得到链表的长度
     * 4.得到size后 从链表的第一个开始遍历(size-index) 得到我们想要的结果
     * 5.找到了就返回该节点 没有找到的话 返回null
     */
    public static HeroNode findLastIndexNode(HeroNode head,int index){
        //链表为空的话 没有找到返回null
        if (head.next == null){
            return null;
        }
        //得到链表的长度(节点个数)
            int size = getLength(head);
            //不能小于0 并且查找的第k个节点不能大于单链表的节点个数
        if (index <=0 || index > size){
            return null;
        }
        //辅助变量'
        HeroNode temp = head.next;
        /**
         *   size-index 总节点个数 减掉 要寻找的倒数个节点数
         *   总结点3 倒数第1个节点 3-1 =2 循环两次找到
         */

        for (int i=0 ; i<size-index; i++){
            temp = temp.next;
        }
        return temp;
    }


    /***
     * 单链表反转
     */
    public static void reversetList(HeroNode head){
        //如果当前链表为空 或者只有一个节点 无需饭庄 直接返回
        if (head.next == null || head.next.next == null){
            return;
        }
        HeroNode cur = head.next;  //临时变量 用于辅助遍历原有链表
        HeroNode next = null;//指向当前节点（辅助变量）[cur]的下一个节点 原有节点的下一个 不然的话会断掉
        HeroNode reverseHead = new HeroNode(0 ,"","");//新的节点
        //遍历原来的列表 每遍历一个就将他取出  并放在新的链表·reverseHead链表中
        while (cur != null){
            next = cur.next; //暂时保存 辅助变量的下一个next
            cur.next = reverseHead.next;//将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur;//新链表的next 等于辅助变量
            cur = next;//让cur后移
        }
        //要反转的next 等于新创建的next
        head.next = reverseHead.next;
    }

    /**
     * 反转
     * 利用栈这个数据结构， 将各个节点压入栈中 利用栈先进后出的特点，实现逆序打印的效果
     * @param head
     */
    public static void reversetPrint(HeroNode head){
            if (head.next == null){
                return;//空链表不能打印
            }
            Stack<HeroNode> stack = new Stack<HeroNode>();
            HeroNode temp = head.next;
            while(temp != null){
                stack.push(temp);
                temp = temp.next;//往后移动
            }
            //遍历出来 将栈的节点打印出来
        while (stack.size()>0){
            System.out.println(stack.pop());//出栈
        }
    }



}
//定义SingLinkedList 管理我们的英雄
class SingleLinkedList{
    //先初始化一个头节点，头节点不动，不存放具体的数据
    private HeroNode head = new HeroNode(0,"","");

    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    public void add(HeroNode heroNode){
        //头节点不能动
        HeroNode temp = head;
        while (true){
            //最后节点的next等于null说明已经到尾部了
            if (temp.next == null){
                break;
            }
            //往后移动
            temp = temp.next;
        }
        //等于新的节点
        temp.next = heroNode;
    }
    //第二种添加方式,根据排名插入到指定位置
    /*
    * 1.先通过temp临时变量找到要插入前的位置 通过遍历
    * 2.找到后 新的节点.next = temp.next (temp.next 和后面是相连 断开)
    * 3.temp.next = 新的节点
    * */
    public void addByOrder(HeroNode heroNode) {
        HeroNode temp = head;//临时变量
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;//说明链表已经在最后
            } else if (temp.next.no > heroNode.no) {//位置找到就在temp的后面插入
                break;
            } else if (temp.next.no == heroNode.no) { //要插入的元素以及存在
                flag = true;
                break;
            }
            //后移 循环遍历链表
            temp = temp.next;
        }
        if (flag) {
            System.out.println("你要插入的元素" + heroNode.no + "以及存在");
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //根据编号no 来修改 即no的编号不能改
    ///根据newHeroNode 来修改
    public void update(HeroNode newHeroNode){
        //判断是否为空
        if (head.next == null){
            System.out.println("要修改的链表为空");
            return;
        }
        HeroNode temp = head.next; //临时变量
        boolean flag = false; //表示是否找到链表
        while(true){
            if (temp == null){
                break; //已经遍历完列表
            }else if (temp.no == newHeroNode.no){
                flag = true;//说明已经找到链表
                break;
            }
            //链表往后移动
            temp = temp.next;
        }
        if (flag){
            //修改值
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }else{
            System.out.printf("没有找到编号为 %d 的节点 不能修改\n ",newHeroNode.no);
        }

    }

    //显示全部链表
    public void list(){
        //链表头部为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //头节点不能为空
        HeroNode temp = head.next;
        while(true){
            if (temp == null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    /**
     * 删除节点
     * head不能动 需要一个tmep节点辅助节点 找到待删除节点的前一个节点
     * 比较时 是temp.next.no 和需要删除的节点的no比较
     * @param no
     */
    public void del(int no){
        HeroNode temp = head;
        boolean flag = false;
        while (true){
            if (temp.next == null){
                break;
            }
            //要删除的节点的前一个节点和no想用 说明找到
            if (temp.next.no == no){
                //找到待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){//找到了 可以删除
            temp.next = temp.next.next;//指向temp的下下个
        }else{
            System.out.printf("要删除的 %d 这个节点不存在",no);
        }
    }
}




//定义HearNode 每个HearNode对象就是一个节点
class HeroNode{
    public int no;//编号
    public String name;//名字
    public String nickname;//名称
    public HeroNode next;//用来指向下一个域


    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}