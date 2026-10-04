/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var findKthLargest = function(nums, k) {
    let heap = [];

    function push(val) {
        heap.push(val);

        let i = heap.length - 1;

        while (i > 0) {
            let parent = Math.floor((i - 1) / 2);

            if (heap[parent] <= heap[i]) {
                break;
            }

            [heap[parent], heap[i]] = [heap[i], heap[parent]];
            i = parent;
        }
    }

    function pop() {
        let min = heap[0];
        let last = heap.pop();

        if (heap.length > 0) {
            heap[0] = last;

            let i = 0;

            while (true) {
                let left = 2 * i + 1;
                let right = 2 * i + 2;
                let smallest = i;

                if (left < heap.length && heap[left] < heap[smallest]) {
                    smallest = left;
                }

                if (right < heap.length && heap[right] < heap[smallest]) {
                    smallest = right;
                }

                if (smallest === i) {
                    break;
                }

                [heap[i], heap[smallest]] = [heap[smallest], heap[i]];
                i = smallest;
            }
        }

        return min;
    }

    for (let num of nums) {
        push(num);

        if (heap.length > k) {
            pop();
        }
    }

    return heap[0];
};