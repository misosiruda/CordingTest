import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

class Solution {

    class BFS {
        public boolean[] visited;
        public int index;

        public BFS(boolean[] visited, int index) {
            this.visited = visited;
            this.index = index;
        }
    }

    public int solution(int n, int[] weak, int[] dist) {
        int m = weak.length;
        int[] weaks = new int[m * 2];
        for (int i = 0; i < m; i++) {
            weaks[i] = weak[i];
            weaks[i + m] = weak[i] + n;
        }
        Arrays.sort(dist);
        ArrayDeque<BFS> queue = new ArrayDeque<>();
        queue.addLast(new BFS(new boolean[m * 2], dist.length - 1));
        while (queue.size() > 0) {
            BFS qNow = queue.pollFirst();
            boolean[] visited = qNow.visited;
            int index = qNow.index;
            int sel = dist[index];
            ArrayList<Integer[]> next = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                int sum = 0;
                int temp = 0;
                int k;
                for (k = j; k < j + m; k++) {
                    if (visited[k]) {
                        break;
                    }
                    temp = sum;
                    sum += weaks[k + 1] - weaks[k];
                    if (sum > sel) {
                        break;
                    }
                }
                next.add(new Integer[] { j, k, temp });
            }
            Collections.sort(next, new Comparator<Integer[]>() {
                @Override
                public int compare(Integer[] o1, Integer[] o2) {
                    return (o2[1] - o2[0]) - (o1[1] - o1[0]);
                }
            });
            int max = next.get(0)[1] - next.get(0)[0];
            for (Integer[] integers : next) {
                if (integers[1] - integers[0] < max / 1.5) {
                    break;
                }
                int j = integers[0], k = integers[1], temp = integers[2];
                boolean[] vi = visited.clone();
                if (temp == 0) {
                    vi[j] = true;
                    vi[j + m] = true;
                } else {
                    for (int i = j; i <= k; i++) {
                        if (i < m) {
                            vi[i] = true;
                            vi[i + m] = true;
                        } else {
                            vi[i] = true;
                            vi[i - m] = true;
                        }
                    }
                }
                boolean isdone = true;
                for (boolean b : vi) {
                    if (!b) {
                        isdone = false;
                        break;
                    }
                }
                if (isdone) {
                    return dist.length - index;
                }
                if (index != 0) {
                    queue.addLast(new BFS(vi, index - 1));
                }
            }
        }
        return -1;
    }
}