class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        int n=nums.length;
        List<Integer> ans=new ArrayList<>();

        Arrays.sort(nums);
        int st=nums[0]+1;
        int end=nums[n-1];
        int k=1;

        for(int i=st;i<end;i++){
            if(nums[k]!=i){
                ans.add(i);
            }
            else{
                k++;
            }
        }
        return ans;
    }
}