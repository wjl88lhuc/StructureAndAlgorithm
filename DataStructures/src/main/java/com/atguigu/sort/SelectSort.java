package com.atguigu.sort;

import java.util.Arrays;

/**
 * 选择排序
 * 选择式排序也属于内部排序法，是从欲排序的数据中，按指定的规则选出某一元素，再依规定交换位置后达到排序的目的。
 *
 * 选择排序思想:
 *
 * 选择排序（select sorting）也是一种简单的排序方法。
 * 它的基本思想是：第一次从arr[0]~arr[n-1]中选取最小值，与arr[0]交换，第二次从arr[1]~arr[n-1]中选取最小值，与arr[1]交换，
 * 第三次从arr[2]~arr[n-1]中选取最小值，与arr[2]交换，…，
 * 第i次从arr[i-1]~arr[n-1]中选取最小值，与arr[i-1]交换，…,
 * 第n-1次从arr[n-2]~arr[n-1]中选取最小值，与arr[n-2]交换，总共通过n-1次，得到一个按排序码从小到大排列的有序序列。
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr ={3,1,80,9,12,8,100,98,68};
        System.out.println(Arrays.toString(arr));
        System.out.println("*******************************************");
        selectSorts(arr);
        System.out.println(Arrays.toString(arr));
    }

    //选择排序
    public static void selectSorts(int[] arr){
        int minIndex = 0;//定义最小值的下标
        int minArrValue =0;//定义最小值
        for (int i = 0; i <arr.length - 1 ; i++) {
            //每一次从 数组的 第 i 项开始循环就假设 arr[i]是最小值
            minIndex = i;
            minArrValue =arr[i];
            for (int j = i; j <arr.length - 1 ; j++) {
                if(arr[j + 1] < minArrValue ){
                    minArrValue = arr[j + 1];
                    minIndex = j + 1;
                }
            }
            if (minIndex != i){
                arr[minIndex] = arr[i];
                arr[i] = minArrValue;
            }
        }
    }
}













