class Solution {
    public long countCommas(long n) {
        long count = 0;
        long start = 1000;
        long commas = 1;

        while (start <= n) {
            long end = start * 1000 - 1;

            if (end < start) { // overflow protection
                end = Long.MAX_VALUE;
            }

            long last = Math.min(n, end);

            count += (last - start + 1) * commas;

            start *= 1000;
            commas++;
        }

        return count;
    }
}