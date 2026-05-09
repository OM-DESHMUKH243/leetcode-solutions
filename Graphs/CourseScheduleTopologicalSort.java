package Graphs;

import java.util.*;

public class CourseScheduleTopologicalSort {

    public static int[] findOrder(int numCourses,
                                  int[][] prerequisites){

        List<Integer>[] graph =
                new ArrayList[numCourses];

        for(int i = 0; i < numCourses; i++){
            graph[i] = new ArrayList<>();
        }

        int[] indegree = new int[numCourses];

        // Build graph
        for(int[] pre : prerequisites){

            int course = pre[0];
            int prereq = pre[1];

            graph[prereq].add(course);

            indegree[course]++;
        }

        Queue<Integer> queue =
                new LinkedList<>();

        // Add indegree 0 nodes
        for(int i = 0; i < numCourses; i++){

            if(indegree[i] == 0){
                queue.offer(i);
            }
        }

        int[] order = new int[numCourses];

        int idx = 0;

        while(!queue.isEmpty()){

            int curr = queue.poll();

            order[idx++] = curr;

            for(int next : graph[curr]){

                indegree[next]--;

                if(indegree[next] == 0){
                    queue.offer(next);
                }
            }
        }

        // Cycle exists
        if(idx != numCourses){
            return new int[0];
        }

        return order;
    }

    public static void main(String[] args){

        int numCourses = 4;

        int[][] prerequisites = {
            {1,0},
            {2,0},
            {3,1},
            {3,2}
        };

        int[] result =
                findOrder(numCourses, prerequisites);

        System.out.println(
            Arrays.toString(result)
        );
    }
}
