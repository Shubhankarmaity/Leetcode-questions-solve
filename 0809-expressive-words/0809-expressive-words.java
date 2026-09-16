class Solution {
    public int expressiveWords(String s, String[] words) {
        int count = 0;

        for (String word : words) {
            if (isStretchy(s, word)) {
                count++;
            }
        }

        return count;
    }

    private boolean isStretchy(String s, String word) {
        int i = 0;
        int j = 0;

        while (i < s.length() && j < word.length()) {
            if (s.charAt(i) != word.charAt(j)) {
                return false;
            }

            int startS = i;
            while (i < s.length() && s.charAt(i) == s.charAt(startS)) {
                i++;
            }
            int countS = i - startS;

            // Count consecutive characters in word
            int startW = j;
            while (j < word.length() && word.charAt(j) == word.charAt(startW)) {
                j++;
            }
            int countW = j - startW;

            // word cannot have more occurrences than s
            if (countW > countS) {
                return false;
            }

            // If s has fewer than 3, we cannot stretch this group.
            // Therefore, both counts must be equal.
            if (countS < 3 && countS != countW) {
                return false;
            }
        }

        // Both strings must be completely processed
        return i == s.length() && j == word.length();
    }
}