
class Solution {

    public String solution(int n, int m, int x, int y, int r, int c, int k) {

        int bestCnt = Math.abs(x - r) + Math.abs(y - c);
        int gap = k - bestCnt;
        if (gap % 2 != 0 || gap < 0) {
            return "impossible";
        }
        int[] direction = new int[4]; // {d, l, r, u} 순서
        if (x > r) {
            direction[3] += x - r;
        } else {
            direction[0] += r - x;
        }
        if (y > c) {
            direction[1] += y - c;
        } else {
            direction[2] += c - y;
        }

        StringBuffer answer = new StringBuffer();

        answer.append("d".repeat(direction[0]));
        int d = Math.min(gap / 2, n - (x + direction[0]));
        answer.append("d".repeat(d));
        direction[3] += d;
        gap -= 2 * d;

        answer.append("l".repeat(direction[1]));
        int l = Math.min(gap / 2, y - (direction[1]) - 1);
        answer.append("l".repeat(l));
        direction[2] += l;
        gap -= 2 * l;

        answer.append("rl".repeat(gap / 2));
        answer.append("r".repeat(direction[2]));
        answer.append("u".repeat(direction[3]));

        return answer.toString();
    }
}