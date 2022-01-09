package com.bis.interview_prep.recursion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class PasswordCracker {

    public static void main(String[] args) throws FileNotFoundException {
        File desktop = new File(System.getProperty("user.home"), "/Desktop/testCases/password_cracker_test_case.txt");
        Scanner scanner = new Scanner(new InputStreamReader(new FileInputStream(desktop)));

        int t = scanner.nextInt();
        for (int i = 0; i < t; ++i) {
            int n = scanner.nextInt();
            List<String> passwords = new ArrayList<>();
            for (int j = 0; j < n; ++j) {
                passwords.add(scanner.next());
            }
            String loginAttempt = scanner.next();
            System.out.println(passwordCracker(passwords, loginAttempt));
        }


        scanner.close();
    }


    public static String passwordCracker(List<String> passwords, String loginAttempt) {
        // Write your code here
        return passwordCrackerRec(passwords, loginAttempt, new HashMap<String, Boolean>());

    }

    static String passwordCrackerRec(List<String> passwords, String loginAttempt, HashMap<String, Boolean> map) {

        //base case
        if (map.containsKey(loginAttempt)){
            return null;
        }


        for (int i = 0; i < passwords.size(); i++) {
            String curPass = passwords.get(i);
            if (loginAttempt.startsWith(curPass)) {
                int len = curPass.length();
                String remLoginAt = loginAttempt.substring(len);
                if (remLoginAt.equals("")) {
                    return curPass;
                } else {
                    String res = passwordCrackerRec(passwords, remLoginAt, map);

                    //memoize
                    map.put(remLoginAt, true);
                    if (res != null)
                        return curPass + " " + res;
                }

            }

        }

        return null;

    }
}

class Main {
    static public void main(String[] args) throws FileNotFoundException {
        File desktop = new File(System.getProperty("user.home"), "/Desktop/testCases/password_cracker_test_case.txt");
        Scanner in = new Scanner(new InputStreamReader(new FileInputStream(desktop)));

        int t = in.nextInt();

        for (int i = 0; i < t; ++i) {
            int n = in.nextInt();
            String[] passwords = new String[n];
            for (int j = 0; j < n; ++j) {
                passwords[j] = in.next();
            }
            String loginAttempt = in.next();
            System.out.println(solve(passwords, loginAttempt));
        }
    }

    private static String solve(String[] passwords, String loginAttempt) {
        String answer = solve(passwords, loginAttempt, 0, new boolean[loginAttempt.length()]);
        return answer != null ? answer : "WRONG PASSWORD";
    }

    static boolean isMatch(String password, String loginAttempt, int start) {
        if ((start + password.length()) <= loginAttempt.length()) {
            for (int k = 0; k < password.length(); ++k) {
                if (password.charAt(k) != loginAttempt.charAt(start + k)) return false;
            }
            return true;
        }
        return false;
    }

    private static String solve(String[] passwords, String loginAttempt, int i, boolean[] visited /* memorization */) {
        if (i == loginAttempt.length()) return "";
        if (visited[i]) return null;

        for (String pass : passwords) {
            if (isMatch(pass, loginAttempt, i)) {
                String ret = solve(passwords, loginAttempt, i + pass.length(), visited);
                if (ret != null) {
                    return ret.length() == 0 ? pass : pass + " " + ret;
                }
            }
        }

        visited[i] = true;
        return null;
    }
}
