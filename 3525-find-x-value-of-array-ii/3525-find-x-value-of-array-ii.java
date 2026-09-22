class Solution {

    static class Node {
        int prod;
        int[] cnt;

        Node(int k) {
            cnt = new int[k];
        }
    }

    static class SegmentTree {
        int n;
        int k;
        Node[] tree;

        SegmentTree(int[] nums, int k) {
            this.n = nums.length;
            this.k = k;
            this.tree = new Node[4 * n];

            build(nums, 1, 0, n - 1);
        }

        private Node merge(Node left, Node right) {
            Node res = new Node(k);

            // Product of the whole segment
            res.prod = (left.prod * right.prod) % k;

            // Prefixes entirely inside left
            for (int r = 0; r < k; r++) {
                res.cnt[r] += left.cnt[r];
            }

            // Prefixes that contain all of left
            // and then a prefix of right
            for (int r = 0; r < k; r++) {
                int newRemainder = (left.prod * r) % k;
                res.cnt[newRemainder] += right.cnt[r];
            }

            return res;
        }

        private void build(int[] nums, int node, int l, int r) {
            if (l == r) {
                tree[node] = new Node(k);

                int remainder = nums[l] % k;

                tree[node].prod = remainder;
                tree[node].cnt[remainder] = 1;

                return;
            }

            int mid = (l + r) >>> 1;

            build(nums, node * 2, l, mid);
            build(nums, node * 2 + 1, mid + 1, r);

            tree[node] = merge(
                tree[node * 2],
                tree[node * 2 + 1]
            );
        }

        void update(int index, int value) {
            update(1, 0, n - 1, index, value);
        }

        private void update(
            int node,
            int l,
            int r,
            int index,
            int value
        ) {
            if (l == r) {
                int remainder = value % k;

                tree[node] = new Node(k);
                tree[node].prod = remainder;
                tree[node].cnt[remainder] = 1;

                return;
            }

            int mid = (l + r) >>> 1;

            if (index <= mid) {
                update(node * 2, l, mid, index, value);
            } else {
                update(node * 2 + 1, mid + 1, r, index, value);
            }

            tree[node] = merge(
                tree[node * 2],
                tree[node * 2 + 1]
            );
        }

        Node query(int ql, int qr) {
            return query(1, 0, n - 1, ql, qr);
        }

        private Node query(
            int node,
            int l,
            int r,
            int ql,
            int qr
        ) {
            // Completely inside range
            if (ql <= l && r <= qr) {
                return tree[node];
            }

            int mid = (l + r) >>> 1;

            if (qr <= mid) {
                return query(node * 2, l, mid, ql, qr);
            }

            if (ql > mid) {
                return query(node * 2 + 1, mid + 1, r, ql, qr);
            }

            Node left = query(node * 2, l, mid, ql, qr);
            Node right = query(node * 2 + 1, mid + 1, r, ql, qr);

            return merge(left, right);
        }
    }

    public int[] resultArray(
        int[] nums,
        int k,
        int[][] queries
    ) {
        int n = nums.length;

        SegmentTree segTree = new SegmentTree(nums, k);

        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            // Persistent update
            nums[index] = value;
            segTree.update(index, value);

            // We need prefixes of [start ... n-1]
            Node node = segTree.query(start, n - 1);

            result[i] = node.cnt[x];
        }

        return result;
    }
}