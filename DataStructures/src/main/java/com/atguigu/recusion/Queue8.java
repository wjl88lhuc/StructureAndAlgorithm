package com.atguigu.recusion;

//八皇后问题
public class Queue8 {

    public static void main(String[] args) {
        //测试一把， 8 皇后是否正确
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.println("总共有 " + queue8.sum + " 种方法");
        System.out.println("共有 " + queue8.conflictCount + " 次判断是否冲突");
    }

    //定义一个max表示共有多少个皇后
    int max = 8;
    //定义数组array,保存皇后放置位置的结果，比如 arr = {0,4,7,5,2,6,1,3}
    int[] array = new int[max];

    int sum = 0;//保存放置方法的总个数
    int conflictCount = 0 ;//保存 是否冲突的判断次数


    //编写一个方法，可以将皇后摆放的位置输出
    private void print(){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "\t");
        }
        System.out.println();
    }

    /**
     * 编写一个方法，当防止第 n  个 皇后的时候判断当前将要放置的这个皇后是否与已经摆放 的 皇后们 冲突。
     * 特别注意： check 是 每一次递归时，进入到check 中都有for(int i =0; i < max ; i++),因此 会回溯
     * @param n 表示第 n 皇后
     * @return 返回true的时候，表示新摆放的皇后与已经摆放的皇后不在同一列，也不在同一斜线上
     */
    private boolean judgeConflict(int n ){
        conflictCount++;
        for (int i = 0; i <n ; i++) {
            //array[i] == array[n] 表示在同一列
            //Math.abs(array[n] - array[i]) 表示在同一斜线上
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])){
                return false;
            }
        }
        return true;
    }

    //编写一个方法，放置第 n 个皇后
    private void check(int n){
        if (n == max){//n = 8 ，其实 8 个皇后就已然放好
            print();
            sum++;
            System.out.println("*********************************");
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max ; i++) {
            //先把当前这个皇后 n ,放到改行的第 1 列
            array[n] =i;
            //判断当放置第 n 个皇后到第 i 列，是否冲突
            if (judgeConflict(n)){// true 表示不冲突。如果不冲突，那么就接着放置第 n + 1 个皇后
                check(n + 1);
            }
            //如果冲突，就继续执行array[n] = i ; 即将第 n 个皇后，放置在本行的后移的一个位置( 因为 i++ )
        }
    }

}


















