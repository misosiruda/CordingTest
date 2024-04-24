import java.util.HashMap;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        HashMap<String, Integer> index = new HashMap<>();
        for (int i = 0; i < players.length; i++) {
            index.put(players[i], i);
        }
        for (String st : callings) {
            int idx = index.get(st);
            String prev = players[idx - 1];
            players[idx] = players[idx - 1];
            players[idx - 1] = st;
            index.put(st, idx - 1);
            index.put(prev, idx);
        }
        return players;
    }
}