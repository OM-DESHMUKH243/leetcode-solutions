package DP;

import java.util.*;

public class MinimumEffortPathDijkstra {

    public static int minimumEffortPath(
            int[][] heights){

        int rows = heights.length;
        int cols = heights[0].length;

        int[][] effort =
                new int[rows][cols];

        for(int[] row : effort){

            Arrays.fill(row, Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> pq =
                new PriorityQueue<>(
                    (a, b) -> a[0] - b[0]
                );

        // {effort, row, col}
        pq.offer(new int[]{0, 0, 0});

        effort[0][0] = 0;

        int[][] directions = {
            {1,0},
            {-1,0},
            {0,1},
            {0,-1}
        };

        while(!pq.isEmpty()){

            int[] curr = pq.poll();

            int currEffort = curr[0];
            int r = curr[1];
            int c = curr[2];

            // Destination reached
            if(r == rows - 1 &&
               c == cols - 1){

                return currEffort;
            }

            for(int[] dir : directions){

                int nr = r + dir[0];
                int nc = c + dir[1];

                if(nr >= 0 && nr < rows
                   && nc >= 0 && nc < cols){

                    int edgeCost =
                        Math.abs(
                            heights[r][c]
                          - heights[nr][nc]
                        );

                    int newEffort =
                        Math.max(
                            currEffort,
                            edgeCost
                        );

                    if(newEffort
                       < effort[nr][nc]){

                        effort[nr][nc] =
                                newEffort;

                        pq.offer(
                            new int[]{
                                newEffort,
                                nr,
                                nc
                            }
                        );
                    }
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

        int result =
                minimumEffortPath(heights);

        System.out.println(
            "Minimum Effort Path: "
            + result
        );
    }
}
