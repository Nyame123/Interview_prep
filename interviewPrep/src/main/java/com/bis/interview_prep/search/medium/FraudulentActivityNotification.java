package com.bis.interview_prep.search.medium;

import java.util.Arrays;
import java.util.List;

/**
 * In this HackerRank Fraudulent Activity Notifications
 * Interview preparation kit you have Given the number of trailing days d
 * and a client's total daily expenditures for a period of n days, find and print the
 * number of times the client will receive a notification over all n days.
 **/
public class FraudulentActivityNotification {


    public static void main(String[] args) {

        int d = 3;
        int[] exp = new int[]{10, 20, 30, 40, 50};
        int res = activityNotifications(exp, d);
        System.out.println(res);
    }

    //Time Complexity = O(n*d) where d is a constant factor 201
    static int activityNotifications(int[] expenditure, int d) {

        int notificationCount = 0;

        int[] data = new int[201];
        for (int i = 0; i < d; i++) {
            data[expenditure[i]]++;
        }

        for (int i = d; i < expenditure.length; i++) {

            double median = getMedian(d, data);
            //System.out.print(median + " Med ");
            if (expenditure[i] >= 2 * median) {
                notificationCount++;

            }

            data[expenditure[i]]++;
            data[expenditure[i - d]]--;

        }

        return notificationCount;

    }

    private static double getMedian(int d, int[] data) {
        double median = 0;
        if (d % 2 == 0) {
            Integer m1 = null;
            Integer m2 = null;
            int count = 0;
            for (int j = 0; j < data.length; j++) {
                count += data[j];
                if (m1 == null && count > d / 2) {
                    m1 = j;
                }
                if (count > d / 2 + 1) {
                    m2 = j;
                    break;
                }
            }
            median = (m1 + m2) / 2.0;

        } else {
            int count = 0;
            for (int j = 0; j < data.length; j++) {
                count += data[j];
                if (count > d / 2) {
                    median = j;
                    break;
                }
            }
        }
        return median;
    }

    //Time Complexity = O(n*nlog(n))
    static int fraudActivityNot(List<Integer> expenditure, int d) {
        int n = expenditure.size();
        int count = 0;
        for (int i = 0; i < n - d; i++) {
            int[] trailingArr = new int[d];
            for (int j = i; j < i + d; j++) {
                trailingArr[j - i] = expenditure.get(j);
                //System.out.print(expenditure.get(j)+" ");
            }

            Arrays.sort(trailingArr);

            int half = d / 2;
            int dExp = i + d;
            double mid = (d % 2 == 0) ?
                    (double) (trailingArr[half] + trailingArr[half - 1]) / 2 :
                    trailingArr[half];

            //System.out.println("Mid "+mid);

            if (expenditure.get(dExp) >= 2 * mid) {
                count++;
            }

        }

        return count;
    }
}
