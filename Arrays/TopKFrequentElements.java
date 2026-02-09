import java.util.*;

public class TopKFrequentElements {

    public static int[] topKFrequent(int[] nums, int k) {
        
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for(int n : nums){
            map.put(n, map.getOrDefault(n,0)+1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(
            (a,b) -> map.get(b) - map.get(a)
        );

        pq.addAll(map.keySet());

        int[] res = new int[k];
        for(int i=0;i<k;i++){
            res[i] = pq.poll();
        }

        return res;
    }

    public static void main(String[] args) {
        int nums[] = {1,1,1,2,2,3};
        int k = 2;

        int ans[] = topKFrequent(nums,k);

        System.out.println("Top K elements:");
        for(int n: ans){
            System.out.print(n+" ");
        }
    }
}
