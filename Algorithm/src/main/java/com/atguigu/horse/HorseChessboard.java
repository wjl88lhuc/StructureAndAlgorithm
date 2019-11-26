package com.atguigu.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

//骑士周游
public class HorseChessboard {
    public static void main(String[] args) {
        //测试 骑士周游算法是否正确
        X = 8;
        Y = 8;
        int row = 1;//马儿初始位置的行， 从 1 开始编号
        int column = 1 ;//马儿的初始位置的列，从1 开始编号
        //创建棋盘
        int[][] chessboard = new int[X][Y];

        visited = new boolean[X * Y];//初始值都是false
        //测试一下耗时
        long start = System.currentTimeMillis();
        traversalChessboard(chessboard,row - 1,column -1,1);
        long end = System.currentTimeMillis();
        System.out.println("共耗时长 ： " + (end - start) / 1000);
        //输出棋盘最后的情况
        for (int[] rows : chessboard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }

    private static int X;//棋盘的列数
    private static int Y;//棋盘的行数
    //创建一个数组，标记棋盘的各个位置是否被访问过
    private static boolean visited[];
    //使用一个属性变量标记整个棋盘的所有位置是否都被访问过了
    public static boolean finished;//true 表示成功

    /**
     * 功能： 完成骑士周游问题的算法
     * @param chessboard  棋盘
     * @param row 马儿当前的位置的行  从 0 开始
     * @param column  马儿的当前位置的列 从0  开始
     * @param step  表示第几步，初始位置就是第 1 步
     */
    public static void traversalChessboard(int[][] chessboard,int row,int column,int step){
        chessboard[row][column] = step;
        visited[row * X + column] = true;//标记该位置已经被访问过
        //获取当前位置可以走的下一步
        ArrayList<Point> ps = next(new Point(column, row));
        //对ps 进行排序，排序的规则就是对ps 的所有Point对象的下一步的位置的数目，进行非递减排序
        sort(ps);
        while (!ps.isEmpty()){
            Point p = ps.remove(0);//取出下一个可以走的位置
            //判断该段是否已经访问过
            if (!visited[p.y * X + p.x]){//进入if里面说明还没有被访问过
                traversalChessboard(chessboard,p.y,p.x,step + 1);
            }
        }

        //判断马儿是否完成了任务， 使用 step 和应该走的步数进行比较，如果没有达到数量，则表示没有完成任务，将整个棋盘置为 0
        //说明：step < X * Y  成立的两种情况
        //1. 棋盘到目前位置，仍然没有走完
        //2. 棋盘处于一个回溯的过程
        if(step < X * Y && !finished){
            chessboard[row][column] = 0;
            visited[row * X + column] =false;
        }else{
            finished = true;
        }

    }

    //根据当前这一步的所有下一步的选择位置，进行非递减排序,减少回溯的次数。贪心算法的思想进行优化。下一步最少的先进行回溯
    public static void sort(ArrayList<Point> ps){
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int count = next(o1).size() - next(o2).size();
                if (count ==0){
                    return 0;
                }
                if (count > 0){
                    return 1;
                }
                return -1;
            }
        });
    }


    /**
     * 功能：根据当前的位置，计算马尔还能走哪些位置，并放入到一个集合中
     * @param curPoint
     * @return
     */
    public static ArrayList<Point> next(Point curPoint){
        //创建一个ArrayList<Point>
        ArrayList<Point> ps = new ArrayList<>();
        //创建一个Point
        Point p1 = new Point();
        //判断
        if ((p1.x = curPoint.x - 2) >= 0 && ((p1.y = curPoint.y - 1) >= 0)){
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 1) >= 0 && ((p1.y = curPoint.y - 2) >= 0)){
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 1) < X  && ((p1.y = curPoint.y - 2) >= 0)){
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 2) < X && ((p1.y = curPoint.y - 1) >= 0)){
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 2) < X && ((p1.y = curPoint.y + 1) <Y)){
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 1) < X && ((p1.y = curPoint.y + 2) <Y)){
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 1) >= 0 && ((p1.y = curPoint.y + 2) <Y)){
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 2) >= 0 && ((p1.y = curPoint.y + 1) <Y)){
            ps.add(new Point(p1));
        }
        return ps;
    }



}






































































