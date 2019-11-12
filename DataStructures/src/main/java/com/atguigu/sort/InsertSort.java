package com.atguigu.sort;

//插入排序

import java.util.Arrays;

/**
 * 插入式排序属于内部排序法，是对于欲排序的元素以插入的方式找寻该元素的适当位置，以达到排序的目的。
 *
 * 插入排序（Insertion Sorting）的基本思想是：
 * 把n个待排序的元素看成为一个有序表和一个无序表，开始时有序表中只包含一个元素，无序表中包含有n-1个元素，
 * 排序过程中每次从无序表中取出第一个元素，把它的排序码依次与有序表元素的排序码进行比较，将它插入到有序表中的适当位置，
 * 使之成为新的有序表。
 *
 * 插入排序的执行效率：  插入排序 比 选择排序效率稍低，相差无几，但是比 冒泡排序 快多了
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {98,-12,60,35,23,87,56,-20};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
        int[] arr2 = {98,-12,60,35,23,87,56,-20};
        insertSrots(arr2);
        System.out.println("******************************");
        System.out.println(Arrays.toString(arr2));
    }

    //升序排序
    public static void insertSort(int[] arr){
        int temp = arr[0];
        int biggerStartIndex = 0;
        for (int i = 1; i <arr.length ; i++) {
            if (arr[1] >= arr[0] && i == 1){
                continue;
            }
            if (arr[1] < arr[0] && i == 1){
                temp =arr[0];
                arr[0] = arr[1];
                arr[1] =temp;
                continue;
            }
            if (arr[i] >= arr[i - 1]){
                continue;
            }
            for (int j = i -1; j > 0; j--) {
                if (arr[i] < arr[j] && arr[i] >= arr[j - 1]){//第一个条件
                    //例如 {23，98，83}，arr[i] = 83,满足这个条件 arr[i] < 98 && arr[i] >= 23,
                    // 所以就找到了 maxStartIndex的位置所用了
                    biggerStartIndex = j;
                    break;
                }
                if (arr[i] < arr[j] && arr[i] < arr[j - 1]){
                    continue;
                }
            }
            //当除了上面的这个内部第二层循环的时候，如果一直都没有执行过 biggerStartIndex = j; ，
            // 那么就说明arr[i] 比 arr[0] 还小，即 biggerStartIndex = 0；
            temp=arr[i];
            for (int j =i ; j >biggerStartIndex ; j--) {
                arr[j] = arr[j - 1];
            }
            arr[biggerStartIndex] = temp;
            biggerStartIndex = 0; //非常重要，要记得将maxStartIndex 重置为 0
        }
    }

    //与 insertSort 方法的区别 就是在遍历的时候，如果当前元素依次比前面的元素还小，同时把前面的元素往后移
    public static void insertSrots(int[] arr){
        int currentValue = 0;
        int currentFrontIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            currentValue =arr[i];//每一次都把无序数组中的当前元素存下来
            currentFrontIndex =i - 1;
            while (currentFrontIndex >= 0 && currentValue < arr[currentFrontIndex]){
                arr[currentFrontIndex + 1] = arr[currentFrontIndex];
                currentFrontIndex--;
            }
            if (currentFrontIndex + 1 != i){
                arr[currentFrontIndex + 1] = currentValue;
            }
        }
    }

}





























