package com.atguigu.kmp;

public class ViolenceMatch {
    public static void main(String[] args) {
        //测试匹配算法
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String target  = "尚硅谷你尚硅你";
        System.out.println(violenceMatch(str1, target));
    }

    /**
     * 暴力匹配算法实现子字符串查找的实现
     * @param str1
     * @param target
     * @return
     */
    public static int violenceMatch(String str1 ,String target){
        char[] s1 = str1.toCharArray();
        char[] s2 = target.toCharArray();

        int s1Len = s1.length;
        int s2Len = s2.length;

        int i = 0;// i  索引指向 s1
        int j = 0; // j 索引指向 s2
        while (i < s1Len && j < s2Len){//保证在检索时候，不越界
            if (s1[i] == s2[j]){//匹配成功
                i++;
                j++;
            }else{//没有匹配成功
                i = i -(j - 1);
                j = 0;
            }
        }
        //判断是否匹配成功
        if (j == s2Len){
            return i - j;
        }else{
            return -1;
        }
    }



}








































