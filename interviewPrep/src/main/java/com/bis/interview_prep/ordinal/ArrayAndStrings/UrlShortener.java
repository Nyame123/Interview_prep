package com.bis.interview_prep.ordinal.ArrayAndStrings;
/**
 * Implement a URL shortener with the following methods:
 *
 * shorten(url), which shortens the url into a six-character alphanumeric string, such as zLg6wl.
 * restore(short), which expands the shortened string into the original url. If no such shortened string exists, return null.
 **/
public class UrlShortener {

    public static void main(String[] args) {
        int n = 123456545;
        String shorturl = shortenUrl(n);
        System.out.println("Shortened URL "+shorturl);

        long uniqueID = UrlToID(shorturl);
        System.out.println("Unique ID = "+uniqueID);
    }

    static String shortenUrl(int id){
        char[] map = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        StringBuilder sb = new StringBuilder();
        while (id > 0){
            sb.append(map[id%62]);
            id /= 62;
        }

        sb.reverse();
        return sb.toString();
    }

    static long UrlToID(String shortUrl){

        int id = 0;
        for (int i = 0; i < shortUrl.length(); i++) {
            char c = shortUrl.charAt(i);
            if('a' <= c && 'z' >= c){
                id = id*62 + c-'a';
            }else if('A' <= c && 'Z' >= c){
                id = id*62 + c-'A' + 26;
            }else if('0' <= c && '9' >= c){
                id = id*62 + c-'0' + 52;
            }
        }

        return id;
    }
}
