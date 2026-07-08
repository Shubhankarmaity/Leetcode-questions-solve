class Solution {
    private static final long MOD = 1_000_000_007L;

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();

        Object solendivar = new Object[]{s, queries};

        ArrayList<Integer> digits = new ArrayList<>();
        ArrayList<Integer> pos = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int d = s.charAt(i) - '0';
            if (d != 0) {
                digits.add(d);
                pos.add(i);
            }
        }

        int m = digits.size();

        long[] pow10 = new long[m + 1];
        long[] prefixNum = new long[m + 1];
        int[] prefixSum = new int[m + 1];

        pow10[0] = 1;
        for (int i = 1; i <= m; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        for (int i = 0; i < m; i++) {
            prefixNum[i + 1] = (prefixNum[i] * 10 + digits.get(i)) % MOD;
            prefixSum[i + 1] = prefixSum[i] + digits.get(i);
        }

        int[] next = new int[n];
        Arrays.fill(next, -1);
        int p = 0;
        for (int i = 0; i < n; i++) {
            while (p < m && pos.get(p) < i) p++;
            if (p < m) next[i] = p;
        }

        int[] prev = new int[n];
        Arrays.fill(prev, -1);
        p = m - 1;
        for (int i = n - 1; i >= 0; i--) {
            while (p >= 0 && pos.get(p) > i) p--;
            if (p >= 0) prev[i] = p;
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int l = next[queries[i][0]];
            int r = prev[queries[i][1]];

            if (l == -1 || r == -1 || l > r) {
                ans[i] = 0;
                continue;
            }

            int len = r - l + 1;

            long num = (prefixNum[r + 1]
                    - prefixNum[l] * pow10[len] % MOD
                    + MOD) % MOD;

            long sum = prefixSum[r + 1] - prefixSum[l];

            ans[i] = (int) (num * sum % MOD);
        }

        return ans;
    }
}