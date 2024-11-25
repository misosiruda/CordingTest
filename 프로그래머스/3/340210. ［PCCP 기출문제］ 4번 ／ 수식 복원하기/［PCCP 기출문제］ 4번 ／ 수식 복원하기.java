import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

class Solution {

    /** 답이 있는 연산일 때 자릿수 변화가 있으면 그 진수를 저장하는 변수 */
    private int fixedBase = 0;

    /**
     * 원래는 정규식을 사용하거나 replace를 사용하려 했으나
     * 너무 느릴 것 같아서 만든 숫자가 아닌 char를 지우는 함수
     *
     * @param str input string
     * @return 숫자만 남은 string
     */
    private String removeNonNumeric(String str) {
        StringBuilder result = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                result.append(c);
            }
        }
        return result.toString();
    }

    /**
     * 입력 연산들 에 있는 숫자 보다 우리가 원하는 진수는 높을 것이기에
     * 미리 구해준다.
     *
     * @param expressions 입력 연산들
     * @return 나온 숫자보다 하나 높은 숫자
     */
    private int getMinNum(String[] expressions) {
        int min = 0;
        for (String expString : expressions) {
            // 문자를 비교하게 되면 큰일 나기 때문에 지워준다.
            char[] charArr = removeNonNumeric(expString).toCharArray();
            for (char c : charArr) {
                if ((c - '0') >= min) {
                    min = c - '0' + 1;
                }
            }
        }
        return min;
    }

    /**
     * base 진수 일때 a 에 b를 op 연산한 값을 String으로 반환하는 함수
     * + fixedBase 를 할당 할 경우도 있기 때문에 답이 있는 연산일 때 예외처리를 위한 매개변수도 받는다.
     *
     * @param a    왼쪽 숫자
     * @param b    오른쪽 숫자
     * @param op   연산자
     * @param base 현재 진수
     * @param dap  답이 있는 연산 인지
     * @return 연산한 값
     */
    private String baseOperation(String a, String b, String op, int base, boolean dap) {
        // 자릿수 맞추기
        int maxLength = Math.max(a.length(), b.length());
        a = padLeft(a, maxLength, '0');
        b = padLeft(b, maxLength, '0');

        // 연산 수행
        if (op.equals("+")) {
            return addBaseNumbers(a, b, base, dap); // fixedBase 에 대한 예외 처리는 안쪽에서 한다.
        } else if (op.equals("-")) {
            return subtractBaseNumbers(a, b, base, dap);
        } else {
            return "";
        }
    }

    /**
     * 연산자가 더하기 일 때 계산
     *
     * @param a    왼쪽 숫자
     * @param b    오른쪽 숫자
     * @param base 현재 진수
     * @param dap  답이 있는 연산 인지
     * @return 연산한 값
     */
    private String addBaseNumbers(String a, String b, int base, boolean dap) {
        StringBuilder result = new StringBuilder();
        int carry = 0; // 자리올림 값

        for (int i = a.length() - 1; i >= 0; i--) {
            int digitA = a.charAt(i) - '0';
            int digitB = b.charAt(i) - '0';
            int sum = digitA + digitB + carry;
            carry = sum / base;
            result.append(sum % base);
            if (dap && carry > 0) { // 답이 있는 연산이고 자릿수 변화가 있으면
                fixedBase = base;
            }
        }
        if (carry > 0) {
            result.append(carry);
        }

        return result.reverse().toString();
    }

    /**
     * 연산자가 빼기 일 때 계산
     *
     * @param a    왼쪽 숫자
     * @param b    오른쪽 숫자
     * @param base 현재 진수
     * @param dap  답이 있는 연산 인지
     * @return 연산한 값
     */
    private String subtractBaseNumbers(String a, String b, int base, boolean dap) {
        StringBuilder result = new StringBuilder();
        boolean borrow = false;

        for (int i = a.length() - 1; i >= 0; i--) {
            int digitA = a.charAt(i) - '0';
            int digitB = b.charAt(i) - '0';
            if (borrow) { // 자릿수 변화가 있으면
                digitA -= 1;
                if (dap) { // 답이 있는 연산 이면
                    fixedBase = base;
                }
            }
            if (digitA < digitB) {
                digitA += base;
                borrow = true;
            } else {
                borrow = false;
            }
            result.append(digitA - digitB);
        }

        // 앞쪽의 불필요한 0 제거
        while (result.length() > 1 && result.charAt(result.length() - 1) == '0') {
            result.setLength(result.length() - 1);
        }

        return result.reverse().toString();
    }

    /**
     * 문자열에 왼쪽에 padChar을 채워주는 함수
     *
     * @param str     채울 문자열
     * @param length  채울 길이
     * @param padChar 채울 char
     * @return 채운 문자열
     */
    private String padLeft(String str, int length, char padChar) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length - str.length(); i++) {
            sb.append(padChar);
        }
        sb.append(str);
        return sb.toString();
    }

    /**
     * 입력받은 연산들을 답이 있는 연산들과 노답인 연산들로 나누는 함수
     * 자바의 매개변수를 참조하는 특성을 이용했다.
     *
     * @param expressions 입력받은 연산들
     * @param dapList     답이 있는 연산들
     * @param noDapList   노답인 연산들
     */
    private void setSeparateList(String[] expressions, ArrayList<String> dapList, ArrayList<String> noDapList) {
        for (String string : expressions) {
            if (string.indexOf("X") >= 0) {
                noDapList.add(string);
            } else {
                dapList.add(string);
            }
        }
    }

    /**
     * 입력받은 연산들을 직접 해당 진수로 계산했을 때
     * 답이 있는 연산이면 정답이 일치 하는지 확인 하거나
     * 노답이면 답을 적어서 HashSet에 넣어서 HashMap에 저장 하는 함수
     *
     * @param list      입력받은 연산 리스트
     * @param base      현재 진수
     * @param isDap     답이 있는 연산 인지
     * @param answerMap 노답들의 답들을 적어둔 HashMap
     * @return 답이 있는 연산 일때 연산 결과가 답과 틀리다면 해당 진수는 아니기 때문에 예외처리를 위해 false 반환
     */
    private boolean calcCompare(ArrayList<String> list, int base, boolean isDap,
            HashMap<Integer, HashSet<String>> answerMap) {
        if (isDap) { // 답이 있는 연산 일때
            for (String string : list) {
                String[] strArr = string.split(" ");
                fixedBase = 0;
                if (!strArr[4].equals(baseOperation(strArr[0], strArr[2], strArr[1], base, isDap))) {
                    // 써있는 답과 연산한 답이 틀릴 경우
                    answerMap.clear(); // 해당 진수는 틀렸으니 모두 초기화
                    fixedBase = 0;
                    return false;
                }
                if (fixedBase != 0) {
                    // 답이 맞고 정답인 진수가 나왔으면 바로 true 반환 답이 있는 연산은 할 필요가 없음
                    return true;
                }
            }
            return true;
        } else { // 노답인 연산일 때
            for (int i = 0; i < list.size(); i++) {
                String string = list.get(i);
                String[] strArr = string.split(" ");
                strArr[4] = baseOperation(strArr[0], strArr[2], strArr[1], base, isDap);
                answerMap.put(i, answerMap.getOrDefault(i, new HashSet<>()));
                answerMap.get(i).add(String.join(" ", strArr));
                // 답을 계산해서 넣은 문자열을 answerMap안에 index 에 있는 HashSet에 넣어둔다.
            }
            return true;
        }
    }

    /**
     * 노답인 연산들의 답들을 넣어 만든 answerMap 을 생성하는 함수
     *
     * @param min       진수가 될 수 있는 수중 최솟값
     * @param dapList   답이 있는 연산 리스트
     * @param noDapList 노답인 연산 리스트
     * @return 노답인 연산들의 답들이 들어있는 answerMap
     */
    private HashMap<Integer, HashSet<String>> getAnswerHashMap(int min, ArrayList<String> dapList,
            ArrayList<String> noDapList) {
        /**
         * 노답인 연산의 index 즉 순서가 중요하기 때문에 일단 HashMap으로 감싸기
         * 그 다음 연산 결과가 같으면 하나로 봐야 하기 때문에 HashSet으로 감싸기
         * 그니까
         * answerMap = {
         * key = Integer,
         * value = HashSet {
         *      value = 연산된 문자열
         *      }
         * }
         * 위와 같은 형태로 나오게됨
         */
        HashMap<Integer, HashSet<String>> answerMap = new HashMap<>();
        for (int i = min; i <= 9; i++) {
            if (calcCompare(dapList, i, true, answerMap)) {
                if (fixedBase != 0) {
                    break;//진수가 하나로 좁혀지면 더 계산할 필요가 없음
                } else {
                    calcCompare(noDapList, i, false, answerMap);
                }
            }
        }

        if (fixedBase != 0) {
            answerMap.clear();//진수가 하나로 좁혀지면 이전에 계산 결과들 초기화
            calcCompare(noDapList, fixedBase, false, answerMap);
        }

        return answerMap;
    }

    public String[] solution(String[] expressions) {
        int min = getMinNum(expressions); //가능한 진수중 가장 작은 값
        ArrayList<String> dapList = new ArrayList<>(); // 답이 있는 연산 리스트
        ArrayList<String> noDapList = new ArrayList<>(); // 노답인 연산 리스트
        setSeparateList(expressions, dapList, noDapList); // 연산 리스트들 나누기
        // 노답인 연산 리스트 들 에 연산후 답을 넣은 answerMap 생성
        HashMap<Integer, HashSet<String>> answerMap = getAnswerHashMap(min, dapList, noDapList);
        String[] answer = new String[answerMap.size()]; // 문제에서 요구하는 형태로로 변화하기 위한 변수
        for (Entry<Integer, HashSet<String>> entry : answerMap.entrySet()) {
            if (entry.getValue().size() == 1) { // 답이 하나로 좁혀져 있으면 그냥 표기
                answer[entry.getKey()] = entry.getValue().iterator().next();
            } else { // 여러개면 답 부분을 ? 로 치화하여 표기
                String[] strArr = entry.getValue().iterator().next().split(" ");
                strArr[4] = "?";
                answer[entry.getKey()] = String.join(" ", strArr);
            }
        }
        return answer;
    }
}