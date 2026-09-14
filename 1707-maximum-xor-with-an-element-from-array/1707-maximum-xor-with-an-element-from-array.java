import java.util.*;

class Solution {

    class TrieNode {
        TrieNode[] child = new TrieNode[2];
    }

    private TrieNode root;

    private void insert(int num) {
        TrieNode curr = root;

        for (int i = 30; i >= 0; i--) {
            int bit = (num >> i) & 1;

            if (curr.child[bit] == null) {
                curr.child[bit] = new TrieNode();
            }

            curr = curr.child[bit];
        }
    }

    private int getMaxXor(int num) {
        TrieNode curr = root;
        int result = 0;

        for (int i = 30; i >= 0; i--) {
            int bit = (num >> i) & 1;
            int opposite = 1 - bit;

            if (curr.child[opposite] != null) {
                result |= (1 << i);
                curr = curr.child[opposite];
            } else {
                curr = curr.child[bit];
            }
        }

        return result;
    }

    public int[] maximizeXor(int[] nums, int[][] queries) {

        Arrays.sort(nums);

        // Store: {x, m, originalIndex}
        int[][] q = new int[queries.length][3];

        for (int i = 0; i < queries.length; i++) {
            q[i][0] = queries[i][0]; // x
            q[i][1] = queries[i][1]; // m
            q[i][2] = i;             // original index
        }

        // Sort queries by m
        Arrays.sort(q, (a, b) -> Integer.compare(a[1], b[1]));

        int[] answer = new int[queries.length];

        root = new TrieNode();

        int numsIndex = 0;

        for (int[] query : q) {

            int x = query[0];
            int m = query[1];
            int originalIndex = query[2];

            // Add all nums <= m into Trie
            while (numsIndex < nums.length && nums[numsIndex] <= m) {
                insert(nums[numsIndex]);
                numsIndex++;
            }

            // No number <= m exists
            if (numsIndex == 0) {
                answer[originalIndex] = -1;
            } else {
                answer[originalIndex] = getMaxXor(x);
            }
        }

        return answer;
    }
}