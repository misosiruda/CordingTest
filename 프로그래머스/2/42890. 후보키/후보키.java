import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;

class Solution {
    private Deque<ArrayList<Integer>> comList;

    private void combination(int[] arr, boolean[] visited, int start, int n, int r) {
        if (r == 0) {
            ArrayList<Integer> com = new ArrayList<>();
            for (int i = 0; i < arr.length; i++) {
                if (visited[i]) {
                    com.add(arr[i]);
                }
            }
            comList.add(com);
            return;
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            combination(arr, visited, i + 1, n, r - 1);
            visited[i] = false;
        }
    }

    private boolean checkContains(String[][] relation, ArrayList<Integer> com) {
        StringBuilder[] sb = new StringBuilder[relation.length];
        for (int i = 0; i < sb.length; i++) {
            sb[i] = new StringBuilder();
        }
        for (int i = 0; i < com.size(); i++) {
            int idx = com.get(i);
            for (int j = 0; j < sb.length; j++) {
                sb[j].append(relation[j][idx]);
            }
        }
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < sb.length; i++) {
            if (set.contains(sb[i].toString())) {
                return false;
            } else {
                set.add(sb[i].toString());
            }
        }
        return true;
    }

    public int solution(String[][] relation) {
        int answer = 0;
        comList = new ArrayDeque<>();
        int n = relation[0].length;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            combination(arr, visited, 0, n, i + 1);
        }

        int size = comList.size();
        while (!comList.isEmpty()) {
            ArrayList<Integer> com = comList.pollFirst();
            if (!checkContains(relation, com)) {
                continue;
            }
            answer++;
            Deque<ArrayList<Integer>> temp = new ArrayDeque<>();

            while (!comList.isEmpty()) {
                ArrayList<Integer> comp = comList.pollFirst();
                boolean isContains = true;
                for (int j = 0; j < com.size(); j++) {
                    isContains &= comp.contains(com.get(j));
                }
                if (!isContains) {
                    temp.addLast(comp);
                }
            }
            comList.addAll(temp);
        }

        return answer;
    }
}