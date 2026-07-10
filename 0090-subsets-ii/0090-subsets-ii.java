import java.util.*;

class Solution {

    private static void findSubSet(int ind,int[]nums,List<Integer>ds,List<List<Integer>>anslist){
        anslist.add(new ArrayList<>(ds));
        for(int i=ind;i<nums.length;i++){
            if(i!=ind && nums[i]==nums[i-1]){
                continue;
            }
            ds.add(nums[i]);
            findSubSet(i+1,nums,ds,anslist);
            ds.remove(ds.size()-1);
        }
    }
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> anslist=new ArrayList<>();
        findSubSet(0,nums,new ArrayList<>(),anslist);
        return anslist;
    }
}