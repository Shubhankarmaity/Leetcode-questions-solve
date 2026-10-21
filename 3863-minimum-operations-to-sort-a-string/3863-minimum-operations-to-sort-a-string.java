class Solution {
    public int minOperations(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();

        char min = 'z';
        char max = 'a';
        boolean sorted = true;

        for (int i = 0; i < n; i++) {
            min = (char) Math.min(min, arr[i]);
            max = (char) Math.max(max, arr[i]);

            if (i > 0 && arr[i] < arr[i - 1]) {
                sorted = false;
            }
        }

        // Already sorted
        if (sorted) {
            return 0;
        }

        // Cannot sort the entire string in one operation
        if (n == 2) {
            return -1;
        }

        // Minimum at the beginning or maximum at the end
        if (arr[0] == min || arr[n - 1] == max) {
            return 1;
        }

        // Minimum or maximum exists in the middle
        for (int i = 1; i < n - 1; i++) {
            if (arr[i] == min || arr[i] == max) {
                return 2;
            }
        }

        // Minimum is at the end and maximum is at the beginning,
        // with neither appearing in the middle
        return 3;
    }
}