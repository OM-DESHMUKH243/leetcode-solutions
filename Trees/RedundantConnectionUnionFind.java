package Trees;

import java.util.*;

public class RedundantConnectionUnionFind {

    static int[] parent;

    public static int[] findRedundantConnection(
            int[][] edges){

        int n = edges.length;

        parent = new int[n + 1];

        // Initialize DSU
        for(int i = 1; i <= n; i++){

            parent[i] = i;
        }

        for(int[] edge : edges){

            int u = edge[0];
            int v = edge[1];

            int rootU = find(u);
            int rootV = find(v);

            // Cycle found
            if(rootU == rootV){

                return edge;
            }

            union(rootU, rootV);
        }

        return new int[0];
    }

    private static int find(int x){

        if(parent[x] != x){

            parent[x] =
                    find(parent[x]);
        }

        return parent[x];
    }

    private static void union(int x,
                              int y){

        parent[x] = y;
    }

    public static void main(String[] args){

        int[][] edges = {
            {1,2},
            {1,3},
            {2,3}
        };

        int[] result =
                findRedundantConnection(edges);

        System.out.println(
            Arrays.toString(result)
        );
    }
}