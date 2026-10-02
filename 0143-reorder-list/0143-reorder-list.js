/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val === undefined ? 0 : val)
 *     this.next = (next === undefined ? null : next)
 * }
 */

/**
 * @param {ListNode} head
 * @return {void} Do not return anything, modify head in-place instead.
 */
var reorderList = function(head) {
    if (head === null || head.next === null) {
        return;
    }

    // Step 1: Find the middle
    let slow = head;
    let fast = head;

    while (fast.next !== null && fast.next.next !== null) {
        slow = slow.next;
        fast = fast.next.next;
    }

    // Step 2: Reverse the second half
    let second = slow.next;
    slow.next = null;

    let prev = null;

    while (second !== null) {
        let next = second.next;
        second.next = prev;
        prev = second;
        second = next;
    }

    // Step 3: Merge the two halves
    let first = head;
    second = prev;

    while (second !== null) {
        let firstNext = first.next;
        let secondNext = second.next;

        first.next = second;
        second.next = firstNext;

        first = firstNext;
        second = secondNext;
    }
};