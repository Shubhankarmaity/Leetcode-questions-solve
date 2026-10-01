/**
 * @param {string} s
 * @param {string} t
 * @return {boolean}
 */
var isAnagram = function(s, t) {
    if(s.length!=t.length){
        return false;
    }
    let arrS = s.split('');
    let arrT = t.split('');

    arrS.sort();
    arrT.sort();
    for(let i=0;i<s.length;i++){
        if(arrS[i]!=arrT[i]){
            return false;
            break;
        }
    }
    return true;
};