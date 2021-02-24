package com.wang.algorism.leetcode.addInt;

public class Main {
    public static void main(String[] args) {
        int num1 = 888;
        int num2 = 2;
        Node list1 = convertToList(num1);
        Node list2 =  convertToList(num2);
        Node result = exeAdd(list1, list2);
        System.out.println(toNumber(result));
    }

    public static class Node {
        int val;
        Node next;
        public Node(int val){
            this.val = val;
        }
        public Node(int val, Node next){
            this.val = val;
            this.next = next;
        }
    }

    public static Node convertToList(int num){
        Node result = null;
        Node tmp = null;
        while(num > 0){
            int remainder = num % 10;
            num = num / 10;
            if(result == null){
                result = new Node(remainder);
                tmp = result;
            } else {
                tmp.next = new Node(remainder);
                tmp = tmp.next;
            }
        }
        return result == null ? new Node(0) : result;
    }

    public static Node exeAdd(Node list1, Node list2){
        Node result = null;
        Node tmp = null;
        boolean jump = false;
        while(list1 != null || list2 != null || jump){
            int val1 = list1 == null ? 0 : list1.val;
            int val2 = list2 == null ? 0 : list2.val;
            int sum = val1 + val2 + (jump ? 1 : 0);
            if(result == null){
                result = new Node(sum % 10);
                tmp = result;
            } else {
                tmp.next = new Node(sum % 10);
                tmp = tmp.next;
            }
            jump = sum >= 10;
            if (list1 != null) {
                list1 = list1.next;
            }
            if (list2 != null) {
                list2 = list2.next;
            }
        }
        return result == null ? new Node(0) : result;
    }

    public static long toNumber(Node node){
        long result = 0;
        int m = 1;
        while(node != null){
            result += (long) node.val * m;
            node = node.next;
            m = m * 10;
        }
        return result;
    }

}
