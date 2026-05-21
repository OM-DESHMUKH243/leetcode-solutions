package DP;

public class LongestIncreasingPathDFS {

    static int rows;
    static int cols;

    static int[][] memo;

    static final int[][] directions = {
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1}
    };

    public static int longestIncreasingPath(
            int[][] matrix){

        rows = matrix.length;

        cols = matrix[0].length;

        memo = new int[rows][cols];

        int longest = 0;

        for(int r = 0; r < rows; r++){

            for(int c = 0; c < cols; c++){

                longest = Math.max(
                    longest,
                    dfs(matrix, r, c)
                );
            }
        }

        return longest;
    }

    private static int dfs(int[][] matrix,
                           int r,
                           int c){

        // Memoized result
        if(memo[r][c] != 0){
            return memo[r][c];
        }

        int best = 1;

        for(int[] dir : directions){

            int nr = r + dir[0];
            int nc = c + dir[1];

            if(nr >= 0 && nr < rows
               && nc >= 0 && nc < cols
               && matrix[nr][nc]
                    > matrix[r][c]){

                best = Math.max(

                    best,

                    1 + dfs(matrix, nr, nc)
                );
            }
        }

        memo[r][c] = best;

        return best;
    }

    public static void main(String[] args){

        int[][] matrix = {
            {9,9,4},
            {6,6,8},
            {2,1,1}
        };

        int result =
                longestIncreasingPath(matrix);

        System.out.println(
            "Longest Increasing Path: "
            + result
        );
    }
}
