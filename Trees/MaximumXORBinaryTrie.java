package Trees;

public class MaximumXORBinaryTrie {

    static class TrieNode {

        TrieNode[] child = new TrieNode[2];
    }

    public static int findMaximumXOR(
            int[] nums) {

        TrieNode root = new TrieNode();

        // Insert all numbers
        for(int num : nums) {

            TrieNode curr = root;

            for(int bit = 31; bit >= 0; bit--) {

                int currentBit =
                    (num >> bit) & 1;

                if(curr.child[currentBit] == null) {

                    curr.child[currentBit] =
                        new TrieNode();
                }

                curr = curr.child[currentBit];
            }
        }

        int maxXor = 0;

        for(int num : nums) {

            TrieNode curr = root;

            int currentXor = 0;

            for(int bit = 31; bit >= 0; bit--) {

                int currentBit =
                    (num >> bit) & 1;

                int oppositeBit =
                    1 - currentBit;

                if(curr.child[oppositeBit] != null) {

                    currentXor |= (1 << bit);

                    curr = curr.child[oppositeBit];

                } else {

                    curr = curr.child[currentBit];
                }
            }

            maxXor =
                Math.max(maxXor, currentXor);
        }

        return maxXor;
    }

    public static void main(String[] args) {

        int[] nums = {
            3,10,5,25,2,8
        };

        int result =
            findMaximumXOR(nums);

        System.out.println(
            "Maximum XOR: " + result
        );
    }
}
