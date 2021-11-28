package com.bis.interview_prep.ordinal.ArrayAndStrings;

import java.util.List;

public class TruckTour {

    public static void main(String[] args) {


    }

    public static int truckTour(List<List<Integer>> petrolpumps) {
        // Write your code here
        int maxIndex = 0;
        int sum = 0;
        int startIndex = 0;
        int n = petrolpumps.size();
        for (int i = 0; i < n; i++) {
            int am = petrolpumps.get(i).get(0);
            int dis = petrolpumps.get(i).get(1);
            sum += am - dis;

            if (sum < 0) {
                sum = 0;
                startIndex = i + 1;
            }

        }


        return startIndex;
    }
}
