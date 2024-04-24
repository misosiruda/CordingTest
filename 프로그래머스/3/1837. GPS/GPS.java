import java.util.ArrayList;
import java.util.Arrays;

class Solution {

    class Graph {
        ArrayList<Integer> list;

        Graph() {
            list = new ArrayList<>();
        }
    }

    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        Graph[] graph = new Graph[n + 1];
        int[][] dp = new int[k][n + 1];
        int inf = 200;

        for (int i = 0; i < n + 1; i++) {
            graph[i] = new Graph();
            graph[i].list.add(i);
        }
        for (int[] edge : edge_list) {
            int a = edge[0], b = edge[1];
            graph[a].list.add(b);
            graph[b].list.add(a);
        }
        for (int[] is : dp) {
            Arrays.fill(is, inf);
        }
        dp[0][gps_log[0]] = 0;

        for (int t = 1; t < k; t++) {
            for (int prev = 0; prev < n + 1; prev++) {
                if (dp[t - 1][prev] == inf) {
                    continue;
                }
                for (Integer next : graph[prev].list) {
                    if (next == gps_log[t]) {
                        dp[t][next] = Math.min(dp[t][next], dp[t - 1][prev]);
                    } else {
                        dp[t][next] = Math.min(dp[t][next], dp[t - 1][prev] + 1);
                    }
                }
            }
        }
        return dp[k - 1][gps_log[k - 1]] == inf ? -1 : dp[k - 1][gps_log[k - 1]];
    }
}