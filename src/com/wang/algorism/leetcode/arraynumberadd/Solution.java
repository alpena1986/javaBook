package com.wang.algorism.leetcode.arraynumberadd;

import java.math.BigDecimal;
import java.math.RoundingMode;

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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        BigDecimal intNumber = getNumeric(l1);
        BigDecimal intNumber1 = getNumeric(l2);
        BigDecimal sum = intNumber.add(intNumber1);
        return constructNode(sum);
    }

    /**
     * 从链表中获取数值
     * @param l1
     * @return
     */
    public BigDecimal getNumeric(ListNode l1) {
        BigDecimal value = BigDecimal.ZERO;
        BigDecimal times = BigDecimal.ONE;
        while (l1 != null) {
            value = value.add(times.multiply(new BigDecimal(l1.val)));
            l1 = l1.next;
            times = times.multiply(new BigDecimal(10));
        }
        return value;
    }

    public ListNode constructNode(BigDecimal numberValue) {
        ListNode root = null;
        ListNode cur = null;

        int divisor = 10;

        while(numberValue.compareTo(BigDecimal.ZERO) > 0){
            BigDecimal num = numberValue.divideAndRemainder(new BigDecimal(divisor))[1];
            if(root == null){
                root = new ListNode(num.intValue());
                cur = root;
            } else {
                ListNode next = new ListNode(num.intValue());
                cur.next = next;
                cur = next;
            }
            numberValue = numberValue.divide(new BigDecimal(10), RoundingMode.DOWN);
        }

        if(root == null) {
            root = new ListNode(0);
        }
        return root;
    }


}