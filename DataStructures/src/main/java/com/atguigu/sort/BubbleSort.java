package com.atguigu.sort;

import java.util.Arrays;

//冒泡排序
//冒泡排序的时间复杂度是 O(n*n)
public class BubbleSort {
    public static void main(String[] args) {
        int arr[] = {3,9,-1,10,-2};
        System.out.println(Arrays.toString(arr));
        System.out.println("*******************");
        bubleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    //将冒泡排序封装成一个方法
    public static void bubleSort(int[] arr){
        int temp = 0;//临时变量
        boolean flag = false;//标识符，表示是否进行过交换
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]){
                    flag =true;
                    temp = arr[j];
                    arr[j]=arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (flag ==false){
                //说明在一趟排序中一次交换都没有交换过，那么就直接终止排序
            }else{
                flag = false;//重置 flag,进行下次排序
            }
        }
    }

}

















