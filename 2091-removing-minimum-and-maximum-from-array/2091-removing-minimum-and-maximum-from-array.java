class Solution {
    public int minimumDeletions(int[] nums) {
        int n=nums.length;
        int Max=nums[0];
        int Min=nums[0];
        int i=0,j=0;

        for(int k=0;k<n;k++){
            if(Max<nums[k]){
                Max=nums[k];
                i=k;
            }
            if(Min>nums[k]){
                Min=nums[k];
                j=k;
            }
        }
        int left = Math.min(i, j);
        int right = Math.max(i, j);

        int option1 = right + 1;              // remove both from left
        int option2 = n - left;               // remove both from right
        int option3 = left + 1 + n - right;   // one from each side

        return Math.min(option1, Math.min(option2, option3));
    }
}