package com.atguigu.stack;

import java.util.LinkedList;

public class LinkedListStackDemo {
    public static void main(String[] args) {

        Node node1=new Node(5);
        Node node2=new Node(4);
        Node node3=new Node(2);
        Node node4=new Node(15);

        LinkedListStack linkedListStack = new LinkedListStack();

        linkedListStack.push(node1);
        linkedListStack.push(node2);
        linkedListStack.push(node3);
        linkedListStack.push(node4);
        linkedListStack.list();

        linkedListStack.pop();
        linkedListStack.pop();
//        linkedListStack.pop();


        linkedListStack.list();


    }
}

class LinkedListStack {
    private Node top = null;

    public boolean isEmpty() {
        return top == null;
    }

    public void push(Node newNode) {
        newNode.setNext(top);
        top = newNode;
    }

    public void pop() {
        if (isEmpty()) {
            System.out.println("栈为空，无法弹出");
            return;//if判断里面一定要加return，不然还会继续执行下面的代码
        }
        Node popNode = top;
        top = top.getNext();
        System.out.println("弹出的节点值为："+popNode.getNum());
    }

    //list函数显示所有的栈节点，注意，不能改变top，故应该设置一个临时指针
    public void list() {
        if (isEmpty()) {
            System.out.println("栈为空");
            return;
        }
        Node curNode=top;
        while (true) {
            if(curNode==null){
                break;
            }
            System.out.println(curNode.getNum());
            curNode=curNode.getNext();
        }
    }
}

class Node {
    private int num;
    private Node next;

    public Node(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}