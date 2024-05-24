
class Solution {
    public int[] solution(int num, int total) {
        int[] answer = new int[num];
        if (num % 2 == 0) {
            int middle = (int) Math.ceil((double) total / num);
            for (int i = 0; i < num / 2; i++) {
                answer[num / 2 + i] = middle + i;
                answer[num / 2 - (i + 1)] = middle - (i + 1);
            }
        } else {
            int middle = (int) Math.ceil((double) total / num);
            answer[(int) Math.floor((double) num / 2)] = middle;
            for (int i = 1; i < num / 2 + 1; i++) {
                answer[num / 2 + i] = middle + i;
                answer[num / 2 - i] = middle - i;
            }
            answer[num / 2] = total - (middle * (num - 1));
        }
        return answer;
    }
}
