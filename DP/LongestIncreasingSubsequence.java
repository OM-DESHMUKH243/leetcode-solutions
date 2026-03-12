package DP;

import java.util.*;

public class LongestIncreasingSubsequence {

    public static int lengthOfLIS(int[] nums) {

        int n = nums.length;
        int[] dp = new int[n];

        Arrays.fill(dp, 1);

        int max = 1;

        for(int i = 1; i < n; i++){

            for(int j = 0; j < i; j++){

                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }

            }

            max = Math.max(max, dp[i]);
        }

        return max;
    }

    public static void main(String[] args) {

        int[] nums = {10,9,2,5,3,7,101,18};

        int result = lengthOfLIS(nums);

        System.out.println("Length of LIS: " + result);
    }
}
