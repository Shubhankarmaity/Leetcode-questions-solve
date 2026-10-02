/**
 * @param {number[][]} isInfected
 * @return {number}
 */
var containVirus = function(isInfected) {
    let rows = isInfected.length;
    let cols = isInfected[0].length;
    let totalWalls = 0;

    let dirs = [[1, 0], [-1, 0], [0, 1], [0, -1]];

    while (true) {
        let visited = Array.from(
            { length: rows },
            () => Array(cols).fill(false)
        );

        let regions = [];
        let frontiers = [];
        let walls = [];

        for (let r = 0; r < rows; r++) {
            for (let c = 0; c < cols; c++) {

                if (isInfected[r][c] !== 1 || visited[r][c]) {
                    continue;
                }

                let queue = [[r, c]];
                let cells = [];
                let frontier = new Set();
                let wallCount = 0;

                visited[r][c] = true;

                for (let q = 0; q < queue.length; q++) {
                    let [x, y] = queue[q];
                    cells.push([x, y]);

                    for (let [dx, dy] of dirs) {
                        let nx = x + dx;
                        let ny = y + dy;

                        if (
                            nx < 0 || nx >= rows ||
                            ny < 0 || ny >= cols
                        ) {
                            continue;
                        }

                        if (isInfected[nx][ny] === 1 && !visited[nx][ny]) {
                            visited[nx][ny] = true;
                            queue.push([nx, ny]);
                        } else if (isInfected[nx][ny] === 0) {
                            frontier.add(nx + "," + ny);
                            wallCount++;
                        }
                    }
                }

                regions.push(cells);
                frontiers.push(frontier);
                walls.push(wallCount);
            }
        }

        if (regions.length === 0) {
            break;
        }

        let best = -1;

        for (let i = 0; i < frontiers.length; i++) {
            if (frontiers[i].size > 0) {
                if (
                    best === -1 ||
                    frontiers[i].size > frontiers[best].size
                ) {
                    best = i;
                }
            }
        }

        if (best === -1) {
            break;
        }

        totalWalls += walls[best];

        for (let [x, y] of regions[best]) {
            isInfected[x][y] = -1;
        }

        for (let i = 0; i < regions.length; i++) {
            if (i === best) {
                continue;
            }

            for (let cell of frontiers[i]) {
                let [x, y] = cell.split(",").map(Number);
                isInfected[x][y] = 1;
            }
        }
    }

    return totalWalls;
};