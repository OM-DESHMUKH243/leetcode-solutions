class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        int max = nums[0];
        int min = nums[0];
        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int curr = nums[i];

            int tempMax = Math.max(curr, Math.max(max * curr, min * curr));
            min = Math.min(curr, Math.min(max * curr, min * curr));
            max = tempMax;

            result = Math.max(result, max);
        }

        return result;
    }
}
