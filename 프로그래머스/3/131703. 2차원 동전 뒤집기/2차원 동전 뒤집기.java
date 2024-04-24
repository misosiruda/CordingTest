class Solution {
    public int solution(int[][] beginning, int[][] target) {
        int answer1 = 0;
        int[][] beginningCopy = new int[beginning.length][];
        for (int i = 0; i < beginningCopy.length; i++) {
            beginningCopy[i] = beginning[i].clone();
        }
        boolean[] flicked = new boolean[target[0].length];
        for (int i = 0; i < flicked.length; i++) {
            if (beginning[0][i] != target[0][i]) {
                answer1++;
                flicked[i] = true;
            }
        }
        boolean isPossible1 = true;
        for (int y = 0; y < target.length; y++) {
            boolean isSame = false;
            boolean isDiff = false;
            for (int x = 0; x < target[0].length; x++) {
                if (flicked[x]) {
                    beginning[y][x] = beginning[y][x] == 1 ? 0 : 1;
                }
                if (beginning[y][x] == target[y][x]) {
                    isSame = true;
                } else {
                    isDiff = true;
                }

            }
            if (isSame && isDiff) {
                isPossible1 = false;
                break;
            }
            if (isDiff) {
                answer1++;
            }
        }
        int answer2 = 0;
        flicked = new boolean[target[0].length];
        boolean isPossible2 = true;
        for (int i = 0; i < flicked.length; i++) {
            if (beginningCopy[0][i] == target[0][i]) {
                answer2++;
                flicked[i] = true;
            }
        }
        for (int y = 0; y < target.length; y++) {
            boolean isSame = false;
            boolean isDiff = false;
            for (int x = 0; x < target[0].length; x++) {
                if (flicked[x]) {
                    beginningCopy[y][x] = beginningCopy[y][x] == 1 ? 0 : 1;
                }
                if (beginningCopy[y][x] == target[y][x]) {
                    isSame = true;
                } else {
                    isDiff = true;
                }

            }
            if (isSame && isDiff) {
                isPossible2 = false;
                break;
            }
            if (isDiff) {
                answer2++;
            }
        }
        if (answer1 < answer2 && isPossible1) {
            return answer1;
        } else if (isPossible2) {
            return answer2;
        }
        return -1;
    }
}