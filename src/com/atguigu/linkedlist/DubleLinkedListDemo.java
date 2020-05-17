package com.atguigu.linkedlist;

/**
 * Created by GuoChengQian on 2020/5/15 10:29
 */
public class DubleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        HeroNode2 herNode = new HeroNode2(1,"宋江","及时雨");
        HeroNode2 herNode2 = new HeroNode2(2,"卢俊义","玉麒麟");
        HeroNode2 herNode3 = new HeroNode2(3,"吴用","智多心星");
        HeroNode2 herNode4 = new HeroNode2(4,"林冲","豹子头");
        //创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
       /* doubleLinkedList.add(herNode);
        doubleLinkedList.add(herNode2);
        doubleLinkedList.add(herNode3);
        doubleLinkedList.add(herNode4);
        //显示
        doubleLinkedList.list();

        //修改
        HeroNode2 newHeroNode = new HeroNode2(4,"公孙胜","入云龙");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后显示列表");
        doubleLinkedList.list();
        //删除
        doubleLinkedList.del(4);
        System.out.println("删除后显示");
        doubleLinkedList.list();*/
       doubleLinkedList.addOrder(herNode);
        doubleLinkedList.addOrder(herNode2);
        doubleLinkedList.addOrder(herNode4);
        doubleLinkedList.addOrder(herNode3);
        doubleLinkedList.list();
    }
}
class DoubleLinkedList {
    //初始化一个头节点，头节点不要动 不存放具体的数据
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHeroNode2() {
        return head;
    }
    //显示双向链表
    //显示链表(遍历)
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能为空 因此我们需要一个辅助变量来遍历
        HeroNode2 temp = head.next;
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

    //添加节点到单项链表
    //当不考虑编号顺序时
    //1.找到当前链表的最后节点
    public void add(HeroNode2 herNode) {
        //辅助变量 应为head节点不能动
        HeroNode2 temp = head;
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
        //形成一个双向链表
        temp.next = herNode; //最后一个节点得next指向新的节点
        herNode.pre = temp; //新的节点的pre 等于 零时变量
    }

    //双向链表添加 按照顺序
    public void addOrder(HeroNode2 node) {
        if (this.head.next == null) { //head的next节点为空的话
            this.head.next = node;//添加新的值到最前端
            node.pre = this.head.next; //新节点的pre 等于 head节点的next
            return;
        }
        //临时变量
        HeroNode2 temp = this.head;
        while (temp.next != null) {
            temp = temp.next;//往下移动
            if (temp.no >= node.no) {//temp的编号 大于新节点的编号
                //temp的pre的next 等于node
                temp.pre.next = node;
                //node的pre 等于 temp的pre
                node.pre = temp.pre;
                //node的
                node.next = temp;
                temp.pre = temp;
                return;
            }
        }
        temp.next = node;
        node.pre = temp;
    }
    //修改节点信息 根据no编号来修改 即编号不能更改
    //根据newHerNode 的 no 来修改
    public void update(HeroNode2 newHernode) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点 根据no编号
        //定义一个辅助变量
        HeroNode2 temp = head.next;
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

    //删除节点
    //1.对于双向链表 我们可以直接找到要删除的这个节点
    //2.找到后自我删除
    public void del(int no) {
        if(head.next == null){
            System.out.println("链表为空，无法删除");
            return;
        }

        HeroNode2 temp = head.next; //辅助变量(指针)
        boolean flag = false; //标志是否找到待删除节点
        while (true) {
            if (temp == null) { //已经到链表最后
                break;
            }
            if (temp.no == no) {
                //找到待删除节点的前一个节点
                flag = true;
                break;
            }
            temp = temp.next;//往后移
        }
        //判断flag
        if (flag) {
            //可以删除
//            temp.next = temp.next.next; 单向链表删除
            temp.pre.next = temp.next;
            if (temp.next != null){ //最后一个节点的话就不弄了 不写的话出现空指针
                temp.next.pre = temp.pre;
            }
        } else {
            //说明没有找到
            System.out.printf("要删除的节点%d 不存在啊", no);
        }
    }
}
    //创建一个双向链表的类
class   HeroNode2{
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;//指向下一个节点
    public HeroNode2 pre; //指向上一个节点 默认为null

    public HeroNode2 getPre() {
        return pre;
    }

    public void setPre(HeroNode2 pre) {
        this.pre = pre;
    }

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

        @Override
        public String toString() {
            return "HeroNode2{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    ", nickname='" + nickname + '\'' +
                    '}';
        }
    }