/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var subarraySum = function(nums, k) {
    let map = new Map();
    map.set(0, 1);

    let sum = 0;
    let count = 0;

    for (let num of nums) {
        sum += num;

        let needed = sum - k;

        if (map.has(needed)) {
            count += map.get(needed);
        }

        map.set(sum, (map.get(sum) || 0) + 1);
    }

    return count;
};