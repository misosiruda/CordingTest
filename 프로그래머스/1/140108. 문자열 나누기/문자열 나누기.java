class Solution {
    public static int solution(String s) {
        int answer = 0;

        int i = 0;
        while (s.length() > 0 && i < s.length()) {
            char target = s.charAt(0);
            int targetCnt = 0;
            int otherCnt = 0;
            for(i = 0; i < s.length(); i++) {
                if(s.charAt(i) == target) {
                    targetCnt++;
                }
                else {
                    otherCnt++;
                }
                if(targetCnt == otherCnt) {
                    s = s.substring(i + 1);
                    // System.out.println("next : " + s + ";");
                    answer++;
                    i = 0;
                    break;
                }
            }
        }

        if(s.length() != 0) {
            answer++;
        }
        return answer;
    }
}