package com.bis.interview_prep.dynamicProgramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * You have been given n boxes, with Hi,Wi,Di
 * The boxes cannot be rotated and can only be stacked on
 * top of another if each box in the stack is strictly larger than
 * the box above it in terms of all dimensions.
 * Print the tallest height of the stack. The stack height is the
 * sum of all boxes in the stack.
 **/
public class BoxStacks {

    public static void main(String[] args) {
        List<Box> boxes = new ArrayList<>();
        boxes.add(new Box(1, 2, 3));
        boxes.add(new Box(2, 3, 4));
        boxes.add(new Box(4, 5, 9));
        boxes.add(new Box(7, 7, 10));
        boxes.add(new Box(3, 4, 5));
        boxes.add(new Box(4, 6, 6));
        boxes.add(new Box(2, 5, 3));

        int height = boxHeightChoices(boxes);
        System.out.println(height);
    }

    static int boxHeight(List<Box> boxes) {
        if (boxes.isEmpty())
            return 0;
        //sort the boxes
        Collections.sort(boxes);
        int maxHeight = 0;
        HashMap<Integer, Integer> memo = new HashMap<>();

        for (int i = 0; i < boxes.size(); i++) {
            Box box = boxes.get(i);
            int height = boxHeight(boxes, i, box, memo);
            //height += box.height;

            maxHeight = Math.max(maxHeight, height);
            memo.put(i, maxHeight);
        }

        return maxHeight;
    }

    static int boxHeightChoices(List<Box> boxes) {
        if (boxes.isEmpty())
            return 0;
        //sort the boxes
        Collections.sort(boxes);
        HashMap<Integer, Integer> memo = new HashMap<>();

        int height = boxHeightChoices(boxes, 0, null, memo);

        return height;
    }

    private static int boxHeightChoices(List<Box> boxes, int index, Box box, HashMap<Integer, Integer> memo) {
        //base case
        if (index >= boxes.size())
            return 0;

        if (memo.containsKey(index)) {
            return memo.get(index);
        }

        int maxHeight = 0;
        Box bottomBox = boxes.get(index);
        //take box
        if (box == null || bottomBox.canBeAbove(box)) {
            maxHeight = boxHeightChoices(boxes, index+1, bottomBox,memo);
            maxHeight += bottomBox.height;
            memo.put(index,maxHeight);
        }

        //do not tak box
        int height = boxHeightChoices(boxes,index+1,box,memo);
        maxHeight = Math.max(maxHeight, height);
        return maxHeight;
    }


    //using dp
    private static int boxHeight(List<Box> boxes, int index, Box box, HashMap<Integer, Integer> memo) {
        //base case
        if (memo.containsKey(index)) {
            return memo.get(index);
        }

        int maxHeight = 0;
        //starting building the box stack from previous index
        for (int i = index + 1; i < boxes.size(); i++) {
            Box bottomBox = boxes.get(i);
            if (bottomBox.canBeAbove(box)) {
                int height = boxHeight(boxes, i, bottomBox,memo);
                //height += bottomBox.height;
                maxHeight = Math.max(maxHeight, height);
            }
        }

        memo.put(index,maxHeight+box.height);
        return maxHeight+box.height;
    }


    private static int boxHeight(List<Box> boxes, int index, Box box) {

        int maxHeight = 0;
        //starting building the box stack from previous index
        for (int i = index + 1; i < boxes.size(); i++) {
            Box bottomBox = boxes.get(i);
            if (bottomBox.canBeAbove(box)) {
                int height = boxHeight(boxes, i, bottomBox);
                //height += bottomBox.height;
                maxHeight = Math.max(maxHeight, height);
            }
        }

        return maxHeight + box.height;
    }



    static class Box implements Comparable<Box> {

        int height;
        int depth;
        int width;

        public Box(int height, int depth, int width) {
            this.height = height;
            this.depth = depth;
            this.width = width;
        }

        public boolean canBeAbove(Box box) {
            return box.height > height && box.width > width && box.depth > depth;
        }

        @Override
        public int compareTo(Box box) {
            return Integer.compare(box.height, height);
        }
    }
}
