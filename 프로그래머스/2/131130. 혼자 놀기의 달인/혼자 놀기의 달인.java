import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Solution {
    public int solution(int[] cards) {
        ArrayList<Integer> counts = new ArrayList<>();
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] != 0) {
                int index = i;
                int count = 0;
                while (cards[index] != 0) {
                    int temp = cards[index];
                    cards[index] = 0;
                    index = temp - 1;
                    count++;
                }
                counts.add(count);
            }
        }
        Collections.sort(counts, Comparator.reverseOrder());
        if (counts.size() < 2) {
            return 0;
        }
        return (int) counts.get(1) * counts.get(0);
    }
}