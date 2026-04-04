package Backtracking;

import java.util.*;

public class SudokuSolver {

    public static void solveSudoku(char[][] board){

        boolean[][] row = new boolean[9][10];
        boolean[][] col = new boolean[9][10];
        boolean[][] box = new boolean[9][10];

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){

                if(board[i][j] != '.'){

                    int num = board[i][j] - '0';

                    row[i][num] = true;
                    col[j][num] = true;
                    box[(i/3)*3 + j/3][num] = true;
                }
            }
        }

        solve(board, 0, 0, row, col, box);
    }

    private static boolean solve(char[][] board, int r, int c,
                                 boolean[][] row, boolean[][] col, boolean[][] box){

        if(r == 9) return true;

        if(c == 9) return solve(board, r + 1, 0, row, col, box);

        if(board[r][c] != '.'){
            return solve(board, r, c + 1, row, col, box);
        }

        for(int num = 1; num <= 9; num++){

            int boxIndex = (r/3)*3 + c/3;

            if(row[r][num] || col[c][num] || box[boxIndex][num]){
                continue;
            }

            board[r][c] = (char)(num + '0');

            row[r][num] = true;
            col[c][num] = true;
            box[boxIndex][num] = true;

            if(solve(board, r, c + 1, row, col, box)){
                return true;
            }

            board[r][c] = '.';
            row[r][num] = false;
            col[c][num] = false;
            box[boxIndex][num] = false;
        }

        return false;
    }

    public static void main(String[] args){

        char[][] board = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };

        solveSudoku(board);

        System.out.println("Solved Sudoku:");

        for(char[] row : board){
            System.out.println(Arrays.toString(row));
        }
    }
}
