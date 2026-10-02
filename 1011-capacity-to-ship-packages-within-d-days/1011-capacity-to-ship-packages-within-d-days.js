/**
 * @param {number[]} weights
 * @param {number} days
 * @return {number}
 */
var shipWithinDays = function(weights, days) {
    let left = Math.max(...weights);
    let right = weights.reduce((sum, weight) => sum + weight, 0);

    while (left <= right) {
        let mid = Math.floor((left + right) / 2);

        let daysUsed = 1;
        let currentWeight = 0;

        for (let weight of weights) {
            if (currentWeight + weight > mid) {
                daysUsed++;
                currentWeight = weight;
            } else {
                currentWeight += weight;
            }
        }

        if (daysUsed <= days) {
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }

    return left;
};