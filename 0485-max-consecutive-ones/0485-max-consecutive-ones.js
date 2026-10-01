/**
 * @param {number[]} nums
 * @return {number}
 */
var findMaxConsecutiveOnes = function(nums) {
    let currCount=0,maxCount=0;
    for(let num of nums){
        if(num===1){
            currCount++;
            maxCount=Math.max(currCount,maxCount);
        }
        else{
            currCount=0;
        }
    }
    return maxCount
};