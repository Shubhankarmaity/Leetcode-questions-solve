class Solution {
    public boolean checkDivisibility(int n) {
        return n % (isSum(n) + isProduct(n)) == 0;
    }

    private static int isSum(int n) {
        int sum = 0;
        String str = Integer.toString(n);

        for (int i = 0; i < str.length(); i++) {
            sum += str.charAt(i) - '0';
        }

        return sum;
    }

    private static int isProduct(int n) {
        int prod = 1;
        String str = Integer.toString(n);

        for (int i = 0; i < str.length(); i++) {
            prod *= str.charAt(i) - '0';
        }

        return prod;
    }
}