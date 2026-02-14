// Day 44: Daily Temperatures
// Monotonic Stack Problem
// Time: O(n) | Space: O(n)

import java.util.Stack;

public class DailyTemperatures {

    public static int[] dailyTemperatures(int[] temperatures) {

        int n = temperatures.length;
        int[] result = new int[n];

        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < n; i++){

            while(!stack.isEmpty() &&
                  temperatures[i] > temperatures[stack.peek()]){

                int prevIndex = stack.pop();
                result[prevIndex] = i - prevIndex;
            }

            stack.push(i);
        }

        return result;
    }

    // main method for testing in VS Code
    public static void main(String[] args) {

        int[] temps = {73,74,75,71,69,72,76,73};
        int[] ans = dailyTemperatures(temps);

        System.out.print("Output: ");
        for(int x : ans){
            System.out.print(x + " ");
        }
    }
}
