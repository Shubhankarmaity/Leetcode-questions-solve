/**
 * @param {string} s
 * @param {string} t
 * @return {string}
 */
var minWindow = function(s, t) {
    if (t.length > s.length) {
        return "";
    }

    let need = new Map();

    for (let ch of t) {
        need.set(ch, (need.get(ch) || 0) + 1);
    }

    let window = new Map();

    let left = 0;
    let right = 0;

    let required = need.size;
    let formed = 0;

    let minLen = Infinity;
    let start = 0;

    while (right < s.length) {
        let ch = s[right];

        if (need.has(ch)) {
            window.set(ch, (window.get(ch) || 0) + 1);

            if (window.get(ch) === need.get(ch)) {
                formed++;
            }
        }

        while (formed === required) {
            if (right - left + 1 < minLen) {
                minLen = right - left + 1;
                start = left;
            }

            let leftChar = s[left];

            if (need.has(leftChar)) {
                window.set(leftChar, window.get(leftChar) - 1);

                if (window.get(leftChar) < need.get(leftChar)) {
                    formed--;
                }
            }

            left++;
        }

        right++;
    }

    if (minLen === Infinity) {
        return "";
    }

    return s.substring(start, start + minLen);
};