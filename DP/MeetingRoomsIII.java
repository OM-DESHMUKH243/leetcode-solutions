package DP;

import java.util.*;

public class MeetingRoomsIII {

    public static int mostBooked(int n, int[][] meetings){

        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);

        PriorityQueue<Integer> freeRooms = new PriorityQueue<>();
        for(int i = 0; i < n; i++) freeRooms.add(i);

        PriorityQueue<long[]> busyRooms = new PriorityQueue<>(
            (a, b) -> a[0] == b[0] ? (int)(a[1] - b[1]) : Long.compare(a[0], b[0])
        );

        int[] count = new int[n];

        for(int[] meeting : meetings){

            int start = meeting[0];
            int end = meeting[1];

            while(!busyRooms.isEmpty() && busyRooms.peek()[0] <= start){
                freeRooms.add((int)busyRooms.poll()[1]);
            }

            if(!freeRooms.isEmpty()){

                int room = freeRooms.poll();
                busyRooms.add(new long[]{end, room});
                count[room]++;

            } else {

                long[] earliest = busyRooms.poll();
                long newEnd = earliest[0] + (end - start);

                busyRooms.add(new long[]{newEnd, (int)earliest[1]});
                count[(int)earliest[1]]++;
            }
        }

        int maxRoom = 0;

        for(int i = 1; i < n; i++){
            if(count[i] > count[maxRoom]){
                maxRoom = i;
            }
        }

        return maxRoom;
    }

    public static void main(String[] args){

        int n = 2;

        int[][] meetings = {
            {0,10},
            {1,5},
            {2,7},
            {3,4}
        };

        int result = mostBooked(n, meetings);

        System.out.println("Most used room: " + result);
    }
}
