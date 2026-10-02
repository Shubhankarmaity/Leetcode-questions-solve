/**
 * @param {number[]} prices
 * @return {number}
 */
var maxProfit = function(prices) {
    let i=0,minPrice=prices[0];
    let maxProfit=0;
    while(i<prices.length){
        if(minPrice>prices[i]){
            minPrice=prices[i];
        }
        else{
            maxProfit=Math.max(maxProfit,prices[i]-minPrice);
        }
        i++;
    }
    return maxProfit;
};