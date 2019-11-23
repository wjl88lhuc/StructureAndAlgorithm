package com.atguigu.dac;

public class Hanoitower {
    private static int times =0;
    public static void main(String[] args) {
        hanoitower(1000,'A','B','C');
        System.out.println("总共进行的次数： " + times);
    }

    /**
     * 哈诺塔的移动的方法，使用分治算法
     * @param num
     * @param a
     * @param b
     * @param c
     */
    public static void hanoitower(int num,char a,char b,char c){
        if (num <= 0){
            return;
        }
        //如果只有一个盘
        if (num == 1){
            System.out.println("第 1 个盘从 "  + a  + "--->" + c);
            times++;
        }else{
            //如果我们有 n >= 2 情况，我们总是可以看作是两个盘 1 ，最下边的一个盘2 ，上面的所有盘看作是一个盘
            // 1 . 先吧最上面的所有盘 A ---> B,移动过程使用到 c
            hanoitower(num - 1 , a,c,b);
            //2 .把最下边的盘 A ---> C
            System.out.println("第 " + num + " 个盘从 " + a + "--->" + c);
            times++;
            //3. 把B塔所有的盘 B --->,移动过程使用到 a 塔
            hanoitower(num - 1,b,a,c);
        }
    }
}


















