class Solution {
    public boolean isGood(int[] nums) {
        int n=nums.length;
        Arrays.sort(nums);
        int []ans=new int[nums[n-1]+1];
        int i=1;

        while(i<=ans.length){
            if(i==ans.length){
                ans[i-1]=i-1;
                break;
            }
            ans[i-1]=i;
            i++;
        }

        if(ans.length!=nums.length){
            return false;
        }
        int j=0;
        while(j<n){
            if(nums[j]!=ans[j]){
                return false;
            }
            j++;
        }
        return true;
    }
}