class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n=nums.length;
        int maxArr[]=new int[n];
        int minArr[]=new int[n];
        int maxVal=nums[0];
        int minVal=nums[n-1];
        for(int i=0;i<n;i++){
            if(nums[i]>maxVal){
                maxVal=nums[i];
            }
            maxArr[i]=maxVal;
        }
        for(int i=n-1;i>=0;i--){
            if(nums[i]<minVal){
                minVal=nums[i];
            }
            minArr[i]=minVal;
        }
        for(int i=0;i<n;i++){
            if((maxArr[i]-minArr[i])<=k){
                return i;
            }
        }
        return -1;
    }
}