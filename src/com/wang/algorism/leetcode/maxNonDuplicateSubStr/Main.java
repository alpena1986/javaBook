package com.wang.algorism.leetcode.maxNonDuplicateSubStr;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("1234abcdd123459999"));
    }

    public static int lengthOfLongestSubstring(String s) {
        Set<Character> tmpSet = null;

        // 最长子串长度
        int max = 0;

        int len = s.length();

        for (int i = 0; i < len; i++) {
            tmpSet = new HashSet<>();
            for (int j = i; j < len; j++) {
                int l = j - i + 1;
                tmpSet.add(s.charAt(j));
                if (!sizeIs(tmpSet, l)) {
                    break;
                } else if (l > max) {
                    max = l;
                }
            }
        }
        return max;
    }

    public static boolean sizeIs(Set set, int size){
        return set.size() == size;
    }
}
