import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Deque<Integer[]> stack = new ArrayDeque<>();// {주가, index}
        stack.addLast(new Integer[] { prices[0], 0 });
        for (int i = 1; i < prices.length; i++) {
            while (stack.size() > 0 && prices[i] < stack.peekLast()[0]) {
                Integer[] now = stack.pollLast();
                answer[now[1]] = i - now[1];
            }
            stack.addLast(new Integer[] { prices[i], i });
        }
        while (stack.size() > 0) {
            Integer[] now = stack.pollLast();
            answer[now[1]] = prices.length - now[1] - 1;
        }
        return answer;
    }
}