class Solution {
    public long sumAndMultiply(int n) {
        int sum = 0;
        String str = Integer.toString(n);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '0') {
                sb.append(str.charAt(i));
                sum += str.charAt(i) - '0';
            }
        }

        if (sb.length() == 0) return 0;

        long x = Long.parseLong(sb.toString());
        return x * sum;
    }
}