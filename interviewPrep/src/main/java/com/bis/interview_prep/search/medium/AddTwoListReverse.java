package com.bis.interview_prep.search.medium;

import com.bis.interview_prep.codeWar.CodeWarChallenge;

/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order, and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 *
 * Example 2:
 *
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 * Example 3:
 *
 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * Output: [8,9,9,9,0,0,0,1]
 **/
public class AddTwoListReverse {


    public CodeWarChallenge.ListNode addTwoNumbers(CodeWarChallenge.ListNode l1, CodeWarChallenge.ListNode l2) {
        int h1 = getHeight(l1);
        int h2 = getHeight(l2);

        if(h1 < h2){
            int dif = h2-h1;
            paddZeros(l1,dif);
        }else if(h2 < h1){
            int dif = h1-h2;
            paddZeros(l2,dif);
        }

        // System.out.printf("H1 %d, H2 %d",h1,h2);
        // printNode(l2);

        CodeWarChallenge.ListNode cur = l1;
        CodeWarChallenge.ListNode prev = null;
        int rem = 0;
        while(l1 != null && l2 != null){
            int sum = cur.val + l2.val+rem;
            int mod = sum % 10;
            cur.val = mod;

            prev = cur;


            rem = sum / 10;
            cur = cur.next;
            l2 = l2.next;
        }

        if(rem > 0){
            prev.next = new CodeWarChallenge.ListNode(rem);
        }

        return l1;
    }

    static void printNode(CodeWarChallenge.ListNode root){
        System.out.println();
        CodeWarChallenge.ListNode cur = root;
        while(cur != null){
            System.out.print(cur.val +",");
            cur = cur.next;
        }
    }

    static void paddZeros(CodeWarChallenge.ListNode root, int num){
        CodeWarChallenge.ListNode cur = root;
        while(cur.next != null){
            cur = cur.next;
        }

        int i = 0;
        while(i < num){
            cur.next = new CodeWarChallenge.ListNode(0);
            cur = cur.next;
            i++;
        }

        // return root;
    }

    static int getHeight(CodeWarChallenge.ListNode root){
        if(root == null){
            return 0;
        }

        return getHeight(root.next) + 1;
    }
}
