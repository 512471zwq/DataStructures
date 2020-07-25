package com.atguigu.linkedlist;

import java.util.Stack;

//演示栈的基本使用
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();

        //入栈
        stack.add("Smith");
        stack.add("Tom");
        stack.add("Jack");
        stack.add("Bill");

        //出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }
}
