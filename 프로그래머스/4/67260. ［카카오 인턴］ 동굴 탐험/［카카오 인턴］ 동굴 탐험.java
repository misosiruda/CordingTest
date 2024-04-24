import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;

class Solution {

    private class Node {
        ArrayList<Integer> line;

        public Node() {
            line = new ArrayList<>();
        }
    }

    public boolean solution(int n, int[][] path, int[][] order) {

        boolean[] visited = new boolean[n];
        Node[] graph = new Node[n];
        boolean[] locked = new boolean[n];
        Deque<Integer> queue = new ArrayDeque<>();
        HashMap<Integer, Integer> odr = new HashMap<>();
        HashSet<Integer> reach = new HashSet<>();

        for (int i = 0; i < n; i++) {
            graph[i] = new Node();
        }
        for (int[] is : path) {
            graph[is[0]].line.add(is[1]);
            graph[is[1]].line.add(is[0]);
        }
        for (int[] is : order) {
            locked[is[1]] = true;
            odr.put(is[0], is[1]);
        }

        int visitCnt = 0;
        queue.add(0);
        while (queue.size() > 0) {
            int now = queue.pollFirst();
            if (!reach.contains(now)) {
                reach.add(now);
            }
            if (locked[now]) {
                continue;
            }
            visited[now] = true;
            visitCnt++;
            int lock = odr.getOrDefault(now, -1);
            if (lock >= 0 && locked[lock]) {
                locked[lock] = false;
                if (reach.contains(lock)) {
                    queue.addLast(lock);
                }
            }
            for (Integer i : graph[now].line) {
                if (visited[i]) {
                    continue;
                }
                queue.add(i);
            }
        }

        if (visitCnt < n) {
            return false;
        }
        return true;
    }
}