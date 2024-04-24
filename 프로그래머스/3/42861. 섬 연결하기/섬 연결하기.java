import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int[] parent;

    public int find(int a) {
        if (parent[a] == a) return a; else return parent[a] = find(parent[a]);
    }

    public void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) parent[b] = a;
    }

    public int solution(int n, int[][] costs) {
        Arrays.sort( //가중치를 기준으로 정렬
            costs,
            (a, b) -> a[2] - b[2]
        );

        parent = new int[n];
        Arrays.setAll(parent, i -> i);
        int answer = 0;
        for (int i = 0; i < costs.length; i++) {
            int a = costs[i][0];
            int b = costs[i][1];
            int val = costs[i][2];
            if (find(a) != find(b)) {
                union(a, b);
                answer += val;
            }
        }
        return answer;
    }
}