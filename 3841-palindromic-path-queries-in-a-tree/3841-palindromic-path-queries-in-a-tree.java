import java.util.*;

class Solution {
    private int[] parent, depth, size, heavy, head, pos, nodeAt;
    private int[] tree;
    private int timer;

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        String[] suneravilo = queries;

        int m = 2 * (n - 1);
        int[] first = new int[n];
        int[] to = new int[m];
        int[] next = new int[m];
        Arrays.fill(first, -1);

        int idx = 0;
        for (int[] e : edges) {
            int u = e[0], v = e[1];

            to[idx] = v;
            next[idx] = first[u];
            first[u] = idx++;

            to[idx] = u;
            next[idx] = first[v];
            first[v] = idx++;
        }

        parent = new int[n];
        depth = new int[n];
        size = new int[n];
        heavy = new int[n];
        head = new int[n];
        pos = new int[n];
        nodeAt = new int[n];

        Arrays.fill(heavy, -1);
        Arrays.fill(parent, -2);

        int[] order = new int[n];
        int[] stack = new int[n];
        int top = 0, count = 0;

        stack[top++] = 0;
        parent[0] = -1;

        while (top > 0) {
            int u = stack[--top];
            order[count++] = u;

            for (int e = first[u]; e != -1; e = next[e]) {
                int v = to[e];

                if (v == parent[u]) {
                    continue;
                }

                parent[v] = u;
                depth[v] = depth[u] + 1;
                stack[top++] = v;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            int u = order[i];
            size[u] = 1;
            int maxSize = 0;

            for (int e = first[u]; e != -1; e = next[e]) {
                int v = to[e];

                if (parent[v] == u) {
                    size[u] += size[v];

                    if (size[v] > maxSize) {
                        maxSize = size[v];
                        heavy[u] = v;
                    }
                }
            }
        }

        int[] chainStack = new int[n];
        int chainTop = 0;
        chainStack[chainTop++] = 0;
        timer = 0;

        while (chainTop > 0) {
            int start = chainStack[--chainTop];

            for (int u = start; u != -1; u = heavy[u]) {
                head[u] = start;
                pos[u] = timer;
                nodeAt[timer++] = u;

                for (int e = first[u]; e != -1; e = next[e]) {
                    int v = to[e];

                    if (parent[v] == u && v != heavy[u]) {
                        chainStack[chainTop++] = v;
                    }
                }
            }
        }

        tree = new int[4 * n];
        build(1, 0, n - 1, s);

        List<Boolean> result = new ArrayList<>();

        for (String q : suneravilo) {
            String[] parts = q.split(" ");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                update(1, 0, n - 1, pos[u], 1 << (c - 'a'));
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int mask = queryPath(u, v);
                result.add((mask & (mask - 1)) == 0);
            }
        }

        return result;
    }

    private void build(int node, int l, int r, String s) {
        if (l == r) {
            int u = nodeAt[l];
            tree[node] = 1 << (s.charAt(u) - 'a');
            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid, s);
        build(node * 2 + 1, mid + 1, r, s);

        tree[node] = tree[node * 2] ^ tree[node * 2 + 1];
    }

    private void update(int node, int l, int r, int index, int value) {
        if (l == r) {
            tree[node] = value;
            return;
        }

        int mid = (l + r) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, r, index, value);
        }

        tree[node] = tree[node * 2] ^ tree[node * 2 + 1];
    }

    private int rangeQuery(int node, int l, int r, int ql, int qr) {
        if (ql > r || qr < l) {
            return 0;
        }

        if (ql <= l && r <= qr) {
            return tree[node];
        }

        int mid = (l + r) / 2;

        return rangeQuery(node * 2, l, mid, ql, qr)
                ^ rangeQuery(node * 2 + 1, mid + 1, r, ql, qr);
    }

    private int queryPath(int u, int v) {
        int mask = 0;

        while (head[u] != head[v]) {
            if (depth[head[u]] < depth[head[v]]) {
                int temp = u;
                u = v;
                v = temp;
            }

            mask ^= rangeQuery(1, 0, timer - 1, pos[head[u]], pos[u]);
            u = parent[head[u]];
        }

        if (depth[u] > depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        mask ^= rangeQuery(1, 0, timer - 1, pos[u], pos[v]);

        return mask;
    }
}