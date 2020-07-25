package com.atguigu.queue;

import java.util.Scanner;

public class CircleQueueDemo {
    public static void main(String[] args) {
        CircleQueue cq = new CircleQueue(5);
        char key = ' ';
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show):show all data of the queue");
            System.out.println("a(add):add dato to queue");
            System.out.println("g(get):get data from queue");
            System.out.println("h(head):show head of the queue");
            System.out.println("e(exit):exit");
            key = sc.next().charAt(0);
            switch (key) {
                case 's':
                    cq.showCircleQueue();
                    break;
                case 'a':
                    System.out.println("please input a number");
                    int i = sc.nextInt();
                    cq.addCircleQueue(i);
                    break;
                case 'g':
                    try {
                        int res = cq.getCircleQueue();
                        System.out.printf("the data is %d\n", res);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'h':
                    try {
                        int res = cq.headCircleQueue();
                        System.out.printf("the data is %d\n", res);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'e':
                    sc.close();
                    loop = false;
                    break;
            }
        }
    }
}

class CircleQueue {
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    public CircleQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        front = 0;
        rear = 0;
        arr = new int[maxSize];
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    public void addCircleQueue(int x) {
        if (isFull()) {
            System.out.println("the queue is full");
            return;
        } else {
            arr[rear] = x;
            rear = (rear + 1) % maxSize;
        }
    }

    public int getCircleQueue() {
        if (isEmpty())
            throw new RuntimeException("the queue is empty");
        else {
            front = (front + 1) % maxSize;
            return arr[front - 1];
        }
    }

    public int headCircleQueue() {
        if (isEmpty())
            throw new RuntimeException("the queue is empty");
        else {
            return arr[front];
        }
    }

    public void showCircleQueue() {
        if (isEmpty())
            System.out.println("the queue is empty");
        else {
            for (int i = front, j = rear; i != j; i = (i + 1) % maxSize) {
                System.out.println(arr[i]);
            }
        }
    }
}
