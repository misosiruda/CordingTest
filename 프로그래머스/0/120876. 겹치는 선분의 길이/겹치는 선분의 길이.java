class Solution {
    public int solution(int[][] lines) {
        int answer = 0;
        int[] map = new int[300];
        for (int[] is : lines) {
            map[is[0] + 101]++;
            map[is[1] + 101]--;
        }
        int n = 0;
        for (int i : map) {
            n += i;
            if (n > 1) {
                answer++;
            }
        }
        return answer;
    }
}