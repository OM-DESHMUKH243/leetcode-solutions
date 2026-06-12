package Strings;

public class MinimumWindowSubstring2 {

    public static String minWindow(
            String s,
            String t) {

        if(s.length() == 0 ||
           t.length() == 0) {

            return "";
        }

        int[] freq =
                new int[128];

        for(char c : t.toCharArray()) {

            freq[c]++;
        }

        int left = 0;
        int right = 0;

        int count = t.length();

        int minLen =
                Integer.MAX_VALUE;

        int start = 0;

        while(right < s.length()) {

            char c =
                    s.charAt(right);

            if(freq[c] > 0) {

                count--;
            }

            freq[c]--;

            right++;

            while(count == 0) {

                if(right - left
                   < minLen) {

                    minLen =
                        right - left;

                    start = left;
                }

                char leftChar =
                        s.charAt(left);

                freq[leftChar]++;

                if(freq[leftChar] > 0) {

                    count++;
                }

                left++;
            }
        }

        return minLen ==
               Integer.MAX_VALUE
               ? ""
               : s.substring(
                        start,
                        start + minLen
                 );
    }

    public static void main(
            String[] args) {

        String s =
                "ADOBECODEBANC";

        String t =
                "ABC";

        System.out.println(
                "Minimum Window = "
                + minWindow(s, t)
        );
    }
}
