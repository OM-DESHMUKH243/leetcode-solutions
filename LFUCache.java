import java.util.*;

public class LFUCache {

    class Node {

        int key;
        int value;
        int freq;

        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.freq = 1;
        }
    }

    class DoublyLinkedList {

        Node head;
        Node tail;

        int size;

        DoublyLinkedList() {

            head = new Node(0, 0);
            tail = new Node(0, 0);

            head.next = tail;
            tail.prev = head;

            size = 0;
        }

        void add(Node node) {

            node.next = head.next;
            node.prev = head;

            head.next.prev = node;
            head.next = node;

            size++;
        }

        void remove(Node node) {

            node.prev.next = node.next;
            node.next.prev = node.prev;

            size--;
        }

        Node removeLast() {

            if (size == 0) {
                return null;
            }

            Node last = tail.prev;

            remove(last);

            return last;
        }
    }

    private final int capacity;
    private int minFreq;

    private Map<Integer, Node> nodeMap;
    private Map<Integer, DoublyLinkedList> freqMap;

    public LFUCache(int capacity) {

        this.capacity = capacity;

        nodeMap = new HashMap<>();
        freqMap = new HashMap<>();

        minFreq = 0;
    }

    public int get(int key) {

        if (!nodeMap.containsKey(key)) {
            return -1;
        }

        Node node = nodeMap.get(key);

        updateFrequency(node);

        return node.value;
    }

    public void put(int key, int value) {

        if (capacity == 0) {
            return;
        }

        if (nodeMap.containsKey(key)) {

            Node node = nodeMap.get(key);

            node.value = value;

            updateFrequency(node);

            return;
        }

        if (nodeMap.size() == capacity) {

            DoublyLinkedList minList =
                    freqMap.get(minFreq);

            Node removedNode =
                    minList.removeLast();

            nodeMap.remove(removedNode.key);
        }

        Node newNode =
                new Node(key, value);

        nodeMap.put(key, newNode);

        minFreq = 1;

        freqMap
                .computeIfAbsent(
                        1,
                        k -> new DoublyLinkedList()
                )
                .add(newNode);
    }

    private void updateFrequency(Node node) {

        int oldFreq = node.freq;

        DoublyLinkedList oldList =
                freqMap.get(oldFreq);

        oldList.remove(node);

        if (oldFreq == minFreq &&
                oldList.size == 0) {

            minFreq++;
        }

        node.freq++;

        freqMap
                .computeIfAbsent(
                        node.freq,
                        k -> new DoublyLinkedList()
                )
                .add(node);
    }
}