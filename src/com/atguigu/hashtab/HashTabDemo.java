package com.atguigu.hashtab;

import java.util.Scanner;

/**
 * Created by ChengQian on 2020/5/26 17:10
 */
public class HashTabDemo {
    public static void main(String[] args) {

        //创建哈希表
        HashTab hashTab = new HashTab(7);

        //写一个简单的菜单
        String key = "";
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("delete: 删除雇员");
            System.out.println("exit: 退出雇员");
            key = input.next();
            switch (key){
                case "add":
                    System.out.println("请输入员工id");
                    int id = input.nextInt();
                    System.out.println("请输入员工姓名");
                    String name = input.next();
                    Emp emp = new Emp(id,name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = input.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "delete":
                    System.out.println("请输入要删除的id");
                    id = input.nextInt();
                    hashTab.delete(id);
                    break;
                case "exit":
                    input.close();
                    System.exit(0);
                default:
                    System.out.println("输入错误");
                    break;
            }
        }
    }
}
//HashTab 管理多条链表
class HashTab{
    //数组里面放着链表
    private EmpLinkList[] empLinkListsArray;
    private  int size ;//多少条链表
    //构造器
    public HashTab(int size){
        this.size = size;
        //初始化empLinkListsArray
        empLinkListsArray = new EmpLinkList[size];
        //初始化每个链表
        for (int i=0; i<size; i++){
            empLinkListsArray[i] =  new EmpLinkList();
        }
    }

    //添加雇员
    public void add(Emp emp){
        //根据员工id 得到该员工应当添加到那条链表
        int empLinkedListNo = hashFun(emp.id);
        // temp加入对应链表
         empLinkListsArray[empLinkedListNo].add(emp);
    }
    //遍历所有链表 遍历hashtab
    public void list(){
        for (int i=0; i<size; i++){
            empLinkListsArray[i].list(i);
        }
    }

    //编写散列函数 使用一个简单取模法
    public int hashFun(int id){
        return id % size;
    }
    //输出id查找雇员
    public void findEmpById(int id){
        //使用散列函数 确定到那条链表查找
        int empLinkedListNo = hashFun(id);
        Emp emp = empLinkListsArray[empLinkedListNo].findEmpById(id);
        if (emp != null){
            System.out.printf("在第%d条链表中 找到雇员id = %d\n",(empLinkedListNo+1),id);
        }else{
            System.out.println("在哈希表中没有找到该雇员~");
        }
    }
    //根据id删除链表
    public void delete(int id){
        int empLinkedListNo = hashFun(id);
        empLinkListsArray[empLinkedListNo].delete(id);
    }
}

//表示一个雇员
class Emp{
    public int id;
    public String name;
    public Emp next;
    public Emp(int id,String name){
        this.id = id;
        this.name = name;
    }
}
//创建EmpLinkedList 表示链表
class EmpLinkList{
    //头指针 执行第一个Emp 因此我们这个链表的head 是直接指向第一个Emp
    private Emp head; //默认为null

    //雇员直接加入到本链表的最后即可
    public void add(Emp emp){
        //如果添加第一个雇员
        if (head == null){
            head = emp;
            return;
        }
        //如果不是第一个雇员 则使用第一个辅助指针 帮助定位到最后
        Emp curEmp = head;
        while(true){
            if (curEmp.next == null){//链表最后
                break;
            }
            curEmp = curEmp.next;//后移
        }
        //退出时 直接将emp加入链表
        curEmp.next = emp;
    }

    //显示链表
    public void list(int no ){
        if (head == null){//链表为空
            System.out.println("第"+(no+1)+" 为空");
            return;
        }
        System.out.println("第"+(no+1)+"链表信息为");
        Emp curEmp = head;//辅助指针
        while (true){
            System.out.printf("=> id=%d name=%s\t",curEmp.id,curEmp.name);
            if (curEmp.next == null) {//说明curEmp 已经是最后节点
                break;
            }
            curEmp = curEmp.next;//后移 遍历
        }
        System.out.println();
    }

    //根据id查询雇员
    //如果查找到 返回Emp 没有找到 返回null
    public Emp findEmpById(int id){
         if (head == null){
             System.out.println("链表为空");
             return null;
         }
         //辅助指针
        Emp curEmp = head;
         while (true){
             if (curEmp.id == id){
                    break;//此时curEmp就指向要查找的雇员
             }
             //退出
             if (curEmp.next == null){//链表没有找到改雇员
                  curEmp = null;
                  break;
             }
             curEmp = curEmp.next;//往后移动
         }
         return curEmp;
    }

    //删除
    public void delete(int no){
          if(head == null){
              System.out.println("要删除的链表为空");
              return;
          }
          Emp curEmp = head;
          boolean flag = false;//用于标记是否找到
          while(true){
              if(head.next==null){//到达链表最后
                    break;
              }
              if(curEmp.next.id == no){
                  flag = true;//说明找到
                  break;
              }
              curEmp = curEmp.next;//往后移动
          }
          if (flag){
              curEmp.next = curEmp.next.next;
              System.out.println("删除成功");
          }else{
              System.out.printf("抱歉没有找到%d",no);
          }

    }


}