package DP;
public class ClimbingStairs {

    public static int climbStairs(int n) {

        if (n <= 2)
            return n;

        int a = 1;
        int b = 2;

        for (int i = 3; i <= n; i++) {

            int temp = a + b;
            a = b;
            b = temp;
        }

        return b;
    }

    public static void main(String[] args) {

        int n = 5;

        int result = climbStairs(n);

        System.out.println("Number of ways: " + result);
    }
}
