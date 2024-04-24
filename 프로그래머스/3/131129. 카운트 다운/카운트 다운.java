
class Solution {
   public static int[] solution(int target) {
        int omake = 0;
        if (target > 300) {
            omake = (target - 300) / 60;
            target = 300 + target % 60;
        }
        int[][] DP = new int[target + 1][2];

        int index = 0;
        DP[index][0] = 0;
        DP[index][1] = 0;
        index++;
        for (int i = 1; i < 61 && i < target + 1; i++) {
            if (i < 21) {
                DP[index][0] = 1;
                DP[index][1] = 1;
                index++;
            } else if (i < 41) {
                if (i % 2 == 0) {
                    DP[index][0] = 1;
                    DP[index][1] = 0;
                    index++;
                } else if (i % 3 == 0) {
                    DP[index][0] = 1;
                    DP[index][1] = 0;
                    index++;
                } else {
                    DP[index][0] = 2;
                    DP[index][1] = 2;
                    index++;
                }
            } else {
                if (i % 3 == 0) {
                    DP[index][0] = 1;
                    DP[index][1] = 0;
                    index++;
                } else if (i == 50) {
                    DP[index][0] = 1;
                    DP[index][1] = 1;
                    index++;
                } else if (i > 50) {
                    DP[index][0] = 2;
                    DP[index][1] = 2;
                    index++;
                } else {
                    DP[index][0] = 2;
                    DP[index][1] = 1;
                    index++;
                }
            }
        }
        for (int i = 61; i <= target; i++) {
            int[] minArr = new int[] { 0, 0 };
            minArr[0] = DP[1][0] + DP[i - 1][0];
            minArr[1] = DP[1][1] + DP[i - 1][1];
            for (int j = 1; j < 61; j++) {
                int[] nowArr = new int[] { 0, 0 };
                nowArr[0] = DP[j][0] + DP[i - j][0];
                nowArr[1] = DP[j][1] + DP[i - j][1];
                if (nowArr[0] < minArr[0]) {
                    minArr[0] = nowArr[0];
                    minArr[1] = nowArr[1];
                } else if (nowArr[0] == minArr[0] && nowArr[1] > minArr[1]) {
                    minArr[0] = nowArr[0];
                    minArr[1] = nowArr[1];
                }
            }
            DP[index][0] = minArr[0];
            DP[index][1] = minArr[1];
            index++;
        }
        int[] answer = new int[2];
        answer[0] = omake + DP[target][0];
        answer[1] = DP[target][1];
        return answer;
    }
}