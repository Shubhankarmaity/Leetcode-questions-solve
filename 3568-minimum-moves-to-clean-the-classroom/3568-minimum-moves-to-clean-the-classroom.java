import java.util.*;

class Solution {
    static class State {
        int r, c, energy, mask;

        State(int r, int c, int energy, int mask) {
            this.r = r;
            this.c = c;
            this.energy = energy;
            this.mask = mask;
        }
    }

    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int sr = -1, sc = -1;
        List<int[]> litter = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);
                if (ch == 'S') {
                    sr = i;
                    sc = j;
                } else if (ch == 'L') {
                    litter.add(new int[]{i, j});
                }
            }
        }

        int k = litter.size();
        if (k == 0) return 0;

        int[][] id = new int[m][n];
        for (int[] row : id) Arrays.fill(row, -1);

        for (int i = 0; i < k; i++) {
            id[litter.get(i)[0]][litter.get(i)[1]] = i;
        }

        int[][][] visited = new int[m][n][1 << k];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(visited[i][j], -1);
            }
        }

        Queue<State> q = new ArrayDeque<>();
        q.offer(new State(sr, sc, energy, 0));
        visited[sr][sc][0] = energy;

        int target = (1 << k) - 1;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int moves = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                State cur = q.poll();

                if (cur.mask == target) return moves;

                for (int[] d : dirs) {
                    int nr = cur.r + d[0];
                    int nc = cur.c + d[1];

                    if (nr < 0 || nr >= m || nc < 0 || nc >= n ||
                        classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }

                    int newEnergy = cur.energy - 1;
                    if (newEnergy < 0) continue;

                    int newMask = cur.mask;
                    char ch = classroom[nr].charAt(nc);

                    if (ch == 'L') {
                        newMask |= 1 << id[nr][nc];
                    }

                    if (ch == 'R') {
                        newEnergy = energy;
                    }

                    if (visited[nr][nc][newMask] >= newEnergy) continue;

                    visited[nr][nc][newMask] = newEnergy;
                    q.offer(new State(nr, nc, newEnergy, newMask));
                }
            }

            moves++;
        }

        return -1;
    }
}