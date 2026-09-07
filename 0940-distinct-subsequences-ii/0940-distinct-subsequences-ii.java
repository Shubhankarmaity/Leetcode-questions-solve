class Solution {
    public int distinctSubseqII(String s) {
        final int MOD = 1_000_000_007;

        long[] end = new long[26];
        long total = 0;

        for (char c : s.toCharArray()) {
            int idx = c - 'a';

            long newEnd = (total + 1) % MOD;

            total = (total - end[idx] + newEnd + MOD) % MOD;
            end[idx] = newEnd;
        }

        return (int) total;
    }
}