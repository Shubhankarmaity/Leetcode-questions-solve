/**
 * @param {string} s
 * @return {number}
 */
var scoreOfParentheses = function(s) {
    let stack = [0];

    for (let par of s) {
        if (par === '(') {
            stack.push(0);
        } else {
            let inner = stack.pop();
            let score = Math.max(2 * inner, 1);

            stack[stack.length - 1] += score;
        }
    }

    return stack[0];
};