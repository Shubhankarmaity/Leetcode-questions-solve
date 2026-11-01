class Solution {
    public long maxTotalValue(int[] nums, int k) {
        int n=nums.length;
        long val=Long.MIN_VALUE;
        int i=0,j=n-1;

        if(n<2){
            return 0;
        }

        Arrays.sort(nums);
        while(i<j){
            long newVal=(long) k *(nums[j]-nums[i]);
            val=Math.max(val,newVal);
            i++;
            j--;
        }

        return val;
    }
}