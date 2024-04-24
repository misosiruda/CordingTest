import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;

class Solution {

    public int solution(int k, int[] tangerine) {
        HashMap<Integer, Integer> kindMap = new HashMap<>();
        for (int i : tangerine) {
            kindMap.put(i, kindMap.getOrDefault(i, 0) + 1);
        }
        ArrayList<Integer[]> list = new ArrayList<>();
        for (Entry<Integer, Integer> entry : kindMap.entrySet()) {
            list.add(new Integer[] { entry.getKey(), entry.getValue() });
        }
        Collections.sort(
            list,
            new Comparator<Integer[]>() {
                @Override
                public int compare(Integer[] o1, Integer[] o2) {
                    return o2[1] - o1[1];
                }
            }
        );
        for (int i = 0; i < list.size(); i++) {
            k -= list.get(i)[1];
            if (k <= 0) {
                return i + 1;
            }
        }
        return 0;
    }
}