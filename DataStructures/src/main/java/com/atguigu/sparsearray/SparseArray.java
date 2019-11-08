package com.atguigu.sparsearray;

/**
 * 稀疏数组
 */
public class SparseArray {
    public static void main(String[] args) {
        //创建一个原始的二维数组 11 * 11
        // 0 表示没有棋子，1 表示 黑子  ，2 表示 蓝子
        int chessArra1[][] =new int[11][11];
        chessArra1[1][2] = 1;
        chessArra1[2][3] = 2;
        //输出原始的二位数组
        System.out.println("------原始的二维数组------");
        for (int[] row : chessArra1) {
            for (int data : row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        //将二位数组 转换为 稀疏数组
        //1. 先遍历二维数组得到非0 数据的个数
        int sum =0;  //sum 记录原始数组的非零 元素个数
        System.out.println(chessArra1.length);
        for (int[] row : chessArra1) {
            for (int data : row) {
                if (data != 0){
                    sum += 1;
                }
            }
        }
        System.out.println(sum);

        //2. 创建对应的稀疏数组
        int sparseArray[][]=new int[sum + 1][3];

        //3. 给系数数组赋值
        //3.1 第一行记录原始数组共有多少行，多少列，多少个非0 元素
        sparseArray[0][0] = chessArra1.length;
        sparseArray[0][1]= chessArra1.length; // 如果是正方形就是一样，如果不是正方形不是一样的
        sparseArray[0][2] = sum;

        //3.2 遍历二位数组，将非0 的值存放到稀疏数组中 即可。
        int count =0 ;// 用于记录
        for (int i = 0; i < chessArra1.length ; i++) {
            for (int j = 0; j < chessArra1[i].length ; j++) {
                if (chessArra1[i][j] != 0){
                    count++;
                    sparseArray[count][0]=i;
                    sparseArray[count][1]=j;
                    sparseArray[count][2]= chessArra1[i][j];
                }
            }
        }

        //稀疏数组的输出
        System.out.println("得到的稀疏数组为。。。。。。。。。。。。");
        for (int[] row : sparseArray) {
            for (int data : row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        //将稀疏数组还原为原始数组: 稀疏数组的第一行第一列就是原始数组的行数，第一行的第二列就是原始数组的列数
        int chessArra2[][] = new int[sparseArray[0][0]][sparseArray[0][1]];

        //遍历稀疏数组;从第二行开始遍历
        for (int i = 1; i <sparseArray.length ; i++) {
            chessArra2[sparseArray[i][0]][sparseArray[i][1]] =sparseArray[i][2];
        }

        System.out.println("还原之后得到的原始数组");
        for (int[] row : chessArra2) {
            for (int data : row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }



    }
}










































