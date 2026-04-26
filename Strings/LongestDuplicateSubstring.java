package Strings;

import java.util.*;

public class LongestDuplicateSubstring {

    private static final long MOD = 1000000007L;
    private static final long BASE = 256;

    public static String longestDupSubstring(String s) {

        int n = s.length();

        int left = 1;
        int right = n - 1;

        int start = -1;
        int maxLen = 0;

        while(left <= right){

            int mid = left + (right - left) / 2;

            int idx = search(s, mid);

            if(idx != -1){

                start = idx;
                maxLen = mid;

                left = mid + 1;

            } else {

                right = mid - 1;
            }
        }

        return start == -1
                ? ""
                : s.substring(start, start + maxLen);
    }

    private static int search(String s, int len){

        int n = s.length();

        long hash = 0;
        long power = 1;

        // Initial hash
        for(int i = 0; i < len; i++){

            hash = (hash * BASE + s.charAt(i)) % MOD;

            if(i < len - 1){
                power = (power * BASE) % MOD;
            }
        }

        Map<Long, List<Integer>> map = new HashMap<>();

        map.putIfAbsent(hash, new ArrayList<>());
        map.get(hash).add(0);

        // Sliding window
        for(int i = len; i < n; i++){

            // Remove leftmost character
            hash = (hash
                    - s.charAt(i - len) * power % MOD
                    + MOD) % MOD;

            // Add new character
            hash = (hash * BASE + s.charAt(i)) % MOD;

            int start = i - len + 1;

            // Collision check
            if(map.containsKey(hash)){

                String current = s.substring(start, start + len);

                for(int prev : map.get(hash)){

                    String old = s.substring(prev, prev + len);

                    if(old.equals(current)){
                        return start;
                    }
                }
            }

            map.putIfAbsent(hash, new ArrayList<>());
            map.get(hash).add(start);
        }

        return -1;
    }

    public static void main(String[] args) {

        String s = "banana";

        String result = longestDupSubstring(s);

        System.out.println("Longest Duplicate Substring: " + result);
    }
}
