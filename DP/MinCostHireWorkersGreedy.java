package DP;

import java.util.*;

public class MinCostHireWorkersGreedy {

    public static double mincostToHireWorkers(int[] quality,
                                              int[] wage,
                                              int k){

        int n = quality.length;

        double[][] workers = new double[n][2];

        for(int i = 0; i < n; i++){

            workers[i][0] =
                (double) wage[i] / quality[i];

            workers[i][1] = quality[i];
        }

        Arrays.sort(workers,
            (a, b) -> Double.compare(a[0], b[0])
        );

        PriorityQueue<Integer> maxHeap =
            new PriorityQueue<>(Collections.reverseOrder());

        int totalQuality = 0;

        double result = Double.MAX_VALUE;

        for(double[] worker : workers){

            int q = (int) worker[1];

            totalQuality += q;

            maxHeap.offer(q);

            if(maxHeap.size() > k){

                totalQuality -= maxHeap.poll();
            }

            if(maxHeap.size() == k){

                double cost =
                    totalQuality * worker[0];

                result = Math.min(result, cost);
            }
        }

        return result;
    }

    public static void main(String[] args){

        int[] quality = {10,20,5};
        int[] wage = {70,50,30};
        int k = 2;

        double result =
            mincostToHireWorkers(quality, wage, k);

        System.out.println(
            "Minimum Cost: " + result
        );
    }
}
