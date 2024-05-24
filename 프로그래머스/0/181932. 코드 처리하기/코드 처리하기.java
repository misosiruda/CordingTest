import java.util.*;

class Solution {
    public String solution(String code) {
        StringBuilder answer = new StringBuilder();
        int mode = 0;
        char[] arr = code.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '1') {
                mode = mode == 0 ? 1 : 0;
                continue;
            }
            if (mode == 0 && i % 2 == 0) {
                answer.append(arr[i]);
            }
            if (mode == 1 && i % 2 != 0) {
                answer.append(arr[i]);
            }
        }
        return answer.length() > 0 ? answer.toString() : "EMPTY";
    }
}