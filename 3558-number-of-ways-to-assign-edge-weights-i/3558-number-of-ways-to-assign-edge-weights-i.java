import java.util.*;

class Solution {
    private static final int MOD = 1_000_000_007;
    private List<Integer>[] graph;

    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
        }

        int depth = dfs(1, 0);

        return modPow(2, depth - 1);
    }

    private int dfs(int node, int parent) {
        int maxDepth = 0;

        for (int next : graph[node]) {
            if (next != parent) {
                maxDepth = Math.max(maxDepth, 1 + dfs(next, node));
            }
        }

        return maxDepth;
    }

    private int modPow(long base, int exp) {
        long ans = 1;

        while (exp > 0) {
            if ((exp & 1) == 1) {
                ans = (ans * base) % MOD;
            }
            base = (base * base) % MOD;
            exp >>= 1;
        }

        return (int) ans;
    }
}