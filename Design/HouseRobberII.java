package Design;

public class HouseRobberII {

    public static int rob(int[] nums) {

        int n = nums.length;

        if(n == 1) return nums[0];

        return Math.max(robRange(nums, 0, n-2), robRange(nums, 1, n-1));
    }

    public static int robRange(int[] nums, int start, int end){

        int prev1 = 0;
        int prev2 = 0;

        for(int i = start; i <= end; i++){

            int pick = nums[i] + prev2;
            int skip = prev1;

            int curr = Math.max(pick, skip);

            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }

    public static void main(String[] args) {

        int[] nums = {2,3,2};

        int result = rob(nums);

        System.out.println("Maximum money robbed: " + result);
    }
}