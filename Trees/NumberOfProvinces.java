package Trees;

public class NumberOfProvinces {

    static int[] parent;
    static int[] rank;

    public static int findCircleNum(int[][] isConnected){

        int n = isConnected.length;

        parent = new int[n];
        rank = new int[n];

        for(int i = 0; i < n; i++){
            parent[i] = i;
        }

        for(int i = 0; i < n; i++){

            for(int j = i + 1; j < n; j++){

                if(isConnected[i][j] == 1){
                    union(i, j);
                }
            }
        }

        int provinces = 0;

        for(int i = 0; i < n; i++){
            if(parent[i] == i){
                provinces++;
            }
        }

        return provinces;
    }

    private static int find(int x){

        if(parent[x] != x){
            parent[x] = find(parent[x]);
        }

        return parent[x];
    }

    private static void union(int x, int y){

        int rootX = find(x);
        int rootY = find(y);

        if(rootX == rootY) return;

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
    }

    public static void main(String[] args){

        int[][] isConnected = {
            {1,1,0},
            {1,1,0},
            {0,0,1}
        };

        int result = findCircleNum(isConnected);

        System.out.println("Number of provinces: " + result);
    }
}
