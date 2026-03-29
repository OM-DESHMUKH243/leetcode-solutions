package Heap;

public class TaskScheduler {

    public static int leastInterval(char[] tasks, int n){

        int[] count = new int[26];

        for(char ch : tasks){
            count[ch - 'A']++;
        }

        int maxFreq = 0;

        for(int c : count){
            maxFreq = Math.max(maxFreq, c);
        }

        int maxCount = 0;

        for(int c : count){
            if(c == maxFreq){
                maxCount++;
            }
        }

        int result = (maxFreq - 1) * (n + 1) + maxCount;

        return Math.max(tasks.length, result);
    }

    public static void main(String[] args){

        char[] tasks = {'A','A','A','B','B','B'};
        int n = 2;

        int ans = leastInterval(tasks, n);

        System.out.println("Minimum time required: " + ans);

    }
}
