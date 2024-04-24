class Solution {
    public int solution(int n, int count) {
        int MOD = 1000000007;
        long[][] dp = new long[n + 1][n + 1];
        dp[1][1] = 1;
        for (int i = 2; i < n + 1; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i][j] = (dp[i - 1][j] + (i - 1) * 2 * dp[i - 1][j - 1]) % MOD;
            }
        }
        return (int) dp[n][n - count + 1] % MOD;
    }
}