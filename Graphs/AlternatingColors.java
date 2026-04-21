package Graphs;

import java.util.*;

public class AlternatingColors {

    public static int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges){

        @SuppressWarnings("unchecked")
        List<int[]>[] graph = new ArrayList[n];

        for(int i = 0; i < n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int[] e : redEdges){
            graph[e[0]].add(new int[]{e[1], 0});
        }

        for(int[] e : blueEdges){
            graph[e[0]].add(new int[]{e[1], 1});
        }

        int[] result = new int[n];
        Arrays.fill(result, -1);

        boolean[][] visited = new boolean[n][2];

        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{0, 0});
        queue.add(new int[]{0, 1});

        visited[0][0] = true;
        visited[0][1] = true;

        int steps = 0;

        while(!queue.isEmpty()){

            int size = queue.size();

            for(int i = 0; i < size; i++){

                int[] curr = queue.poll();

                int node = curr[0];
                int lastColor = curr[1];

                if(result[node] == -1){
                    result[node] = steps;
                }

                for(int[] nei : graph[node]){

                    int next = nei[0];
                    int color = nei[1];

                    if(color != lastColor && !visited[next][color]){

                        visited[next][color] = true;
                        queue.add(new int[]{next, color});
                    }
                }
            }

            steps++;
        }

        return result;
    }

    public static void main(String[] args){

        int n = 3;

        int[][] redEdges = {{0,1},{1,2}};
        int[][] blueEdges = {};

        int[] result = shortestAlternatingPaths(n, redEdges, blueEdges);

        System.out.println("Result: " + Arrays.toString(result));
    }
}
