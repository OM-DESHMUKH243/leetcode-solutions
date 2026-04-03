package Backtracking;

import java.util.*;

public class NQueens {

    public static List<List<String>> solveNQueens(int n){

        List<List<String>> result = new ArrayList<>();

        boolean[] col = new boolean[n];
        boolean[] diag = new boolean[2 * n];
        boolean[] anti = new boolean[2 * n];

        char[][] board = new char[n][n];

        for(char[] row : board){
            Arrays.fill(row, '.');
        }

        placeQueens(0, n, board, col, diag, anti, result);

        return result;
    }

    private static void placeQueens(int row, int n, char[][] board,
                                    boolean[] col, boolean[] diag, boolean[] anti,
                                    List<List<String>> res){

        if(row == n){

            List<String> config = new ArrayList<>();

            for(char[] r : board){
                config.add(new String(r));
            }

            res.add(config);
            return;
        }

        for(int c = 0; c < n; c++){

            if(col[c] || diag[row - c + n] || anti[row + c]){
                continue;
            }

            board[row][c] = 'Q';

            col[c] = true;
            diag[row - c + n] = true;
            anti[row + c] = true;

            placeQueens(row + 1, n, board, col, diag, anti, res);

            board[row][c] = '.';
            col[c] = false;
            diag[row - c + n] = false;
            anti[row + c] = false;
        }
    }

    public static void main(String[] args){

        int n = 4;

        List<List<String>> result = solveNQueens(n);

        System.out.println("Solutions: " + result);
    }
}
