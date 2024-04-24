class Solution {
    public String solution(String s) {
        String[] arr = s.split(" ");
        String min = "9999999";
        String max = "-9999999";
        for (String str : arr) {
            if (Integer.parseInt(min) > Integer.parseInt(str)) {
                min = str;
            }
            if (Integer.parseInt(max) < Integer.parseInt(str)) {
                max = str;
            }
        }
        String answer = min + " " + max;
        return answer;
    }
}