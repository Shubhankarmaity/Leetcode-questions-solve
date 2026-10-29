
import java.util.*;

class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;

        List<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        int maxCost = 0;

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            if (online[u] && online[v]) {
                graph[u].add(new int[]{v, w});
                maxCost = Math.max(maxCost, w);
            }
        }

        int left = 0;
        int right = maxCost;
        int answer = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (isValid(mid, graph, n, k)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    private boolean isValid(int minCost, List<int[]>[] graph, int n, long k) {
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);

        PriorityQueue<long[]> pq = new PriorityQueue<>(
            Comparator.comparingLong(a -> a[0])
        );

        dist[0] = 0;
        pq.offer(new long[]{0, 0});

        while (!pq.isEmpty()) {
            long[] current = pq.poll();

            long cost = current[0];
            int u = (int) current[1];

            if (cost != dist[u]) {
                continue;
            }

            if (cost > k) {
                return false;
            }

            if (u == n - 1) {
                return true;
            }

            for (int[] edge : graph[u]) {
                int v = edge[0];
                int w = edge[1];

                if (w < minCost) {
                    continue;
                }

                long newCost = cost + w;

                if (newCost < dist[v] && newCost <= k) {
                    dist[v] = newCost;
                    pq.offer(new long[]{newCost, v});
                }
            }
        }

        return false;
    }
}
