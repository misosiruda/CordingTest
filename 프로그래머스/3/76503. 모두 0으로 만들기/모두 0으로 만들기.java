import java.util.ArrayList;

class Solution {

    static Graph[] graph;
    static long answer;
    static long[] agg;

    private class Graph {
        ArrayList<Integer> child;

        Graph() {
            child = new ArrayList<>();
        }
    }

    private void DFS(int node, int parent) {
        Integer child;
        for (int i = 0; i < graph[node].child.size(); i++) {
            child = graph[node].child.get(i);
            if (child == parent) {
                continue;
            }
            DFS(child, node);
            answer += Math.abs(agg[child]);
            if (agg[child] > 0) {
                agg[node] += agg[child];
                agg[child] -= agg[child];
            } else {
                agg[node] += agg[child];
                agg[child] -= agg[child];
            }
        }
    }

    public long solution(int[] a, int[][] edges) {
        graph = new Graph[a.length];
        for (int i = 0; i < a.length; i++) {
            graph[i] = new Graph();
        }
        for (int[] is : edges) {
            int l = is[0];
            int r = is[1];
            graph[l].child.add(r);
            graph[r].child.add(l);
        }
        agg = new long[a.length];
        for (int i = 0; i < a.length; i++) {
            agg[i] = a[i];
        }

        DFS(0, 0);
        for (long i : agg) {
            if (i != 0) {
                return -1;
            }
        }
        return answer;
    }
}