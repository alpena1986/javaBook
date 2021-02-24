package com.wang.algorism.leetcode;

public class LongestCommonPrefix {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestCommonPrefix(new String[]{"1abc", "1abcd", "1abced"}));
    }

    public static class Solution {

        public String longestCommonPrefix(String[] strs) {
            StringBuilder sb = new StringBuilder();
            String base = strs[0];
            if (base == null) {
                return "";
            }
            for (int i = 0; i < base.length(); i++) {
                Character a = getCharAt(base, i);
                boolean ok = true;
                if (a == null) {
                    ok = false;
                }
                for (int j = 1; j < strs.length; j++) {
                    Character charAt = getCharAt(strs[j], i);
                    if (charAt == null || !charAt.equals(a)) {
                        ok = false;
                    }
                }
                if(ok){
                    sb.append(a);
                } else {
                    break;
                }
            }
            return sb.toString();
        }

        public Character getCharAt(String str, int index) {
            if (str == null) {
                return null;
            }
            if (str.length() <= index) {
                return null;
            }
            return str.charAt(index);
        }
    }
}


