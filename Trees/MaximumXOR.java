package Trees;

public class MaximumXOR {

    static class TrieNode {
        TrieNode[] children = new TrieNode[2];
    }

    static TrieNode root = new TrieNode();

    public static void insert(int num){

        TrieNode node = root;

        for(int i = 31; i >= 0; i--){

            int bit = (num >> i) & 1;

            if(node.children[bit] == null){
                node.children[bit] = new TrieNode();
            }

            node = node.children[bit];
        }
    }

    public static int getMaxXor(int num){

        TrieNode node = root;
        int xor = 0;

        for(int i = 31; i >= 0; i--){

            int bit = (num >> i) & 1;
            int opposite = 1 - bit;

            if(node.children[opposite] != null){

                xor |= (1 << i);
                node = node.children[opposite];

            } else {

                node = node.children[bit];
            }
        }

        return xor;
    }

    public static int findMaximumXOR(int[] nums){

        for(int num : nums){
            insert(num);
        }

        int maxXor = 0;

        for(int num : nums){
            maxXor = Math.max(maxXor, getMaxXor(num));
        }

        return maxXor;
    }

    public static void main(String[] args){

        int[] nums = {3,10,5,25,2,8};

        int result = findMaximumXOR(nums);

        System.out.println("Maximum XOR: " + result);
    }
}
