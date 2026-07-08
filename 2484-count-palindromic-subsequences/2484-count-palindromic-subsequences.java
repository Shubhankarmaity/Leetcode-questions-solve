class Solution {
    static final int MOD = 1_000_000_007;

    public int countPalindromes(String s) {
        int n = s.length();
        char[] ch = s.toCharArray();

        long[][] leftPair = new long[10][10];
        long[][] rightPair = new long[10][10];

        int[] leftCnt = new int[10];
        int[] rightCnt = new int[10];

        // Build right pair counts
        for (int i = n - 1; i >= 0; i--) {
            int d = ch[i] - '0';
            for (int j = 0; j < 10; j++) {
                rightPair[d][j] += rightCnt[j];
            }
            rightCnt[d]++;
        }

        long ans = 0;

        for (int mid = 0; mid < n; mid++) {
            int d = ch[mid] - '0';

            // Remove current digit from right structures
            rightCnt[d]--;
            for (int j = 0; j < 10; j++) {
                rightPair[d][j] -= rightCnt[j];
            }

            // Count palindromes centered at mid
            for (int a = 0; a < 10; a++) {
                for (int b = 0; b < 10; b++) {
                    ans = (ans + leftPair[a][b] * rightPair[b][a]) % MOD;
                }
            }

            // Add current digit to left structures
            for (int j = 0; j < 10; j++) {
                leftPair[j][d] += leftCnt[j];
            }
            leftCnt[d]++;
        }

        return (int) ans;
    }
}