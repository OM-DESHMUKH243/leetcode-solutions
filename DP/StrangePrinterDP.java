package DP;

public class StrangePrinterDP {

    public static int strangePrinter(String s) {

        int n = s.length();

        int[][] dp = new int[n][n];

        for (int i = n - 1; i >= 0; i--) {

            dp[i][i] = 1;

            for (int j = i + 1; j < n; j++) {

                dp[i][j] = dp[i + 1][j] + 1;

                for (int k = i + 1; k <= j; k++) {

                    if (s.charAt(i) == s.charAt(k)) {

                        int cost =
                                dp[i][k - 1];

                        if (k + 1 <= j) {

                            cost += dp[k + 1][j];
                        }

                        dp[i][j] =
                                Math.min(
                                        dp[i][j],
                                        cost
                                );
                    }
                }
            }
        }

        return dp[0][n - 1];
    }

    public static void main(String[] args) {

        String s = "aaabbb";

        System.out.println(
                "Minimum Turns = "
                + strangePrinter(s)
        );
    }
}