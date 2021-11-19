package com.bis.interview_prep.ordinal.ArrayAndStrings;

/**
 * A string is said to be a special string if either of two conditions is met:
 * <p>
 * 1. All of the characters are the same, e.g. aaa.
 * 2. All characters except the middle one are the same, e.g. aadaa.
 * A special substring is any substring of a string which meets one of those criteria.
 * Given a string, determine how many special substrings can be formed from it.
 **/
public class SpecialString {

    public static void main(String[] args) {
        String string = "aaabbc";
        int specialStringCount = substrCount(string);
        System.out.println(specialStringCount);
    }

    private static int substrCount(String string) {

        char cur;
        int counter = 0;
        for (int i = 0; i < string.length(); i++) {
            int prevIndex = i - 1;
            int forwardIndex = i + 1;
            cur = string.charAt(i);

            Character check = null;
            do {
                boolean isSpecial = false;

                if (forwardIndex < string.length() && string.charAt(forwardIndex) == cur) { //the second case
                    if (check == null)
                        check = string.charAt(forwardIndex);
                    else {
                        if (string.charAt(forwardIndex) != check)
                            break;
                    }
                    counter++;
                    isSpecial = true;
                } else if (prevIndex >= 0 && forwardIndex < string.length()
                        && string.charAt(prevIndex) == string.charAt(forwardIndex)) { //first case
                    if (check == null)
                        check = string.charAt(prevIndex);
                    else {
                        if (string.charAt(prevIndex) != check)
                            break;
                    }
                    counter++;
                    isSpecial = true;
                }

                if (!isSpecial)
                    break;

                //update the pointers
                prevIndex--;
                forwardIndex++;

            } while ((prevIndex >= 0 || forwardIndex < string.length()));

        }

        return string.length() + counter;
    }
}
