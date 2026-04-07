package Backtracking;

import java.util.*;

public class WordSearchII {

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;
    }

    public static List<String> findWords(char[][] board, String[] words){

        TrieNode root = buildTrie(words);
        List<String> result = new ArrayList<>();

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                dfs(board, i, j, root, result);
            }
        }

        return result;
    }

    private static void dfs(char[][] board, int i, int j,
                            TrieNode node, List<String> result){

        char ch = board[i][j];

        if(ch == '#' || node.children[ch - 'a'] == null){
            return;
        }

        node = node.children[ch - 'a'];

        if(node.word != null){
            result.add(node.word);
            node.word = null;
        }

        board[i][j] = '#';

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        for(int d = 0; d < 4; d++){

            int x = i + dx[d];
            int y = j + dy[d];

            if(x >= 0 && y >= 0 && x < board.length && y < board[0].length){
                dfs(board, x, y, node, result);
            }
        }

        board[i][j] = ch;
    }

    private static TrieNode buildTrie(String[] words){

        TrieNode root = new TrieNode();

        for(String word : words){

            TrieNode curr = root;

            for(char ch : word.toCharArray()){

                int idx = ch - 'a';

                if(curr.children[idx] == null){
                    curr.children[idx] = new TrieNode();
                }

                curr = curr.children[idx];
            }

            curr.word = word;
        }

        return root;
    }

    public static void main(String[] args){

        char[][] board = {
            {'o','a','a','n'},
            {'e','t','a','e'},
            {'i','h','k','r'},
            {'i','f','l','v'}
        };

        String[] words = {"oath","pea","eat","rain"};

        List<String> result = findWords(board, words);

        System.out.println("Found words: " + result);
    }
}