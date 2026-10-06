/**
 * @param {number[]} nums
 * @return {number[][]}
 */
var subsets = function(nums) {
    let res=[];
    let ans=[];
    findSubSet(nums,res,ans,0);
    return res;
};
/**
 * @param {number[]} nums
 * @param {number[]} res
 * @param {number[]} ans
 * @param {number} idx
 * @return {void}
 */
 var findSubSet=function(nums,res,ans,idx){
    if(idx===nums.length){
        res.push([...ans]);
        return;
    }
    ans.push(nums[idx]);
    findSubSet(nums,res,ans,idx+1);
    ans.pop();
    findSubSet(nums,res,ans,idx+1);
 }