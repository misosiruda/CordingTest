import java.util.Arrays;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int maxAp = 0, maxCp = 0;
        for (int[] is : problems) {
            maxAp = Math.max(maxAp, is[0]);
            maxCp = Math.max(maxCp, is[1]);
        }
        int ap = Math.min(alp, maxAp);
        int cp = Math.min(cop, maxCp);
        int[][] dp = new int[maxAp + 1][maxCp + 1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], 301);
        }
        dp[ap][cp] = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (i + 1 <= maxAp) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                }
                if (j + 1 <= maxCp) {
                    dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
                }
                for (int[] is : problems) {
                    if (i >= is[0] && j >= is[1]) {
                        int nextAp = Math.min(maxAp, i + is[2]);
                        int nextCp = Math.min(maxCp, j + is[3]);
                        dp[nextAp][nextCp] = Math.min(dp[nextAp][nextCp], dp[i][j] + is[4]);
                    }
                }
            }
        }
        return dp[maxAp][maxCp];
    }
}