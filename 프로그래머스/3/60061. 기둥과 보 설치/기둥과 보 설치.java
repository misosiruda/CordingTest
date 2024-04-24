import java.util.ArrayList;

class Solution {
    int[][] pillar;
    int[][] beam;

    private boolean check(int x, int y, int type) {
        if (type == 0) {
            if (y == 2 || pillar[x][y - 1] == 1 || beam[x - 1][y] == 1 || beam[x][y] == 1) {
                return true;
            }
        } else {
            if (pillar[x][y - 1] == 1 || pillar[x + 1][y - 1] == 1
                    || (beam[x - 1][y] == 1 && beam[x + 1][y] == 1)) {
                return true;
            }
        }
        return false;
    }

    public int[][] solution(int n, int[][] build_frame) {
        ArrayList<Integer[]> result = new ArrayList<>();
        pillar = new int[n + 4][n + 4];
        beam = new int[n + 4][n + 4];

        for (int[] is : build_frame) {
            int x = is[0], y = is[1], type = is[2], cod = is[3];// cod = create or delete
            x += 2;
            y += 2;// out of index range 를 막기 위한 +1;
            if (cod == 1) {
                if (type == 0) {
                    if (check(x, y, type)) {
                        pillar[x][y] = 1;
                    }
                } else {
                    if (check(x, y, type)) {
                        beam[x][y] = 1;
                    }
                }

            } else {
                if (type == 0) {
                    boolean isDeletable = true;
                    pillar[x][y] = 0;
                    for (int cx = 0; cx < beam.length; cx++) {
                        for (int cy = 0; cy < beam.length; cy++) {
                            if (pillar[cx][cy] == 1) {
                                if (!check(cx, cy, 0)) {
                                    isDeletable = false;
                                    break;
                                }
                            }
                            if (beam[cx][cy] == 1) {
                                if (!check(cx, cy, 1)) {
                                    isDeletable = false;
                                    break;
                                }
                            }
                        }
                        if (!isDeletable) {
                            break;
                        }
                    }
                    if (!isDeletable) {
                        pillar[x][y] = 1;
                    }
                } else {
                    boolean isDeletable = true;
                    beam[x][y] = 0;
                    for (int cx = 0; cx < beam.length; cx++) {
                        for (int cy = 0; cy < beam.length; cy++) {
                            if (pillar[cx][cy] == 1) {
                                if (!check(cx, cy, 0)) {
                                    isDeletable = false;
                                    break;
                                }
                            }
                            if (beam[cx][cy] == 1) {
                                if (!check(cx, cy, 1)) {
                                    isDeletable = false;
                                    break;
                                }
                            }
                        }
                        if (!isDeletable) {
                            break;
                        }
                    }
                    if (!isDeletable) {
                        beam[x][y] = 1;
                    }
                }
            }
        }
        for (int x = 0; x < beam.length; x++) {
            for (int y = 0; y < beam.length; y++) {
                if (pillar[x][y] == 1) {
                    result.add(new Integer[] { x - 2, y - 2, 0 });
                }
                if (beam[x][y] == 1) {
                    result.add(new Integer[] { x - 2, y - 2, 1 });
                }
            }
        }

        int[][] answer = new int[result.size()][3];
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < 3; j++) {
                answer[i][j] = result.get(i)[j];
            }
        }
        return answer;
    }
}