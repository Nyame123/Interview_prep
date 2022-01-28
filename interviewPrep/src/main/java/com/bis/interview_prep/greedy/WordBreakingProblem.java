package com.bis.interview_prep.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s and an integer k, break up the string into multiple lines such that each line has a length of k or less.
 * You must break it up so that words don't break across lines. Each line has to have the maximum possible amount of words.
 * If there's no way to break the text up, then return null.
 * <p>
 * You can assume that there are no spaces at the ends of the string and that there is exactly one space between each word.
 * <p>
 * For example, given the string "the quick brown fox jumps over the lazy dog" and k = 10, you should return:
 * ["the quick", "brown fox", "jumps over", "the lazy", "dog"]. No string in the list has a length of more than 10.
 **/
public class WordBreakingProblem {

    public static void main(String[] args) {
        String sentence = "THESE TERMS AND CONDITIONS OF SERVICE (the Terms) ARE A LEGAL AND BINDING AGREEMENT BETWEEN YOU AND NATIONAL GEOGRAPHIC governing your use of this site, www.nationalgeographic.com, which includes but is not limited to products, software and services offered by way of the website such as the Video Player, Uploader, and other applications that link to these Terms (the Site). Please review the Terms fully before you continue to use the Site. By using the Site, you agree to be bound by the Terms. You shall also be subject to any additional terms posted with respect to individual sections of the Site. Please review our Privacy Policy, which also governs your use of the Site, to understand our practices. If you do not agree, please discontinue using the Site. National Geographic reserves the right to change the Terms at any time without prior notice. Your continued access or use of the Site after such changes indicates your acceptance of the Terms as modified. It is your responsibility to review the Terms regularly. The Terms were last updated on 18 July 2011.";
//        String sentence = "the quick brown fox jumps over the lazy dog";
        int k = 80;
        List<String> res = wordBreak(sentence,k);
        System.out.println(res);
    }

    static List<String> wordBreak(String s, int k) {
        int i = 0;
        String[] wordArr = s.split(" ");
        StringBuilder sb = new StringBuilder();
        int accumulatedLen = 0;
        List<String> wordBreak = new ArrayList<>();
        while (i < wordArr.length){
            String word = wordArr[i];
            int len = word.length();
            if (word.length() > k){
                System.out.println("Cannot break the words into multiple lines");
                return null;
            }else if (accumulatedLen + len + 1 <= k+1){
                sb.append(word).append(" ");
                accumulatedLen += len+1;
                i++;
            }else{
                wordBreak.add(sb.toString());
                sb = new StringBuilder();
                accumulatedLen = 0;

            }


        }
        wordBreak.add(sb.toString());

        return wordBreak;
    }
}
