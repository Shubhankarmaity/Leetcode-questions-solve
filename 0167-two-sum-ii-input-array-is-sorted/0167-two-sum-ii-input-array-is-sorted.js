/**
 * @param {number[]} numbers
 * @param {number} target
 * @return {number[]}
 */
var twoSum = function(numbers, target) {
    let i=0,j=numbers.length-1;
    let ans=[];
    while(i<j){
        let sum=numbers[i]+numbers[j];
        if(target>sum){
            i++;
        }
        else if(target<sum){
            j--;
        }
        else{
            ans[0]=i+1;
            ans[1]=j+1;
            return ans;
        }
    }
};