class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int MOD = 1_000_000_007;
        int n = board.size();

        int[][] dp = new int[n][n];
        int[][] ways = new int[n][n];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        dp[n - 1][n - 1] = 0;
        ways[n - 1][n - 1] = 1;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {

                char c = board.get(i).charAt(j);

                if (c == 'X' || (i == n - 1 && j == n - 1))
                    continue;

                int best = -1;
                int count = 0;

                int[][] dirs = {{1, 0}, {0, 1}, {1, 1}};

                for (int[] d : dirs) {
                    int ni = i + d[0];
                    int nj = j + d[1];

                    if (ni >= n || nj >= n)
                        continue;
                    if (dp[ni][nj] == -1)
                        continue;

                    if (dp[ni][nj] > best) {
                        best = dp[ni][nj];
                        count = ways[ni][nj];
                    } else if (dp[ni][nj] == best) {
                        count = (count + ways[ni][nj]) % MOD;
                    }
                }

                if (best == -1)
                    continue;

                int val = 0;
                if (c >= '1' && c <= '9')
                    val = c - '0';

                dp[i][j] = best + val;
                ways[i][j] = count;
            }
        }

        if (dp[0][0] == -1)
            return new int[]{0, 0};

        return new int[]{dp[0][0], ways[0][0]};
    }
}