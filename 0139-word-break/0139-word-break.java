class Solution {

    static class Node {
        Node[] children = new Node[26];
        boolean eow = false;
    }

    private Node root;

    public Solution() {
        root = new Node();
    }

    public boolean wordBreak(String s, List<String> wordDict) {

        // Build Trie
        for (String word : wordDict) {
            insert(word);
        }

        // dp[i] = can s[0...i-1] be segmented?
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 0; i < s.length(); i++) {

            if (!dp[i]) {
                continue;
            }

            Node curr = root;

            for (int j = i; j < s.length(); j++) {

                int idx = s.charAt(j) - 'a';

                if (curr.children[idx] == null) {
                    break;
                }

                curr = curr.children[idx];

                if (curr.eow) {
                    dp[j + 1] = true;
                }
            }
        }

        return dp[s.length()];
    }

    public void insert(String word) {

        Node curr = root;

        for (int i = 0; i < word.length(); i++) {

            int idx = word.charAt(i) - 'a';

            if (curr.children[idx] == null) {
                curr.children[idx] = new Node();
            }

            curr = curr.children[idx];
        }

        curr.eow = true;
    }

    public boolean search(String word) {

        Node curr = root;

        for (int i = 0; i < word.length(); i++) {

            int idx = word.charAt(i) - 'a';

            if (curr.children[idx] == null) {
                return false;
            }

            curr = curr.children[idx];
        }

        return curr.eow;
    }
}