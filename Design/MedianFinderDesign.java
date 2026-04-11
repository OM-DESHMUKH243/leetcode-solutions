package Design;

import java.util.*;

public class MedianFinderDesign {

    static class MedianFinder {

        PriorityQueue<Integer> maxHeap;
        PriorityQueue<Integer> minHeap;

        public MedianFinder(){

            maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            minHeap = new PriorityQueue<>();
        }

        public void addNum(int num){

            maxHeap.offer(num);

            minHeap.offer(maxHeap.poll());

            if(minHeap.size() > maxHeap.size()){
                maxHeap.offer(minHeap.poll());
            }
        }

        public double findMedian(){

            if(maxHeap.size() == minHeap.size()){
                return (maxHeap.peek() + minHeap.peek()) / 2.0;
            }

            return maxHeap.peek();
        }
    }

    public static void main(String[] args){

        MedianFinder mf = new MedianFinder();

        mf.addNum(1);
        mf.addNum(2);

        System.out.println(mf.findMedian()); // 1.5

        mf.addNum(3);

        System.out.println(mf.findMedian()); // 2.0
    }
}
