import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

class Solution {

    /**
     * 주사위 조합
     */
    ArrayList<Dice> combi;
    /**
     * 주사위 갯수
     */
    int n;

    /**
     * 주사위 조합 객체
     */
    private class Dice {
        /**
         * 주사위 조합에서 선택한 주사위의 번호 1번부터 시작
         */
        Integer[] sel;
        /**
         * 현재 주사위 조합에서 나올 수 있는 합들을 묶어 놓은 해시맵 {key : 합, value : 갯수}
         */
        HashMap<Integer, Integer> sums;

        /**
         * 생성자
         * 
         * @param sel 현재 선택한 조합
         */
        Dice(Integer[] sel) {
            this.sel = sel;
            sums = new HashMap<>();
        }

        /**
         * 혹시 모를 빈 생성자
         */
        Dice() {
        }

        /**
         * 현재 조합에 합을 구하는 함수
         * 
         * @param dice 전체 주사위
         */
        void setSum(int[][] dice) {
            ArrayList<Integer> s = new ArrayList<>();// 합들을 넣어줄 리스트 선언
            for (int i : dice[sel[0] - 1]) {
                s.add(i);// 첫번째 주사위의 값들을 넣어준다.
            }
            for (int i = 1; i < sel.length; i++) {
                ArrayList<Integer> temp = new ArrayList<>();// 더해준 값을 넣어둘 임시 리스트 선언
                for (Integer p : s) {
                    for (int n : dice[sel[i] - 1]) {
                        temp.add(p + n);// 전에 주사위 값들에 현재 주사위 값을 합쳐서 temp에 담는다.
                    }
                    s = temp;// s에 temp 를 넣고 다시 재귀
                }
            }
            for (Integer num : s) {// 중복값들을 정리 하기 위해 해시맵에 넣어준다.
                sums.put(num, sums.getOrDefault(num, 0) + 1);
            }
        }

        /**
         * 반대 주사위 조합인지 판단하는 함수
         * 
         * @param other 상대 주사위 조합 Dice 객체
         * @return true : 반대 조합, false : 겹치는 주사위가 있음
         */
        boolean isOpposite(Dice other) {
            Integer[] o = other.sel;
            for (int i = 0; i < o.length; i++) {
                for (int j = 0; j < o.length; j++) {
                    if (sel[i] == o[j]) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    /**
     * 주사위 조합을 구하는 재귀 함수
     * 
     * @param cb 직전까지 계산한 주사위 조합
     */
    private void getCombination(Integer[] cb) {
        int prev = 0; // 전에 계산한 숫자를 구하기 위한 변수
        int idx = 0; // 다음 넣을 인덱스를 구하기 위한 변수
        for (Integer i : cb) {
            if (i == null) {
                break; // 앞에서 부터 넣었을 테니 null을 만나면 바로 끝내기
            }
            prev = i;
            idx++;
        }
        if (idx == cb.length) {// 만약 더이상 넣을 자리가 없다면 조합의 완성이니 끝
            combi.add(new Dice(cb));
            return;
        }
        for (int i = prev + 1; i <= n; i++) {// 아니면 다음 조합을 구해서 재귀
            Integer[] temp = cb.clone();
            temp[idx] = i;
            getCombination(temp);
        }
        // 만약에 조합이 완성할 수 없는 상황(ex: n이 4일때 처음 선택한 주사위가 4일 경우)
        // 이면 여기 까지 와서 아무것도 일어나지 않는다.
    }

    /**
     * 현재 선택한 주사위 조합이 얼마나 이겼는지를 점수로 계산해 반환 하는 함수
     * 
     * @param idx   현재 선택한 주사위 조합의 인덱스 번호
     * @param combi 주사위 조합들 리스트
     * @return 이긴 횟수를 반환한다.
     */
    private int getScore(int idx, ArrayList<Dice> combi) {
        int score = 0; // 점수를 담을 변수 선언
        Dice a = combi.get(idx); // 현재 선택한 주사위 조합
        Dice b = new Dice(); // 상대 조합을 찾기 위해 변수 선언
        for (Dice dice : combi) {
            if (a.isOpposite(dice)) { // 반대 인지 구하고 b 에 담는다.
                b = dice;
                break;
            }
        }

        // 이미 해시맵을 써서 상당히 많은 시간을 단축 했음으로 따로 예외 처리를 하지 않는다.
        for (Entry<Integer, Integer> aEntry : a.sums.entrySet()) {
            int anum = aEntry.getKey();
            int acnt = aEntry.getValue();
            for (Entry<Integer, Integer> bEntry : b.sums.entrySet()) {
                int bnum = bEntry.getKey();
                int bcnt = bEntry.getValue();
                // 문제 점 승률을 비교하라 해서 얼마나 이겼는지 보다 이긴 횟수 / 진 횟수 가 높기 위해
                // 이긴 횟수 - 진 횟수를 구했으나 14, 21 케이스 에서 실패 했다.
                // 이긴 횟수만 더해주니 통과
                if (anum > bnum) {
                    score += acnt * bcnt;
                }
            }
        }

        return score;
    }

    public int[] solution(int[][] dice) {
        combi = new ArrayList<>();// 주사위 조합 객체를 담은 리스트 초기화
        n = dice.length; // 주사위 갯수 초기화
        int[] result;// 이긴 횟수들을 담을 배열 선언

        getCombination(new Integer[n / 2]);// 주사위 조합들을 생성 해주고
        for (Dice d : combi) {// 주사위 조합에 따른 합들을 넣어주고
            d.setSum(dice);
        }
        result = new int[combi.size()];// 주사위 조합의 크기에 맞춰 이긴 횟수를 담을 배열 초기화

        // 그리고 result 에 점수들을 담고
        for (int i = 0; i < result.length; i++) {
            int score = getScore(i, combi);
            result[i] = score;
        }

        // 점수들 중에서 가장 큰 값의 인덱스 번호를 구한다
        int idx = 0;
        int max = 0;
        for (int i = 0; i < result.length; i++) {
            if (result[i] > max) {
                idx = i;
                max = result[i];
            }
        }

        // 그리고 최종 반환할 answer 에다가 idx 번호에 있는 주사위 조합을 넣어준다.
        int[] answer = new int[n / 2];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = combi.get(idx).sel[i];
        }
        return answer;
    }
}