/**
 * @param {number[]} temperatures
 * @return {number[]}
 */
var dailyTemperatures = function(temperatures) {
    let n = temperatures.length;
    let ans = new Array(n).fill(0);
    let stack = [];

    for (let i = n - 1; i >= 0; i--) {
        while (
            stack.length > 0 &&
            temperatures[stack[stack.length - 1]] <= temperatures[i]
        ) {
            stack.pop();
        }

        if (stack.length > 0) {
            ans[i] = stack[stack.length - 1] - i;
        }

        stack.push(i);
    }

    return ans;
};