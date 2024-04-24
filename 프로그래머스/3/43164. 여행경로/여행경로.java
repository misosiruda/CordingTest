import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

class Solution {

    public String[] solution(String[][] tickets) {
        Arrays.sort(
            tickets,
            new Comparator<String[]>() {
                @Override
                public int compare(String[] a, String[] b) {
                    return a[1].compareTo(b[1]);
                }
            }
        );
        String[] answer = new String[tickets.length + 2];
        boolean[] visited = new boolean[tickets.length];
        Arrays.fill(visited, true);
        answer[0] = "1";
        answer[1] = "ICN";
        ArrayList<String[]> queue = new ArrayList<>();
        ArrayList<boolean[]> visitQ = new ArrayList<>();
        queue.add(answer);
        visitQ.add(visited);
        while (queue.size() > 0) {
            String[] q = queue.get(0);
            queue.remove(0);
            boolean[] v = visitQ.get(0);
            visitQ.remove(0);
            if (q[q.length - 1] != null) {
                answer = Arrays.copyOfRange(q, 1, q.length);
                break;
            }
            int index = Integer.parseInt(q[0]);
            for (int i = 0; i < tickets.length; i++) {
                String[] fly = tickets[i];
                if (fly[0].equals(q[index]) && v[i]) {
                    String[] temp = q.clone();
                    temp[index + 1] = fly[1];
                    temp[0] = Integer.toString(index + 1);
                    queue.add(temp);
                    boolean[] tmp = v.clone();
                    tmp[i] = false;
                    visitQ.add(tmp);
                }
            }
        }
        return answer;
    }
}