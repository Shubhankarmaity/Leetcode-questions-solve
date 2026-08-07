class Solution {
    public int smallestNumber(int n, int t) {
        for(int i=n;i<=n+10;i++){
            if(findDiv(i)%t==0){
                return i;
            }
        }
        return -1;
    }
    private static int findDiv(int n){
        String str=Integer.toString(n);
        int sum=1;
        for(int i=0;i<str.length();i++){
            sum*=str.charAt(i)-'0';
        }
        return sum;
    }
}