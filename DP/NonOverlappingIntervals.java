package DP;

import java.util.*;

public class NonOverlappingIntervals {

    public static int eraseOverlapIntervals(int[][] intervals){

        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        int countRemoved = 0;

        int prevEnd = intervals[0][1];

        for(int i = 1; i < intervals.length; i++){

            int currStart = intervals[i][0];
            int currEnd = intervals[i][1];

            if(currStart < prevEnd){
                countRemoved++;
            }
            else{
                prevEnd = currEnd;
            }

        }

        return countRemoved;
    }

    public static void main(String[] args){

        int[][] intervals = {
            {1,2},
            {2,3},
            {3,4},
            {1,3}
        };

        int result = eraseOverlapIntervals(intervals);

        System.out.println("Minimum removals: " + result);

    }
}
