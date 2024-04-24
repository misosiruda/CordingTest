import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Solution {

    private int[][] map;
    boolean[][] verVisited;
    boolean[][] horVisited;

    private class Q {
        public int[] pos;
        public int count;

        public Q(int[] pos, int count) {
            this.pos = pos;
            this.count = count;
        }
    }

    private int[][] rotate(int[] pos) {
        int[][] result = new int[4][1];
        int ly = pos[0], lx = pos[1];
        int ry = pos[2], rx = pos[3];
        if (ly == ry) {// 가로
            if (map[ly - 1][lx] == 0 && map[ry - 1][rx] == 0) {// 위쪽 두공간이 비었으면
                result[0] = new int[] { ly, lx, ly - 1, lx };
                result[1] = new int[] { ry, rx, ry - 1, rx };
            }
            if (map[ly + 1][lx] == 0 && map[ry + 1][rx] == 0) {// 아래쪽 두공간이 비었으면
                result[2] = new int[] { ly, lx, ly + 1, lx };
                result[3] = new int[] { ry, rx, ry + 1, rx };
            }
        }
        if (lx == rx) {// 세로
            if (map[ly][lx - 1] == 0 && map[ry][rx - 1] == 0) {// 왼쪽 두공간이 비었으면
                result[0] = new int[] { ly, lx, ly, lx - 1 };
                result[1] = new int[] { ry, rx, ry, rx - 1 };
            }
            if (map[ly][lx + 1] == 0 && map[ry][rx + 1] == 0) {// 오른쪽 두공간이 비었으면
                result[2] = new int[] { ly, lx, ly, lx + 1 };
                result[3] = new int[] { ry, rx, ry, rx + 1 };
            }
        }
        return result;
    }

    private boolean checkCanGo(int[] pos) {
        int ly = pos[0], lx = pos[1];
        int ry = pos[2], rx = pos[3];
        if (map[ly][lx] == 0 && map[ry][rx] == 0) {
            if (ly == ry) {
                if (horVisited[ly][lx] && horVisited[ry][rx]) {
                    return false;
                } else {
                    horVisited[ly][lx] = true;
                    horVisited[ry][rx] = true;
                }
            } else if (lx == rx) {
                if (verVisited[ly][lx] && verVisited[ry][rx]) {
                    return false;
                } else {
                    verVisited[ly][lx] = true;
                    verVisited[ry][rx] = true;
                }
            }
            return true;
        }
        return false;
    }

    private boolean isOver(int[] pos, int[] end) {
        int ly = pos[0], lx = pos[1];
        int ry = pos[2], rx = pos[3];
        int ey = end[0], ex = end[1];
        if (ly == ey && lx == ex) {
            return true;
        } else if (ry == ey && rx == ex) {
            return true;
        }
        return false;
    }

    public int solution(int[][] board) {
        int answer = 0;
        int[] dy = { 1, 0, -1, 0 };
        int[] dx = { 0, 1, 0, -1 };
        int[] end = { board.length, board.length };
        map = new int[board.length + 2][board.length + 2];
        for (int i = 0; i < map.length; i++) {
            Arrays.fill(map[i], 1);
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                map[i + 1][j + 1] = board[i][j];
            }
        }
        verVisited = new boolean[map.length][map.length];
        horVisited = new boolean[map.length][map.length];
        Deque<Q> queue = new ArrayDeque<>();
        queue.add(new Q(new int[] { 1, 1, 1, 2 }, 0));
        while (queue.size() > 0) {
            Q qNow = queue.pollFirst();
            int[] pos = qNow.pos;
            int count = qNow.count;
            int ly = pos[0], lx = pos[1];
            int ry = pos[2], rx = pos[3];
            for (int i = 0; i < 4; i++) {
                int[] nextPos = { ly + dy[i], lx + dx[i], ry + dy[i], rx + dx[i] };
                if (checkCanGo(nextPos)) {
                    if (isOver(nextPos, end)) {
                        return count + 1;
                    }
                    queue.addLast(new Q(nextPos, count + 1));
                }
            }
            int[][] nextRotate = rotate(pos);
            for (int i = 0; i < nextRotate.length; i++) {
                if (nextRotate[i][0] == 0) {
                    continue;
                }
                if (isOver(nextRotate[i], end)) {
                    return count + 1;
                }
                ly = nextRotate[i][0];
                lx = nextRotate[i][1];
                ry = nextRotate[i][2];
                rx = nextRotate[i][3];
                if (ly == ry) {
                    if (horVisited[ly][lx] && horVisited[ry][rx]) {
                        continue;
                    } else {
                        queue.addLast(new Q(nextRotate[i], count + 1));
                    }
                } else if (lx == rx) {
                    if (verVisited[ly][lx] && verVisited[ry][rx]) {
                        continue;
                    } else {
                        queue.addLast(new Q(nextRotate[i], count + 1));
                    }
                }
            }
        }
        return answer;
    }
}