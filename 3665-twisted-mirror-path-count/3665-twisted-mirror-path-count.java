class Solution {
    static final int MOD = 1_000_000_007;

    int[][] grid;
    int m, n;
    int[][][] memo;

    public int uniquePaths(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;

        memo = new int[m][n][2];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                memo[i][j][0] = -2;
                memo[i][j][1] = -2;
            }
        }

        long[][] dp = new long[m][n];
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == 0) continue;

                if (j + 1 < n) {
                    int[] p = getNext(i, j + 1, 0);
                    if (p[0] != -1) {
                        dp[p[0]][p[1]] =
                            (dp[p[0]][p[1]] + dp[i][j]) % MOD;
                    }
                }

                if (i + 1 < m) {
                    int[] p = getNext(i + 1, j, 1);
                    if (p[0] != -1) {
                        dp[p[0]][p[1]] =
                            (dp[p[0]][p[1]] + dp[i][j]) % MOD;
                    }
                }
            }
        }

        return (int) dp[m - 1][n - 1];
    }

    int[] getNext(int r, int c, int dir) {
        if (r < 0 || r >= m || c < 0 || c >= n) {
            return new int[]{-1, -1};
        }

        if (grid[r][c] == 0) {
            return new int[]{r, c};
        }

        if (memo[r][c][dir] != -2) {
            return decode(memo[r][c][dir]);
        }

        int nr, nc, ndir;

        if (dir == 0) {
            nr = r + 1;
            nc = c;
            ndir = 1;
        } else {
            nr = r;
            nc = c + 1;
            ndir = 0;
        }

        int[] result = getNext(nr, nc, ndir);

        memo[r][c][dir] = encode(result);

        return result;
    }

    int encode(int[] p) {
        if (p[0] == -1) return -1;
        return p[0] * n + p[1];
    }

    int[] decode(int value) {
        if (value == -1) {
            return new int[]{-1, -1};
        }
        return new int[]{value / n, value % n};
    }
}