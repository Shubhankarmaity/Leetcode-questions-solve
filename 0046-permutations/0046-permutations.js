/**
 * @param {number[]} nums
 * @return {number[][]}
 */
var permute = function(nums) {
    let res = [];
    let ans = [];
    let used = new Array(nums.length).fill(false);

    function backtrack() {
        if (ans.length === nums.length) {
            res.push([...ans]);
            return;
        }

        for (let i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }

            ans.push(nums[i]);
            used[i] = true;

            backtrack();

            used[i] = false;
            ans.pop();
        }
    }

    backtrack();

    return res;
};