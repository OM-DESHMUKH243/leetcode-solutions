package DP;

import java.util.*;

public class MinimumArrowsBurstBalloons {

    public static int findMinArrowShots(int[][] points){

        if(points.length == 0) return 0;

        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));

        int arrowCount = 1;
        int currentEnd = points[0][1];

        for(int i = 1; i < points.length; i++){

            int start = points[i][0];
            int end = points[i][1];

            if(start <= currentEnd){
                continue;
            }
            else{
                arrowCount++;
                currentEnd = end;
            }

        }

        return arrowCount;
    }

    public static void main(String[] args){

        int[][] points = {
            {10,16},
            {2,8},
            {1,6},
            {7,12}
        };

        int result = findMinArrowShots(points);

        System.out.println("Minimum arrows needed: " + result);

    }
}
