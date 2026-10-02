/**
 * @param {number[]} height
 * @return {number}
 */
var maxArea = function(height) {
    let i=0,j=height.length-1;
    let maxWat=Number.MIN_SAFE_INTEGER;

    while(i<j){
        let locmax=Math.min(height[i],height[j])*(j-i);
        maxWat=Math.max(locmax,maxWat);
        if(height[i]<=height[j]){
            i++;
        }
        else{
            j--;
        }
    }
    return maxWat;
};