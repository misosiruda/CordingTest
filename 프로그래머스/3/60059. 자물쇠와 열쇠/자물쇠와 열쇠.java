import java.util.ArrayList;

class Solution {

    private boolean contains(ArrayList<Integer[]> lockLocalPosList, Integer[] other) {
        for (int i = 0; i < lockLocalPosList.size(); i++) {
            Integer[] integers = lockLocalPosList.get(i);
            if (integers[0] == other[0] && integers[1] == other[1]) {
                return true;
            }
        }
        return false;
    }

    private boolean checkOutofRange(Integer[] check, int[] range) {
        if (range[0] <= check[0] && check[0] <= range[1] && range[2] <= check[1] && check[1] <= range[3]) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkBump(ArrayList<Integer[]> lockLocalPosList, ArrayList<Integer[]> keyLocalPosList,
            int[] range) {
        int oriY = 0, oriX = 0;
        for (Integer[] oriPos : keyLocalPosList) {
            oriY = -oriPos[0];
            oriX = -oriPos[1];
            int checkCnt = 0;
            for (Integer[] selPos : keyLocalPosList) {
                int selY = selPos[0] + oriY;
                int selX = selPos[1] + oriX;
                if (contains(lockLocalPosList, new Integer[] { selY, selX })) {
                    checkCnt++;
                } else {
                    if (checkOutofRange(new Integer[] { selY, selX }, range)) {
                        break;
                    }
                }
            }
            if (checkCnt == lockLocalPosList.size()) {
                return true;
            }
        }
        return false;
    }

    private void rotatePosList(ArrayList<Integer[]> keyLocalPosList) {
        int min = 0;
        for (Integer[] integers : keyLocalPosList) {
            if (min > integers[1]) {
                min = integers[1];
            }
        }
        if (min < 0) {
            for (Integer[] integers : keyLocalPosList) {
                integers[1] += min;
            }
        }
        for (Integer[] integers : keyLocalPosList) {
            int tempY = integers[0];
            int tempX = integers[1];
            integers[0] = tempX;
            integers[1] = -tempY;
        }
    }

    public boolean solution(int[][] key, int[][] lock) {
        int N = lock.length;
        int M = key.length;
        ArrayList<Integer[]> lockLocalPosList = new ArrayList<>(); // 자물쇠의 홈 한개를 기준으로 local position 을 담은 리스트
        ArrayList<Integer[]> keyLocalPosList = new ArrayList<>(); // 열쇠의 돌기 한개를 """
        int oriY = -1, oriX = -1;
        int[] range = new int[4];
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (lock[y][x] == 0) {
                    if (oriY == -1 && oriX == -1) {
                        oriY = y;
                        oriX = x;
                        range = new int[] { -y, N - y - 1, -x, N - x - 1 };
                        lockLocalPosList.add(new Integer[] { 0, 0 });
                    } else {
                        lockLocalPosList.add(new Integer[] { y - oriY, x - oriX });
                    }
                }
            }
        }
        oriY = -1;
        oriX = -1;
        for (int y = 0; y < M; y++) {
            for (int x = 0; x < M; x++) {
                if (key[y][x] == 1) {
                    if (oriY == -1 && oriX == -1) {
                        oriY = y;
                        oriX = x;
                        keyLocalPosList.add(new Integer[] { 0, 0 });
                    } else {
                        keyLocalPosList.add(new Integer[] { y - oriY, x - oriX });
                    }
                }
            }
        }
        if (lockLocalPosList.size() > keyLocalPosList.size()) {
            return false;
        }
        for (int i = 0; i < 4; i++) {
            if (!checkBump(lockLocalPosList, keyLocalPosList, range)) {
                if (i == 3)
                    return false;
                rotatePosList(keyLocalPosList);
            } else {
                return true;
            }
        }
        return true;
    }
}