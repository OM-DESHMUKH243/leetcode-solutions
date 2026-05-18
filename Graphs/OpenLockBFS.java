package Graphs;

import java.util.*;

public class OpenLockBFS {

    public static int openLock(
            String[] deadends,
            String target){

        Set<String> dead =
                new HashSet<>(
                    Arrays.asList(deadends)
                );

        if(dead.contains("0000")){
            return -1;
        }

        Queue<String> queue =
                new LinkedList<>();

        Set<String> visited =
                new HashSet<>();

        queue.offer("0000");

        visited.add("0000");

        int steps = 0;

        while(!queue.isEmpty()){

            int size = queue.size();

            for(int i = 0; i < size; i++){

                String curr = queue.poll();

                if(curr.equals(target)){
                    return steps;
                }

                for(String next :
                        getNeighbors(curr)){

                    if(!dead.contains(next)
                       && !visited.contains(next)){

                        visited.add(next);

                        queue.offer(next);
                    }
                }
            }

            steps++;
        }

        return -1;
    }

    private static List<String> getNeighbors(
            String s){

        List<String> neighbors =
                new ArrayList<>();

        char[] arr = s.toCharArray();

        for(int i = 0; i < 4; i++){

            char original = arr[i];

            // Forward
            arr[i] =
                original == '9'
                ? '0'
                : (char)(original + 1);

            neighbors.add(
                new String(arr)
            );

            // Backward
            arr[i] =
                original == '0'
                ? '9'
                : (char)(original - 1);

            neighbors.add(
                new String(arr)
            );

            arr[i] = original;
        }

        return neighbors;
    }

    public static void main(String[] args){

        String[] deadends = {
            "0201",
            "0101",
            "0102",
            "1212",
            "2002"
        };

        String target = "0202";

        int result =
                openLock(deadends, target);

        System.out.println(
            "Minimum Moves: " + result
        );
    }
}
