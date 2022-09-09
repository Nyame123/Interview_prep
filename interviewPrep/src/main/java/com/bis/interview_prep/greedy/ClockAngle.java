package com.bis.interview_prep.greedy;
/**
 * Given the hours and the minutes on an analog clock, return the angle formed between the two hands.
 * Note: Return the smaller of the two angles formed.
 *
 * Ex: Given the following hours and minutes...
 *
 * hours = 12, minutes = 0, return 0 (the hands are on top of each other).
 * Ex: Given the following hours and minutes...
 *
 * hours = 3, minutes = 25, return 47.5.
 **/
public class ClockAngle {

    public static void main(String[] args) {
        int hour = 3, minute = 25;
        double angleDif = angleDifference(hour,minute);
        System.out.println(angleDif);
    }

    private static double angleDifference(int hour, int minute) {
        if (hour < 0 || hour > 12 || minute < 0 || minute > 60){
            return Double.MAX_VALUE;
        }

        if (hour == 12){
            hour = 0;
        }

        if (minute == 60){
            minute = 0;
            hour++;
        }



        double hourAngle = (0.5 * (hour*60 + minute));
        double minuteAngle = minute*6;

        double angle = Math.abs(hourAngle-minuteAngle);

        return Math.min(360-angle,angle);
    }
}
