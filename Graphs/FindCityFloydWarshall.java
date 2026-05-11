package Graphs;

import java.util.*;

public class FindCityFloydWarshall {

    public static int findTheCity(int n,
                                  int[][] edges,
                                  int distanceThreshold){

        int INF = (int)1e9;

        int[][] dist = new int[n][n];

        // Initialize matrix
        for(int i = 0; i < n; i++){

            Arrays.fill(dist[i], INF);

            dist[i][i] = 0;
        }

        // Add edges
        for(int[] edge : edges){

            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            dist[u][v] = w;
            dist[v][u] = w;
        }

        // Floyd-Warshall
        for(int k = 0; k < n; k++){

            for(int i = 0; i < n; i++){

                for(int j = 0; j < n; j++){

                    if(dist[i][k] != INF &&
                       dist[k][j] != INF){

                        dist[i][j] = Math.min(

                            dist[i][j],

                            dist[i][k] + dist[k][j]
                        );
                    }
                }
            }
        }

        int minReachable = Integer.MAX_VALUE;

        int answer = -1;

        for(int city = 0; city < n; city++){

            int count = 0;

            for(int neighbor = 0;
                neighbor < n;
                neighbor++){

                if(dist[city][neighbor]
                   <= distanceThreshold){

                    count++;
                }
            }

            // Tie -> greater index
            if(count <= minReachable){

                minReachable = count;

                answer = city;
            }
        }

        return answer;
    }

    public static void main(String[] args){

        int n = 4;

        int[][] edges = {
            {0,1,3},
            {1,2,1},
            {1,3,4},
            {2,3,1}
        };

        int threshold = 4;

        int result =
            findTheCity(n, edges, threshold);

        System.out.println(
            "Answer City: " + result
        );
    }
}
