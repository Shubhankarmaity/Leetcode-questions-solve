class Solution {
    public int smallestIndex(int[] nums) {
        int n=nums.length;
        for(int i=0;i<n;i++){
            if(i==findDigitSum(nums[i])){
                return i;
            }
        }
        return -1;
    }
    private static int findDigitSum(int x){
        String s=Integer.toString(x);
        int sum=0;
        for(int i=0;i<s.length();i++){
            sum+=s.charAt(i)-'0';
        }
        return sum;
    }
}