/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number[]}
 */
var topKFrequent = function(nums, k) {
    const map=new Map();
    for(let num of nums){
        map.set(num,(map.get(num)||0)+1);
    }
    let arr = Array.from(map.entries());
    arr.sort((a,b)=>b[1]-a[1]);
    let ans=[];
    for(let i=0;i<k;i++){
        ans.push(arr[i][0]);
    }
    return ans;
};