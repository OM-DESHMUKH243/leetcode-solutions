import java.util.*;

class AllOne {

    class Node {

        int count;
        Set<String> keys;

        Node prev;
        Node next;

        Node(int count) {
            this.count = count;
            this.keys = new HashSet<>();
        }
    }

    private Node head;
    private Node tail;

    private Map<String, Node> map;

    public AllOne() {

        head = new Node(0);
        tail = new Node(0);

        head.next = tail;
        tail.prev = head;

        map = new HashMap<>();
    }

    public void inc(String key) {

        if (!map.containsKey(key)) {

            Node first = head.next;

            if (first != tail && first.count == 1) {

                first.keys.add(key);
                map.put(key, first);

            } else {

                Node node = new Node(1);

                node.keys.add(key);

                insertAfter(head, node);

                map.put(key, node);
            }

        } else {

            Node curr = map.get(key);

            Node next = curr.next;

            int newCount = curr.count + 1;

            if (next != tail && next.count == newCount) {

                next.keys.add(key);

                map.put(key, next);

            } else {

                Node node = new Node(newCount);

                node.keys.add(key);

                insertAfter(curr, node);

                map.put(key, node);
            }

            curr.keys.remove(key);

            removeIfEmpty(curr);
        }
    }

    public void dec(String key) {

        Node curr = map.get(key);

        if (curr.count == 1) {

            map.remove(key);

        } else {

            Node prev = curr.prev;

            int newCount = curr.count - 1;

            if (prev != head && prev.count == newCount) {

                prev.keys.add(key);

                map.put(key, prev);

            } else {

                Node node = new Node(newCount);

                node.keys.add(key);

                insertAfter(prev, node);

                map.put(key, node);
            }
        }

        curr.keys.remove(key);

        removeIfEmpty(curr);
    }

    public String getMaxKey() {

        if (tail.prev == head) {
            return "";
        }

        return tail.prev.keys.iterator().next();
    }

    public String getMinKey() {

        if (head.next == tail) {
            return "";
        }

        return head.next.keys.iterator().next();
    }

    private void insertAfter(Node prev, Node node) {

        node.next = prev.next;
        node.prev = prev;

        prev.next.prev = node;
        prev.next = node;
    }

    private void removeIfEmpty(Node node) {

        if (!node.keys.isEmpty()) {
            return;
        }

        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}

public class AllOneDataStructure {

    public static void main(String[] args) {

        AllOne ds = new AllOne();

        ds.inc("hello");
        ds.inc("hello");

        System.out.println("Max: " + ds.getMaxKey());
        System.out.println("Min: " + ds.getMinKey());

        ds.inc("leet");

        System.out.println("Max: " + ds.getMaxKey());
        System.out.println("Min: " + ds.getMinKey());

        ds.dec("hello");

        System.out.println("After decrement:");

        System.out.println("Max: " + ds.getMaxKey());
        System.out.println("Min: " + ds.getMinKey());
    }
}