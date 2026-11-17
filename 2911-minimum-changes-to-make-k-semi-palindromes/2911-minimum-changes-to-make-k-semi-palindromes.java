import java.util.*;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        final int INF = 1_000_000;
        char[] c = s.toCharArray();

        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int len = j - i + 1;
                if (len == 1) {
                    cost[i][j] = INF; 
                    continue;
                }
                int best = INF;
                for (int d = 1; d < len; d++) {
                    if (len % d != 0) continue;
                    int m = len / d;
                    int changes = 0;
                    for (int r = 0; r < d; r++) {
                        for (int p = 0; p < m / 2; p++) {
                            int a = i + r + p * d;
                            int b = i + r + (m - 1 - p) * d;
                            if (c[a] != c[b]) changes++;
                        }
                    }
                    best = Math.min(best, changes);
                }
                cost[i][j] = best;
            }
        }

        
        int[][] dp = new int[k + 1][n + 1];
        for (int[] row : dp) Arrays.fill(row, INF);
        dp[0][0] = 0;

        for (int j = 1; j <= k; j++) {
            for (int i = 2 * j; i <= n; i++) {
                for (int t = 2 * (j - 1); t <= i - 2; t++) {
                    if (dp[j - 1][t] >= INF) continue;
                    dp[j][i] = Math.min(dp[j][i], dp[j - 1][t] + cost[t][i - 1]);
                }
            }
        }
        return dp[k][n];
    }
}