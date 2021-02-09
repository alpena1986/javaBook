package com.wang.algorism.leetcode.arraynumberadd;

public class Main {


    public static void main(String[] args) {
        Solution solution = new Solution();

        ListNode l1 = new ListNode(2);
        ListNode tmp1 = l1;
        for (int i = 0; i < 11; i++) {
            ListNode a = new ListNode(2);
            tmp1.next = a;
            tmp1 = a;
        }


        ListNode l2 = new ListNode(2);
        ListNode tmp = l2;
        for (int i = 0; i < 11; i++) {
            ListNode a = new ListNode(2);
            tmp.next = a;
            tmp = a;
        }
        ListNode listNode = solution.addTwoNumbers(l1, l2);

        int c = 0;
        c++;
    }
}
