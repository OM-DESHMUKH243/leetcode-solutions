package DP;

public class TargetSum {

    public static int findTargetSumWays(int[] nums, int target){

        int total = 0;

        for(int n : nums){
            total += n;
        }

        if(Math.abs(target) > total){
            return 0;
        }

        if((target + total) % 2 != 0){
            return 0;
        }

        int subsetSum = (target + total) / 2;

        int[] dp = new int[subsetSum + 1];

        dp[0] = 1;

        for(int num : nums){

            for(int s = subsetSum; s >= num; s--){

                dp[s] += dp[s - num];

            }

        }

        return dp[subsetSum];
    }

    public static void main(String[] args){

        int[] nums = {1,1,1,1,1};
        int target = 3;

        int result = findTargetSumWays(nums, target);

        System.out.println("Number of ways: " + result);

    }
}
