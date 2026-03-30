package Graphs;

import java.util.*;

public class NetworkDelayTime {

    public static int networkDelayTime(int[][] times, int n, int k){

        Map<Integer, List<int[]>> adj = new HashMap<>();

        for(int[] t : times){
            adj.computeIfAbsent(t[0], x -> new ArrayList<>())
               .add(new int[]{t[1], t[2]});
        }

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
            (a, b) -> a[0] - b[0]
        );

        minHeap.add(new int[]{0, k});

        Set<Integer> visited = new HashSet<>();

        int totalTime = 0;

        while(!minHeap.isEmpty()){

            int[] curr = minHeap.poll();
            int time = curr[0];
            int node = curr[1];

            if(visited.contains(node)) continue;

            visited.add(node);
            totalTime = time;

            if(adj.containsKey(node)){

                for(int[] edge : adj.get(node)){

                    int next = edge[0];
                    int cost = edge[1];

                    if(!visited.contains(next)){
                        minHeap.add(new int[]{time + cost, next});
                    }
                }
            }
        }

        return visited.size() == n ? totalTime : -1;
    }

    public static void main(String[] args){

        int[][] times = {
            {2,1,1},
            {2,3,1},
            {3,4,1}
        };

        int n = 4;
        int k = 2;

        int result = networkDelayTime(times, n, k);

        System.out.println("Network delay time: " + result);
    }
}