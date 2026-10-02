/**
 * @param {string} s
 * @return {number}
 */
var lengthOfLongestSubstring = function(s) {
    let i=0,j=0;
    const set=new Set();
    let maxLen=0;

    while(j<s.length){
       while(set.has(s[j])){
            set.delete(s[i]);
            i++;
        }
        set.add(s[j]);
        maxLen=Math.max(maxLen,j-i+1);
        j++;
    }
    return maxLen;
};