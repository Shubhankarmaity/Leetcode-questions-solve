class NumArray {

    int[] tree;
    int n;

    public NumArray(int[] nums) {
        n = nums.length;

        if (n == 0) {
            return;
        }

        tree = new int[4 * n];

        build(nums, 0, 0, n - 1);
    }

    // Build segment tree
    private void build(int[] nums, int node, int start, int end) {

        if (start == end) {
            tree[node] = nums[start];
            return;
        }

        int mid = start + (end - start) / 2;

        build(nums, 2 * node + 1, start, mid);
        build(nums, 2 * node + 2, mid + 1, end);

        tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
    }

    public void update(int index, int val) {
        update(0, 0, n - 1, index, val);
    }

    private void update(int node, int start, int end,
                        int index, int val) {

        if (start == end) {
            tree[node] = val;
            return;
        }

        int mid = start + (end - start) / 2;

        if (index <= mid) {
            update(2 * node + 1, start, mid, index, val);
        } else {
            update(2 * node + 2, mid + 1, end, index, val);
        }

        tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
    }

    public int sumRange(int left, int right) {
        return query(0, 0, n - 1, left, right);
    }

    private int query(int node, int start, int end,
                      int left, int right) {

        // Completely outside
        if (right < start || end < left) {
            return 0;
        }

        // Completely inside
        if (left <= start && end <= right) {
            return tree[node];
        }

        int mid = start + (end - start) / 2;

        int leftSum = query(
            2 * node + 1,
            start,
            mid,
            left,
            right
        );

        int rightSum = query(
            2 * node + 2,
            mid + 1,
            end,
            left,
            right
        );

        return leftSum + rightSum;
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */