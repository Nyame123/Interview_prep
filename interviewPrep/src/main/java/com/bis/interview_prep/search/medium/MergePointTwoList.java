package com.bis.interview_prep.search.medium;

import com.bis.interview_prep.search.Node;

public class MergePointTwoList {

    public static void main(String[] args) {
        Node head = new Node(2);
        head.next = new Node(12);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(3);
        head.next.next.next.next.next = new Node(12);
        head.next.next.next.next.next.next = new Node(2);
        Node second =new Node(12);
        second.next = new Node(12);
        second.next.next = new Node(3);
        second.next.next = head.next.next;
        /*second.next.next.next = new Node(4);
        second.next.next.next.next = new Node(3);
        second.next.next.next.next.next = new Node(12);
        second.next.next.next.next.next.next = new Node(2);*/

        int meetingPoint = findMergeNode1(head,second);
        System.out.println(meetingPoint);
    }

    static int findMergeNode1(Node head1,Node head2){
        Node curA = head1;
        Node curB = head2;
        while (curA != curB){

            if (curA.next == null){
                curA.next = head2;
            }else{
                curA = curA.next;
            }

            if (curB.next == null){
                curB.next = head1;
            }else{
                curB = curB.next;
            }
        }

        return curB.data;
    }

    static int findMergeNode(Node head1, Node head2) {
        //System.out.println("Head1");
        int height1 = height(head1);
        //System.out.println("Head1");
        int height2 = height(head2);

        //System.out.println("H1 "+height1);
        //System.out.println("H2 "+height2);

        Node high,slow;
        if(height1 > height2){
            int dif = height1 - height2;
            high = head1;
            slow = head2;
            while(dif != 0){
                high = high.next;
                dif--;
            }
        }else{
            int dif = height2 - height1;
            high = head2;
            slow = head1;
            while(dif != 0){
                high = high.next;
                dif--;
            }
        }

        while(slow != null && high != null){
            //System.out.printf("Slow %d and High %d\n",slow.data,high.data);
            if(slow.equals(high)){
                return slow.data;
            }

            slow = slow.next;
            high = high.next;
        }

        return 0; //default if no meet point;
    }

    static int height(Node head){
        Node cur = head;
        int count = 0;
        while(cur != null){
            //System.out.print(cur.data+" ");
            cur = cur.next;
            count++;
        }

        //System.out.println();
        return count;
    }

}
