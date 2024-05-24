class Solution {
    public int solution(int[] common) {
        int answer = 0;
        int a = common[0];
        int b = common[1];
        int c = common[2];
        if (a - b == b - c) {
            answer = common[common.length - 1] - (a - b);
        } else {
            answer = common[common.length - 1] * (b / a);
        }
        return answer;
    }
}