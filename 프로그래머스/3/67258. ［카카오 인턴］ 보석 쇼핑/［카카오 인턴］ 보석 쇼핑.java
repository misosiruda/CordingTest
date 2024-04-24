import java.util.HashMap;
import java.util.Map.Entry;

class Solution {

    private HashMap<String, Integer> gemIndexMap = new HashMap<String, Integer>();

    private int getMinIndex() {
        int min = 100001;
        for (Entry<String, Integer> entry : gemIndexMap.entrySet()) {
            min = Math.min(min, entry.getValue());
        }
        return min == 100001 ? 0 : min;
    }

    public int[] solution(String[] gems) {
        int start = 0, end = 0;
        int[] answer = new int[2];
        for (int i = 0; i < gems.length; i++) {
            if (!gemIndexMap.containsKey(gems[i])) {
                gemIndexMap.put(gems[i], i);
                end = i;
                answer = new int[] { start + 1, end + 1 };
            } else {
                gemIndexMap.put(gems[i], i);
                end = i;
                if (gems[start].equals(gems[i])) {
                    start = getMinIndex();
                    if (answer[1] - answer[0] > end - start) {
                        answer = new int[] { start + 1, end + 1 };
                    }
                }
            }
        }
        return answer;
    }
}