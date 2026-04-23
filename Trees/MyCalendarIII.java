package Trees;

import java.util.*;

public class MyCalendarIII {

    static class MyCalendarThree {

        TreeMap<Integer, Integer> map;

        public MyCalendarThree(){
            map = new TreeMap<>();
        }

        public int book(int start, int end){

            map.put(start, map.getOrDefault(start, 0) + 1);

            map.put(end, map.getOrDefault(end, 0) - 1);

            int active = 0;
            int maxOverlap = 0;

            for(int val : map.values()){

                active += val;

                maxOverlap = Math.max(maxOverlap, active);
            }

            return maxOverlap;
        }
    }

    public static void main(String[] args){

        MyCalendarThree cal = new MyCalendarThree();

        System.out.println(cal.book(10,20)); // 1
        System.out.println(cal.book(50,60)); // 1
        System.out.println(cal.book(10,40)); // 2
        System.out.println(cal.book(5,15));  // 3
        System.out.println(cal.book(5,10));  // 3
        System.out.println(cal.book(25,55)); // 3
    }
}
