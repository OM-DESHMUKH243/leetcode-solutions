package Graphs;

import java.util.*;

public class ShortestPathAllNodesBitmask {

    public static int shortestPathLength(int[][] graph){

        int n = graph.length;

        int finalMask = (1 << n) - 1;

        Queue<int[]> queue = new LinkedList<>();

        boolean[][] visited =
                new boolean[n][1 << n];

        // Multi-source BFS
        for(int i = 0; i < n; i++){

            int mask = (1 << i);

            queue.offer(new int[]{i, mask});

            visited[i][mask] = true;
        }

        int steps = 0;

        while(!queue.isEmpty()){

            int size = queue.size();

            for(int i = 0; i < size; i++){

                int[] curr = queue.poll();

                int node = curr[0];
                int mask = curr[1];

                if(mask == finalMask){
                    return steps;
                }

                for(int neighbor : graph[node]){

                    int nextMask =
                            mask | (1 << neighbor);

                    if(!visited[neighbor][nextMask]){

                        visited[neighbor][nextMask] = true;

                        queue.offer(
                            new int[]{neighbor, nextMask}
                        );
                    }
                }
            }

            steps++;
        }

        return -1;
    }

    public static void main(String[] args){

        int[][] graph = {
            {1,2,3},
            {0},
            {0},
            {0}
        };

        int result =
                shortestPathLength(graph);

        System.out.println(
            "Shortest Path Length: " + result
        );
    }
}
