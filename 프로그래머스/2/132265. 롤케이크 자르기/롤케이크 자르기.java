import java.util.HashSet;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        int len = topping.length;
        int[] acc = new int[len];
        int[] accre = new int[len];
        HashSet<Integer> count = new HashSet<>();
        for (int i = 0; i < len; i++) {
            count.add(topping[i]);
            acc[i] = count.size();
        }
        count = new HashSet<>();
        for (int i = len - 1; i >= 0; i--) {
            count.add(topping[i]);
            accre[i] = count.size();
        }
        for (int i = 1; i < len; i++) {
            if (acc[i - 1] == accre[i]) {
                answer++;
            }
        }
        return answer;
    }
}