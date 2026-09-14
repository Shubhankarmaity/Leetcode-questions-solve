class Solution {
    public void rotate(int[] nums, int k) {
        int n=nums.length;
        k=k%n;
        int temp[]=new int[k];
        int j=0;
        for(int i=n-k;i<n;i++){
            temp[j++]=nums[i];
        }
        for(int i=0;i<n-k;i++){
            nums[n-1-i]=nums[n-1-k-i];
        }
        for(int i=0;i<k;i++){
            nums[i]=temp[i];
        }
    }
}