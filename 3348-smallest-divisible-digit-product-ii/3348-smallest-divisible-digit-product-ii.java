import java.util.*;

class Solution {

    // factors[digit][prime]
    // prime order = 2, 3, 5, 7
    static int[][] factors = {
        {0, 0, 0, 0}, // 0
        {0, 0, 0, 0}, // 1
        {1, 0, 0, 0}, // 2
        {0, 1, 0, 0}, // 3
        {2, 0, 0, 0}, // 4
        {0, 0, 1, 0}, // 5
        {1, 1, 0, 0}, // 6
        {0, 0, 0, 1}, // 7
        {3, 0, 0, 0}, // 8
        {0, 2, 0, 0}  // 9
    };

    public String smallestNumber(String num, long t) {

        // Required factors of t: 2,3,5,7
        int[] need = new int[4];
        int[] primes = {2, 3, 5, 7};

        for (int i = 0; i < 4; i++) {
            while (t % primes[i] == 0) {
                need[i]++;
                t /= primes[i];
            }
        }

        // t contains another prime > 7
        if (t != 1) {
            return "-1";
        }

        /*
         * Convert required prime factors into actual digits.
         * Example:
         * 2^3 -> 8
         * 3^2 -> 9
         * 2*3 -> 6
         */
        int[] requiredDigits = makeDigits(need);

        // If we need more digits than num.length,
        // smallest answer has length num.length + 1.
        if (countDigits(requiredDigits) > num.length()) {
            return build(requiredDigits, num.length() + 1);
        }

        // Count prime factors present in num.
        int[] prefix = getFactors(num);

        int firstZero = num.indexOf('0');

        // If num itself is zero-free and already satisfies t.
        if (firstZero == -1 && contains(prefix, need)) {
            return num;
        }

        /*
         * Try changing one digit.
         * We go from right to left because we want
         * the smallest possible number.
         */
        for (int i = num.length() - 1; i >= 0; i--) {

            int current = num.charAt(i) - '0';

            // Remove current digit from prefix.
            for (int j = 0; j < 4; j++) {
                prefix[j] -= factors[current][j];
            }

            // If there is a zero before/equal to this position,
            // the unchanged prefix would contain zero.
            if (firstZero != -1 && i > firstZero) {
                continue;
            }

            // Try making this digit bigger.
            for (int d = current + 1; d <= 9; d++) {

                int[] remaining = new int[4];

                for (int j = 0; j < 4; j++) {
                    remaining[j] =
                        Math.max(0,
                            need[j]
                            - prefix[j]
                            - factors[d][j]);
                }

                int[] digits = makeDigits(remaining);
                int needed = countDigits(digits);
                int space = num.length() - i - 1;

                if (needed <= space) {

                    StringBuilder ans = new StringBuilder();

                    // Keep prefix
                    ans.append(num.substring(0, i));

                    // Bigger digit
                    ans.append(d);

                    // Smallest possible unused digits
                    for (int k = 0; k < space - needed; k++) {
                        ans.append('1');
                    }

                    ans.append(build(digits, needed));

                    return ans.toString();
                }
            }
        }

        /*
         * No answer with the same length.
         * Use one extra digit.
         */
        return build(requiredDigits, num.length() + 1);
    }

    // Get prime factor counts of all digits in num.
    private int[] getFactors(String s) {
        int[] count = new int[4];

        for (char c : s.toCharArray()) {
            int d = c - '0';

            for (int j = 0; j < 4; j++) {
                count[j] += factors[d][j];
            }
        }

        return count;
    }

    // Does 'have' contain all factors in 'need'?
    private boolean contains(int[] have, int[] need) {
        for (int i = 0; i < 4; i++) {
            if (have[i] < need[i]) {
                return false;
            }
        }

        return true;
    }

    /*
     * Convert prime factors into digits.
     *
     * Example:
     * 2^3 -> 8
     * 2^2 -> 4
     * 3^2 -> 9
     * 2*3 -> 6
     */
    private int[] makeDigits(int[] cnt) {

        int a = cnt[0]; // 2
        int b = cnt[1]; // 3
        int c = cnt[2]; // 5
        int d = cnt[3]; // 7

        int[] result = new int[8];

        // We store count of each digit.
        int[] digitCount = new int[10];

        // 2^3 -> 8
        digitCount[8] = a / 3;
        a %= 3;

        // 3^2 -> 9
        digitCount[9] = b / 2;
        b %= 2;

        // 2^2 -> 4
        digitCount[4] = a / 2;
        a %= 2;

        // 2 * 3 -> 6
        if (a == 1 && b == 1) {
            digitCount[6]++;
            a = 0;
            b = 0;
        }

        // 3 * 4 -> 2 * 6
        if (b == 1 && digitCount[4] > 0) {
            digitCount[4]--;
            digitCount[6]++;
            digitCount[2]++;
            b = 0;
        }

        // Remaining 2
        digitCount[2] += a;

        // Remaining 3
        digitCount[3] += b;

        // 5 and 7
        digitCount[5] = c;
        digitCount[7] = d;

        return digitCount;
    }

    private int countDigits(int[] digitCount) {
        int total = 0;

        for (int i = 0; i <= 9; i++) {
            total += digitCount[i];
        }

        return total;
    }

    // Build digits in increasing order.
    private String build(int[] digitCount, int totalLength) {

        StringBuilder sb = new StringBuilder();

        for (int d = 1; d <= 9; d++) {
            for (int k = 0; k < digitCount[d]; k++) {
                sb.append(d);
            }
        }

        // Add 1s at the beginning if necessary.
        while (sb.length() < totalLength) {
            sb.insert(0, '1');
        }

        return sb.toString();
    }
}