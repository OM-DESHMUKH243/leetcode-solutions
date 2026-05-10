package Graphs;

import java.util.*;

public class NetworkDelayBellmanFord {

    public static int networkDelayTime(int[][] times,
                                       int n,
                                       int k){

        int[] dist = new int[n + 1];

        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[k] = 0;

        // Bellman-Ford Relaxation
        for(int i = 1; i <= n - 1; i++){

            boolean updated = false;

            for(int[] edge : times){

                int u = edge[0];
                int v = edge[1];
                int w = edge[2];

                if(dist[u] != Integer.MAX_VALUE &&
                   dist[u] + w < dist[v]){

                    dist[v] = dist[u] + w;

                    updated = true;
                }
            }

            // Optimization
            if(!updated){
                break;
            }
        }

        int maxTime = 0;

        for(int i = 1; i <= n; i++){

            if(dist[i] == Integer.MAX_VALUE){
                return -1;
            }

            maxTime = Math.max(maxTime, dist[i]);
        }

        return maxTime;
    }

    public static void main(String[] args){

        int[][] times = {
            {2,1,1},
            {2,3,1},
            {3,4,1}
        };

        int n = 4;
        int k = 2;

        int result =
            networkDelayTime(times, n, k);

        System.out.println(
            "Network Delay Time: " + result
        );
    }
}
