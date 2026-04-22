package Graphs;

import java.util.*;

public class ShortestPathObstacleElimination {

    public static int shortestPath(int[][] grid, int k){

        int m = grid.length;
        int n = grid[0].length;

        if(m == 1 && n == 1) return 0;

        boolean[][][] visited = new boolean[m][n][k + 1];

        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{0, 0, k});

        visited[0][0][k] = true;

        int steps = 0;

        int[][] dirs = {
            {1,0},
            {-1,0},
            {0,1},
            {0,-1}
        };

        while(!queue.isEmpty()){

            int size = queue.size();

            for(int i = 0; i < size; i++){

                int[] curr = queue.poll();

                int row = curr[0];
                int col = curr[1];
                int remain = curr[2];

                if(row == m - 1 && col == n - 1){
                    return steps;
                }

                for(int[] d : dirs){

                    int nr = row + d[0];
                    int nc = col + d[1];

                    if(nr < 0 || nc < 0 || nr >= m || nc >= n){
                        continue;
                    }

                    int nextRemain = remain - grid[nr][nc];

                    if(nextRemain >= 0 &&
                       !visited[nr][nc][nextRemain]){

                        visited[nr][nc][nextRemain] = true;

                        queue.offer(new int[]{
                            nr,
                            nc,
                            nextRemain
                        });
                    }
                }
            }

            steps++;
        }

        return -1;
    }

    public static void main(String[] args){

        int[][] grid = {
            {0,0,0},
            {1,1,0},
            {0,0,0},
            {0,1,1},
            {0,0,0}
        };

        int k = 1;

        int result = shortestPath(grid, k);

        System.out.println("Shortest Path: " + result);
    }
}
