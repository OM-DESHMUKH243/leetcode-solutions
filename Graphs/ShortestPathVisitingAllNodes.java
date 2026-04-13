package Graphs;

import java.util.*;

public class ShortestPathVisitingAllNodes {

    public static int shortestPathLength(int[][] graph){

        int n = graph.length;
        int allVisited = (1 << n) - 1;

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][1 << n];

        for(int i = 0; i < n; i++){

            int mask = 1 << i;

            queue.add(new int[]{i, mask});
            visited[i][mask] = true;
        }

        int steps = 0;

        while(!queue.isEmpty()){

            int size = queue.size();

            for(int i = 0; i < size; i++){

                int[] curr = queue.poll();

                int node = curr[0];
                int mask = curr[1];

                if(mask == allVisited){
                    return steps;
                }

                for(int next : graph[node]){

                    int nextMask = mask | (1 << next);

                    if(!visited[next][nextMask]){

                        visited[next][nextMask] = true;
                        queue.add(new int[]{next, nextMask});
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

        int result = shortestPathLength(graph);

        System.out.println("Shortest Path Length: " + result);
    }
}
