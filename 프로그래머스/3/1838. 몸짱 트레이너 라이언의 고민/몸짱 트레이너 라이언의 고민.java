import java.util.ArrayList;

class Solution {
    public int solution(int n, int m, int[][] timetable) {

        int max = 0;
        int[] preSum = new int[722];
        for (int[] time : timetable) {
            preSum[time[0] - 600]++;
            preSum[time[1] - 599]--;
        }
        int sum = 0;
        for (int i = 0; i < preSum.length; i++) {
            sum += preSum[i];
            preSum[i] = sum;
            max = Math.max(max, sum);
        }

        if (max <= 1) {
            return 0;
        }
        ArrayList<Integer[]> list = new ArrayList<>();
        for (int dist = 2 * (n - 1); dist >= 1; dist--) {
            for (int sy = 0; sy < n; sy++) {
                list = new ArrayList<>();
                for (int x = 0; x < n; x++) {
                    next: for (int y = 0; y < n; y++) {
                        if (x == 0 && y < sy) {
                            continue;
                        }

                        for (Integer[] pos : list) {
                            if (Math.abs(pos[0] - x) + Math.abs(pos[1] - y) < dist) {
                                continue next;
                            }
                        }
                        list.add(new Integer[] { x, y });
                        if (list.size() == max) {
                            return dist;
                        }
                    }
                }
            }
        }
        return 0;
    }
}