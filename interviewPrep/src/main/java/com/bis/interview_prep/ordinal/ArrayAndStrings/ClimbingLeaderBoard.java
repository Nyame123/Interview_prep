package com.bis.interview_prep.ordinal.ArrayAndStrings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * An arcade game player wants to climb to the top of the
 * leaderboard and track their ranking. The game uses Dense Ranking, so its leaderboard works like this:
 * <p>
 * The player with the highest score is ranked number 1 on the leaderboard.
 * Players who have equal scores receive the same ranking number, and the
 * next player(s) receive the immediately following ranking number.
 **/
public class ClimbingLeaderBoard {

    public static void main(String[] args) {
        List<Integer> ranked = Arrays.asList(100,100,50,40,40,20,10);
        List<Integer> player = Arrays.asList(5,25,50,120);

        List<Integer> result = climbingLeaderboard(ranked,player);
        System.out.println(result);
    }

    public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
        // Write your code here
        //remove duplicates
        List<Integer> noDup = new ArrayList<>();
        Set<Integer> checker = new HashSet<>();
        for (int i = 0; i < ranked.size(); i++) {
            int rank = ranked.get(i);
            if (!checker.contains(rank)) {
                checker.add(rank);
                noDup.add(rank);
            }
        }

        //System.out.println(noDup);
        List<Integer> ans = new ArrayList<>();
        int n = player.size();
        for (int i = 0; i < n; i++) {
            int score = player.get(i);
            int rank = binarySearch(score, noDup) + 1;
            ans.add(rank);
            //System.out.println(rank);
        }

        return ans;

    }


    static int binarySearch(int x, List<Integer> ranked) {
        int size = ranked.size();
        //special binary search for terminal position
        if (x < ranked.get(size - 1)) { //last position
            return size;
        } else if (x > ranked.get(0)) {//first position
            return 0;
        }

        int left = 0;
        int right = ranked.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (ranked.get(mid) == x) {
                return mid;
            } else if (x > ranked.get(mid) && mid - 1 >= 0 && x < ranked.get(mid - 1)) {
                return mid;
            } else if (x > ranked.get(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }

        }

        return -1;
    }
}
