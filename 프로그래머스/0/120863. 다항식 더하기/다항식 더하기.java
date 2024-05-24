import java.util.*;

class Solution {
    public String solution(String polynomial) {
        String answer = "";
        int num = 0;
        int xNum = 0;
        String[] stringArr = polynomial.split(" ");
        for (String string : stringArr) {
            if (string.charAt(string.length() - 1) == 'x') {
                if (string.length() == 1) {
                    xNum++;
                    continue;
                }
                xNum += Integer.parseInt(string.substring(0, string.length() - 1));
            } else if (!string.equals("+")) {
                num += Integer.parseInt(string);
            }
        }

        if (num != 0 && xNum != 0) {
            if (xNum == 1) {
                answer = "x + " + num;
            } else {
                answer = xNum + "x + " + num;
            }
        } else if (num != 0) {
            answer = num + "";
        } else {
            answer = xNum == 1 ? "x" : xNum + "x";
        }

        return answer;
    }
}