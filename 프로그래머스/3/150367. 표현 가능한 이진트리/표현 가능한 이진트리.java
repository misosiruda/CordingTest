class Solution {

    private boolean checkMakeTree(String str) {
        int len = str.length();
        if (len == 1) {
            if (str.equals("1")) return true; else return false;
        }
        if (len == 3) {
            if (str.charAt(1) == '1') return true; else {
                if (str.charAt(0) == '0' && str.charAt(2) == '0') return true; else return false;
            }
        }
        int middle = (int) len / 2;
        String left = str.substring(0, middle);
        String right = str.substring(middle + 1);
        if (str.charAt(middle) == '0') {
            boolean checkChildLeft = false;
            boolean checkChildRight = false;
            middle = (int) left.length() / 2;
            if (left.charAt(middle) == '0') checkChildLeft = true;
            middle = (int) right.length() / 2;
            if (right.charAt(middle) == '0') checkChildRight = true;
            if (!checkChildLeft || !checkChildRight) return false;
        }
        if (!checkMakeTree(left)) return false;
        if (!checkMakeTree(right)) return false;
        return true;
    }

    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for (int i = 0; i < answer.length; i++) {
            String str = Long.toBinaryString(numbers[i]);
            int p = 0;
            for (int j = 0; str.length() > Math.pow(2, j) - 1; j++) {
                p = j + 1;
            }
            p = (int) Math.pow(2, p) - str.length() - 1;
            str = "0".repeat(p) + str;
            if (checkMakeTree(str)) answer[i] = 1; else answer[i] = 0;
        }
        return answer;
    }
}