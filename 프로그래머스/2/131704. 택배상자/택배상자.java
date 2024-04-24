import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        int[] oriArr = new int[order.length];
        for (int i = 0; i < order.length; i++) {
            oriArr[i] = i + 1;
        }

        int idx = 0;
        for (int i = 0; i < order.length; i++) {
            int n = oriArr[i];
            if (stack.peek() == order[idx]) {
                stack.pop();
                answer++;
                i--;
                idx++;
                continue;
            }
            if (n == order[idx]) {
                answer++;
                idx++;
                continue;
            }
            stack.push(n);
        }

        while (idx < order.length && stack.peek() == order[idx]) {
            answer++;
            stack.pop();
            idx++;
        }

        return answer;
    }
}