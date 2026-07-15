class Solution {
    public int gcdOfOddEvenSums(int n) {
        int sumOdd=1;
        int sumEven=2;
        int i=1;
        int j=2;
        int count=1;
        while(count<n){
            i+=2;
            sumOdd+=i;
            j+=2;
            sumEven+=j;
            count++;
        }
        int ans=findGCD(sumOdd,sumEven);
        return ans;
    }
    private static int findGCD(int a, int b) {
        if (b == 0) {
            return a;
        }
        return findGCD(b, a % b);
    }
}