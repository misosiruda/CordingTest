class Solution {

    boolean isChanged = true;
    int answer = 0;
    char[][] map;
    boolean[][] change;

    void checkDel() {
        for (int y = 0; y < change.length - 1; y++) {
            for (int x = 0; x < change[0].length - 1; x++) {
                char now = map[y][x];
                if (now != ' ' && map[y][x + 1] == now && map[y + 1][x] == now && map[y + 1][x + 1] == now) {
                    change[y][x] = true;
                    change[y][x + 1] = true;
                    change[y + 1][x] = true;
                    change[y + 1][x + 1] = true;
                }
            }
        }
        System.out.println();
    }

    void delChange() {
        for (int y = 0; y < change.length; y++) {
            for (int x = 0; x < change[0].length; x++) {
                if (change[y][x]) {
                    map[y][x] = ' ';
                    isChanged = true;
                    answer++;
                }
            }
        }

        for (int y = map.length - 1; y >= 0; y--) {
            for (int x = 0; x < map[0].length; x++) {
                if (map[y][x] == ' ') {
                    char temp = ' ';
                    for (int i = y - 1; i >= 0; i--) {
                        if (map[i][x] != ' ') {
                            temp = map[i][x];
                            map[i][x] = ' ';
                            break;
                        }
                    }
                    map[y][x] = temp;
                }
            }
        }
        System.out.println();
    }

    public int solution(int m, int n, String[] board) {
        isChanged = true;
        answer = 0;
        map = new char[m][n];
        change = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            map[i] = board[i].toCharArray();
        }

        while (isChanged) {
            isChanged = false;
            change = new boolean[m][n];
            checkDel();
            delChange();
        }
        return answer;
    }
}