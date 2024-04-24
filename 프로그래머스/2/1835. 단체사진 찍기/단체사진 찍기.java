import java.util.ArrayDeque;
import java.util.Deque;

class Solution {

    Character[] people = { 'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T' };
    Deque<Character[]> combi;

    class Condition {
        char a;
        char b;
        char op;
        Integer diff;

        Condition(String str) {
            char[] arr = str.toCharArray();
            a = arr[0];
            b = arr[2];
            op = arr[3];
            diff = Integer.parseInt("" + arr[4]);
        }
    }

    void setCombi(Character[] arr) {
        Character[] ppl = people.clone();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                for (int j = 0; j < ppl.length; j++) {
                    if (ppl[j] == arr[i]) {
                        ppl[j] = null;
                        break;
                    }
                }
            } else {
                for (Character c : ppl) {
                    if (c != null) {
                        arr[i] = c.charValue();
                        if (i == arr.length - 1) {
                            combi.add(arr.clone());
                            break;
                        } else {
                            setCombi(arr.clone());
                        }
                    }
                }
                break;
            }
        }
    }

    boolean checkCondition(Character[] arr, String str) {
        Condition c = new Condition(str);
        int a = 0;
        int b = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == c.a) {
                a = i;
            }
            if (arr[i] == c.b) {
                b = i;
            }
        }
        switch (c.op) {
            case '=':
                if (Math.abs(a - b) == c.diff + 1) {
                    return true;
                }
                break;
            case '>':
                if (Math.abs(a - b) > c.diff + 1) {
                    return true;
                }
                break;
            case '<':
                if (Math.abs(a - b) < c.diff + 1) {
                    return true;
                }
                break;
        }
        return false;
    }

    public int solution(int n, String[] data) {
        combi = new ArrayDeque<>();
        setCombi(new Character[8]);
        int answer = 0;
        while (combi.size() > 0) {
            Character[] now = combi.pollFirst();
            int i;
            for (i = 0; i < data.length; i++) {
                if (!checkCondition(now, data[i])) {
                    break;
                }
            }
            if (i == data.length) {
                answer++;
            }
        }
        return answer;
    }
}