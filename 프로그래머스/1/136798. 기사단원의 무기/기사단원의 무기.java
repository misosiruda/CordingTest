class Solution {
    private int getMeasureCount(int n, int limit, int power) {
        int count = 0;
        for (int i = 1; i * i <= n; i++) {
            if (i * i == n)
                count++;
            else if (n % i == 0)
                count += 2;
            if (count > limit) {
                return power;
            }
        }
        return count;
    }
    public int solution(int number, int limit, int power) {
        int answer = 0;
        for (int i = 1; i <= number; i++) {
            answer += getMeasureCount(i, limit, power);
        }
        return answer;
    }
}