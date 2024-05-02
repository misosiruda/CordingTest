import java.util.Map;

class Solution {
    public int[] solution(String[] park, String[] routes) {
        Map<String, Integer> dir = Map.of(
                "N", 0,
                "S", 1,
                "W", 2,
                "E", 3);
        int[] dy = { -1, 1, 0, 0 };
        int[] dx = { 0, 0, -1, 1 };
        int[] pos = new int[2];
        char[][] map = new char[park.length][];
        for (int i = 0; i < map.length; i++) {
            map[i] = park[i].toCharArray();
        }
        all: for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                if (map[y][x] == 'S') {
                    pos[0] = y;
                    pos[1] = x;
                    break all;
                }
            }
        }

        for (String odr : routes) {
            String[] arr = odr.split(" ");
            int[] temp = pos.clone();
            int d = dir.get(arr[0]);
            int ny = pos[0] + dy[d];
            int nx = pos[1] + dx[d];
            int dist = Integer.parseInt(arr[1]);
            dist--;
            while (dist >= 0) {
                if (ny < 0 || ny >= map.length || nx < 0 || nx >= map[0].length || map[ny][nx] == 'X') {
                    pos = temp.clone();
                    break;
                }
                pos[0] = ny;
                pos[1] = nx;
                ny = pos[0] + dy[d];
                nx = pos[1] + dx[d];
                dist--;
            }
        }
        return pos;
    }
}