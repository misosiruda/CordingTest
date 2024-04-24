class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        double dd = Math.pow(d, 2);
        for (int x = 0; x <= d; x += k) {
            double y = Math.sqrt(dd - Math.pow(x, 2));
            answer += Math.floor(y / k) + 1;
        }
        return answer;
    }
}
