package com.atguigu.sort;

import java.util.Arrays;

//归并排序算法
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8,4,5,7,1,3,6,2};
        int[] temp =new int[arr.length];
        mergeSort(arr,0,arr.length -1,temp);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 分 + 合 方法
     * @param arr
     * @param left
     * @param right
     * @param temp
     */
    public static void mergeSort(int[] arr,int left,int right,int[] temp){
        if (left < right){
            int mid = (left + right) / 2; // 中间索引
            //向左递归进行分解
            mergeSort(arr,left,mid,temp);

            //向右递归进行分解
            mergeSort(arr,mid + 1,right,temp);

            merge(arr,left,mid,right,temp);
        }
    }

    /**
     *合并方法
     * @param arr 排序的原始数组
     * @param left 左边的有序序列的初始索引
     * @param mid 中间索引
     * @param right 右边的索引
     * @param temp 做中转的数组
     */
    public static void merge(int[] arr,int left,int mid,int right,int[] temp){
        int iTmp = left; // 初始化iTmp,左边有序序列的初始化索引
        int jTmp = mid + 1;//初始化jTmp，右边有序序列的初始化索引
        int t = 0;// 指向temp数组的当前索引

        //（1）先把左右两边(有序)的数据按照贵族填充到temp数组，直到左右两边的有序序列有一边处理完毕为止
        while (iTmp <=mid && jTmp <= right){//继续
            //如果左边的有序序列的当前元素，小于等于右边有序序列的当前元素,即将左边的当前元素，拷贝到temp数组，然后t++,iTmp++
            if (arr[iTmp] <= arr[jTmp]){
                temp[t] = arr[iTmp];
                t++;
                iTmp++;
            }else {//反之，将右边有序序列的当前元素填充到temp数组中
                temp[t] = arr[jTmp];
                t++;
                jTmp++;
            }
        }

        //（2）把剩余数据的一边的数据一次全部填充到temp
        while (iTmp <= mid){//说明左边的有序序列还有剩余元素，那么就把剩余的元素填充到temp
            temp[t] = arr[iTmp];
            t++;
            iTmp++;
        }
        while (jTmp <= right){//说明右边的有序序列还有剩余元素，那么就把剩余的元素填充到temp
            temp[t] = arr[jTmp];
            t++;
            jTmp++;
        }
        //（3）将temp数组的元素拷贝到arr中，但是并不是每一次都拷贝所有
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right){
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }

    }

}






















