package Trees;

import java.util.*;

public class MyCalendarTreeMap {

    private TreeMap<Integer, Integer> calendar;

    public MyCalendarTreeMap() {

        calendar = new TreeMap<>();
    }

    public boolean book(int start, int end) {

        Integer prev =
                calendar.floorKey(start);

        Integer next =
                calendar.ceilingKey(start);

        // Previous overlap
        if(prev != null &&
           calendar.get(prev) > start){

            return false;
        }

        // Next overlap
        if(next != null &&
           next < end){

            return false;
        }

        calendar.put(start, end);

        return true;
    }

    public static void main(String[] args){

        MyCalendarTreeMap cal =
                new MyCalendarTreeMap();

        System.out.println(
            cal.book(10, 20)
        );

        System.out.println(
            cal.book(15, 25)
        );

        System.out.println(
            cal.book(20, 30)
        );
    }
}
