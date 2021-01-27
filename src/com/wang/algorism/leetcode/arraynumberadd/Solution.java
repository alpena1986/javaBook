package com.wang.algorism.leetcode.arraynumberadd;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */


class Solution {
    public static void main(String[] args) {
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
        ListNode listNode = addTwoNumbers(l1, l2);

        int c = 0;
        c++;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        long intNumber = getIntNumber(l1);
        long intNumber1 = getIntNumber(l2);
        long sum = intNumber + intNumber1;
        return constructNode(sum);
    }

    public static long getIntNumber(ListNode l1) {
        long v = 0;
        long dim = 1L;
        while (l1 != null) {
            v += ((long) l1.val * dim);
            l1 = l1.next;
            dim = dim * 10;
        }
        return v;
    }

    public static ListNode constructNode(long val) {
        ListNode root = null;
        ListNode cur = null;
        int dim = 10;
        while(val > 0){
            int num = Math.toIntExact(val % dim);
            if(root == null){
                root = new ListNode(num);
                cur = root;
            } else {
                ListNode next = new ListNode(num);
                cur.next = next;
                cur = next;
            }
            val = val / 10;
        }

        if(root == null) {
            root = new ListNode(0);
        }
        return root;
    }


}