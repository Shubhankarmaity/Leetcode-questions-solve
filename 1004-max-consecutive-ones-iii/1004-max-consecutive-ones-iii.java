class Solution {
    public int longestOnes(int[] nums, int k) {
        int n=nums.length;
        int i=0,j=0;
        int count=0;
        int maxLen=Integer.MIN_VALUE;

        while(i<n && j<n){
            if(nums[j]==1){
                j++;
            }
            else if(nums[j]==0 && count<k){
                count++;
                j++;
            }
            else{
                maxLen=Math.max(maxLen,j-i);
                i++;
                j=i;
                count=0;
            }
        }
        maxLen=Math.max(maxLen,j-i);
        return maxLen;
    }
}