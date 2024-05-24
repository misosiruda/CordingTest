import java.util.*;

class Solution {
    public int solution(String A, String B) {
        int answer = -1;
        int max = A.length();
        Deque<String> queue = new ArrayDeque<>();
        for (String string : A.split("")) {
            queue.addLast(string);
        }
        for (int i = 0; i < max; i++) {
            String[] temp = queue.toArray(new String[queue.size()]);
            String tmp = String.join("", temp);
            if (tmp.equals(B)) {
                answer = i;
                break;
            }
            queue.addFirst(queue.pollLast());
        }
        return answer;
    }
}