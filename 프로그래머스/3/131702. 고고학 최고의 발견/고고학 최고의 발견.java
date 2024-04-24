class Solution {

    int[][] map;
    int[] dy = { 1, -1, 0, 0 };
    int[] dx = { 0, 0, -1, 1 };

    private void turn(int y, int x) {
        map[y][x] += 1;
        map[y][x] %= 4;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (0 <= ny && ny < map.length && 0 <= nx && nx < map[0].length) {
                map[ny][nx] += 1;
                map[ny][nx] %= 4;
            }
        }
    }

    public int solution(int[][] clockHands) {
        int maxidx = (int) Math.pow(4, clockHands.length);
        int answer = 999999999;

        for (int i = 0; i < maxidx; i++) {
            String comString = String.format("%" + clockHands.length + "s", Integer.toString(i, 4)).replace(" ", "0");
            char[] comArr = comString.toCharArray();
            int cnt = 0;
            map = new int[clockHands.length][];

            for (int j = 0; j < clockHands.length; j++) {
                map[j] = clockHands[j].clone();
            }

            for (int x = 0; x < comArr.length; x++) {
                for (int j = 0; j < comArr[x] - '0'; j++) {
                    turn(0, x);
                    cnt++;
                }
            }
            for (int y = 1; y < map.length; y++) {
                for (int x = 0; x < map[0].length; x++) {
                    while (map[y - 1][x] != 0) {
                        turn(y, x);
                        cnt++;
                    }
                }
            }
            boolean isDone = true;
            all: for (int y = 0; y < map.length; y++) {
                for (int x = 0; x < map[0].length; x++) {
                    if (map[y][x] != 0) {
                        isDone = false;
                        break all;
                    }
                }
            }
            if (isDone) {
                answer = Math.min(cnt, answer);
            }
        }
        return answer;
    }
}