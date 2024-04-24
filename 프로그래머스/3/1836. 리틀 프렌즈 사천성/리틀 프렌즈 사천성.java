import java.util.HashSet;
import java.util.PriorityQueue;

class Solution {

    private boolean find(char sel, char[][] map) {
        int ay = -1, ax = -1;
        int by = -1, bx = -1;
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                if (map[y][x] == sel) {
                    if (ay == -1) {
                        ay = y;
                        ax = x;
                    } else {
                        by = y;
                        bx = x;
                    }
                }
            }
        }
        int sy = -1, ey = -1;
        if (ay < by) {
            sy = ay;
            ey = by;
        } else {
            sy = by;
            ey = ay;
        }
        int sx = -1, ex = -1;
        if (ax < bx) {
            sx = ax;
            ex = bx;
        } else {
            sx = bx;
            ex = ax;
        }
        if ((sy == ay && sx == ax) || (sy == by && sx == bx)) {
            boolean thisWay = true;
            for (int i = sy; i < ey + 1; i++) {
                if (map[i][sx] != sel && map[i][sx] != '.') {
                    thisWay = false;
                    break;
                }
            }
            for (int i = sx; i < ex; i++) {
                if (map[ey][i] != sel && map[ey][i] != '.') {
                    thisWay = false;
                    break;
                }
            }
            if (thisWay) {
                return true;
            }
            thisWay = true;
            for (int i = sx; i < ex; i++) {
                if (map[sy][i] != sel && map[sy][i] != '.') {
                    thisWay = false;
                    break;
                }
            }
            for (int i = sy; i < ey + 1; i++) {
                if (map[i][ex] != sel && map[i][ex] != '.') {
                    thisWay = false;
                    break;
                }
            }
            if (thisWay) {
                return true;
            }
        } else {
            boolean thisWay = true;
            for (int i = sy; i < ey + 1; i++) {
                if (map[i][sx] != sel && map[i][sx] != '.') {
                    thisWay = false;
                    break;
                }
            }
            for (int i = sx; i < ex; i++) {
                if (map[sy][i] != sel && map[sy][i] != '.') {
                    thisWay = false;
                    break;
                }
            }
            if (thisWay) {
                return true;
            }
            thisWay = true;
            for (int i = sx; i < ex; i++) {
                if (map[ey][i] != sel && map[ey][i] != '.') {
                    thisWay = false;
                    break;
                }
            }
            for (int i = sy; i < ey + 1; i++) {
                if (map[i][ex] != sel && map[i][ex] != '.') {
                    thisWay = false;
                    break;
                }
            }
            if (thisWay) {
                return true;
            }
        }
        return false;
    }

    public String solution(int m, int n, String[] board) {
        char[][] map = new char[m][n];
        for (int i = 0; i < map.length; i++) {
            map[i] = board[i].toCharArray();
        }
        HashSet<Character> cardSet = new HashSet<>();
        PriorityQueue<Character> deletable = new PriorityQueue<>();
        cardSet.add('0');
        String answer = "";
        while (cardSet.size() > 0) {
            cardSet = new HashSet<>();
            deletable = new PriorityQueue<>();
            for (int y = 0; y < map.length; y++) {
                for (int x = 0; x < map[0].length; x++) {
                    if (map[y][x] != '.' && map[y][x] != '*') {
                        if (cardSet.contains(map[y][x])) {
                            continue;
                        }
                        if (find(map[y][x], map)) {
                            cardSet.add(map[y][x]);
                            deletable.add(map[y][x]);
                        }
                    }
                }
            }
            char sel = deletable.size() > 0 ? deletable.poll() : '0';
            if ('A' <= sel && sel <= 'Z') {
                answer += sel;
                for (int y = 0; y < map.length; y++) {
                    for (int x = 0; x < map[0].length; x++) {
                        if (map[y][x] == sel) {
                            map[y][x] = '.';
                        }
                    }
                }
            }
        }
        boolean isImpossible = false;
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                if (map[y][x] != '.' && map[y][x] != '*') {
                    isImpossible = true;
                    break;
                }
            }
        }
        if (isImpossible) {
            return "IMPOSSIBLE";
        } else {
            return answer;
        }
    }
}