package com.atguigu.bnsnr;

public class BinarysearcgNoRecur {
    public static void main(String[] args) {
        int[] arr = {1,3,8,10,11,67,100};
        System.out.println(binarySearch(arr, 100));
    }

    /**
     * 二分查找的非递归实现
     * @param arr 查找的数组（这里讨论的是升序的有序数组）
     * @param target  查找的数据
     * @return  找到返回下标，反之返回 -1
     */
    public static int binarySearch(int arr[],int target){
        int left = 0;
        int  right = arr.length -1 ;
        int minIndex = (left + right) / 2;
        while (left <= right){//继续查找
            if (target == arr[minIndex]){
                return minIndex;
            }
            if (target < arr[minIndex]){
                right = minIndex - 1;
            }
            if (target > minIndex){
                left = minIndex + 1;
            }
            minIndex = (left + right) / 2;
        }
        return  -1 ;
    }

}











































