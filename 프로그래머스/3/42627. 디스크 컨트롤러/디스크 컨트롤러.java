import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public int solution(int[][] jobs) {
        PriorityQueue<Integer[]> queue = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] a, Integer[] b) {
                return a[1] == b[1] ? a[0] - b[0] : a[1] - b[1];
            }
        });
        Arrays.sort(jobs, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] == b[0] ? a[1] - b[1] : a[0] - b[0];
            }
        });
        int sum = 0;
        int time = 0;
        for (int[] task : jobs) {
            while (queue.size() > 0 && task[0] > time) {
                Integer[] done = queue.poll();
                time += done[1];
                sum += time - done[0];
            }
            if (queue.size() == 0 && time <= task[0]) {
                time = task[0] + task[1];
                sum += task[1];
                continue;
            }
            queue.add(new Integer[] { task[0], task[1] });
        }
        while (queue.size() > 0) {
            Integer[] done = queue.poll();
            time += done[1];
            sum += time - done[0];
        }
        return (int) sum / jobs.length;
    }
}