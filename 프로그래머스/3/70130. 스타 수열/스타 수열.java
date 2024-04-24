import java.util.LinkedList;

class Solution {

    public int solution(int[] a) {
        if (a.length < 2) {
            return 0;
        }
        int[] countArray = new int[a.length];
        /**두번 전 숫자 */
        int prev2 = -1;
        /**한번 전 숫자 */
        int prev1 = a[0];
        int leftban = a[0];
        int bantime = 0;
        int maxIndex = 0;
        for (int i = 1; i < a.length; i++) {
            int number = a[i];
            if (bantime > 2) {
                leftban = -1;
            }
            if (prev2 == prev1 && prev1 == number) {
                prev2 = prev1;
                prev1 = number;
                bantime++;
                continue;
            }
            if (number == prev1 && number == leftban) {
                countArray[leftban] -= 1;
            }

            if (prev1 == prev2) {
                leftban = prev1;
                bantime = 0;
            }
            countArray[prev1] += 1;
            if (countArray[maxIndex] < countArray[prev1]) maxIndex = prev1;
            prev2 = prev1;
            prev1 = number;
            bantime++;
        }
        if (leftban != a[a.length - 1]) {
            countArray[a[a.length - 1]] += 1;
        }
        int answer = 0;
        LinkedList<Integer> maxList = new LinkedList<>();
        maxList.addFirst(maxIndex);
        for (int i = 0; i < countArray.length; i++) {
            if (countArray[i] > countArray[maxIndex] / 1.5 && i != maxIndex) {
                maxList.addFirst(i);
            }
        }

        int maxAnswer = 0;
        for (int maxidx : maxList) {
            answer = 0;
            boolean canTurn = a[0] == maxidx ? false : true;
            for (int i = 0; i < countArray.length - 1; i++) {
                if (a[i] == maxidx) {
                    if (a[i + 1] == maxidx) {
                        if (canTurn) {
                            canTurn = false;
                            answer += 2;
                            continue;
                        }
                        continue;
                    }
                    answer += 2;
                } else {
                    if (a[i + 1] != maxidx) {
                        canTurn = true;
                    }
                }
            }
            if (canTurn && a[a.length - 1] == maxidx) {
                answer += 2;
            }
            maxAnswer = Math.max(answer, maxAnswer);
        }
        return maxAnswer;
    }
}