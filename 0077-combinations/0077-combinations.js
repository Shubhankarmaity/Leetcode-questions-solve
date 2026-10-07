/**
 * @param {number} n
 * @param {number} k
 * @return {number[][]}
 */
var combine = function(n, k) {
    let result = [];
    let current = [];

    function backtrack(start) {
        if (current.length === k) {
            result.push([...current]);
            return;
        }

        for (let i = start; i <= n; i++) {
            current.push(i);

            backtrack(i + 1);

            current.pop();
        }
    }

    backtrack(1);

    return result;
};