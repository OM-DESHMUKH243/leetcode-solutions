package DP;

import java.util.*;

public class MinCostConnectTwoGroups {

    public static int connectTwoGroups(List<List<Integer>> cost){

        int m = cost.size();
        int n = cost.get(0).size();

        int[][] dp = new int[m + 1][1 << n];

        for(int[] row : dp){
            Arrays.fill(row, Integer.MAX_VALUE / 2);
        }

        dp[0][0] = 0;

        for(int i = 0; i < m; i++){

            for(int mask = 0; mask < (1 << n); mask++){

                for(int j = 0; j < n; j++){

                    int newMask = mask | (1 << j);

                    dp[i + 1][newMask] = Math.min(
                        dp[i + 1][newMask],
                        dp[i][mask] + cost.get(i).get(j)
                    );
                }
            }
        }

        int[] minCost = new int[n];
        Arrays.fill(minCost, Integer.MAX_VALUE);

        for(int j = 0; j < n; j++){
            for(int i = 0; i < m; i++){
                minCost[j] = Math.min(minCost[j], cost.get(i).get(j));
            }
        }

        int result = Integer.MAX_VALUE;

        for(int mask = 0; mask < (1 << n); mask++){

            int extra = 0;

            for(int j = 0; j < n; j++){
                if((mask & (1 << j)) == 0){
                    extra += minCost[j];
                }
            }

            result = Math.min(result, dp[m][mask] + extra);
        }

        return result;
    }

    public static void main(String[] args){

        List<List<Integer>> cost = new ArrayList<>();

        cost.add(Arrays.asList(15, 96));
        cost.add(Arrays.asList(36, 2));

        int result = connectTwoGroups(cost);

        System.out.println("Minimum Cost: " + result);
    }
}
