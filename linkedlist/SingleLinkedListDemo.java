package com.atguigu.linkedlist;

import java.awt.*;
import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(3, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(5, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(8, "林冲", "豹子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();

        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);
//        // 测试一下单链表的反转功能
//        System.out.println("原来链表的情况~~");
        singleLinkedList.list();

        HeroNode hero5 = new HeroNode(7, "的宋江", "及e时雨");
        HeroNode hero6 = new HeroNode(2, "给卢俊义", "给玉麒麟");
        HeroNode hero7 = new HeroNode(9, "的吴用", "和智多星");


        SingleLinkedList singleLinkedList1=new SingleLinkedList();
        singleLinkedList1.addByOrder(hero5);
        singleLinkedList1.addByOrder(hero6);
        singleLinkedList1.addByOrder(hero7);


        singleLinkedList1.list();

        mergeList(singleLinkedList.getHead(),singleLinkedList1.getHead());

        singleLinkedList.list();



//        System.out.println("反转单链表~~");
//        reversetList(singleLinkedList.getHead());
//        singleLinkedList.list();

//        //逆序打印单链表
//        reversePrint(singleLinkedList.getHead());

        //HeroNode newHeroNode = new HeroNode(2, "小路", "小麒麟");
        //singleLinkedList.update(newHeroNode);

        //singleLinkedList.del(2);


        //测试一下 求单链表中有效节点的个数
        //System.out.println("有效的节点个数=" + getLength(singleLinkedList.getHead()));

        //测试一下看看是否得到了倒数第K个节点
        //HeroNode res = findLastIndexNode(singleLinkedList.getHead(), 3);
        //System.out.println("res=" + res);
    }

    /**
     * 获取单链表的长度
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) { //空链表
            return 0;
        }
        int length = 0;
        //定义一个辅助的变量, 这里我们没有统计头节点
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next; //遍历
        }
        return length;
    }

    /**
     * 查找单链表中的倒数第k个结点
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        //判断如果链表为空，返回null
        if (head.next == null) {
            return null;//没有找到
        }
        //第一个遍历得到链表的长度(节点个数)
        int size = getLength(head);
        //第二次遍历  size-index 位置，就是我们倒数的第K个节点
        //先做一个index的校验
        if (index <= 0 || index > size) {
            return null;
        }
        //定义给辅助变量， for 循环定位到倒数的index
        HeroNode cur = head.next; //3 // 3 - 1 = 2
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    /**
     * 将单链表反转
     */
    public static void reversetList(HeroNode head) {
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }

        //定义一个辅助的指针(变量)，帮助我们遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null;// 指向当前节点[cur]的下一个节点
        HeroNode reverseHead = new HeroNode(0, "", "");
        //遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead 的最前端
        while (cur != null) {//注意是cur，而不是cur.next
            next = cur.next;//先暂时保存当前节点的下一个节点，因为后面需要使用
            cur.next = reverseHead.next;//将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur; //将cur 连接到新的链表上
            cur = next;//让cur后移
        }
        //将head.next 指向 reverseHead.next , 实现单链表的反转
        head.next = reverseHead.next;
    }

    /**
     * 逆序打印单链表
     */
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;//空链表，不能打印
        }
        //创建要给一个栈，将各个节点压入栈
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        //将链表的所有节点压入栈
        while (cur != null) {
            stack.push(cur);
            cur = cur.next; //cur后移，这样就可以压入下一个节点
        }
        //将栈中的节点进行打印,pop 出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop()); //stack的特点是先进后出
        }
    }

    /**
     * 合并两个有序的单链表
     */
    public static void mergeList(HeroNode head1,HeroNode head2){
        HeroNode newHead=new HeroNode(0,"","");
        HeroNode curToOne=head1.next;
        HeroNode curToTwo=head2.next;
        HeroNode curToNewHead=newHead;
        while(curToOne!=null&&curToTwo!=null){
            if(curToOne.no>=curToTwo.no){
                curToNewHead.next=curToTwo;
                curToNewHead=curToNewHead.next;
                curToTwo=curToTwo.next;
            }else if(curToOne.no<curToTwo.no){
                curToNewHead.next=curToOne;
                curToNewHead=curToNewHead.next;
                curToOne=curToOne.next;
            }
        }
        while(curToOne!=null){
            curToNewHead.next=curToOne;
            curToOne=curToOne.next;
        }
        while(curToTwo!=null){
            curToNewHead.next=curToTwo;
            curToTwo=curToTwo.next;
        }
        head1.next=newHead.next;
    }
}

