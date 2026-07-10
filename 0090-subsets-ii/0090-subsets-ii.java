import java.util.*;

class Solution {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> ans = new ArrayList<>();
        helper(nums, 0, new ArrayList<>(), ans, false);

        return ans;
    }

    private void helper(int[] nums, int index,
                        List<Integer> curr,
                        List<List<Integer>> ans,
                        boolean prevTaken) {

        if (index == nums.length) {
            ans.add(new ArrayList<>(curr));
            return;
        }

        // Don't take current
        helper(nums, index + 1, curr, ans, false);

        // If current is duplicate and previous duplicate wasn't taken,
        // don't take this one.
        if (index > 0 && nums[index] == nums[index - 1] && !prevTaken) {
            return;
        }

        // Take current
        curr.add(nums[index]);
        helper(nums, index + 1, curr, ans, true);
        curr.remove(curr.size() - 1);
    }
}