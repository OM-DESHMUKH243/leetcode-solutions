package Design;

import java.util.*;

public class LFUCacheDesign {

    static class Node {
        int key, value, freq;
        Node prev, next;

        Node(int k, int v){
            key = k;
            value = v;
            freq = 1;
        }
    }

    static class DLL {
        Node head, tail;
        int size;

        DLL(){
            head = new Node(0,0);
            tail = new Node(0,0);
            head.next = tail;
            tail.prev = head;
        }

        void add(Node node){
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
            size++;
        }

        void remove(Node node){
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }

        Node removeLast(){
            if(size > 0){
                Node node = tail.prev;
                remove(node);
                return node;
            }
            return null;
        }
    }

    static class LFUCache {

        int capacity, minFreq;
        Map<Integer, Node> keyMap;
        Map<Integer, DLL> freqMap;

        public LFUCache(int capacity){
            this.capacity = capacity;
            keyMap = new HashMap<>();
            freqMap = new HashMap<>();
        }

        public int get(int key){

            if(!keyMap.containsKey(key)) return -1;

            Node node = keyMap.get(key);
            update(node);
            return node.value;
        }

        public void put(int key, int value){

            if(capacity == 0) return;

            if(keyMap.containsKey(key)){
                Node node = keyMap.get(key);
                node.value = value;
                update(node);
            }
            else{

                if(keyMap.size() == capacity){
                    DLL minList = freqMap.get(minFreq);
                    Node removed = minList.removeLast();
                    keyMap.remove(removed.key);
                }

                Node node = new Node(key, value);
                minFreq = 1;

                DLL list = freqMap.getOrDefault(1, new DLL());
                list.add(node);
                freqMap.put(1, list);

                keyMap.put(key, node);
            }
        }

        private void update(Node node){

            int freq = node.freq;
            DLL list = freqMap.get(freq);

            list.remove(node);

            if(freq == minFreq && list.size == 0){
                minFreq++;
            }

            node.freq++;

            DLL newList = freqMap.getOrDefault(node.freq, new DLL());
            newList.add(node);
            freqMap.put(node.freq, newList);
        }
    }

    public static void main(String[] args){

        LFUCache cache = new LFUCache(2);

        cache.put(1,1);
        cache.put(2,2);

        System.out.println(cache.get(1)); // 1

        cache.put(3,3);

        System.out.println(cache.get(2)); // -1
        System.out.println(cache.get(3)); // 3
    }
}
