package com.atguigu.kmp;

import java.util.Arrays;

public class KmpAlgorithm {
    public static void main(String[] args) {
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String target  = "尚硅谷你尚硅你";
        System.out.println(kmpSearch(str1,target,kmpNext(target)));
    }

    /**
     *获取得到一个字符串（子串）的部分匹配值表
     * @param dest
     * @return
     */
    public static int[] kmpNext(String dest){
        //创建一个next 数组吧盘存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0;//如果字符串是长度为 1 ，那么部分匹配值就是 0。也就是说 当字符串的长度是 1 的时候，没有前缀和后缀子字符串，所以就没有部分匹配值。
        for (int i = 1,j = 0; i <dest.length() ; i++) {
            while (j > 0 && dest.charAt(i) != dest.charAt(j)){
                j = next[j - 1];
            }
            //当 dest.charAt(i) == dest.charAt(j) 满足是，部分匹配值就 +1
            if (dest.charAt(i) == dest.charAt(j)){
                j++;
            }
            next[i]  =j;
        }
        return next;
    }

    /**
     * 写出我们的kmp搜索算法
     * @param str1  源字符串
     * @param str2  字串
     * @param next  部分匹配表，是字串对应的部分匹配表
     * @return  如果是 -1 就是没有匹配到，否则返回第一个匹配位置
     */
    public static int kmpSearch(String str1,String str2,int[] next){
        //遍历
        for (int i = 0,j = 0; i < str1.length(); i++) {

            //需要考虑 str1.charAt(i) != str2.charAt(j) 的情况,需要调整 j 的大小
            //kmp算法核心
            while (j > 0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)){
                j++;
            }
            if (j == str2.length()){//找到了
                return i - j + 1;
            }
        }
        return  -1;
    }

}
































