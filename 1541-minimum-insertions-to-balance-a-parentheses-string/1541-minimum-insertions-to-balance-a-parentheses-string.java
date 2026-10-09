
class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int need = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                // A closing pair must have an even number
                // of closing parentheses remaining.
                if (need % 2 == 1) {
                    insertions++;
                    need--;
                }

                need += 2;
            } else {
                need--;

                if (need < 0) {
                    // Insert an opening parenthesis
                    insertions++;
                    need = 1;
                }
            }
        }

        return insertions + need;
    }
}
