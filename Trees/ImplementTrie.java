package Trees;

public class ImplementTrie {

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }

    static class Trie {

        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word){

            TrieNode curr = root;

            for(char ch : word.toCharArray()){

                int idx = ch - 'a';

                if(curr.children[idx] == null){
                    curr.children[idx] = new TrieNode();
                }

                curr = curr.children[idx];
            }

            curr.isEnd = true;
        }

        public boolean search(String word){

            TrieNode node = find(word);

            return node != null && node.isEnd;
        }

        public boolean startsWith(String prefix){

            return find(prefix) != null;
        }

        private TrieNode find(String str){

            TrieNode curr = root;

            for(char ch : str.toCharArray()){

                int idx = ch - 'a';

                if(curr.children[idx] == null){
                    return null;
                }

                curr = curr.children[idx];
            }

            return curr;
        }
    }

    public static void main(String[] args){

        Trie trie = new Trie();

        trie.insert("apple");

        System.out.println("Search apple: " + trie.search("apple"));
        System.out.println("Search app: " + trie.search("app"));
        System.out.println("StartsWith app: " + trie.startsWith("app"));

        trie.insert("app");

        System.out.println("Search app: " + trie.search("app"));
    }
}
