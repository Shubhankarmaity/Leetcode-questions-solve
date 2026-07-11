class Solution {
    List<Integer>[] graph;
    boolean[] visited;
    int nodes;
    int edgeCount;

    public int countCompleteComponents(int n, int[][] edges) {
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        visited = new boolean[n];
        int complete = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                nodes = 0;
                edgeCount = 0;
                dfs(i);

                // Every edge is counted twice
                edgeCount /= 2;

                if (edgeCount == nodes * (nodes - 1) / 2) {
                    complete++;
                }
            }
        }

        return complete;
    }

    private void dfs(int u) {
        visited[u] = true;
        nodes++;
        edgeCount += graph[u].size();

        for (int v : graph[u]) {
            if (!visited[v]) {
                dfs(v);
            }
        }
    }
}