class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n=nums.length;
        int []ans=new int[n];

        for(int i=0;i<n;i++){
            int j=(i+1)%n;
            while(j<n){
                if(nums[j]>nums[i]){
                    ans[i]=nums[j];
                    break;
                }
                else if(i==j){
                    ans[i]=-1;
                    break;
                }
                j=(j+1)%n;
            }
        }
        return ans;
    }
}