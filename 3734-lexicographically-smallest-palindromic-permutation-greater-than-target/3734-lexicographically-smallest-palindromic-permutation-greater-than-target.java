class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] cnt = new int[26];

        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        int odd = 0;
        char mid = 0;

        for (int i = 0; i < 26; i++) {
            if ((cnt[i] & 1) == 1) {
                odd++;
                mid = (char) ('a' + i);
            }
        }

        if (odd > 1) return "";

        int[] half = new int[26];
        for (int i = 0; i < 26; i++) {
            half[i] = cnt[i] / 2;
        }

        int halfLen = n / 2;
        StringBuilder left = new StringBuilder();

        for (int pos = 0; pos < halfLen; pos++) {
            boolean found = false;

            for (int c = 0; c < 26; c++) {
                if (half[c] == 0) continue;

                half[c]--;
                left.append((char) ('a' + c));

                if (canMakeGreater(left, half, mid, target)) {
                    found = true;
                    break;
                }

                left.deleteCharAt(left.length() - 1);
                half[c]++;
            }

            if (!found) return "";
        }

        String leftStr = left.toString();
        StringBuilder ans = new StringBuilder(leftStr);

        if (n % 2 == 1) {
            ans.append(mid);
        }

        ans.append(new StringBuilder(leftStr).reverse());

        String result = ans.toString();
        return result.compareTo(target) > 0 ? result : "";
    }

    private boolean canMakeGreater(
            StringBuilder prefix,
            int[] half,
            char mid,
            String target) {

        StringBuilder left = new StringBuilder(prefix);

        for (int c = 25; c >= 0; c--) {
            for (int k = 0; k < half[c]; k++) {
                left.append((char) ('a' + c));
            }
        }

        String leftStr = left.toString();
        StringBuilder palindrome = new StringBuilder(leftStr);

        if (target.length() % 2 == 1) {
            palindrome.append(mid);
        }

        palindrome.append(new StringBuilder(leftStr).reverse());

        return palindrome.toString().compareTo(target) > 0;
    }
}