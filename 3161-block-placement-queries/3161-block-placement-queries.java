import java.util.*;

class Solution {
    public List<Boolean> getResults(int[][] queries) {
        int n = 50001;

        // Segment tree stores the maximum gap in each range.
        SegmentTree st = new SegmentTree(n);

        // Initially, position 0 acts as a boundary.
        st.update(0, 0);

        List<Boolean> ans = new ArrayList<>();

        // Obstacles that have been placed
        TreeSet<Integer> obstacles = new TreeSet<>();
        obstacles.add(0);

        for (int[] q : queries) {
            if (q[0] == 1) {
                int x = q[1];

                Integer left = obstacles.lower(x);
                Integer right = obstacles.higher(x);

                if (left == null) left = 0;

                // New obstacle x splits the gap [left, right].
                st.update(x, x - left);

                if (right != null) {
                    st.update(right, right - x);
                }

                obstacles.add(x);
            } else {
                int x = q[1];
                int size = q[2];

                // Find the largest gap ending at or before x.
                int maxGap = st.query(0, x);

                // Also consider the gap from the last obstacle to x.
                Integer last = obstacles.floor(x);

                if (last != null) {
                    maxGap = Math.max(maxGap, x - last);
                }

                ans.add(maxGap >= size);
            }
        }

        return ans;
    }

    static class SegmentTree {
        int[] tree;
        int n;

        SegmentTree(int n) {
            this.n = n;
            tree = new int[4 * n];
        }

        void update(int pos, int value) {
            update(1, 0, n - 1, pos, value);
        }

        void update(int node, int l, int r, int pos, int value) {
            if (l == r) {
                tree[node] = value;
                return;
            }

            int mid = l + (r - l) / 2;

            if (pos <= mid) {
                update(node * 2, l, mid, pos, value);
            } else {
                update(node * 2 + 1, mid + 1, r, pos, value);
            }

            tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
        }

        int query(int ql, int qr) {
            if (ql > qr) return 0;
            return query(1, 0, n - 1, ql, qr);
        }

        int query(int node, int l, int r, int ql, int qr) {
            if (qr < l || r < ql) {
                return 0;
            }

            if (ql <= l && r <= qr) {
                return tree[node];
            }

            int mid = l + (r - l) / 2;

            return Math.max(
                query(node * 2, l, mid, ql, qr),
                query(node * 2 + 1, mid + 1, r, ql, qr)
            );
        }
    }
}