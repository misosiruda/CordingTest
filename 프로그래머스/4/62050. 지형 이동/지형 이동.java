import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Union {

    public Union(int n) {}
}

class Solution {

    private ArrayList<int[]> map;
    private int[] parent;

    private int find(int a) {
        if (parent[a] == a) return a; else return parent[a] = find(parent[a]);
    }

    private void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) parent[b] = a;
    }

    private void createMap(int[][] land, int height) {
        int n = land.length;
        int m = land[0].length;
        map = new ArrayList<int[]>();
        int[] dy = { 1, 0 };
        int[] dx = { 0, 1 };
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                for (int i = 0; i < 2; i++) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];
                    if (ny >= n || nx >= m) {
                        continue;
                    } else {
                        int nidx = (ny * m) + nx;
                        int pidx = (y * m) + x;
                        int diff = Math.abs(land[y][x] - land[ny][nx]);
                        diff = diff - height > 0 ? diff : 0;
                        map.add(new int[] { pidx, nidx, diff });
                    }
                }
            }
        }
        Collections.sort(map, (a, b) -> a[2] - b[2]);
        parent = new int[n * m];
        Arrays.setAll(parent, x -> x);
    }

    public int solution(int[][] land, int height) {
        int answer = 0;
        createMap(land, height);
        for (int[] value : map) {
            int a = value[0];
            int b = value[1];
            int diff = value[2];
            if (find(a) != find(b)) {
                union(a, b);
                answer += diff;
            }
        }
        return answer;
    }
}