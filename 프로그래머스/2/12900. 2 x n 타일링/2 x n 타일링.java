class Solution {
    public int solution(int n) {
        if (n < 3) {
            return n;
        }

        int MOD = 1000000007;
        int a = 1;
        int b = 2;
        int c = 0;
        for (int i = 3; i <= n; i++) {
            c = (a + b) % MOD;
            a = b;
            b = c;
        }
        return c;
    }
}