import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

class Solution {

    class Graph {
        ArrayList<Integer[]> child;

        Graph() {
            child = new ArrayList<>();
        }
    }

    class Q {
        int cost;
        int pos;

        Q(int cost, int pos) {
            this.cost = cost;
            this.pos = pos;
        }
    }

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {

        Graph[] graph = new Graph[n + 1];
        HashSet<Integer> gate = new HashSet<>();
        HashSet<Integer> summit = new HashSet<>();
        int[] costs = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new Graph();
        }
        for (int[] p : paths) {
            int a = p[0];
            int b = p[1];
            int cost = p[2];
            graph[a].child.add(new Integer[] { b, cost });
            graph[b].child.add(new Integer[] { a, cost });
        }
        for (int s : summits) {
            summit.add(s);
        }
        Arrays.fill(costs, 999999999);
        costs[0] = 0;
        for (int g : gates) {
            gate.add(g);
        }

        PriorityQueue<Q> queue = new PriorityQueue<>(new Comparator<Q>() {
            @Override
            public int compare(Solution.Q a, Solution.Q b) {
                return a.cost - b.cost;
            }
        });
        for (int g : gates) {
            queue.add(new Q(0, g));
        }
        while (queue.size() > 0) {
            Q qn = queue.poll();
            if (costs[qn.pos] > qn.cost) {
                costs[qn.pos] = qn.cost;
            } else {
                continue;
            }
            for (Integer[] c : graph[qn.pos].child) {
                if (gate.contains(c[0])) {
                    continue;
                }
                if (summit.contains(c[0])) {
                    int co = Math.max(qn.cost, c[1]);
                    costs[c[0]] = Math.min(co, costs[c[0]]);
                    continue;
                }
                if (costs[c[0]] < qn.cost && costs[c[0]] < c[1]) {
                    continue;
                }
                queue.add(new Q(Math.max(c[1], qn.cost), c[0]));
            }
        }

        int[][] result = new int[summits.length][2];
        for (int i = 0; i < result.length; i++) {
            result[i][0] = summits[i];
            result[i][1] = costs[summits[i]];
        }
        Arrays.sort(result, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if(a[1] == b[1]){
                    return a[0] - b[0];
                } else {
                    return a[1] - b[1];
                }
            }
        });
        int[] answer = result[0];
        return answer;
    }
}