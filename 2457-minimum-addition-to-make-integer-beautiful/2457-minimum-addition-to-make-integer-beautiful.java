class Solution {
    public long makeIntegerBeautiful(long n, int target) {
        if (digitSum(n) <= target) {
            return 0;
        }

        long original = n;
        long base = 10;

        while (digitSum(n) > target) {
            n = ((n / base) + 1) * base;
            base *= 10;
        }

        return n - original;
    }

    private int digitSum(long num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}