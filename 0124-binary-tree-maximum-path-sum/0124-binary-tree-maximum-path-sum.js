/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val === undefined ? 0 : val)
 *     this.left = (left === undefined ? null : left)
 *     this.right = (right === undefined ? null : right)
 * }
 */

/**
 * @param {TreeNode} root
 * @return {number}
 */
var maxPathSum = function(root) {
    let maxSum = -Infinity;

    function dfs(node) {
        if (node === null) {
            return 0;
        }

        let leftGain = Math.max(0, dfs(node.left));
        let rightGain = Math.max(0, dfs(node.right));

        let pathThroughNode = leftGain + node.val + rightGain;

        maxSum = Math.max(maxSum, pathThroughNode);

        return node.val + Math.max(leftGain, rightGain);
    }

    dfs(root);

    return maxSum;
};