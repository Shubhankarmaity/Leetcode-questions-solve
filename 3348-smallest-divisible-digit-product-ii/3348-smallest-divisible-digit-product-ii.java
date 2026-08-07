import java.util.*;

class Solution {
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

        int[] need = new int[4];
        int[] primes = {2, 3, 5, 7};

        for (int i = 0; i < 4; i++) {
            while (t % primes[i] == 0) {
                need[i]++;
                t /= primes[i];
            }
        }

        if (t != 1) {
            return "-1";
        }
        int[] requiredDigits = makeDigits(need);
        if (countDigits(requiredDigits) > num.length()) {
            return build(requiredDigits, num.length() + 1);
        }
        int[] prefix = getFactors(num);

        int firstZero = num.indexOf('0');
        if (firstZero == -1 && contains(prefix, need)) {
            return num;
        }
        for (int i = num.length() - 1; i >= 0; i--) {

            int current = num.charAt(i) - '0';
            for (int j = 0; j < 4; j++) {
                prefix[j] -= factors[current][j];
            }
            if (firstZero != -1 && i > firstZero) {
                continue;
            }
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
                    ans.append(num.substring(0, i));
                    ans.append(d);
                    for (int k = 0; k < space - needed; k++) {
                        ans.append('1');
                    }

                    ans.append(build(digits, needed));

                    return ans.toString();
                }
            }
        }
        return build(requiredDigits, num.length() + 1);
    }
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
    private boolean contains(int[] have, int[] need) {
        for (int i = 0; i < 4; i++) {
            if (have[i] < need[i]) {
                return false;
            }
        }

        return true;
    }
    private int[] makeDigits(int[] cnt) {

        int a = cnt[0]; // 2
        int b = cnt[1]; // 3
        int c = cnt[2]; // 5
        int d = cnt[3]; // 7

        int[] result = new int[8];
        int[] digitCount = new int[10];
        digitCount[8] = a / 3;
        a %= 3;
        digitCount[9] = b / 2;
        b %= 2;
        digitCount[4] = a / 2;
        a %= 2;
        if (a == 1 && b == 1) {
            digitCount[6]++;
            a = 0;
            b = 0;
        }
        if (b == 1 && digitCount[4] > 0) {
            digitCount[4]--;
            digitCount[6]++;
            digitCount[2]++;
            b = 0;
        }
        digitCount[2] += a;
        digitCount[3] += b;
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
    private String build(int[] digitCount, int totalLength) {

        StringBuilder sb = new StringBuilder();

        for (int d = 1; d <= 9; d++) {
            for (int k = 0; k < digitCount[d]; k++) {
                sb.append(d);
            }
        }
        while (sb.length() < totalLength) {
            sb.insert(0, '1');
        }

        return sb.toString();
    }
}