class Solution {
    private int[][] matrix;
    private int[][] DP;

    public int solution(int[][] matrix_sizes) {
        this.matrix = matrix_sizes;
        this.DP = new int[matrix.length + 1][matrix.length + 1];
        return SetDP(0, matrix.length);
    }

    private int GetDP(int s, int e) {
        if (DP[s][e] == 0) DP[s][e] = SetDP(s, e);
        return DP[s][e];
    }

    private int SetDP(int s, int e) {
        if (e - s == 1) return 0;

        int ans = 999999999;
        for (int m = s + 1; m < e; m++) {
            int left = GetDP(s, m);
            int right = GetDP(m, e);
            int current = matrix[s][0] * matrix[m][0] * matrix[e - 1][1];
            ans = Math.min(ans, left + right + current);
        }
        return ans;
    }
}