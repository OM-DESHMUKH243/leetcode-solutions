package Backtracking;

import java.util.*;

public class CombinationSum {

    public static List<List<Integer>> combinationSum(int[] candidates, int target){

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

        solve(candidates, target, 0, current, result);

        return result;
    }

    private static void solve(int[] nums, int target, int index,
                              List<Integer> curr, List<List<Integer>> res){

        if(target == 0){
            res.add(new ArrayList<>(curr));
            return;
        }

        if(target < 0){
            return;
        }

        for(int i = index; i < nums.length; i++){

            curr.add(nums[i]);

            solve(nums, target - nums[i], i, curr, res);

            curr.remove(curr.size() - 1);
        }
    }

    public static void main(String[] args){

        int[] candidates = {2,3,6,7};
        int target = 7;

        List<List<Integer>> ans = combinationSum(candidates, target);

        System.out.println("Combinations: " + ans);
    }
}
