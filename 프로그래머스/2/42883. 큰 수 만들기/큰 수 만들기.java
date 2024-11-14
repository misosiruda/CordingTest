import java.util.ArrayDeque;

class Solution {
    public String solution(String number, int k) {
        ArrayDeque<Character> deque = new ArrayDeque<>();
        int len = number.length();

        for (int i = 0; i < len; i++) {
            char c = number.charAt(i);
            // 스택이 비어있지 않고, 제거할 수 있는 숫자가 남아있으며, 현재 숫자가 스택 top보다 크면 제거
            while (!deque.isEmpty() && k > 0 && deque.peek() < c) {
                deque.pop();
                k--;
            }
            deque.push(c);
        }

        // 남은 숫자를 제거해야 할 경우
        while (k > 0) {
            deque.pop();
            k--;
        }

        // Deque을 StringBuilder로 변환하여 결과 생성
        StringBuilder answer = new StringBuilder();
        len = deque.size();
        for (int i = 0; i < len; i++) {
            char c = deque.pollLast();
            answer.append(c);
        }

        return answer.toString();
    }
}