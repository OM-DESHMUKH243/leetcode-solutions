package DP;

public class DistinctSubsequences {

    public static int numDistinct(String s, String t){

        int n = s.length();
        int m = t.length();

        int[][] dp = new int[n + 1][m + 1];

        for(int i = 0; i <= n; i++){
            dp[i][0] = 1;
        }

        for(int i = 1; i <= n; i++){

            char sc = s.charAt(i - 1);

            for(int j = 1; j <= m; j++){

                char tc = t.charAt(j - 1);

                if(sc == tc){
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }
                else{
                    dp[i][j] = dp[i - 1][j];
                }

            }

        }

        return dp[n][m];
    }

    public static void main(String[] args){

        String s = "rabbbit";
        String t = "rabbit";

        int result = numDistinct(s, t);

        System.out.println("Number of subsequences: " + result);

    }
}
