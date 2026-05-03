package DP;

import java.util.*;

public class DifferentHatsWays {

    static final int MOD = 1000000007;

    public static int numberWays(List<List<Integer>> hats) {

        int n = hats.size();

        List<Integer>[] hatToPeople = new ArrayList[41];

        for (int i = 0; i <= 40; i++) {
            hatToPeople[i] = new ArrayList<>();
        }

        for (int person = 0; person < n; person++) {

            for (int hat : hats.get(person)) {
                hatToPeople[hat].add(person);
            }
        }

        int totalMask = (1 << n) - 1;

        Integer[][] dp = new Integer[41][1 << n];

        return dfs(1, 0, totalMask, hatToPeople, dp);
    }

    private static int dfs(int hat,
                           int mask,
                           int totalMask,
                           List<Integer>[] hatToPeople,
                           Integer[][] dp) {

        if (mask == totalMask) {
            return 1;
        }

        if (hat > 40) {
            return 0;
        }

        if (dp[hat][mask] != null) {
            return dp[hat][mask];
        }

        long ways = dfs(hat + 1, mask, totalMask, hatToPeople, dp);

        for (int person : hatToPeople[hat]) {

            if ((mask & (1 << person)) == 0) {

                ways += dfs(
                        hat + 1,
                        mask | (1 << person),
                        totalMask,
                        hatToPeople,
                        dp
                );

                ways %= MOD;
            }
        }

        return dp[hat][mask] = (int) ways;
    }

    public static void main(String[] args) {

        List<List<Integer>> hats = new ArrayList<>();

        hats.add(Arrays.asList(3, 4));
        hats.add(Arrays.asList(4, 5));
        hats.add(Arrays.asList(5));

        int result = numberWays(hats);

        System.out.println("Number of ways: " + result);
    }
}
