package com.bis.interview_prep.search;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Given an input file with four billion non-negative integers, provide an algorithm to
 * generate an integer that is not contained in the file. Assume you have 1 GB of memory available for
 * this task.
 * FOLLOW UP
 * What if you have only 10MB of memory? Assume that all the values are distinct and we now have
 * no more than one billion non-negative integers.
 **/
public class MissingInt {

    public static void main(String[] args) throws IOException {
        findMissingInts();


        /**
         * In the follow up question, we can use the same method but we have to
         * divide the files into chunks since we have 10MB, we can use 2^20 as a block size.
         *
         * Now we have blocks of integers then we can apply the above solution to it.
         **/

    }

    //If you want to read from a file
    static void readFile(File file) throws IOException {
        Scanner scanner = new Scanner(new FileReader(file));
        File output = new File(System.getProperty("user.home"), "/Desktop/integers_available.txt");
        BufferedWriter bufferedInputStream = new BufferedWriter(new FileWriter(output));
        while (scanner.hasNextInt()) {
            bufferedInputStream.write(scanner.nextInt());
        }

        scanner.close();
        bufferedInputStream.flush();
        bufferedInputStream.close();

    }

    static void findMissingInts() throws FileNotFoundException {
        int n = 100;
        byte[] bytes = new byte[n / 8];
        File output = new File(System.getProperty("user.home"), "/Desktop/availables_ints.txt");
        Scanner scanner = new Scanner(new FileReader(output));

        while (scanner.hasNextInt()) {
            int availInt = scanner.nextInt();

            //get the block of byte to write
            int block = availInt / 8;
            int mod = availInt % 8;
            int bitMask = (1 << mod);
            bytes[block] |= bitMask;
        }

        scanner.close();

        for (int i = 0; i < bytes.length; i++) {
            for (int j = 0; j < 8; j++) {
                if ((bytes[i] & (1 << j)) == 0) {
                    System.out.println(i * 8 + j);
                }
            }
        }

    }
}
