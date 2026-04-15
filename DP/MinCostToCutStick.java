package DP;

import java.util.*;

public class MinCostToCutStick {

    public static int minCost(int n, int[] cuts){

        int m = cuts.length;

        int[] arr = new int[m + 2];
        arr[0] = 0;
        arr[m + 1] = n;

        for(int i = 0; i < m; i++){
            arr[i + 1] = cuts[i];
        }

        Arrays.sort(arr);

        int[][] dp = new int[m + 2][m + 2];

        for(int len = 2; len < m + 2; len++){

            for(int left = 0; left + len < m + 2; left++){

                int right = left + len;
                dp[left][right] = Integer.MAX_VALUE;

                for(int k = left + 1; k < right; k++){

                    dp[left][right] = Math.min(
                        dp[left][right],
                        arr[right] - arr[left]
                        + dp[left][k]
                        + dp[k][right]
                    );
                }

                if(dp[left][right] == Integer.MAX_VALUE){
                    dp[left][right] = 0;
                }
            }
        }

        return dp[0][m + 1];
    }

    public static void main(String[] args){

        int n = 7;
        int[] cuts = {1,3,4,5};

        int result = minCost(n, cuts);

        System.out.println("Minimum Cost: " + result);
    }
}
