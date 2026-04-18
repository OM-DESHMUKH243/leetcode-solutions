package Graphs;

public class RemoveMaxEdges {

    static class DSU {
        int[] parent, rank;
        int components;

        DSU(int n){
            parent = new int[n + 1];
            rank = new int[n + 1];
            components = n;

            for(int i = 1; i <= n; i++){
                parent[i] = i;
            }
        }

        int find(int x){
            if(parent[x] != x){
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        boolean union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);

            if(rootX == rootY) return false;

            if(rank[rootX] < rank[rootY]){
                parent[rootX] = rootY;
            }
            else if(rank[rootX] > rank[rootY]){
                parent[rootY] = rootX;
            }
            else{
                parent[rootY] = rootX;
                rank[rootX]++;
            }

            components--;
            return true;
        }
    }

    public static int maxNumEdgesToRemove(int n, int[][] edges){

        DSU alice = new DSU(n);
        DSU bob = new DSU(n);

        int removed = 0;

        for(int[] e : edges){
            if(e[0] == 3){
                boolean a = alice.union(e[1], e[2]);
                boolean b = bob.union(e[1], e[2]);

                if(!a && !b){
                    removed++;
                }
            }
        }

        for(int[] e : edges){
            if(e[0] == 1){
                if(!alice.union(e[1], e[2])){
                    removed++;
                }
            }
        }

        for(int[] e : edges){
            if(e[0] == 2){
                if(!bob.union(e[1], e[2])){
                    removed++;
                }
            }
        }

        if(alice.components != 1 || bob.components != 1){
            return -1;
        }

        return removed;
    }

    public static void main(String[] args){

        int n = 4;

        int[][] edges = {
            {3,1,2},
            {3,2,3},
            {1,1,3},
            {1,2,4},
            {2,3,4}
        };

        int result = maxNumEdgesToRemove(n, edges);

        System.out.println("Max removable edges: " + result);
    }
}
