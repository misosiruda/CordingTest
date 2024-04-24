import java.util.ArrayList;

class Solution {

    Graph[] graph;
    boolean[] light;
    int answer = 0;

    private class Graph {
        ArrayList<Integer> connect;

        Graph() {
            connect = new ArrayList<>();
        }
    }

    private void DFS(int node, int parent) {
        for (Integer child : graph[node].connect) {
            if (child == parent) {
                continue;
            }
            DFS(child, node);
            if (!light[child] && !light[node]) {
                light[node] = true;
                answer++;
            }
        }
    }

    public int solution(int n, int[][] lighthouse) {
        graph = new Graph[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new Graph();
        }
        for (int[] li : lighthouse) {
            graph[li[0] - 1].connect.add(li[1] - 1);
            graph[li[1] - 1].connect.add(li[0] - 1);
        }
        light = new boolean[n];
        DFS(0, 0);
        return answer;
    }
}