/**
 * @param {string} s
 * @param {number} k
 * @return {number}
 */
var characterReplacement = function(s, k) {
    let freq = new Map();

    let left = 0;
    let maxFreq = 0;
    let maxLen = 0;

    for (let right = 0; right < s.length; right++) {
        freq.set(s[right], (freq.get(s[right]) || 0) + 1);

        maxFreq = Math.max(maxFreq, freq.get(s[right]));

        while ((right - left + 1) - maxFreq > k) {
            freq.set(s[left], freq.get(s[left]) - 1);
            left++;
        }

        maxLen = Math.max(maxLen, right - left + 1);
    }

    return maxLen;
};