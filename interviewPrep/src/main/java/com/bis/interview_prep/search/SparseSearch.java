package com.bis.interview_prep.search;

/**
 * Given a sorted array of strings that is interspersed with empty strings, write a
 * method to find the location of a given string.
 * EXAMPLE
 * Input: ball,{"at","", "", "", "ball", "" , "" , "car", "", "", "dad", "",""}
 * Output: 4
 **/
public class SparseSearch {

    public static void main(String[] args) {
        String[] sparseSortedString = {"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
        int index = findPositionOf("dad", sparseSortedString);
        System.out.println(index);
    }


    //since the string is sorted and interspersed with empty string,
    //it becomes difficult to implement normal  binary search on it. we use modified binary search
    private static int findPositionOf(String searcheedItem, String[] sparseSortedString) {

        int index = binarySearch(sparseSortedString, 0, sparseSortedString.length - 1, searcheedItem);
        return index;
    }

    private static int binarySearch(String[] sparseSortedString, int low, int high, String item) {

        while (low <= high) {
            int mid = low + (high - low) / 2;
            //if the item at mid is empty, search the non-empty string close to it
            if (sparseSortedString[mid].equalsIgnoreCase("")) {
                int forward = mid;
                int backward = mid;
                //forward search
                while (sparseSortedString[forward].equals("")) {
                    forward++;
                    if (forward >= sparseSortedString.length) {
                        break;
                    }
                }

                //backward search
                while (sparseSortedString[backward].equals("")) {
                    backward--;
                    if (backward < 0) {
                        break;
                    }

                }

                //decide here with forward and backward pointer
                if (sparseSortedString[forward].compareTo(item) == 0)
                    return forward;
                else if (item.compareTo(sparseSortedString[forward]) > 0)
                    mid = forward;
                else if (item.compareTo(sparseSortedString[backward]) == 0)
                    return backward;
                else if (item.compareTo(sparseSortedString[backward]) < 0)
                    mid = backward;
            }

            if (mid >= sparseSortedString.length || sparseSortedString[mid].compareTo(item) > 0) {
                high = mid - 1;
            } else if (mid < 0 || sparseSortedString[mid].compareTo(item) < 0) {
                low = mid + 1;
            } else
                return mid;


        }
        return -1;
    }
}
