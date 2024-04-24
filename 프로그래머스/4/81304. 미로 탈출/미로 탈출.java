import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

class Solution {

    private class Q {
        int cost;
        int pos;
        boolean[] visited;

        public Q(int cost, int pos, boolean[] visited) {
            this.cost = cost;
            this.pos = pos;
            this.visited = visited;
        }
    }

    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        HashMap<Integer, Integer> trapIndex = new HashMap<>();
        for (int i = 0; i < traps.length; i++) {
            trapIndex.put(traps[i], i);
        }
        boolean[] trapVisited = new boolean[traps.length];
        boolean[] allVisited = new boolean[n + 1];
        int[] answers = new int[n + 1];
        int inf = 10000000;
        int[][] graph = new int[n + 1][n + 1];
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(graph[i], inf);
            graph[i][i] = 0;
        }
        for (int[] is : roads) {
            int a = is[0];
            int b = is[1];
            int dist = is[2];
            graph[a][b] = Math.min(graph[a][b], dist);
        }
        answers = graph[start];
        PriorityQueue<Q> queue = new PriorityQueue<>(new Comparator<Q>() {
            @Override
            public int compare(Solution.Q a, Solution.Q b) {
                return a.cost - b.cost;
            }
        });
        queue.add(new Q(0, start, trapVisited));
        allVisited[start] = true;
        while (queue.size() > 0) {
            Q qNow = queue.poll();
            int cost = qNow.cost;
            int pos = qNow.pos;
            if (pos == end) {
                return cost;
            }
            boolean[] visited = qNow.visited;
            int posTI = trapIndex.getOrDefault(pos, -1);// pos trap index
            for (int i = 1; i < n + 1; i++) {
                boolean isrevers = false;
                int nextTI = trapIndex.getOrDefault(i, -1);// next trap index
                if (posTI != -1) {
                    isrevers ^= visited[posTI];
                }
                if (nextTI != -1) {
                    isrevers ^= visited[nextTI];
                }
                int nextCost;
                if (isrevers) {
                    nextCost = graph[i][pos];
                } else {
                    nextCost = graph[pos][i];
                }
                if (nextCost != 0 && nextCost != inf) {
                    if (allVisited[i] && i != end && posTI == -1 && nextTI == -1) {
                        if (answers[i] > cost + nextCost) {
                            answers[i] = cost + nextCost;
                        } else {
                            continue;
                        }
                    }
                    boolean[] vi = visited.clone();
                    if (nextTI != -1) {
                        vi[nextTI] = !vi[nextTI];
                    } else {
                        allVisited[i] = true;
                    }
                    queue.add(new Q(nextCost + cost, i, vi));
                }
            }
        }
        return 0;
    }
}