import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public int[] solution(int[] numList, int n) {
        List<Integer> list = new ArrayList<>();

        for (int num : numList) {
            list.add(num - n);
        }

        Collections.sort(list, (a, b) -> Math.abs(a) == Math.abs(b) ? b - a : Math.abs(a) - Math.abs(b));

        return list.stream().map(num -> num + n).mapToInt(Integer::intValue).toArray();
    }
}