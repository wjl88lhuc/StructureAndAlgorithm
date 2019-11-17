package com.atguigu.search;

import java.util.Arrays;

/**
 * 斐波那契查找算法
 */
public class FibolaqiSearch {
    public static void main(String[] args) {
        int[] arr = {1,8,10,89,100,1234};
        System.out.println(fiSearch(arr,101,20));
    }

    /**
     * 因为后面需要mid = low + F(k - 1) - 1,需要使用到斐波那契数列，因此我们需要先获取到一个斐波那契数列。
     * 这里我们使用非递归的方法得到一个斐波那契数列
     * @return
     */
    public static int[] fib(int size){
        int[] fi = new int[size];
        fi[0] = 1;
        fi[1] = 1;
        for (int i = 2; i < size; i++) {
            fi[i] = fi[i - 1 ] + fi[i - 2];
        }
        return fi;
    }

    /**
     *编写斐波那契查找算法。
     * 这里使用非递归的方式编写算法
     * @param arr 数组
     * @param key  我们要查找的关键字
     * @return 返回对应的下标，如果没有查找到，那么就返回 -1
     */
    public static int fiSearch(int[] arr,int key,int fiSize){
        int low = 0;
        int high = arr.length - 1;
        int k = 0;//表示斐波那契分割数值的下标
        int mid = 0;//存放我们的mid值
        int f[] = fib(fiSize);//获取斐波那契数列
        //获取斐波那契数列分割数值的下标
        while (high > f[k] - 1){
            k++;
        }
        //因为f[k] 这个值可能大于 数组的长度，因此我们需要使用Arrays类来构造一个新的数组并指向arr
        int[] temp = Arrays.copyOf(arr, f[k]);//不足的部分使用 0 填充
        //实际上，需要arr数组的最后一个数来填充temp
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }

        //使用while循环来处理，找到我们的数key
        while(low <= high){//只要满足这个条件，就可以继续找
            mid = low + f[k - 1] -1 ;
            if (key < temp[mid]){
                //我们应该继续向数组的前面查找(左边)
                high = mid - 1;
                //全部元素 = 前面的元素 + 后面的元素。f[k] = f[k - 1] + f[ k - 2]。
                // 因为前面有f[k - 1] 个元素，所以就可以继续拆分 f[k -1] = f[k -2] + f[f - 3]。
                //即在 f[k - 1] 的前面继续查找k--,下次循环 mid = f[k-1 -1] - 1
                k--;
            }else if (key > temp[mid]){//我们应该继续向数组的后面查找（右边）
                low = mid + 1;
                //f[k] = f[k - 1] + f[k - 2]。因为后面我们有f[k - 2],所以可以继续拆分 f[k -1] = f[k -3] + f[f - 4]。
                //即下次循环 mid = f[k - 1 - 2] - 1 。在f[ k - 2]的前面查找 k -= 2
                k -= 2;
            }else {//找到
                //需要确定返回的是哪一个下标
                if (mid <= high){
                    return mid;
                }else {
                    return high;
                }
            }
        }
        return  -1;
    }
}










































