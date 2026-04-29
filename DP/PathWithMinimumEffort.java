package DP;

import java.util.*;

public class PathWithMinimumEffort {

    public static int minimumEffortPath(int[][] heights){

        int m = heights.length;
        int n = heights[0].length;

        int[][] effort = new int[m][n];

        for(int[] row : effort){
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        effort[0][0] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> a[0] - b[0]
        );

        pq.offer(new int[]{0, 0, 0});

        int[][] dirs = {
            {1,0},
            {-1,0},
            {0,1},
            {0,-1}
        };

        while(!pq.isEmpty()){

            int[] curr = pq.poll();

            int currEffort = curr[0];
            int row = curr[1];
            int col = curr[2];

            if(row == m - 1 && col == n - 1){
                return currEffort;
            }

            for(int[] d : dirs){

                int nr = row + d[0];
                int nc = col + d[1];

                if(nr < 0 || nc < 0 || nr >= m || nc >= n){
                    continue;
                }

                int edgeWeight = Math.abs(
                    heights[row][col] - heights[nr][nc]
                );

                int newEffort = Math.max(
                    currEffort,
                    edgeWeight
                );

                if(newEffort < effort[nr][nc]){

                    effort[nr][nc] = newEffort;

                    pq.offer(new int[]{
                        newEffort,
                        nr,
                        nc
                    });
                }
            }
        }

        return 0;
    }

    public static void main(String[] args){

        int[][] heights = {
            {1,2,2},
            {3,8,2},
            {5,3,5}
        };

        int result = minimumEffortPath(heights);

        System.out.println("Minimum Effort: " + result);
    }
}
