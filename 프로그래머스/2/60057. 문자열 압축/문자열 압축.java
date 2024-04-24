import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int solution(String s) {
        int answer = s.length();
        Deque<String> strs = new ArrayDeque<>();
        for (int i = 1; i <= s.length() / 2; i++) {
            strs = new ArrayDeque<>();
            int j;
            for (j = 0; j < s.length() - i; j += i) {
                String str = s.substring(j, j + i);
                strs.addLast(str);
            }
            String str = s.substring(j);
            strs.addLast(str);
            String st = "";
            int count = 1;
            StringBuffer sb = new StringBuffer();
            while (strs.size() > 0) {
                if (!st.equals(strs.peekFirst())) {
                    if (count == 1) {
                        sb.append(st);
                    } else {
                        sb.append(count);
                        sb.append(st);
                    }
                    st = strs.pollFirst();
                    count = 1;
                } else {
                    strs.pollFirst();
                    count++;
                }
            }
            if (count == 1) {
                sb.append(st);
            } else {
                sb.append(count);
                sb.append(st);
            }
            answer = Math.min(answer, sb.length());
        }
        return answer;
    }
}