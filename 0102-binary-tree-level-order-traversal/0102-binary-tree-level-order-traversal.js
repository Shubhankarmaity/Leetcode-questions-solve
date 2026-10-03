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
 * @return {number[][]}
 */
var levelOrder = function(root) {
    if (root === null) {
        return [];
    }

    let ans = [];
    let queue = [root];
    let front = 0;

    while (front < queue.length) {
        let size = queue.length - front;
        let level = [];

        for (let i = 0; i < size; i++) {
            let node = queue[front++];
            level.push(node.val);

            if (node.left !== null) {
                queue.push(node.left);
            }

            if (node.right !== null) {
                queue.push(node.right);
            }
        }

        ans.push(level);
    }

    return ans;
};