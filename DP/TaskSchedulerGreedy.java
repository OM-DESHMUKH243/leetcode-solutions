package DP;

public class TaskSchedulerGreedy {

    public static int leastInterval(
            char[] tasks,
            int n){

        int[] freq = new int[26];

        // Frequency count
        for(char task : tasks){

            freq[task - 'A']++;
        }

        int maxFreq = 0;

        for(int f : freq){

            maxFreq = Math.max(maxFreq, f);
        }

        int maxCount = 0;

        // Count max-frequency tasks
        for(int f : freq){

            if(f == maxFreq){

                maxCount++;
            }
        }

        int slots =
            (maxFreq - 1) * (n + 1)
            + maxCount;

        return Math.max(tasks.length, slots);
    }

    public static void main(String[] args){

        char[] tasks = {
            'A','A','A',
            'B','B','B'
        };

        int n = 2;

        int result =
            leastInterval(tasks, n);

        System.out.println(
            "Minimum Intervals: "
            + result
        );
    }
}
