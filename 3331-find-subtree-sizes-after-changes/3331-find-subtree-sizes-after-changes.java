
import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        List<Integer>[] tree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            tree[parent[i]].add(i);
        }

        int[] ans = new int[n];
        Arrays.fill(ans, 1);

        int[] last = new int[26];
        Arrays.fill(last, -1);

        int[] previous = new int[n];

        int[] stack = new int[2 * n + 5];
        int top = 0;

        stack[top++] = 0;

        while (top > 0) {
            int node = stack[--top];

            if (node >= 0) {
                int c = s.charAt(node) - 'a';

                previous[node] = last[c];
                last[c] = node;

                stack[top++] = ~node;

                for (int child : tree[node]) {
                    stack[top++] = child;
                }

            } else {
                node = ~node;

                int c = s.charAt(node) - 'a';

                last[c] = previous[node];

                int newParent;

                if (previous[node] != -1) {
                    newParent = previous[node];
                } else {
                    newParent = parent[node];
                }

                if (newParent != -1) {
                    ans[newParent] += ans[node];
                }
            }
        }

        return ans;
    }
}
