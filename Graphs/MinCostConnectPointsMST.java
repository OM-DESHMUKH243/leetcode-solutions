package Graphs;

import java.util.*;

public class MinCostConnectPointsMST {

    public static int minCostConnectPoints(int[][] points){

        int n = points.length;

        boolean[] visited = new boolean[n];

        PriorityQueue<int[]> pq =
            new PriorityQueue<>(
                (a, b) -> a[0] - b[0]
            );

        pq.offer(new int[]{0, 0});

        int totalCost = 0;

        int edgesUsed = 0;

        while(edgesUsed < n){

            int[] curr = pq.poll();

            int cost = curr[0];
            int node = curr[1];

            if(visited[node]){
                continue;
            }

            visited[node] = true;

            totalCost += cost;

            edgesUsed++;

            for(int next = 0; next < n; next++){

                if(!visited[next]){

                    int dist =
                        Math.abs(points[node][0]
                               - points[next][0])

                      + Math.abs(points[node][1]
                               - points[next][1]);

                    pq.offer(
                        new int[]{dist, next}
                    );
                }
            }
        }

        return totalCost;
    }

    public static void main(String[] args){

        int[][] points = {
            {0,0},
            {2,2},
            {3,10},
            {5,2},
            {7,0}
        };

        int result =
            minCostConnectPoints(points);

        System.out.println(
            "Minimum Cost: " + result
        );
    }
}
