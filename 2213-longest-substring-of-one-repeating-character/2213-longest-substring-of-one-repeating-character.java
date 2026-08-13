class Solution {
    static class Node {
        char leftChar, rightChar;
        int leftLen, rightLen, maxLen, len;

        Node(char c) {
            leftChar = rightChar = c;
            leftLen = rightLen = maxLen = len = 1;
        }

        Node() {}
    }

    Node[] tree;

    private Node merge(Node a, Node b) {
        if (a == null) return b;
        if (b == null) return a;

        Node res = new Node();

        res.len = a.len + b.len;
        res.leftChar = a.leftChar;
        res.rightChar = b.rightChar;

        res.leftLen = a.leftLen;
        res.rightLen = b.rightLen;

        res.maxLen = Math.max(a.maxLen, b.maxLen);

        if (a.rightChar == b.leftChar) {
            res.maxLen = Math.max(
                res.maxLen,
                a.rightLen + b.leftLen
            );
        }

        if (a.leftLen == a.len && a.leftChar == b.leftChar) {
            res.leftLen = a.len + b.leftLen;
        }

        if (b.rightLen == b.len && a.rightChar == b.rightChar) {
            res.rightLen = a.rightLen + b.len;
        }

        return res;
    }

    private void build(String s, int node, int l, int r) {
        if (l == r) {
            tree[node] = new Node(s.charAt(l));
            return;
        }

        int mid = l + (r - l) / 2;

        build(s, node * 2, l, mid);
        build(s, node * 2 + 1, mid + 1, r);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private void update(int node, int l, int r, int idx, char c) {
        if (l == r) {
            tree[node] = new Node(c);
            return;
        }

        int mid = l + (r - l) / 2;

        if (idx <= mid) {
            update(node * 2, l, mid, idx, c);
        } else {
            update(node * 2 + 1, mid + 1, r, idx, c);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    public int[] longestRepeating(
        String s,
        String queryCharacters,
        int[] queryIndices
    ) {
        int n = s.length();

        tree = new Node[4 * n];

        build(s, 1, 0, n - 1);

        int q = queryCharacters.length();
        int[] ans = new int[q];

        for (int i = 0; i < q; i++) {
            int idx = queryIndices[i];
            char c = queryCharacters.charAt(i);

            update(1, 0, n - 1, idx, c);

            ans[i] = tree[1].maxLen;
        }

        return ans;
    }
}