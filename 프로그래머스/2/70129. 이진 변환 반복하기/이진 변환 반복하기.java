class Solution {
    public int[] solution(String s) {
        int count = 0;
        int zero = 0;
        while (true) {
            count++;
            char[] arr = s.toCharArray();
            int cnt = 0;
            for (char c : arr) {
                if (c == '0') {
                    zero++;
                } else {
                    cnt++;
                }
            }
            s = Integer.toBinaryString(cnt);
            if (s.equals("1")) {
                break;
            }
        }
        return new int[] { count, zero };
    }
}