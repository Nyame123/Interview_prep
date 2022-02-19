package com.bis.interview_prep.ordinal.ArrayAndStrings;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, return all of its repeated 10 character substrings.
 * Note: You may assume s only contains uppercase alphabetical characters.
 * <p>
 * Ex: Given the following string s…
 * <p>
 * s = "BBBBBBBBBBB", return ["BBBBBBBBBB"].
 * Ex: Given the following string s…
 * <p>
 * s = "ABABABABABABBBBBBBBBBB", return ["ABABABABAB","BBBBBBBBBB"].
 **/
public class TenRepeatedSubstring {

    public static void main(String[] args) {
        String s = "BBBBBBBBBBB";
        String[] res = allRepeatedTenCharacters(s);
        for (String ans : res)
            System.out.println(ans);
    }

    static String[] allRepeatedTenCharacters(String s) {
        int[] divs = {1, 2, 5};
        int n = s.length();
        List<String> res = new ArrayList<>();
        for (int div = 0; div < divs.length; div++) {
            StringBuilder stringBuilder = new StringBuilder(10);
            for (int i = 0; i < n; i++) {
                if (i+divs[div] <= n) {
                    String block = s.substring(i, i + divs[div]);
                    int count = 10 - divs[div];
                    stringBuilder.delete(0,stringBuilder.length());
                    stringBuilder.append(block);
                    for (int batch = i + (divs[div] * 2); batch <= n; batch += divs[div]) {
                        String sub = s.substring(batch - divs[div], batch);
                        if (!block.equals(sub))
                            break;
                        stringBuilder.append(sub);
                        count -= divs[div];

                        if (count == 0) {
                            res.add(stringBuilder.toString());
                            break;
                        }
                    }
                }
            }

        }
        String[] results = new String[res.size()];
        for (int i = 0; i < res.size(); i++) {
            results[i] = res.get(i);
        }
        return results;
    }
}
