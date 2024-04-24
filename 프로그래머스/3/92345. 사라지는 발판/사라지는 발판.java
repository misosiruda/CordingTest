
class Solution {
    static int[] dy = { 1, 0, -1, 0 };
    static int[] dx = { 0, 1, 0, -1 };
    static int row, col;
    static int MAX = 987654321;

    static class turn {

        boolean isWin;
        int count;

        turn(boolean isWin, int cnt) {
            this.count = cnt;
            this.isWin = isWin;
        }
    }

    public static int solution(int[][] board, int[] aloc, int[] bloc) {
        row = board.length;
        col = board[0].length;

        turn res = dfs(board, aloc, bloc, true, 0);

        return res.count;
    }

    private static turn dfs(int[][] board, int[] posA, int[] posB, boolean isATurn, int cnt) {
        int ay = posA[0];
        int ax = posA[1];
        int by = posB[0];
        int bx = posB[1];

        if ((board[ay][ax] == 0 && isATurn) || (board[by][bx] == 0 && !isATurn)) return new turn(false, cnt);

        int winCount = MAX;
        int loseCount = 0;
        int y, x;
        if (isATurn) {
            y = ay;
            x = ax;
        } else {
            y = by;
            x = bx;
        }
        board[y][x] = 0;

        turn res = null;
        boolean isMoved = false;
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];

            if ((ny < 0 || nx < 0 || ny >= row || nx >= col) || board[ny][nx] == 0) continue;
            isMoved = true;
            if (isATurn) {
                res = dfs(board, new int[] { ny, nx }, posB, !isATurn, cnt + 1);
            } else {
                res = dfs(board, posA, new int[] { ny, nx }, !isATurn, cnt + 1);
            }

            // 다음 순번이 이길 경우 현재 순번이 패배 -> 최대의 움직임
            if (res.isWin) {
                loseCount = Math.max(loseCount, res.count);
            }
            // 다음 순번이 질 경우 현재 순번이 승리 -> 최소의 움직임
            else {
                winCount = Math.min(winCount, res.count);
            }
        }

        board[y][x] = 1;
        // 어떠한 방향으로도 이동 불가
        if (!isMoved) {
            return new turn(false, cnt);
        }
        // 현재 순번이 이기는 경우
        else if (winCount != MAX) {
            return new turn(true, winCount);
        }
        // 현재 순번이 지는 경우
        else {
            return new turn(false, loseCount);
        }
    }
}