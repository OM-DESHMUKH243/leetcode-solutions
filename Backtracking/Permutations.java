package Backtracking;

import java.util.*;

public class Permutations {

    public static List<List<Integer>> permute(int[] nums){

        List<List<Integer>> result = new ArrayList<>();

        backtrack(nums, 0, result);

        return result;
    }

    private static void backtrack(int[] nums, int start, List<List<Integer>> result){

        if(start == nums.length){

            List<Integer> list = new ArrayList<>();

            for(int num : nums){
                list.add(num);
            }

            result.add(list);
            return;
        }

        for(int i = start; i < nums.length; i++){

            swap(nums, start, i);

            backtrack(nums, start + 1, result);

            swap(nums, start, i);
        }
    }

    private static void swap(int[] arr, int i, int j){

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args){

        int[] nums = {1,2,3};

        List<List<Integer>> result = permute(nums);

        System.out.println("Permutations: " + result);
    }
}
