class Solution {
    public int solution(int n, int[] cores) {
        int len = cores.length;
        if (n < len) {
            return n;
        }
        n -= len;

        int left = 1;
        int right = 30000;
        while (left < right) {
            int mid = (int) Math.floor((left + right) / 2);
            int now = 0;
            for (int i : cores) {
                now += (int) Math.floor(mid / i);
            }
            if (now < n) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        int now = 0;
        for (int i : cores) {
            now += (int) Math.floor((left - 1) / i);
        }
        n -= now;
        for (int i = 0; i < len; i++) {
            if (left % cores[i] == 0) {
                if (--n == 0) {
                    return i + 1;
                }
            }
        }
        return 0;
    }
}