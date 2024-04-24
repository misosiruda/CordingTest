import java.util.HashSet;

class Solution {

    private class Line {
        int y;
        int x;
        int dir;

        Line(int y, int x, int dir) {
            this.y = y;
            this.x = x;
            this.dir = dir;
        }

        @Override
        public int hashCode() {
            return y * 10000000 + x * 10 + dir;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Line) {
                Line other = (Line) obj;
                if (this.y == other.y && this.x == other.x && this.dir == other.dir) {
                    return true;
                }
            }
            return false;
        }
    }

    public int solution(int[] arrows) {
        int answer = 0;
        int cen = 200000;

        HashSet<Line> lines = new HashSet<>();
        int y = cen, x = cen;
        int[] dy = { 1, 1, 0, -1, -1, -1, 0, 1 };
        int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };
        for (int i : arrows) {
            lines.add(new Line(y, x, i));
            y += dy[i];
            x += dx[i];
            boolean isNewLine = false;
            for (int j = 0; j < 8; j++) {
                if (j == i) {
                    if (lines.contains(new Line(y, x, (j + 4) % 8))) {
                        isNewLine = false;
                        break;
                    }
                }
                isNewLine |= lines.contains(new Line(y, x, j));
            }
            if (isNewLine) {
                answer++;
            }
            lines.add(new Line(y, x, i));
            lines.add(new Line(y, x, (i + 4) % 8));
            y += dy[i];
            x += dx[i];
            isNewLine = false;
            for (int j = 0; j < 8; j++) {
                if (j == i) {
                    if (lines.contains(new Line(y, x, (j + 4) % 8))) {
                        isNewLine = false;
                        break;
                    }
                }
                isNewLine |= lines.contains(new Line(y, x, j));
            }
            if (isNewLine) {
                answer++;
            }
            lines.add(new Line(y, x, (i + 4) % 8));
        }
        return answer;
    }
}