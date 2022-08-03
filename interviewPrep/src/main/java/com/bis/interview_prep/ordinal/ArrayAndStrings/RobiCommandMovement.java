package com.bis.interview_prep.ordinal.ArrayAndStrings;

import java.util.HashMap;

/**
 * Keed Digital has a small robot (Robi), which moves around in a 2-dimensional grid. Robi can only
 * turn left or right and walk straight. The robot also knows its current position (X, Y) as well as its
 * direction: Top (T), Bottom (B), Left (L) or Right (R).
 * In order to get the Robi to move, we need to input a move command. The move command can
 * be represented with a string consisting of three alphabets L, R and F. F string will be followed by
 * a positive integer X to indicate the distance of how many steps it has to move, which can be
 * explained as follows:
 * ● L: Turn to the left from Robi current position (counterclockwise)
 * ● R: Turn to the right from Robi current position (clockwise)
 * ● FX: Move Forward for X step(s) where X can be any positive integers. For example, F12
 * means moving forward for 12 steps.
 * Initial conditions
 * ● Robi starts at the position (X,Y) of (0,0)
 * ● Robi is facing Top (T), we state Direction (D) to D:T
 * ● We add one Bonus item in a random (X,Y) location in the range of -100 to 100
 *
 * The example
 * Given the move command of LLF9RF4RRF12 means
 * Robi starts at the position X:0; Y:0; D:T
 * 1. Robi turns left - facing Left (X:0; Y:0; D:L)
 * 2. Robi turns left - facing Bottom (X:0; Y:0; D:B)
 * 3. Roman moves forward 9 steps (X:0; Y:-9; D:B)
 * 4. Robi turns right - facing Left (X:0; Y:-9; D:L)
 * 5. Roman moves forward 4 steps (X:-4; Y:-9; D:L)
 * 6. Robi turns right - facing Top (X:-4; Y:-9; D:T)
 * 7. Robi turns right - facing Right (X:-4; Y:-9; D:R)
 * 8. Roman moves forward 12 steps (X:8; Y:-9; D:R)
 * 9. Script prints out the last position > X:8; Y:-9; D:R
 * The task
 * Your task is to create an elegant command-line script in any programming language. The script
 * needs to accept a command-line argument as an input string of the move command and print
 * out the result of the last position (X, Y), direction (T, B, L or R) and if Robi passed the Bonus (B)
 * location.
 *
 **/
public class RobiCommandMovement {

    //Directions
    static int[] DIR = {1,2,3,4};
    static HashMap<Integer,String> dirMap = new HashMap<>();
    public static void main(String[] args) {

        dirMap.put(0,"T");
        dirMap.put(1,"L");
        dirMap.put(2,"B");
        dirMap.put(3,"R");

        String[] command = {"L","R","F","9","R","F","4","R","R","F","12"};
        //X = -8, Y = 9, D = L
        String lastLocation = lastLocation(command);
        System.out.println(lastLocation);
    }

    static String lastLocation(String[] command) {
        int x = 0, y = 0, lastDir = 0;
        String D = "T";
        for (int i = 0; i < command.length; i++) {
            String com = command[i];
            if (com.equals("L")){ //anti-clockwise
                int curDir = ++lastDir % 4;
                D = dirMap.get(curDir);
            }else if (com.equals("R")){ //clockwise
                if (lastDir == 0)
                    lastDir = 3;
                else
                    lastDir--;
                int curDir = lastDir;
                D = dirMap.get(curDir);
            }else{ //movement
                int steps = Integer.parseInt(command[++i]);
                if (D.equals("T")){
                    y += steps;
                }else if (D.equals("L")){
                    x -= steps;
                }else if (D.equals("B")){
                    y -= steps;
                }else if (D.equals("R")){
                    x += steps;
                }
            }
        }
        return String.format("X:%s;Y:%s;D:%s",x,y,D);
    }
}
