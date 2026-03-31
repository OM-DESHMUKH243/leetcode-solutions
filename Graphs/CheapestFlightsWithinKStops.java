package Graphs;


import java.util.*;

public class CheapestFlightsWithinKStops {

    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k){

        Map<Integer, List<int[]>> adj = new HashMap<>();

        for(int[] f : flights){
            adj.computeIfAbsent(f[0], x -> new ArrayList<>())
               .add(new int[]{f[1], f[2]});
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{src, 0, 0});

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        while(!queue.isEmpty()){

            int[] curr = queue.poll();

            int node = curr[0];
            int cost = curr[1];
            int stops = curr[2];

            if(stops > k) continue;

            if(adj.containsKey(node)){

                for(int[] edge : adj.get(node)){

                    int next = edge[0];
                    int price = edge[1];

                    int newCost = cost + price;

                    if(newCost < dist[next]){

                        dist[next] = newCost;
                        queue.add(new int[]{next, newCost, stops + 1});
                    }
                }
            }
        }

        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }

    public static void main(String[] args){

        int[][] flights = {
            {0,1,100},
            {1,2,100},
            {2,3,100},
            {0,2,500}
        };

        int n = 4, src = 0, dst = 3, k = 1;

        int result = findCheapestPrice(n, flights, src, dst, k);

        System.out.println("Cheapest price: " + result);
    }
}
