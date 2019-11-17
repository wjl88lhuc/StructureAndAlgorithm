package com.atguigu.search;

public class InsertValueSearch {
    public static void main(String[] args) {
//        int[] arr = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        int[] arr = new int[100];// 1 - 100的数组
        for (int i = 0; i < arr.length -1; i++) {
            arr[i] = i + 1;
        }
        System.out.println(insertValueSearch(arr,0,arr.length - 1 ,1));

    }

    /**
     *  插值查找算法
     *  当数组 arr 是分布比较的一组数据的时候，那么使用一种插值查找算法比较好。如果分布不均匀的话，那么查找效果不一定比普通的二分查找算法好
     *  插值查找算法是一种改善的二分查找算法。
     *  所以说插值查找算法的前提也是： 数组是有序的一组数据
     * @param arr
     * @param left 左边索引值
     * @param right  右边索引值
     * @param value
     * @return  当返回 -1 的时候就代表没有查找到数据
     */
    public static  int insertValueSearch(int[] arr,int left,int right,int value){
        if (left > right || value < arr[left] || value > arr[right - 1]){
            return  -1 ;
        }
        int midIndex = left + (right - left) * (value - arr[left]) / (arr[right] - arr[left]) ;
        int midValue = arr[midIndex];
        if (value > midValue){
            return  insertValueSearch(arr,midIndex + 1,right,value);
        }
        if (value < midValue){
            return insertValueSearch(arr,left,midIndex - 1,value);
        }
        if (value == midValue){
            return midIndex;
        }
        return -1 ;
    }


}
