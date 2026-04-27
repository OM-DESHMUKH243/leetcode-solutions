package Graphs;

import java.util.*;

public class ShortestBridge {

    static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

    public static int shortestBridge(int[][] grid) {

        int n = grid.length;
        Queue<int[]> queue = new LinkedList<>();
        boolean found = false;

        // Find first island
        for(int i = 0; i < n; i++) {

            if(found) break;

            for(int j = 0; j < n; j++) {

                if(grid[i][j] == 1) {
                    dfs(grid, i, j, queue);
                    found = true;
                    break;
                }
            }
        }

        // BFS expansion
        int level = 0;

        while(!queue.isEmpty()) {

            int size = queue.size();

            for(int i = 0; i < size; i++) {

                int[] curr = queue.poll();

                for(int[] d : dirs) {

                    int nr = curr[0] + d[0];
                    int nc = curr[1] + d[1];

                    if(nr < 0 || nc < 0 || nr >= n || nc >= n)
                        continue;

                    if(grid[nr][nc] == 1)
                        return level;

                    if(grid[nr][nc] == 0) {

                        grid[nr][nc] = -1;
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }

            level++;
        }

        return -1;
    }

    static void dfs(int[][] grid, int r, int c, Queue<int[]> queue) {

        int n = grid.length;

        if(r < 0 || c < 0 || r >= n || c >= n || grid[r][c] != 1)
            return;

        grid[r][c] = -1;
        queue.offer(new int[]{r, c});

        for(int[] d : dirs) {
            dfs(grid, r + d[0], c + d[1], queue);
        }
    }

    public static void main(String[] args) {

        int[][] grid = {
            {0,1,0},
            {0,0,0},
            {0,0,1}
        };

        int result = shortestBridge(grid);

        System.out.println("Minimum flips required: " + result);
    }
}
