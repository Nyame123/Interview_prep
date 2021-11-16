package com.bis.interview_prep.search;
/**
 * Imagine you have a 20 GB file with one string per line. Explain how you would sort
 * the file.
 **/
public class SortBigFile {

    public static void main(String[] args) {

        /**
         * We have 20 GB of file which is very big and we cannot load all the files
         * into an internal memory so we start off with a chunks of the data.
         *
         * 20GB = 20000MB of files, we can divide the files into 20,000 parts with each part
         * have 1GB, which is reasonable.
         *
         * We can now use merge sort or quick sort to sort each chunks and put back into a temporal file.
         * After all the chunks are sorted and it is merged into one file.
         *
         * This method of sorting is called External sorting
         *
         **/
    }


}
