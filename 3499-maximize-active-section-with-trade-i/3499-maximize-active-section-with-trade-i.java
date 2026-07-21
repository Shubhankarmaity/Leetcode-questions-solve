class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int n = s.length();

        int totalOnes = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') totalOnes++;
        }

        if (totalOnes == 0) return 0;

        List<Integer> zeros = new ArrayList<>();
        List<Integer> ones = new ArrayList<>();

        int i = 0;

        while (i < n) {
            char ch = s.charAt(i);
            int j = i;

            while (j < n && s.charAt(j) == ch) {
                j++;
            }

            if (ch == '0')
                zeros.add(j - i);
            else
                ones.add(j - i);

            i = j;
        }

        int ans = totalOnes;

        // A valid trade requires a 1-block with zero-blocks on both sides.
        for (int k = 1; k < zeros.size(); k++) {
            if (k - 1 < ones.size()) {
                ans = Math.max(ans,
                        totalOnes + zeros.get(k - 1) + zeros.get(k));
            }
        }

        return ans;
    }
}