import java.util.HashMap;
import java.util.Arrays;

class TwoSum {

    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int remaining = target - nums[i];

            if (map.containsKey(remaining)) {
                return new int[] { map.get(remaining), i };
            }

            map.put(nums[i], i);
        }

        return new int[] {};
    }

    // MAIN METHOD (for local testing only)
    public static void main(String[] args) {
        TwoSum obj = new TwoSum();

        int[] nums = {2, 7, 11, 15};
        int target = 9;

        int[] result = obj.twoSum(nums, target);

        System.out.println(Arrays.toString(result));
    }
}
