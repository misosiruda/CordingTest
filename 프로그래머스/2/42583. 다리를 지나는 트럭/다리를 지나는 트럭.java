import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        int currentWeight = 0;
        int endTruckCnt = 0;
        int index = 0;
        Deque<Integer> onBridge = new ArrayDeque<>();
        for (int i = 0; i < bridge_length; i++) {
            onBridge.addLast(0);
        }
        while (endTruckCnt < truck_weights.length) {
            answer++;
            int out = onBridge.pollFirst();
            if (out != 0) {
                currentWeight -= out;
                endTruckCnt++;
            }
            if (index >= truck_weights.length) {
                continue;
            }
            if (currentWeight + truck_weights[index] <= weight) {
                currentWeight += truck_weights[index];
                onBridge.addLast(truck_weights[index]);
                index++;
            } else {
                onBridge.addLast(0);
            }
        }
        return answer;
    }
}