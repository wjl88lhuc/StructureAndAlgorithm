package com.atguigu.stack;

public class CalculatorDemo {
    public static void main(String[] args) {
        String expression = "300+20*6-4";//定义表达式
        //创建两个栈，数栈，一个符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义需要的相关变量
        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';//将每次扫描得到的char保存到ch
        String keepNum = "";//用于拼接多位数
        //开始是while循环的扫描expression
        while (true) {
            //将依次得到expression的每一个字符
            ch = expression.substring(index,index+1).charAt(0);
            //判断ch是什么，根据判断进行不同的操作
            if (operStack.isOper(ch)) {//如果是运算符
                //判断当前符号栈是否为空
                if (!operStack.isEmpty()) {
                    //如果符号栈不为空，就进行比较，如果当前的操作符的优先级小于或者等于栈中的操作符，就需要从栈中pop出两个数字，
                    //再从符号栈中pop出一个符号，进行运算，将得到结果，入数栈，然后将当前的操作符入符号栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //把运算结果入数栈
                        numStack.push(res);
                        operStack.push(ch);
                    } else {
                        //如果当前的操作符的优先级大于栈中的操作符，就直接入符号栈
                        operStack.push(ch);
                    }
                } else {
                    //如果为空，则直接入栈
                    operStack.push(ch);
                }
            } else {//如果是数栈，则直接入数栈
                /**
                 * 分析思路：
                 * 1. 当处理多位数时，不能发现是一个数就立即入栈，因为他可能所示多位数
                 * 2. 在处理数，需要向expression的表达式的index后再看一位，如果是数就进行扫描，如果是符号才能入栈
                 * 3. 因此我们需要定义一个字符变量,用于拼接
                 */
                //处理多位数数字
                keepNum += ch;
                //判断下一个字符是不是数字，如果是数字，，就继续扫描，如果不是不是运算符就入栈
                if (index != expression.length() - 1) {//如果不是最一位。如果不做这个判断的话，当到最后一位的时候，会报错数组越界的错误
                    for (int i = index; i < expression.length(); i++) {
                        ch = expression.substring(i + 1, i + 2).charAt(0);
                        if (operStack.isOper(ch)) {
                            break;
                        }
                        index++;
                        keepNum += ch;
                    }
                }
//                numStack.push(ch - 48);//时间上当我们得到字符 1 的时候，ASII码表中的十进制 是 49，相差48
                System.out.println("ch :" + ch + "\t keepNum : " + keepNum);
                numStack.push(Integer.parseInt(keepNum));
                keepNum = "";//非常重要，记得要清空keppNum
            }
            //让index + 1 ,并判断是否扫描到expression的最后
            index++;
            if (index >= expression.length()) {
                break;//退出循环
            }
        }

        //当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数字和符号，并允许
        while (true) {
            //如果符号栈为空的话，则计算结束了，也就是计算到最后的结果，这时数栈中只有一个数字了
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);//入栈
        }
        //将数栈的最后数，pop出栈，就是结果
        System.out.printf("表达式  %s = %d", expression, numStack.pop());
    }
}

//定义一个ArrayStack2表示栈
class ArrayStack2 {
    private int maxSize;//栈的大小
    private int[] stack;//数组模拟栈，数据就存放在该数组中
    private int top = -1;//top表示栈顶，初始化为 -1

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈操作
    public void push(int value) {
        if (isFull()) {
            return;//栈满了，退出，入栈失败
        }
        stack[++top] = value;
    }

    //出栈操作
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空,没有数据");
        }
        return stack[top--];
    }

    //遍历栈
    public void list() {
        if (isEmpty()) {//栈是空的，就不用遍历了
            System.out.println("------栈是空的，没有数据------");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d\t", i, stack[i]);
        }
    }


    //返回运算符的优先级，优先级是程序员来确定的,优先级使用数字来表示，数字越大，优先级就越高
    public int priority(int oper) {//假定目前的表达式只有 + , - ,*, /
        if (oper == '*' || oper == '/') {
            return 1;
        }
        if (oper == '+' || oper == '-') {
            return 0;
        }
        return -1;
    }

    //判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';//假定目前表达式只有加减乘除四种运算符
    }

    //查看栈顶的值，但是不是真正的pop
    public int peek() {
        return stack[top];
    }


    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;//注意顺序
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;//注意顺序
                break;
            default:
                break;
        }
        return res;
    }


}





















