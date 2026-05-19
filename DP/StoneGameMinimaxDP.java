package DP;

public class StoneGameMinimaxDP {

    public static boolean stoneGame(int[] piles){

        int n = piles.length;

        int[][] dp = new int[n][n];

        // Base case
        for(int i = 0; i < n; i++){

            dp[i][i] = piles[i];
        }

        // Build intervals
        for(int len = 2; len <= n; len++){

            for(int left = 0;
                left + len - 1 < n;
                left++){

                int right =
                        left + len - 1;

                dp[left][right] = Math.max(

                    piles[left]
                    - dp[left + 1][right],

                    piles[right]
                    - dp[left][right - 1]
                );
            }
        }

        return dp[0][n - 1] > 0;
    }

    public static void main(String[] args){

        int[] piles = {5,3,4,5};

        boolean result =
                stoneGame(piles);

        System.out.println(
            "Alice Wins: " + result
        );
    }
}