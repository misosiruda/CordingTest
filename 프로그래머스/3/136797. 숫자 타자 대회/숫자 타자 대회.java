import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

class Solution {

    private final int costs[][] = {
            { 1, 7, 6, 7, 5, 4, 5, 3, 2, 3 },
            { 7, 1, 2, 4, 2, 3, 5, 4, 5, 6 },
            { 6, 2, 1, 2, 3, 2, 3, 5, 4, 5 },
            { 7, 4, 2, 1, 5, 3, 2, 6, 5, 4 },
            { 5, 2, 3, 5, 1, 2, 4, 2, 3, 5 },
            { 4, 3, 2, 3, 2, 1, 2, 3, 2, 3 },
            { 5, 5, 3, 2, 4, 2, 1, 5, 3, 2 },
            { 3, 4, 5, 6, 2, 3, 5, 1, 2, 4 },
            { 2, 5, 4, 5, 3, 2, 3, 2, 1, 2 },
            { 3, 6, 5, 4, 5, 3, 2, 4, 2, 1 }
    };

    class Node {
        int count;
        int left, right;
        int idx;

        public Node(int count, int left, int right, int idx) {
            this.count = count;
            this.left = left;
            this.right = right;
            this.idx = idx;
        }

        @Override
        public int hashCode() {
            return Integer.parseInt("" + left + right + idx);
        }

        @Override
        public boolean equals(Object obj) {
            Node o = (Node) obj;
            if (this.left == o.left && this.right == o.right && this.idx == o.idx) {
                return true;
            } else {
                return false;
            }
        }
    }

    public int solution(String numbers) {
        int answer = 0;
        char[] arr = numbers.toCharArray();
        HashMap<Node, Integer> map = new HashMap<>();
        PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Solution.Node a, Solution.Node b) {
                return a.count - b.count;
            }
        });
        int left = 4, right = 6;
        int count = 0;
        int idx = 0;
        queue.add(new Node(count, left, right, idx));
        while (queue.size() > 0) {
            Node qNow = queue.poll();
            count = qNow.count;
            left = qNow.left;
            right = qNow.right;
            idx = qNow.idx;
            if (idx == arr.length) {
                answer = count;
                break;
            }
            int next = Integer.parseInt("" + arr[idx]);
            int leftCost = count + costs[left][next];
            int rightCost = count + costs[right][next];
            if (right != next) {
                Node nodeN = new Node(leftCost, next, right, idx + 1);
                int n = map.getOrDefault(nodeN, -1);
                if (n == -1 || n > leftCost) {
                    map.put(nodeN, leftCost);
                    queue.add(nodeN);
                }
            }
            if (left != next) {
                Node nodeN = new Node(rightCost, left, next, idx + 1);
                int n = map.getOrDefault(nodeN, -1);
                if (n == -1 || n > rightCost) {
                    map.put(nodeN, rightCost);
                    queue.add(nodeN);
                }
            }
        }
        return answer;
    }
}