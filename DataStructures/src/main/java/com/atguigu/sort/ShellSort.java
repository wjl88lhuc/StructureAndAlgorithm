package com.atguigu.sort;

import java.util.Arrays;

/**
 * 希尔排序算法：希尔算法是一种优化的插入排序算法。
 * 效率的比较：
 * 移位希尔算法 > 普通的插入算法 > 互换式的希尔算法
 */
public class ShellSort {

    /**
     * 移位式希尔算法
     * 效率比 互换法的希尔排序算法 要高得多
     * @param arr
     */
    public static void shellSortByShift(int[] arr){
        int temp = 0;
        for (int gap = arr.length / 2; gap > 0 ; gap /= 2) {
            for (int i = gap; i <arr.length ; i++) {
                //遍历数组中的所有元素
                temp =arr[i];
                int j = i;
                if (arr[i] < arr[i - gap]){
                    while (j - gap >= 0 && temp < arr[j - gap]){
                        arr[j] = arr[j -gap];
                        j -= gap;
                    }
                    //当退出这个while循环，就找到 了插入循环的位置，直接放入即可
                    arr[j] = temp;
                }
            }
        }
    }

    //互换法的希尔排序算法
    public static void shellSort1(int[] arr){
        int temp = 0;
        for (int gap = arr.length / 2; gap > 0 ; gap /= 2) {
            for (int i = gap; i <arr.length ; i++) {
                //遍历数组中的所有元素
                for (int j = i - gap; j >= 0 ; j -= gap) {
                    if (arr[j] > arr[j + gap]){
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr ={3,1,80,9,12,8,100,98,68,-1};
        shellSortByShift(arr);
        System.out.println(Arrays.toString(arr));
    }

}




