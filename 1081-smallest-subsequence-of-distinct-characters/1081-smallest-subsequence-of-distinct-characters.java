class Solution {
    public String smallestSubsequence(String s) {
        int n = s.length();

        // last occurrence of each character
        int[] last = new int[26];
        for (int i = 0; i < n; i++) {
            last[s.charAt(i) - 'a'] = i;
        }

        boolean[] visited = new boolean[26];
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (visited[c - 'a']) {
                continue;
            }

            while (!stack.isEmpty()
                    && stack.peek() > c
                    && last[stack.peek() - 'a'] > i) {
                visited[stack.pop() - 'a'] = false;
            }

            stack.push(c);
            visited[c - 'a'] = true;
        }

        StringBuilder ans = new StringBuilder();
        while (!stack.isEmpty()) {
            ans.append(stack.pop());
        }

        return ans.reverse().toString();
    }
}