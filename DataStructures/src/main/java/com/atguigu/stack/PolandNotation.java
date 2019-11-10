package com.atguigu.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰表达式(后缀表达式)（逆波兰计算器）
 */
public class PolandNotation {
    public static void main(String[] args) {
//        char sb = '0';
//        System.out.println(sb + 1);
//        System.out.println(toInfixExpressionList("1+((2+3)*4)-5" ));
        String expression = "1+((2+3)*4)-5";
        //中缀表达式对应的List
        List<String> infixExpressionList = toInfixExpressionList("1+((2+3)*4)-5");
        System.out.println("中缀表达式对应的List : " + infixExpressionList);
        List<String> suffixExpressionTrasferList = middleToSuffixExpressionTrasfer(infixExpressionList);
        System.out.println("后缀表达式对应的List: " + suffixExpressionTrasferList);
        System.out.println(expression + " = " + calculate(suffixExpressionTrasferList));

        //先定义逆波兰表达式
        // (3+4)x5-6 => 3 4 + 5 x 6 -
        String suffixExpression ="3      4 + 5 * 6 -";
        /*
        思路：
            1. 先将 "3 4 + 5 x 6 -" => 放到ArrayList中
            2. 将ArrayList传递给一个方法，遍历ArrayList配合栈完成计算
         */
        List<String> rpnList = getListString(suffixExpression);
//        System.out.println(rpnList);
//        System.out.println(rpnList.size());
        System.out.println("运算结果： " + calculate(rpnList));
    }

    //将一个逆序波兰表达式，依次将数据和运算符放到ArrayList中
    public static List<String> getListString(String suffixExpression){
        //将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String element : split) {
            if( element.length() != 0){//过滤掉空值
                list.add(element.trim());
            }
        }
        return list;
    }

    //完成对逆波兰表达式的运算
    public static int calculate(List<String> list){

        //创建栈，只需要一个栈即可
        Stack<String> stack = new Stack<>();
        //遍历
        for (String item : list) {
            //使用正则表达式来验证数字并取出
            if(item.matches("\\d+")){//匹配的多位数
                //入栈
                stack.push(item);
            }else{
                //pop出两个数，并运算，再将结果入栈
                int num2 = Integer.parseInt(stack.pop());//先出栈的当作第二个数字
                int num1 = Integer.parseInt(stack.pop());//先出栈的当作第一个数字
                int res = 0;
                switch(item){
                    case "+":
                        res = num1 + num2;
                        break;
                    case "*":
                        res=num1 * num2;
                        break;
                    case "/":
                        res= num1 / num2;
                        break;
                    case "-":
                        res=num1 -num2;
                        break;
                    default:
                        throw new RuntimeException("输入运算符号有误");

                }
                //运算完毕，把运算结果入栈操作
                stack.push(String.valueOf(res));
            }
        }
        //最后留在数栈中的数据就是整个表达式总的运算结果
        return Integer.parseInt(stack.pop());
    }

    /**
     * 完成中缀表达式对应的List转换成后缀表达式的功能
     * 说明：
     *1.  例如：1+((2+3)x4)-5 => 1 2 3 + 4 x + 5 -
     * 2. 因为直接对str进行操作不方便，因此先将"1+((2+3)*4)-5" =>中缀的表达式对应的List
     *      即 1+((2+3)*4)-5" =》 ArrayList [1,+,(,(,2,+,3,),),*,4,),-,5]
     * @param middleExpressionList :中缀表达式List
     */
    public static List<String> middleToSuffixExpressionTrasfer(List<String> middleExpressionList){
        //初始化两个栈
        Stack<String> s1 = new Stack<>();//符号栈 s1
        //因为s2 这个栈在整个转换过程中，没有pop出栈操作，而且我们后面还需要逆序输出
        //因此比较麻烦，所以我们这里就不使用Stack<String>()，而直接使用List<String>s2
//        Stack<String> s2 = new Stack<>();//存储中间结果的栈 s2
        List<String> s2 = new ArrayList<String>();//存储中间结果的lists2

        //遍历middleExpressionList
        for (String item : middleExpressionList) {
            //如果是一个数加入s2
            if (item.matches("\\d+")){
                s2.add(item);
            }else if (item.equals("(")){
                s1.push(item);
            }else if (item.equals(")")){
                //如果是右括号")",则依次弹出s1栈顶的运算符，并压入s2中，直到遇到左括号位置，此时将这一对括号丢弃
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();//将s1中的“（”左括号弹出，消除小括号
            }else{
                //当item的优先级小于等于 s1 栈顶的优先级,将s1栈顶的运算符弹出并加入到s2中
                while (s1.size() != 0 && Operation.getPriorityValue(s1.peek()) >= Operation.getPriorityValue(item)){
                    s2.add(s1.pop());
                }
                //不要忘记了，最后还需要将item压入栈中
                s1.push(item);
            }
        }
        //将s1中剩余的运算符依次弹出并加入s2
        while (s1.size() != 0){
            s2.add(s1.pop());
        }
        return s2;//注意：因为是存放List,依次按顺序输出就是对应的后缀表达式对应的List
    }

    /**
     * 将中缀表达式转换成对应的List
     * 例如：即 "1+((2+3)*4)-5" =》 ArrayList [1,+,(,(,2,+,3,),),*,4,),-,5]
     * @param middleExpression: 中缀表达式
     * @return
     */
    public static List<String> toInfixExpressionList(String middleExpression){
        //顶一个一个List,存放中缀表达式对应的内容
        List<String> ls = new ArrayList<>();
        int index = 0;//这是一个指针，用于遍历 中缀表达式字符串
        String str;//用来做对多位数的拼接
        char c;//每一遍历到一个字符，就放入到c中
        do{
            //如果c是一个非数字，我们就需要加入到ls中
            if((c=middleExpression.charAt(index)) < 48 || (c=middleExpression.charAt(index)) > 57 ){//char类的'0'的十进制是 48,'9'的十进制 是 57
                ls.add(String.valueOf(c));
                index++;//index需要后移
            }else{
                //如果是一个数字，则需要考虑多位数
                str = "";//先将str 置换成""
                while (index < middleExpression.length() && (c=middleExpression.charAt(index)) >=48 && (c=middleExpression.charAt(index)) <= 57){
                    str += c;
                    index++;
                }
                ls.add(String.valueOf(str));
            }
        }while (index < middleExpression.length());
        return ls;
    }




}

//编写一个类Operation 可以返回运算符对应的优先级
class  Operation{
    // 加法与减法的优先级都是 1
    private static int ADD = 1;
    private static int SUB = 1;
    //乘法与除法的优先级都是2
    private static int MUL = 2;
    private static int DIV = 2;

    //编写一个方法，返回对应优先级数字
    public static int getPriorityValue(String operation){
        int priorityValue =0;
        switch (operation){
            case "+":
                priorityValue = ADD;
                break;
            case "-":
                priorityValue = SUB;
                break;
            case "*":
                priorityValue = MUL;
                break;
            case "/":
                priorityValue = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return priorityValue;
    }
}



























