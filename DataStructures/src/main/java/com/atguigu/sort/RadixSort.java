package com.atguigu.sort;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {32,10,6,102,987,89,123,432,65,3};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    //基数排序法

    /**
     * 基数排序法就是空间换时间的算法
     * @param arr
     */
    public static void radixSort(int[] arr){
        //定义一个二维数组，表示10个桶，每个桶就是一个一维数组。每一个一维数组的大小都是arr.length
        int[][] bucket = new int[10][arr.length];//10个桶：0，1，2，3，4，5，6，7，8，9

        //为了记录每个桶中实际存放了多少个数据，我们定义一个一维数组来记录各个桶的每次放入的有效数据的个数
        int[] bucketElementCounts = new int[10];//bucketElementCounts[0] 记录的就是 bucket[0] 每次放入的数据的个数，依次类推

        //先得到数组中最大的数的位数
        int maxArrayElement = arr[0];//假设第一个是最大的
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maxArrayElement) {
                maxArrayElement = arr[i];
            }
        }
        int maxArrayElementLength = String.valueOf(maxArrayElement).length();

        for (int i = 0,n = 1; i < maxArrayElementLength; i++,n *= 10) {
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素的第 i 位 的值
                int digitOfElement = arr[j] /n  %  10;
                //放入到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            //按照这个桶的顺序（一维数组的下标依次取出数据，放入原来的数组）
            int index = 0;
            //遍历每一个桶，并将桶中的数据放入到原数组中
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中有数据，我们才会取出该桶中的数据放入到原数组中
                if (bucketElementCounts[k] != 0){
                    //循环第k 个桶(即第 k 个一维数组) 放入
                    for (int l = 0; l < bucketElementCounts[k] ; l++) {
                        //取出元素放入到arr
                        arr[index++] = bucket[k][l];
                    }
                }
                bucketElementCounts[k]=0;
            }
        }
    }

}




















