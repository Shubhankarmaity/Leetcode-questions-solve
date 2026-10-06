// /**
//  * @param {number[]} gas
//  * @param {number[]} cost
//  * @return {number}
//  */
// var canCompleteCircuit = function(gas, cost) {
//     let n=gas.length;
//     let i=0;
//     while(i<n){
//         if(gas[i]>=cost[i]){
//             let count=0;
//             let rem=0;
//             let j=i;
//             while(count<n){
//                 rem=rem+gas[j]-cost[j];
//                 j=(j+1)%n;
//                 if(rem<0){
//                     break;
//                 }
//                 count++;
//             }
//             if(count===n){
//                 return i;
//             }
//         }
//         i++;
//     }
//     return -1;
// };

/**
 * @param {number[]} gas
 * @param {number[]} cost
 * @return {number}
 */
var canCompleteCircuit = function(gas, cost) {
    let total = 0;
    let tank = 0;
    let start = 0;

    for (let i = 0; i < gas.length; i++) {
        let diff = gas[i] - cost[i];

        total += diff;
        tank += diff;

        if (tank < 0) {
            start = i + 1;
            tank = 0;
        }
    }

    if (total < 0) {
        return -1;
    }

    return start;
};