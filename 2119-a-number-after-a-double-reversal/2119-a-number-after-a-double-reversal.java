class Solution {
    public boolean isSameAfterReversals(int num) {
        int rev=Math.abs(reverse(num));
        int rev2=reverse(rev);

        if(rev2==num){
            return true;
        }
        return false;
    }
    private static int reverse(int num){
        int rev=0;
        while(num>0){
            int digit=num%10;
            rev=(rev*10) + digit;
            num=num/10;
        }
        return rev;
    }
}