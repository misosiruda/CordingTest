class Solution {
    public int[] solution(int e, int[] starts) {
        int[] divisorArr = new int[e + 1];
        int[] maximumArr = new int[e + 1];
        for (int i = 2; i <= e; i++) {
            for (int j = 1; j <= e / i; j++) {
                divisorArr[i * j] += 1;
            }
        }
        int max = 0;
        int idx = 0;
        for (int i = e; i >= 1; i--) {
            if (max <= divisorArr[i]) {
                max = divisorArr[i];
                idx = i;
            }
            maximumArr[i] = idx;
        }
        int[] answer = new int[starts.length];
        for (int i = 0; i < starts.length; i++) {
            answer[i] = maximumArr[starts[i]];
        }
        return answer;
    }
}