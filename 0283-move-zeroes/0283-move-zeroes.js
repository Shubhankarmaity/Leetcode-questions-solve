/**
 * @param {number[]} nums
 * @return {void} Do not return anything, modify nums in-place instead.
 */
var moveZeroes = function(nums) {
    let lastZero=0;
    let n=nums.length;
    if(n==1){
        return;
    }
    for(let i=0;i<n;i++){
        if(nums[i]!==0){
            let temp=nums[lastZero];
            nums[lastZero]=nums[i];
            nums[i]=temp;
            lastZero++;
        }
    }
};