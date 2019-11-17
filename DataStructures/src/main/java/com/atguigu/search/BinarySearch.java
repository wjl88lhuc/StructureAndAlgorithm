package com.atguigu.search;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {12,80,230,356,455,677,677,902,956,988,1024};
        System.out.println(binarySearch(arr,9,0,arr.length - 1));

        System.out.println(binarySearchAll(arr,677,0,arr.length - 1));
    }

    /**
     * 二分查找算法。
     * 二分查找算法的前提是数组必须是有序的
     * @param arr
     * @param value
     * @return
     */
    public static int binarySearch(int[] arr,int value,int left,int right){
        //先确定中间的那个数的下标
        if (left > right) {
            return -1;
        }
        int midIndex = (left + right) / 2 ;
        if (arr[midIndex] == value){
            return midIndex;
        }
        if (value > arr[midIndex]){
            return binarySearch(arr,value,midIndex + 1,right);
        }
        if (value < arr[midIndex]){
            return binarySearch(arr,value,left,midIndex - 1);
        }
        return  -1;
    }

    /**
     * 完成一个思考题：查找所有的value。
     * 思路分析：
     * 1. 在找到mid的索引值时，不要马上返回
     * 2. 向midIndex 索引值的左边扫描，将满足 value 的元素的下标，加入到List中。
     * 3. 向 midIndex索引值的右边扫描，将满足value的元素的下标，加入到 List中
     * 4. 将List返回
     * @param arr
     * @param value
     * @param left
     * @param right
     * @return
     */
    public static List binarySearchAll(int[] arr, int value, int left, int right){
        List<Integer> list = new ArrayList<Integer>();
        //先确定中间的那个数的下标
        if (left > right) {
            list.add(-1);
            return list;
        }
        int midIndex = (left + right) / 2 ;
        if (arr[midIndex] == value){
            int tempLeft = midIndex - 1;
            while (true){
                if (tempLeft < 0 || arr[tempLeft] != value){
                    //退出
                    break;
                }
                //否则就将 tempLeft放入到 list中
                list.add(tempLeft);
                tempLeft--;//左移
            }
            list.add(midIndex);
            //向右边扫描
            int tempRight = midIndex + 1;
            while (true){
                if (tempRight > arr.length - 1 || arr[tempRight] != value){
                    break;
                }
                list.add(tempRight);
                tempRight++;
            }
        }
        if (value > arr[midIndex]){
            return binarySearchAll(arr,value,midIndex + 1,right);
        }
        if (value < arr[midIndex]){
            return binarySearchAll(arr,value,left,midIndex - 1);
        }
        return  list;
    }
}
