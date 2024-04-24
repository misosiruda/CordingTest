import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int n, int[][] data) {
        int answer = 0;

        Arrays.sort(data, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] == b[0] ? a[1] - b[1] : a[0] - b[0];
            }
        });

        for (int i = 0; i < data.length - 1; i++) {
            int ax = data[i][0], ay = data[i][1];
            for (int j = i + 1; j < data.length; j++) {
                int bx = data[j][0], by = data[j][1];
                if (ax == bx || ay == by) {
                    continue;
                }
                boolean isNotOkay = false;
                for (int k = i + 1; k < j; k++) {
                    int cx = data[k][0], cy = data[k][1];
                    if (ax < cx && cx < bx && Math.min(ay, by) < cy && cy < Math.max(ay, by)) {
                        isNotOkay = true;
                        break;
                    }
                }
                if (!isNotOkay) {
                    answer++;
                }
            }
        }

        return answer;
    }
}