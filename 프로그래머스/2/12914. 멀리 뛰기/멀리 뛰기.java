import java.util.*;

class Solution {

    public long solution(int n) {
        long a = 1L;
        long b = 2L;
        if (n == 1) {
            return a;
        } else if (n == 2) {
            return b;
        }
        for (int i = 3; i <= n; i++) {
            long tmp = (a + b) % 1234567;
            a = b;
            b = tmp;
        }
        return b;
    }
}