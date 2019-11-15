package com.atguigu.sort;

import org.junit.Test;

import java.util.Arrays;

public class AlgorithemTest {
    @Test
    public void testQuickSort(){
        int[] arr = {30,0, -90,0};
        int[] arr2 = {-90, -30};
        int[] arr3 = {0,-3,34,0,-21,-90,0,45,100,-30,0};
        QuickSort.quickSorts2(arr,0,arr.length - 1);
        QuickSort.quickSorts2(arr2,0,arr2.length -1);
        QuickSort.quickSorts2(arr3,0,arr3.length - 1);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr2));
        System.out.println(Arrays.toString(arr3));
    }

    public static void main(String[] args) {
        int[] arr3 = {0,-3,34,0,-21,-90,0,45,100,-30,0};
        QuickSort.quickSorts2(arr3,0,arr3.length - 1);
        System.out.println(Arrays.toString(arr3));
    }
}
