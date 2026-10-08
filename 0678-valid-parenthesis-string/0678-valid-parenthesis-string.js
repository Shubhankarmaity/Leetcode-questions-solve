/**
 * @param {string} s
 * @return {boolean}
 */
var checkValidString = function(s) {
    let low=0;
    let high=0;
    let q=[];

    for(let char of s){
        if(char==='('){
            low++;
            high++;
        }
        else if(char===')'){
            low--;
            high--;
        }
        else{
            low--;
            high++
        }
        if(high<0){
            return false;
        }
        if(low<0){
            low=0;
        }
    }
    return low===0;
};