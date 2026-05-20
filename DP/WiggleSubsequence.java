package DP;

public class WiggleSubsequence {

    public static int wiggleMaxLength(int[] nums) {

        int n = nums.length;

        if (n < 2)
            return n;

        int up = 1;
        int down = 1;

        for (int i = 1; i < n; i++) {

            // Increasing difference
            if (nums[i] > nums[i - 1]) {
                up = down + 1;
            }

            // Decreasing difference
            else if (nums[i] < nums[i - 1]) {
                down = up + 1;
            }
        }

        return Math.max(up, down);
    }

    public static void main(String[] args) {

        int[] nums = {1, 7, 4, 9, 2, 5};

        int result = wiggleMaxLength(nums);

        System.out.println("Maximum Wiggle Subsequence Length: " + result);
    }
}
