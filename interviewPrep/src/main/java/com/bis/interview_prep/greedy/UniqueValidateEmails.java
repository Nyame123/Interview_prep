package com.bis.interview_prep.greedy;

import java.util.HashSet;

/**
 *This question is asked by Google. Email addresses are made up of local and domain names.
 *  For example in kevin@dailybyte.com, kevin is the local name and dailybye.com is the domain name.
 *  Email addresses may also contain '+' or '.' characters besides lowercase letters.
 *  If there is a '.' in the local name of the email address it is ignored.
 *  Everything after a '+' in the local name is also ignored. For example
 *  ke.vin+abc@dailybyte.com is equivalent to kevin@dailybyte.com Given a list of email addresses, find the number of unique addresses.
 *
 * Ex: Given the following emails...
 *
 * emails = [
 *     "test.email+kevin@dailybyte.com",
 *     "test.e.mail+john.smith@dailybyte.com",
 *     "testemail+david@daily.byte.com"
 * ], return 2. "testemail@dailybyte.com" and "testemail@daily.byte.com" are unique
 * since the first two email addresses in the list are equivalent.
 **/
public class UniqueValidateEmails {

    public static void main(String[] args) {
        String[] emails = {
                "test.email+kevin@dailybyte.com",
                    "test.e.mail+john.smith@dailybyte.com",
     "testemail+david@daily.byte.com"
        };

        System.out.println(numUniqueEmails(emails));

    }

    /**
     * Idea is to use a hashing to store unique emails after
     * processing the emails using the '. and +' rules.
     *
     * Time Complexity = O(N*K) where k = length of email and N is number of emails
     * Space Complexity = O(N);
     **/
    static int numUniqueEmails(String[] emails) {
        HashSet<String> emailUniq = new HashSet<>();
        int n = emails.length;
        for(int i = 0; i < n; i++){
            String email = emails[i];
            String[] parts = email.split("@");
            String localName = parts[0];
            String domain = parts[1];
            StringBuilder sb = new StringBuilder();
            for(char c: localName.toCharArray()){
                if(c == '+'){
                    break;
                }

                if(c == '.'){
                    continue;
                }

                sb.append(c);

            }

            sb.append("@").append(domain);
            //System.out.println(sb);
            emailUniq.add(sb.toString());
        }

        return emailUniq.size();
    }
}
