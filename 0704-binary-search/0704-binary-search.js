/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
var search = function(nums, target) {
    let n=nums.length;
    let i=0,j=n-1;
    while(i<=j){
        let mid=Math.floor((i+j)/2);
        if(nums[mid]===target){
            return mid;
        }
        else if(nums[mid]<target){
            i=mid+1;
        }
        else{
            j=mid-1;
        }
    }
    return -1;
};