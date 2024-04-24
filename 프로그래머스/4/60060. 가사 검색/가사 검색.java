import java.util.ArrayList;
import java.util.HashMap;

class Solution {

    private class First {
        int num;
        char first;

        public First(int num, char first) {
            this.num = num;
            this.first = first;
        }

        @Override
        public int hashCode() {
            return num * 100 + first;
        }

        @Override
        public boolean equals(Object obj) {
            First o = (First) obj;
            if (o == null) {
                return false;
            }
            return num == o.num && first == o.first;
        }
    }

    private class Last {
        int num;
        char last;

        public Last(int num, char last) {
            this.num = num;
            this.last = last;
        }

        @Override
        public int hashCode() {
            return num * 100 + last;
        }

        @Override
        public boolean equals(Object obj) {
            Last o = (Last) obj;
            if (o == null) {
                return false;
            }
            return num == o.num && last == o.last;
        }
    }

    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];

        HashMap<Integer, Integer> numDic = new HashMap<>();
        HashMap<First, ArrayList<String>> firstDic = new HashMap<>();
        HashMap<Last, ArrayList<String>> lastDic = new HashMap<>();
        HashMap<String, Integer> duplDic = new HashMap<>();

        for (String st : words) {
            int len = st.length();
            char f = st.charAt(0);
            char l = st.charAt(len - 1);
            First first = new First(len, f);
            Last last = new Last(len, l);

            numDic.put(len, numDic.getOrDefault(len, 0) + 1);

            ArrayList<String> list = firstDic.getOrDefault(first, new ArrayList<>());
            list.add(st);
            firstDic.put(first, list);

            list = lastDic.getOrDefault(last, new ArrayList<>());
            list.add(st);
            lastDic.put(last, list);
        }

        for (int i = 0; i < queries.length; i++) {
            String st = queries[i];
            int len = st.length();
            char f = st.charAt(0);
            char l = st.charAt(len - 1);
            int n = duplDic.getOrDefault(st, -1);

            if (n == -1) {
                if (f == '?' && l == '?') {
                    n = numDic.getOrDefault(len, 0);
                } else if (l == '?') {
                    ArrayList<String> list = firstDic.getOrDefault(new First(len, f), new ArrayList<>());
                    int start = 0;
                    int end = 0;
                    for (; end < answer.length; end++) {
                        if (st.charAt(end) == '?') {
                            break;
                        }
                    }
                    n = 0;
                    String startString = st.substring(start, end);
                    for (String dic : list) {
                        if (dic.startsWith(startString)) {
                            n++;
                        }
                    }
                } else if (f == '?') {
                    ArrayList<String> list = lastDic.getOrDefault(new Last(len, l), new ArrayList<>());
                    n = 0;
                    for (String dic : list) {
                        int j = 0;
                        for (; j < len; j++) {
                            if (st.charAt(j) != '?' && st.charAt(j) != dic.charAt(j)) {
                                break;
                            }
                        }
                        if (j == len) {
                            n++;
                        }
                    }
                }
            }
            answer[i] = n;
            duplDic.put(st, n);
        }

        return answer;
    }
}