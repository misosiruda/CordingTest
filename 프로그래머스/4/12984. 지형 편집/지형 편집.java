import java.util.ArrayList;
import java.util.Collections;

class Solution {

    public long solution(int[][] land, int P, int Q) {
        ArrayList<Long> map = new ArrayList<>();
        long sum = 0L;

        for (int y = 0; y < land.length; y++) {
            for (int x = 0; x < land[0].length; x++) {
                int num = land[y][x];
                sum += num;
                map.add((long) num);
            }
        }
        Collections.sort(map);

        long answer = 99999999999999999L;
        long prevCount = 0;
        long prevheight = -1;
        for (int i = 0; i < map.size(); i++) {
            if (prevheight != map.get(i)) {
                prevheight = map.get(i);
                long result = (long) ((long) ((long) i * map.get(i) - prevCount) * (long) P)
                        + (long) ((long) (sum - prevCount - (long) ((long) map.size() - (long) i) * map.get(i))
                                * (long) Q);
                if (answer > result) {
                    answer = result;
                }
                prevCount += map.get(i);
            } else {
                prevCount += map.get(i);
            }
        }

        return answer;
    }
}