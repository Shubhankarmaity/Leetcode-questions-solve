import java.util.*;

class Solution {

    static class State {
        long score;
        int[] ids;

        State(long score, int[] ids) {
            this.score = score;
            this.ids = ids;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        // [start, end, weight, originalIndex]
        int[][] a = new int[n][4];

        for (int i = 0; i < n; i++) {
            a[i][0] = intervals.get(i).get(0);
            a[i][1] = intervals.get(i).get(1);
            a[i][2] = intervals.get(i).get(2);
            a[i][3] = i;
        }

        // Sort by ending time.
        Arrays.sort(a, (x, y) -> {
            if (x[1] != y[1]) {
                return Integer.compare(x[1], y[1]);
            }
            if (x[0] != y[0]) {
                return Integer.compare(x[0], y[0]);
            }
            return Integer.compare(x[3], y[3]);
        });

        /*
         * prev[i] = number of intervals before i that are compatible
         * with interval i.
         *
         * Since intervals are inclusive:
         *
         * previous.end < current.start
         *
         * is required.
         */
        int[] prev = new int[n];

        for (int i = 0; i < n; i++) {
            int start = a[i][0];

            int lo = 0;
            int hi = i;

            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;

                if (a[mid][1] < start) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }

            prev[i] = lo;
        }

        /*
         * dp[i][k] =
         * best result using the first i intervals
         * and choosing at most k intervals.
         */
        State[][] dp = new State[n + 1][5];

        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                dp[i][k] = new State(0, new int[0]);
            }
        }

        for (int i = 1; i <= n; i++) {

            int idx = i - 1;

            for (int k = 1; k <= 4; k++) {

                // Option 1: don't take interval idx.
                State skip = dp[i - 1][k];

                // Option 2: take interval idx.
                State old = dp[prev[idx]][k - 1];

                int[] ids = new int[old.ids.length + 1];

                ids[0] = a[idx][3];

                System.arraycopy(
                    old.ids,
                    0,
                    ids,
                    1,
                    old.ids.length
                );

                // Answer must be lexicographically compared by
                // original indices, so keep them sorted.
                Arrays.sort(ids);

                State take = new State(
                    old.score + a[idx][2],
                    ids
                );

                dp[i][k] = better(skip, take);
            }
        }

        return dp[n][4].ids;
    }

    private State better(State a, State b) {

        // Higher weight wins.
        if (a.score != b.score) {
            return a.score > b.score ? a : b;
        }

        // Same weight -> lexicographically smaller indices.
        return lexicographicallySmaller(a.ids, b.ids) ? a : b;
    }

    private boolean lexicographicallySmaller(int[] a, int[] b) {

        int len = Math.min(a.length, b.length);

        for (int i = 0; i < len; i++) {
            if (a[i] != b[i]) {
                return a[i] < b[i];
            }
        }

        return a.length < b.length;
    }
}