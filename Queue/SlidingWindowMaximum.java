package Queue;

import java.util.*;

public class SlidingWindowMaximum {

    public static int[] maxSlidingWindow(int[] nums, int k){

        int n = nums.length;

        int[] result = new int[n - k + 1];

        Deque<Integer> deque = new LinkedList<>();

        int idx = 0;

        for(int i = 0; i < n; i++){

            // Remove expired indices
            while(!deque.isEmpty() &&
                  deque.peekFirst() <= i - k){

                deque.pollFirst();
            }

            // Remove smaller elements
            while(!deque.isEmpty() &&
                  nums[deque.peekLast()] < nums[i]){

                deque.pollLast();
            }

            // Add current index
            deque.offerLast(i);

            // Window formed
            if(i >= k - 1){

                result[idx++] =
                        nums[deque.peekFirst()];
            }
        }

        return result;
    }

    public static void main(String[] args){

        int[] nums = {1,3,-1,-3,5,3,6,7};

        int k = 3;

        int[] result = maxSlidingWindow(nums, k);

        System.out.println(
            Arrays.toString(result)
        );
    }
}
