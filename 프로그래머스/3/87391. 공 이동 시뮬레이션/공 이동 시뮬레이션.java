
class Solution {

    public long solution(int n, int m, int ex, int ey, int[][] queries) {
        long answer = 0;
        long lx = ex, ly = ey;
        long rx = ex, ry = ey;
        for (int i = queries.length - 1; i >= 0; i--) {
            int dir = queries[i][0];
            int dist = queries[i][1];
            if ((rx == 0 && dir == 3) || (ry == 0 && dir == 1) ||
                    (lx == n - 1 && dir == 2) || (ly == m - 1 && dir == 0)) {
                return 0;
            }
            if (dir == 0) {
                if (ly != 0) {
                    ly += dist;
                }
                ry = Math.min(ry + dist, m - 1);
            } else if (dir == 1) {
                if (ry != m - 1) {
                    ry -= dist;
                }
                ly = Math.max(ly - dist, 0);
            } else if (dir == 2) {
                if (lx != 0) {
                    lx += dist;
                }
                rx = Math.min(rx + dist, n - 1);
            } else if (dir == 3) {
                if (rx != n - 1) {
                    rx -= dist;
                }
                lx = Math.max(lx - dist, 0);
            }
            if (lx >= n || ly >= m || rx < 0 || ry < 0) {
                return 0;
            }
        }
        answer = (long) ((long) ((long) rx - (long) lx + 1) * (long) ((long) ry - (long) ly + 1));
        return answer;
    }
}