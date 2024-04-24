class Solution {
    public int solution(String name) {
        int answer = 0, rem = 0, cnt = Integer.MAX_VALUE;

        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) != 'A') {
                answer += calc(name.charAt(i));

                if (i == 0) continue;
                int tmp = rem + name.length()-i;
                cnt = Math.min(cnt, Math.min(tmp+rem, tmp+name.length()-i));
                rem = i;
            }
        }
        cnt = Math.min(rem, cnt);
        return answer+cnt;
    }

    private int calc(char c) {
        int cint = (int) c;
        return Math.min(91-c, c-65);
    }
}