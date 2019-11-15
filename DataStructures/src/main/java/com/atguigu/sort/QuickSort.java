package com.atguigu.sort;

import java.util.Arrays;

/**
 * 快速排序算法
 */
public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = {0,-3,34,0,-21,-90,0,45,100,-30,0};
//        int[] arr = {-90, -30};
        int[] arr = {30,0, -90,0};
        quickSorts(arr,0,arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static  void quickSorts(int[] arr,int left,int right){
        int lTemp = left;// 左下标
        int rTemp = right;// 右下标
        //pivot 中轴值
        int pivot = arr[(left + right) / 2];

//        System.out.println( "left : "+left+ "\tright : "+right+ "\t lTemp : " + lTemp + "\t rTemp : " + rTemp + "\t" +
//                "(left + right) / 2 : "+ ((left + right) / 2) + "\t arr[(left + right) / 2] :" + arr[(left + right) / 2]);
//        System.out.println(Arrays.toString(arr));
//        System.out.println("*****************************************************************************");

        int temp = 0;//临时变量，作为交换时使用
        //while循环的目的是让比 pivot小的值放在左边，比 pivot 大的值放在右边
        while (lTemp < rTemp){
            //在pivot的左边一直找，找到一个大于等于pivot的值，才退出
            while (arr[lTemp] < pivot){
                lTemp += 1;
            }
            //在pivot的右边边一直找，找到一个小于等于pivot的值，才退出
            while (arr[rTemp] > pivot){
                rTemp -= 1;
            }
            //如果 lTemp >= rTemp ,则说明 pivot的左右两边的值 已经按照左边的值全部都小于pivot的值，右边全部都大于pivot的值
            if (lTemp >= rTemp){
                break;
            }
            //交换
            temp = arr[lTemp];
            arr[lTemp]=arr[rTemp];
            arr[rTemp]=temp;
            //如果交换完成之后，发现 arr[lTemp] = pivot, 那么 rTemp-- ,前移。如果不前移，下一次循环就会死循环了
            if (arr[lTemp] == pivot){
                rTemp -= 1; //如果设置成lTemp += 1;那么int[] arr = {30,0, -90,0};就会出现排序失败的
            }
            //如果交换完成之后，发现arr[rTemp] = pivot, 那么 lTemp++ ,后移。如果不后移，下一次循环就会死循环了
            if (arr[rTemp] == pivot){
                lTemp += 1;
            }
        }
        //如果 1 == rTemp ,必须l++ , rTemp-- ,否则可能出现栈溢出。如果没有这一步，像 int[] arr = {-90, -30};就会出现栈溢出
        if (lTemp == rTemp){
            lTemp += 1;
            rTemp -= 1;
        }

        //向左递归
        if (left < rTemp){
            quickSorts(arr,left,rTemp);
        }
        if (right > lTemp){
            quickSorts(arr,lTemp,right);
        }
    }

    public static void quickSorts2(int[] arr,int left,int right){
        if (left > right){
            return;
        }
        //让第一个数字当基准数
        int lTemp =left;
        int rTemp =right;
        int base = arr[left];
        int vTmp;
        while (lTemp < rTemp){
            //从右往左找
            while (lTemp < rTemp && arr[rTemp] >= base){
                rTemp--;
            }
            while (lTemp < rTemp &&  arr[lTemp] <= base){
                lTemp++;
            }

            if (lTemp < rTemp){
                vTmp = arr[lTemp];
                arr[lTemp]=arr[rTemp];
                arr[rTemp] = vTmp;
            }
        }
        //最后将基准为与i和j相等位置的数字交换
        arr[left] = arr[lTemp];
        arr[lTemp] = base;

        //递归调用左半数组
        quickSorts2(arr,left,rTemp-1);

        //递归调用右半数组
        quickSorts2(arr,rTemp + 1,right);
    }

}





























