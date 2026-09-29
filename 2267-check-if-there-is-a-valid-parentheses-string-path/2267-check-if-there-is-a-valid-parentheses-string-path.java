class Solution {
    int m, n;
    char[][] grid;
    Boolean[][][] memo;

    public boolean hasValidPath(char[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;

        if ((m + n - 1) % 2 == 1) return false;

        memo = new Boolean[m][n][m + n];

        return dfs(0, 0, 0);
    }

    private boolean dfs(int r, int c, int balance) {
        // Update balance based on current cell.
        if (grid[r][c] == '(') {
            balance++;
        } else {
            balance--;
        }

        if (balance < 0) return false;

        // Reached destination.
        if (r == m - 1 && c == n - 1) {
            return balance == 0;
        }

        if (memo[r][c][balance] != null) {
            return memo[r][c][balance];
        }

        boolean result = false;

        // Move down
        if (r + 1 < m) {
            result = dfs(r + 1, c, balance);
        }

        // Move right
        if (!result && c + 1 < n) {
            result = dfs(r, c + 1, balance);
        }

        return memo[r][c][balance] = result;
    }
}