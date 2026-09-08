class Solution {

    public int find(int x, int[] parent) {
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = find(parent[x], parent);
    }

    public boolean union(int u, int v, int[] parent, int[] rank) {

        int pu = find(u, parent);
        int pv = find(v, parent);

        // Already connected => this edge creates a cycle
        if (pu == pv) {
            return false;
        }

        // Union by rank
        if (rank[pu] < rank[pv]) {
            parent[pu] = pv;
        }
        else if (rank[pu] > rank[pv]) {
            parent[pv] = pu;
        }
        else {
            parent[pv] = pu;
            rank[pu]++;
        }

        return true;
    }

    public int[] findRedundantConnection(int[][] edges) {

        int n = edges.length;

        int[] parent = new int[n + 1];
        int[] rank = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int[] edge : edges) {

            int u = edge[0];
            int v = edge[1];

            if (!union(u, v, parent, rank)) {
                return edge;
            }
        }

        return new int[0];
    }
}