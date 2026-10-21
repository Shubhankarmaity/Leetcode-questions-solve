class Solution {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int index = -1;
        int minLength = Integer.MAX_VALUE;
    }

    TrieNode root = new TrieNode();

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        int n = wordsContainer.length;

        // Build trie using reversed words
        for (int i = 0; i < n; i++) {
            String word = wordsContainer[i];
            insert(word, i);
        }

        int[] ans = new int[wordsQuery.length];

        for (int i = 0; i < wordsQuery.length; i++) {
            ans[i] = search(wordsQuery[i]);
        }

        return ans;
    }

    private void insert(String word, int index) {
        TrieNode node = root;

        update(node, word, index);

        for (int i = word.length() - 1; i >= 0; i--) {
            int c = word.charAt(i) - 'a';

            if (node.children[c] == null) {
                node.children[c] = new TrieNode();
            }

            node = node.children[c];
            update(node, word, index);
        }
    }

    private void update(TrieNode node, String word, int index) {
        if (word.length() < node.minLength) {
            node.minLength = word.length();
            node.index = index;
        }
    }

    private int search(String query) {
        TrieNode node = root;
        int ans = node.index;

        for (int i = query.length() - 1; i >= 0; i--) {
            int c = query.charAt(i) - 'a';

            if (node.children[c] == null) {
                break;
            }

            node = node.children[c];
            ans = node.index;
        }

        return ans;
    }
}