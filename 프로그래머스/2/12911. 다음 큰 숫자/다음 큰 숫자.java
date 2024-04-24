class Solution {

    public int solution(int n) {
        String origin = Integer.toBinaryString(n);
        char[] oriArr = origin.toCharArray();
        int oneCnt = 0;
        for (char c : oriArr) {
            if (c == '1') oneCnt++;
        }
        for (int i = 1; i < n; i++) {
            String next = Integer.toBinaryString(n + i);
            char[] nextArr = next.toCharArray();
            int nextOneCnt = 0;
            for (char c : nextArr) {
                if (c == '1') nextOneCnt++;
            }
            if (oneCnt == nextOneCnt) {
                return n + i;
            }
        }
        return 0;
    }
}