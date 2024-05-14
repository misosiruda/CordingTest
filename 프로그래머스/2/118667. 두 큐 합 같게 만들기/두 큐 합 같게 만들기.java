import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Deque<Integer> q1 = new ArrayDeque<>();
        Deque<Integer> q2 = new ArrayDeque<>();
        long sum1 = 0;
        long sum2 = 0;
        for (int i = 0; i < queue2.length; i++) {
            q1.addLast(queue1[i]);
            q2.addLast(queue2[i]);
            sum1 += queue1[i];
            sum2 += queue2[i];
        }

        int answer = 0;
        for (; answer < queue2.length * 3 + 1; answer++) {
            if (sum1 > sum2) {
                int n = q1.pollFirst();
                q2.addLast(n);
                sum1 -= n;
                sum2 += n;
            } else if (sum1 < sum2) {
                int n = q2.pollFirst();
                q1.addLast(n);
                sum1 += n;
                sum2 -= n;
            } else {
                break;
            }
        }
        return answer == queue2.length * 3 + 1 ? -1 : answer;
    }
}
