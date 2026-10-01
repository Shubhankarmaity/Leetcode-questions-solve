/**
 * @param {number[]} nums
 * @return {number}
 */
var removeDuplicates = function(nums) {
    let n=nums.length;
    let lastIdx=0;
    if(n==1){
        return 1;
    }
    for(let i=1;i<n;i++){
        if(nums[i]!=nums[lastIdx]){
            nums[lastIdx+1]=nums[i];
            lastIdx++;
        }
    }
    return lastIdx+1;
};