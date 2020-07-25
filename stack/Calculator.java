package com.atguigu.stack;

public class Calculator {
    public static void main(String[] args) {
        String expression = "5-40*2-30";
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        int index = 0;
        char ch = ' ';
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        String keepNum = "";
        while (true) {
            ch = expression.charAt(index);
            //如果是操作符
            if (operStack.isOper(ch)) {
                //如果符号栈为空，直接将符号入栈
                if (operStack.isEmpty()) {
                    operStack.push(ch);
                } else {//如果符号栈不为空，则比较优先级
                    //如果当前符号优先级小于等于栈顶的符号，则弹出两个数进行运算
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = operStack.cal(num1, num2, oper);
                        numStack.push(res);//
                        //符号也要入栈！！！
                        operStack.push(ch);
                    } else {//如果当前符号优先级大于栈顶的符号，则符号直接入栈
                        operStack.push(ch);
                    }
                }
            } else {//如果是数字直接入栈
                keepNum += ch;
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    //判断如果数字是否有多位
//                    if (!operStack.isOper(expression.charAt(index + 1))) {
//                        keepNum += ch;
//                    } else {
//                        numStack.push(Integer.parseInt(keepNum));
//                        keepNum="";
//                    }
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        //如果后一位是运算符，则入栈 keepNum = "1" 或者 "123"
                        numStack.push(Integer.parseInt(keepNum));
                        //重要的!!!!!!, keepNum清空
                        keepNum = "";

                    }
                }
            }
            index++;
            //遍历结束出口
            if (index >= expression.length()) {
                break;
            }
        }
        while (true) {
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            //此处代码有误t
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        int res2 = numStack.pop();
        System.out.println("结果是：" + res2);
    }
}



//先创建一个栈,直接使用前面创建好
//定义一个 ArrayStack2 表示栈, 需要扩展功能
class ArrayStack2 {
    private int maxSize; // 栈的大小
    private int[] stack; // 数组，数组模拟栈，数据就放在该数组
    private int top = -1;// top表示栈顶，初始化为-1

    //构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //增加一个方法，可以返回当前栈顶的值, 但是不是真正的pop
    public int peek() {
        return stack[top];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }
    //栈空
    public boolean isEmpty() {
        return top == -1;
    }
    //入栈-push
    public void push(int value) {
        //先判断栈是否满
        if(isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }
    //出栈-pop, 将栈顶的数据返回
    public int pop() {
        //先判断栈是否空
        if(isEmpty()) {
            //抛出异常
            throw new RuntimeException("栈空，没有数据~");
        }
        int value = stack[top];
        top--;
        return value;
    }
    //显示栈的情况[遍历栈]， 遍历时，需要从栈顶开始显示数据
    public void list() {
        if(isEmpty()) {
            System.out.println("栈空，没有数据~~");
            return;
        }
        //需要从栈顶开始显示数据
        for(int i = top; i >= 0 ; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }
    //返回运算符的优先级，优先级是程序员来确定, 优先级使用数字表示
    //数字越大，则优先级就越高.
    public int priority(int oper) {
        if(oper == '*' || oper == '/'){
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1; // 假定目前的表达式只有 +, - , * , /
        }
    }
    //判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }
    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0; // res 用于存放计算的结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;// 注意顺序
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }

}