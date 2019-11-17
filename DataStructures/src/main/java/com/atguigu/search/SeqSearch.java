package com.atguigu.search;

/**
 * 顺序查找法
 */
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {1,9,11,-3,100,23,98};
        System.out.println(seqSearch(arr,100) == -1? "没有找到" : "找到了，下标值是 " + seqSearch(arr,100));
    }

    /**
     *   这里我们实现的线性查找是找到一个满足调价的值就返回。
     *   如果要查找所有的value,那么我们可以定义另外一个方法，返回结果放到一个集合就可以了。
     * @param arr  这个数组是没有顺序的，即 无序数组
     * @param value 要查找的值
     * @return
     */
    public  static int seqSearch(int[] arr ,int value){
        //线性查找就是逐一比对，发现有相同的值，就返回该值的下标。否则返回 -1
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value){
                return i;
            }
        }
        return -1;
    }

}


























