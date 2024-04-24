import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Solution {

    public int[] solution(int m, int n, int s, int[][] timeMap) {
        int[][] count = new int[m][n];
        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[] { 0, 0 });

        long[][] dist = new long[m][n];
        for (int i = 0; i < m; ++i) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[0][0] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            if (x == m - 1 && y == n - 1 && dist[x][y] <= s) {
                break;
            }
            for (int d = 0; d < 4; ++d) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (0 <= nx && nx < m && 0 <= ny && ny < n
                        && timeMap[nx][ny] != -1
                        && dist[nx][ny] > dist[x][y] + timeMap[nx][ny]) {
                    dist[nx][ny] = dist[x][y] + timeMap[nx][ny];
                    q.add(new int[] { nx, ny });
                    count[nx][ny] = count[x][y] + 1;
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = count[m - 1][n - 1];
        answer[1] = (int) dist[m - 1][n - 1];

        return answer;
    }
}