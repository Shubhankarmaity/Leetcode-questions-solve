/**
 * @param {number[][]} points
 * @param {number} k
 * @return {number[][]}
 */
/**
 * @param {number[][]} points
 * @param {number} k
 * @return {number[][]}
 */
var kClosest = function(points, k) {
    let heap = [];

    function distance(point) {
        return point[0] * point[0] + point[1] * point[1];
    }

    function push(point) {
        heap.push(point);

        let i = heap.length - 1;

        while (i > 0) {
            let parent = Math.floor((i - 1) / 2);

            if (distance(heap[parent]) >= distance(heap[i])) {
                break;
            }

            [heap[parent], heap[i]] = [heap[i], heap[parent]];
            i = parent;
        }
    }

    function pop() {
        let maxPoint = heap[0];
        let last = heap.pop();

        if (heap.length > 0) {
            heap[0] = last;

            let i = 0;

            while (true) {
                let left = 2 * i + 1;
                let right = 2 * i + 2;
                let largest = i;

                if (
                    left < heap.length &&
                    distance(heap[left]) > distance(heap[largest])
                ) {
                    largest = left;
                }

                if (
                    right < heap.length &&
                    distance(heap[right]) > distance(heap[largest])
                ) {
                    largest = right;
                }

                if (largest === i) {
                    break;
                }

                [heap[i], heap[largest]] = [heap[largest], heap[i]];
                i = largest;
            }
        }

        return maxPoint;
    }

    for (let point of points) {
        push(point);

        if (heap.length > k) {
            pop();
        }
    }

    return heap;
};