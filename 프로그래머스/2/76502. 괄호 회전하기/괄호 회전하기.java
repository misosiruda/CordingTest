import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int solution(String s) {
        int answer = 0;
        for (int i = 0; i < s.length(); i++) {
            s = s.substring(1) + s.toCharArray()[0];
            char[] arr = s.toCharArray();
            Deque<Character> stack = new ArrayDeque<>();
            boolean isOkay = true;
            find: for (char c : arr) {
                switch (c) {
                    case '{':
                        stack.addLast(c);
                        break;
                    case '[':
                        stack.addLast(c);
                        break;
                    case '(':
                        stack.addLast(c);
                        break;
                    case '}':
                        if (stack.size() == 0) {
                            isOkay = false;
                            break find;
                        }
                        if (stack.peekLast() == '{') {
                            stack.pollLast();
                        } else {
                            isOkay = false;
                            break find;
                        }
                        break;
                    case ']':
                        if (stack.size() == 0) {
                            isOkay = false;
                            break find;
                        }
                        if (stack.peekLast() == '[') {
                            stack.pollLast();
                        } else {
                            isOkay = false;
                            break find;
                        }
                        break;
                    case ')':
                        if (stack.size() == 0) {
                            isOkay = false;
                            break find;
                        }
                        if (stack.peekLast() == '(') {
                            stack.pollLast();
                        } else {
                            isOkay = false;
                            break find;
                        }
                        break;
                }
            }
            if (!isOkay || stack.size() > 0) {
                continue;
            } else {
                answer++;
            }
        }
        return answer;
    }
}