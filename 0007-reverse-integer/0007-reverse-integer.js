/**
 * @param {number} x
 * @return {number}
 */
var reverse = function(x) {
    const reversedNumber = Number(Math.abs(x).toString().split("").reverse().join(""));
    
    if (reversedNumber > 2147483647) {
        return 0;
    }
    
    return x < 0 ? -reversedNumber : reversedNumber;
};
