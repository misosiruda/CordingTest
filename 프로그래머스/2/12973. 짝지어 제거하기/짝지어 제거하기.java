import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int solution(String s) {
        int answer = -1;
        Deque<Character> stack = new ArrayDeque<>();
        char[] arr = s.toCharArray();
        for (char c : arr) {
            if (stack.size() == 0) {
                stack.addLast(c);
                continue;
            }
            if (stack.peekLast() == c) {
                stack.pollLast();
            } else {
                stack.addLast(c);
            }
        }

        return stack.size() > 0 ? 0 : 1;
    }
}