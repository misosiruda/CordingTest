import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Solution {

    private int[][] agraph;
    private int[][] bgraph;

    /**
     * 현재 노드에 대해 자식 노드들의 배열을 반환하는 함수
     * @param p parent past 등등 부모 노드의 이름
     * @param c child current 등등 현재 노드의 이름
     * @param graph 현재 노드가 있는 트리의 간선 그래프
     * @return 현재 노드의 자식 노드들의 이름이 들어있는 배열
     */
    private int[] getChildArr(int p, int c, int[][] graph) {
        ArrayList<Integer> list = new ArrayList<>(); //갯수를 모르니 리스트로 선언

        for (int[] line : graph) { //현재 노드가 들어있으면 반대편 노드를 리스트에 집어 넣지만 그게 부모 노드와 같다면 무시
            if (line[0] == c && line[1] != p) {
                list.add(line[1]);
            } else if (line[1] == c && line[0] != p) {
                list.add(line[0]);
            }
        }
        int[] result = new int[list.size()]; //반환은 배열로 해야 편하기 때문에 반환은 int[] 로
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    private int DFS(ArrayList<Integer[]> costGraph, int index, boolean[] avisited, boolean[] bvisited) {
        boolean[] av = avisited;
        boolean[] bv = bvisited;
        Integer[] line = costGraph.get(index);
        int cost = line[2];
        av[line[0]] = true;
        bv[line[1]] = true;
        int maxCost = 0;
        for (int i = 0; i < costGraph.size(); i++) {
            Integer[] l = costGraph.get(i);
            if (!av[l[0]] && !bv[l[1]]) {
                maxCost = DFS(costGraph, i, av, bv);
                break;
            }
        }
        av[line[0]] = false;
        bv[line[1]] = false;
        return cost + maxCost;
    }

    /**
     * a 와 b 사이의 최대 유사도를 반환하는 재귀 함수
     * @param ap a노드의 부모 노드 이름
     * @param a a노드의 이름
     * @param bp b노드의 부모 노드 이름
     * @param b b노드의 이름
     * @return a 와 b 의 최대 유사도를 반환
     */
    private int getLeastCost(int ap, int a, int bp, int b) {
        int[] achild = getChildArr(ap, a, agraph); //a노드의 자식 노드들의 이름을 담은 배열
        int[] bchild = getChildArr(bp, b, bgraph); //b노드의 자식 노드들의 이름을 담은 배열
        if (achild.length == 0 || bchild.length == 0) {
            return 1; //만약 자식 노드가 없는 leaf 노드 라면 유사도는 1
        }
        //이 아래로는 둘다 자식 노드가 한개 이상 있을 때 읽힘

        //유사도 간선 그래프 그리기
        ArrayList<Integer[]> costGraph = new ArrayList<>(); //자식 노드간의 유사도 간선을 나타낼 리스트 선언
        for (int ai : achild) { //a의 자식 노드들
            for (int bi : bchild) { //b의 자식 노드들
                int cost = getLeastCost(a, ai, b, bi); //재귀함수로 유사도를 찾아낸다.
                costGraph.add(new Integer[] { ai, bi, cost }); //[a자식 노드, b자식 노드, 유사도]를 추가
            }
        }
        Collections.sort(
            costGraph,
            new Comparator<Integer[]>() {
                @Override
                public int compare(Integer[] a, Integer[] b) {
                    return b[2] - a[2];
                }
            }
        );

        //최대 유사도 간선 경로 탐색(DFS 쓰도록 하자)
        boolean[] avisited = new boolean[101]; //노드가 쓰였는지 안쓰였는지 확인 하기 위한 visited 배열 선언 및 초기화
        boolean[] bvisited = new boolean[101]; //인덱스는 노드의 번호

        int maxCost = 0;
        int maxi = costGraph.size() > 15 ? 15 : costGraph.size();
        for (int i = 0; i < maxi; i++) {
            int c = DFS(costGraph, i, avisited, bvisited) + 1;
            if (c > maxCost) maxCost = c;
        }
        return maxCost;
    }

    public int solution(int n1, int[][] g1, int n2, int[][] g2) {
        agraph = g1;
        bgraph = g2;

        return getLeastCost(0, 1, 0, 1);
    }
}