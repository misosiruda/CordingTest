import java.util.*;

class Solution {

    private boolean isBetween(int num, int l, int r) {
        return l <= num && num <= r;
    }

    public int[] solution(int l, int r) {
        int[] lol = { 5, 55, 555, 5555, 55555, 555555, 505555, 50555, 550555, 500555, 5055, 55055, 555055, 505055,
                50055, 550055, 500055, 505, 5505, 55505, 555505, 505505, 50505, 550505, 500505, 5005, 55005, 555005,
                505005, 50005, 550005, 500005, 50, 550, 5550, 55550, 555550, 505550, 50550, 550550, 500550, 5050, 55050,
                555050, 505050, 50050, 550050, 500050, 500, 5500, 55500, 555500, 505500, 50500, 550500, 500500, 5000,
                55000, 555000, 505000, 50000, 550000,
                500000 };
        List<Integer> answerList = new ArrayList<>();
        for (int i : lol) {
            if (isBetween(i, l, r)) {
                answerList.add(i);
            }
        }

        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i);
        }
        Arrays.sort(answer);
        return answer.length > 0 ? answer : new int[] { -1 };
    }
}