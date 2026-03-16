package DP;

public class PartitionEqualSubsetSum {

    public static boolean canPartition(int[] nums){

        int total = 0;

        for(int num : nums){
            total += num;
        }

        if(total % 2 != 0){
            return false;
        }

        int target = total / 2;

        boolean[] dp = new boolean[target + 1];

        dp[0] = true;

        for(int num : nums){

            for(int j = target; j >= num; j--){

                if(dp[j - num]){
                    dp[j] = true;
                }

            }

        }

        return dp[target];
    }

    public static void main(String[] args){

        int[] nums = {1,5,11,5};

        boolean result = canPartition(nums);

        System.out.println("Can partition: " + result);

    }
}
