import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

class Solution {

    private class Q {
        int num;
        int cost;

        public Q(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }

    private class Node {
        ArrayList<Integer> line;

        Node() {
            line = new ArrayList<>();
        }
    }

    private Node[] graph;
    private boolean[] visited;

    private Deque<Q> getMaxNode(int start) {
        visited = new boolean[graph.length];
        Deque<Q> max = new ArrayDeque<>();
        Deque<Q> queue = new ArrayDeque<>();
        queue.addLast(new Q(start, 0));
        while (queue.size() > 0) {
            Q qn = queue.pollFirst();
            visited[qn.num] = true;
            for (int i : graph[qn.num].line) {
                if (visited[i]) {
                    continue;
                }
                if (graph[i].line.size() == 1) {
                    if (max.size() == 0 || max.peek().cost < qn.cost + 1) {
                        max.clear();
                        max.addLast(new Q(i, qn.cost + 1));
                    } else if (max.peek().cost == qn.cost + 1) {
                        max.addLast(new Q(i, qn.cost + 1));
                    }
                } else {
                    queue.addLast(new Q(i, qn.cost + 1));
                }
            }
        }
        return max;
    }

    public int solution(int n, int[][] edges) {
        graph = new Node[n + 1];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new Node();
        }
        for (int[] is : edges) {
            graph[is[0]].line.add(is[1]);
            graph[is[1]].line.add(is[0]);
        }

        int num = 0;
        Deque<Q> max = getMaxNode(1);
        num = max.peek().num;
        max = getMaxNode(num);
        int firstCost = max.peek().cost;
        int firstCnt = max.size();
        num = max.peek().num;
        max = getMaxNode(num);
        int secondCost = max.peek().cost;
        int secondCnt = max.size();
        if (firstCost != secondCost) {
            return 0;
        } else if (firstCnt >= 2 || secondCnt >= 2) {
            return firstCost;
        } else {
            return firstCost - 1;
        }
    }
}