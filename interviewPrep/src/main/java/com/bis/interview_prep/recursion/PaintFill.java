package com.bis.interview_prep.recursion;
/**
 * Paint Fill is a class that mimic how paint programs fill a point in a
 * screen with a different color and all the point with the same color around
 * the starting point in a two dimensional array of colors.
 **/
public class PaintFill {

    enum Color{
        BLACK,
        GREEN,
        RED,
        YELLOW,
        WHITE,
        ORANGE
    }
    public static void main(String[] args) {

        Color[][] screen = {
                new Color[]{Color.GREEN, Color.GREEN,Color.WHITE,Color.WHITE},
                new Color[]{Color.GREEN, Color.GREEN,Color.WHITE,Color.WHITE},
                new Color[]{Color.GREEN, Color.GREEN,Color.WHITE,Color.WHITE},
                new Color[]{Color.GREEN, Color.YELLOW,Color.WHITE,Color.WHITE},
                new Color[]{Color.GREEN, Color.YELLOW,Color.WHITE,Color.GREEN},
                new Color[]{Color.GREEN, Color.YELLOW,Color.WHITE,Color.GREEN}
        };
       boolean res = fillPaint(screen,0,0,Color.GREEN);
        System.out.println(res);
    }



    static boolean fillPaint(Color[][] screen,int row,int col,Color targetColor){
        if (screen[row][col] == targetColor)
            return false;

        return fill(screen,row,col,screen[row][col],targetColor);
    }

    //This is a depth-first search algorithm
    static boolean fill(Color[][] screen, int row, int col,Color oldColor, Color targetColor) {
        //base cases
        if (row < 0 || row >= screen.length || col < 0 || col >= screen[0].length){
            return false;
        }



        //fill the pixel or point with the new color
        if (screen[row][col] != oldColor)
            return false;

        screen[row][col] = targetColor;

        //recurse downwards
        fill(screen,row+1,col,oldColor,targetColor);
        //recurse upwards
        fill(screen,row-1,col,oldColor,targetColor);
        //recurse leftwards
        fill(screen,row,col-1,oldColor,targetColor);
        //recurse rightwards
        fill(screen,row,col+1,oldColor,targetColor);
        return true;
    }
}
