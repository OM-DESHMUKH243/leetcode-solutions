public class ShortestPalindromeKMP {

    public static String shortestPalindrome(
            String s) {

        String reversed =
                new StringBuilder(s)
                        .reverse()
                        .toString();

        String combined =
                s + "#" + reversed;

        int[] lps =
                buildLPS(combined);

        int palindromeLength =
                lps[combined.length() - 1];

        String remaining =
                s.substring(palindromeLength);

        String prefix =
                new StringBuilder(remaining)
                        .reverse()
                        .toString();

        return prefix + s;
    }

    private static int[] buildLPS(
            String str) {

        int n = str.length();

        int[] lps = new int[n];

        int len = 0;
        int i = 1;

        while(i < n) {

            if(str.charAt(i)
                    == str.charAt(len)) {

                len++;

                lps[i] = len;

                i++;

            } else {

                if(len != 0) {

                    len =
                        lps[len - 1];

                } else {

                    lps[i] = 0;

                    i++;
                }
            }
        }

        return lps;
    }

    public static void main(String[] args) {

        String s = "abcd";

        String result =
                shortestPalindrome(s);

        System.out.println(
                "Shortest Palindrome = "
                + result
        );
    }
}