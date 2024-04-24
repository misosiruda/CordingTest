import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Node
 */
class Node {

    public int name; //자기 자신의 번호
    public ArrayList<Node> child; //자식 노드를 담을 리스트
    public int childIdx; //현재 연결된 길인 자식 index 번호
    public int count; //리프노드로써 도달한 횟수

    public Node(int name) {
        this.name = name;
        child = new ArrayList<>();
        childIdx = 0;
        count = 0;
    }
}

class Tree {

    private Node root; //루트 노드
    private ArrayList<Integer> leafIndexList; //리프의 방문 순서를 담을 리스트
    private boolean[] isAbleLeaf; //리프 노드가 조건을 모두 달성 했는지 알기 위한 boolean 배열
    private int[] target; //계속 매개변수로 받아 오기 귀찮아서 미리 받아오는 target
    private int[] cntArr; //몇번 만에 target에 도달해야 하는지 적힌 배열

    public Tree(int[] target) {
        root = new Node(1); //루트는 언제나 1번 노드 이기때문에 1로 초기화
        leafIndexList = new ArrayList<>(); //리스트 초기화
        isAbleLeaf = new boolean[target.length];
        this.target = target;
        cntArr = new int[target.length];
        Arrays.fill(cntArr, 0);
        for (int i = 0; i < target.length; i++) {
            if (target[i] == 0) {
                isAbleLeaf[i] = true;
            } else {
                isAbleLeaf[i] = false;
            }
        }
    }

    /**
     * 노드 추가 하기 위한 재귀탐색 함수
     * @param parent 부모 노드 번호
     * @param child 자식 노드 번호
     * @param node 현재 선택된 노드
     */
    private void addsearch(int parent, int child, Node node) {
        int curName = node.name;
        if (curName == parent) { //현재 노드가 부모 노드면
            node.child.add(new Node(child)); //자식 리스트에 자식 노드 생성 및 추가
            return;
        }
        if (node.child.size() != 0) { //현재 노드에 자식이 있다면
            for (Node n : node.child) { //자식 노드를 재귀 탐색
                addsearch(parent, child, n);
            }
            return;
        }
    }

    /**
     * edges 를 하나씩 매개변수로 받아 노드 추가를 하는 함수
     * @param edge edges 의 value 를 하나 받아온다.
     */
    public void addNode(int[] edge) {
        int parent = edge[0];
        int child = edge[1];

        addsearch(parent, child, root);
    }

    /**
     * 리프 노드에 몇번 도착하는지 어떤 순서로 도착하는지 알아내기 위한 재귀함수
     * @param node 현재 선택된 노드
     * @return 만약에 불가능한 상황이 오면 false를 반환 한다
     */
    private boolean leafSearch(Node node) {
        if (node.child.size() != 0) { //리프 노드가 아니라면 연결된 길을 통해 탐색
            Node c = node.child.get(node.childIdx); //탐색할 자식 노드 가져오기
            node.childIdx = (node.childIdx + 1) % node.child.size(); //다음 연결된 길 설정하기
            return leafSearch(c); //재귀
        } //이 밑으로는 리프 노드일때 읽혀짐
        leafIndexList.add(node.name); //리프노드 도착 순서 저장
        node.count++; //현재 노드의 도착횟수 증가
        int cnt = node.count;
        int curidx = node.name - 1;
        cntArr[curidx]++; //현재 노드의 도착횟수 증가
        int tgt = target[curidx];
        if (cnt <= tgt && tgt <= cnt * 3) { //이번 도착으로 원하는 결과값이 도출 되는지 아닌지 판단 하는 if 문
            isAbleLeaf[curidx] = true;
        } else if (isAbleLeaf[curidx]) {
            return false;
        }
        return true;
    }

    /**
     * 도착 순서를 판단하기 위한 재귀 함수를 돌리기 위한 함수
     * @return 제대로 끝나면 true 불가능 하면 false 를 반환
     */
    public boolean setLeafList() {
        boolean isKeepGoing = true;
        while (isKeepGoing) {
            if (!leafSearch(root)) {
                return false;
            }
            boolean b = true;
            for (boolean bln : isAbleLeaf) {
                if (!bln) {
                    b = false;
                    break;
                }
            }
            if (b) {
                isKeepGoing = false;
            }
        }
        return true;
    }
    /**
     * 최종 반환할 int[] 배열을 만들 함수
     * @return 정답을 반환한다.
     */
    public int[] makeAnswer() {
        ArrayList<Integer> answer = new ArrayList<>();//index 두개 이상 다루기 귀찮으니 리스트로 선언
        for (int i : leafIndexList) {//리프노드 도착 순서대로 for문 돌려서
            int idx = i - 1;
            if (cntArr[idx] > 1) {//이 노드에 한번 이상 더 와야할때
                cntArr[idx]--;
                if (target[idx] < (cntArr[idx] * 3 + 1)) {//1을 넣어도 충분히 조건을 달성 가능하면
                    answer.add(1);//1을 넣고
                    target[idx] -= 1;//target 에도 1을 뺀다
                } else {//아니라면
                    int num = 0;
                    num = target[idx] - (cntArr[idx] * 3);//이미 조건은 달성 가능하다는걸 알기 때문에 조건에 부합한 숫자를 구한다.
                    answer.add(num);//그 숫자를 넣고
                    target[idx] -= num;//target 에도 뺀다.
                }
            } else {//이 노드에 마지막으로 왔다면
                answer.add(target[idx]);//target 만큼 넣어준다.
            }
        }
        int[] aswrArr = new int[answer.size()];//반환이 int[]로 되어있기에 변환 해준다.
        for (int i = 0; i < aswrArr.length; i++) {
            aswrArr[i] = answer.get(i);
        }
        return aswrArr;
    }
}

class Solution {

    public int[] solution(int[][] edges, int[] target) {
        Arrays.sort(//부모 노드 부터 차례대로 Tree 에 담기 위해 sort 해 준다.
            edges,
            new Comparator<int[]>() {//2차원 배열이라 Comparator 재정의
                @Override
                public int compare(int[] a, int[] b) {
                    return a[0] - b[0] == 0 ? a[1] - b[1] : a[0] - b[0];
                }//부모노드 기준 오름 차순으로 정렬하고 부모노드가 같다면 자식노드를 기준 삼아 정렬해 나중에 연결된 길 부분을 구현할 수 있게 한다.
            }
        );

        Tree tree = new Tree(target);//트리 생성
        for (int[] edge : edges) {
            tree.addNode(edge);//edges 를 하나씩 탐색하며 트리 생성
        }

        if (!tree.setLeafList()) return new int[] { -1 };//리프노드를 조건에 부합할 때 까지 탐색한다. 만약 조건 달성이 불가능 하다면 배열에 -1 담아서 반환
        return tree.makeAnswer();//조건에 부합하게 배열을 만들어서 반환
    }
}