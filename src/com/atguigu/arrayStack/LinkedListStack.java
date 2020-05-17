package com.atguigu.arrayStack;

/**
 * Created by GuoChengQian on 2020/5/16 19:46
 */
public class LinkedListStack {
    public static void main(String[] args) {
        LinkListStack linkListStack = new LinkListStack();
        linkListStack.push(1);
        linkListStack.push(2);
        linkListStack.push(3);
        linkListStack.push(4);
        System.out.println("出栈");
      /*  System.out.println(linkListStack.pop());
        System.out.println(linkListStack.pop());
        System.out.println(linkListStack.pop());
        System.out.println(linkListStack.pop());*/
        System.out.println("使用list出栈");
        linkListStack.list();
    }
}
//栈
class LinkListStack{
    //头节点
    HeroNode head = new HeroNode(0);


    public boolean isEmpty(){
        return head.next == null;
    }

    public boolean isFull(){
        return false;
    }

    //进栈
    public void push(int num){
      if (isEmpty()){
          //如果为空创建一个新的节点
          head.next = new HeroNode(num);
      }else{
          //不为空 实例化新的节点
          HeroNode newNum = new HeroNode(num);
          newNum.next = head.next;// 指向头节点
          head.next = newNum;//
      }
    }


    //出栈
    public int pop(){
        if (isEmpty()){
           new RuntimeException("栈空");
        }
        //拿到当前节点的编号
        int num = head.next.no;
        //头节点为空 每次拿到编号后 往后移动 指向当前节点的下一个
        //4的节点后面3 3的后面为2 拿到就要往后移动
        head.next = head.next.next;
        return num;
    }

    //显示
    public void list(){
        if (isEmpty()){
            System.out.println("栈空");
        }else{
            HeroNode cur = head.next;
            System.out.println("栈顶到栈顶");
            while (cur!= null){
                System.out.println(cur.no);
                cur = cur.next;
            }
        }
    }

}
//单链表
class HeroNode{
    public  int no;//编号
    public  HeroNode next;//指向下一个域

    public HeroNode(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                '}';
    }
}