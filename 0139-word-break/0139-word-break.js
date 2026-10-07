/**
 * @param {string} s
 * @param {string[]} wordDict
 * @return {boolean}
 */
var wordBreak = function(s, wordDict) {
    let set = new Set(wordDict);
    let dp = new Array(s.length + 1).fill(false);

    dp[0] = true;

    for (let i = 1; i <= s.length; i++) {
        for (let j = 0; j < i; j++) {
            let word = s.substring(j, i);

            if (dp[j] && set.has(word)) {
                dp[i] = true;
                break;
            }
        }
    }

    return dp[s.length];
};