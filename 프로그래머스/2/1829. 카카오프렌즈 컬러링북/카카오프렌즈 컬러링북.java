import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        int[] dy = { 1, -1, 0, 0 };
        int[] dx = { 0, 0, -1, 1 };
        Deque<Integer[]> queue = new ArrayDeque<>();// {y, x, 현재 색깔 num}
        for (int y = 0; y < m; y++) {
            for (int x = 0; x < n; x++) {
                if (picture[y][x] == 0) {
                    continue;
                }
                numberOfArea++;
                int count = 1;
                queue.addLast(new Integer[] { y, x, picture[y][x] });
                picture[y][x] = 0;
                while (queue.size() > 0) {
                    Integer[] qN = queue.pollFirst();
                    int cy = qN[0];
                    int cx = qN[1];
                    int color = qN[2];
                    for (int i = 0; i < 4; i++) {
                        int ny = cy + dy[i];
                        int nx = cx + dx[i];
                        if (0 <= ny && ny < m && 0 <= nx && nx < n && picture[ny][nx] == color) {
                            count++;
                            queue.addLast(new Integer[] { ny, nx, color });
                            picture[ny][nx] = 0;
                        }
                    }
                }
                if (maxSizeOfOneArea < count) {
                    maxSizeOfOneArea = count;
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
}