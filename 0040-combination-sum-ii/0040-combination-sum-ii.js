/**
 * @param {number[]} candidates
 * @param {number} target
 * @return {number[][]}
 */
var combinationSum2 = function(candidates, target) {
    candidates.sort((a, b) => a - b);
    let result = [];
    function backtrack(start, target, current) {
        if (target === 0) {
            result.push([...current]);
            return;
        }

        for (let i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] === candidates[i - 1]) {
                continue;
            }

            if (candidates[i] > target) {
                break;
            }

            current.push(candidates[i]);

            backtrack(i + 1, target - candidates[i], current);

            current.pop();
        }
    }
    backtrack(0, target, []);
    return result;
};