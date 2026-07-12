import java.util.*;

class Solution {

    public List<List<String>> solveNQueens(int n) {

        char[][] board = new char[n][n];

        // Fill the board with '.'
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        List<List<String>> ans = new ArrayList<>();

        nQueen(board, 0, ans);

        return ans;
    }

    private void nQueen(char[][] board, int row, List<List<String>> ans) {

        // Base case: all queens are placed
        if (row == board.length) {
            ans.add(printBoard(board));
            return;
        }

        // Try placing queen in every column
        for (int col = 0; col < board.length; col++) {

            if (isSafe(board, row, col)) {

                board[row][col] = 'Q';       // Place queen

                nQueen(board, row + 1, ans); // Recursive call

                board[row][col] = '.';       // Backtrack
            }
        }
    }

    private boolean isSafe(char[][] board, int row, int col) {

        // Check vertical up
        for (int i = row - 1; i >= 0; i--) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        // Check left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        // Check right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }

    private List<String> printBoard(char[][] board) {

        List<String> list = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            list.add(new String(board[i]));
        }

        return list;
    }
}