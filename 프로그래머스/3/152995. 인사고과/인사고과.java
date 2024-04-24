import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public int solution(int[][] scores) {
        PriorityQueue<Integer[]> rank = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] a, Integer[] b) {
                return b[0] - a[0];
            }
        });
        int[][] arr = new int[scores.length][3];
        boolean[] banned = new boolean[scores.length];

        for (int i = 0; i < arr.length; i++) {
            arr[i][0] = scores[i][0];
            arr[i][1] = scores[i][1];
            arr[i][2] = i;
        }
        Arrays.sort(arr, (a, b) -> b[0] == a[0] ? a[1] - b[1] : b[0] - a[0]);

        int maxx = 0;
        int maxy = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            int oy = arr[i][0];
            int ox = arr[i][1];
            if (maxx < ox) {
                maxx = ox;
                maxy = oy;
            }
            int ny = arr[i + 1][0];
            int nx = arr[i + 1][1];
            if (maxy > ny && maxx > nx) {
                banned[i + 1] = true;
                if (arr[i + 1][2] == 0) {
                    return -1;
                }
            }
        }
        for (int i = 0; i < banned.length; i++) {
            if (!banned[i]) {
                rank.add(new Integer[] { arr[i][0] + arr[i][1], arr[i][2] });
            }
        }

        int answer = 0;
        int prev = 0;
        int acel = 1;
        Integer[] now = new Integer[2];
        while (rank.size() > 0) {
            now = rank.poll();
            if (now[1] == 0) {
                if (prev == now[0]) {
                    break;
                } else {
                    answer += acel;
                    break;
                }
            } else if (prev == now[0]) {
                acel++;
                continue;
            } else {
                answer += acel;
                if (acel != 1) {
                    acel = 1;
                }
                prev = now[0];
            }
        }
        if (rank.size() == 0 && now[1] != 0) {
            return -1;
        }
        return answer == 0 ? 1 : answer;
    }
}