/**
 * 定义HeroNode ， 每个HeroNode 对象就是一个节点
 */
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

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

/**
 * 定义SingleLinkedList 管理我们的英雄
 */
class SingleLinkedList {
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    /**
     * 添加节点到单向链表
     */
    public void add(HeroNode heroNode) {
        //temp应该指向head，而不是head.next
        HeroNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        //将最后这个节点的next指向新的节点
        temp.next = heroNode;
        /**
         * 错误的解法
         * 把head.next赋给临时变量temp，这样temp就为null而不是一个对象，无法对null进行操作
         */
//        HeroNode temp = head.next;//如果单链表为空，此时temp=null
//        while (temp != null) {//while循环从一开始就退出了
//            temp = temp.next;
//        }
//        temp = heroNode;//此时的temp为null，无法把一个对象赋值给null
//        错误分析：
//        首先应该将temp指向head，而不是head.next
    }

    /**
     * 按顺序添加单链表
     */
    public void addByOrder(HeroNode heroNode) {
        //因为头节点不能动，因此我们仍然通过一个辅助指针(变量)来帮助找到添加的位置
        //因为单链表，因为我们找的temp 是位于 添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        boolean flag = false; // flag标志添加的编号是否存在，默认为false
        while (true) {
            if (temp.next == null) {//说明temp已经在链表的最后
                break; //
            }
            //遍历节点，如果节点no小于heroNode的no，则继续往后知道找到第一个大于heroNode的no的节点
            if (temp.next.no > heroNode.no) { //位置找到，就在temp的后面插入
                break;
            } else if (temp.next.no == heroNode.no) {//说明希望添加的heroNode的编号已然存在
                flag = true; //说明编号存在
                break;
            }
            temp = temp.next; //!!!一定不要忘，后移，遍历当前链表
        }
        //判断flag 的值
        if (flag) { //不能添加，说明编号存在
            System.out.printf("准备插入的英雄的编号 %d 已经存在了, 不能加入\n", heroNode.no);
        } else {
            //插入到链表中, temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    /**
     * 删除节点
     */
    public void del(int no) {
        HeroNode temp = head;
        boolean flag = false; // 标志是否找到待删除节点的
        while (true) {
            if (temp.next == null) { //已经到链表的最后
                break;
            }
            if (temp.next.no == no) {
                //找到的待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next; //temp后移，遍历
        }
        //判断flag
        if (flag) { //找到
            //可以删除
            temp.next = temp.next.next;//被删除的节点没有被引用，会被垃圾回收机制回收
        } else {
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }
    }

    /**
     * 修改节点的信息
     */
    public void update(HeroNode newHeroNode) {
        //判断是否空
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        //找到需要修改的节点, 根据no编号
        //定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false; //表示是否找到该节点
        while (true) {
            if (temp == null) {
                break; //已经遍历完链表
            }
            if (temp.no == newHeroNode.no) {
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag 判断是否找到要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else { //没有找到
            System.out.printf("没有找到 编号 %d 的节点，不能修改\n", newHeroNode.no);
        }
    }


    /**
     * 显示所有节点的信息
     */
    public void list() {
        HeroNode temp = head;
        if (temp.next == null) {
            System.out.println("the SingleLinkedList is empty");
            return;
        }
        while (temp.next != null) {
            System.out.println(temp.next);
            temp = temp.next;
        }
        return;
    }
}
