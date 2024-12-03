class Solution {
    public int solution(int n) {
        int answer = 0;
        for (int i=1; i<=n; i++) {
            int sum = 0;
            for (int j=i; j<=n; j++) {
                if (sum < n) {
                    sum += j;
                } else if (sum > n) {
                    break;
                } else {
                    answer++;
                    break;
                }
            }
        }
        return answer+1;
    }
}