import java.util.HashMap;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        HashMap<String, Integer> quantityHashMap = new HashMap<>();
        HashMap<String, Integer> giveHashMap = new HashMap<>();
        for (String str : gifts) {
            String[] strArr = str.split(" ");
            String left = strArr[0];
            String right = strArr[1];
            quantityHashMap.put(left, quantityHashMap.getOrDefault(left, 0) + 1);
            quantityHashMap.put(right, quantityHashMap.getOrDefault(right, 0) - 1);
            giveHashMap.put(str, giveHashMap.getOrDefault(str, 0) + 1);
        }
        int[] answers = new int[friends.length];
        int answer = 0;
        for (int i = 0; i < answers.length; i++) {
            String left = friends[i];
            int sum = 0;
            for (int j = 0; j < answers.length; j++) {
                String right = friends[j];
                if (giveHashMap.getOrDefault(left + " " + right, 0) == giveHashMap.getOrDefault(right + " " + left,
                        0)) {
                    if (quantityHashMap.getOrDefault(left, 0) == quantityHashMap.getOrDefault(right, 0)) {
                        continue;
                    } else if (quantityHashMap.getOrDefault(left, 0) > quantityHashMap.getOrDefault(right, 0)) {
                        sum++;
                    }
                } else if (giveHashMap.getOrDefault(left + " " + right, 0) > giveHashMap
                        .getOrDefault(right + " " + left, 0)) {
                    sum++;
                }
            }
            answer = Math.max(sum, answer);
        }
        return answer;
    }
}