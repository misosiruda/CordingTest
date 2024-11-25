import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

class Solution {

    private int fixedBase = 0;

    private boolean isBigger(char c, int n) {
        return (c - '0') >= n;
    }

    private String removeNonNumeric(String str) {
        StringBuilder result = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                result.append(c);
            }
        }
        return result.toString();
    }

    private int getMinNum(String[] expressions) {
        int min = 0;
        for (String expString : expressions) {
            char[] charArr = removeNonNumeric(expString).toCharArray();
            for (char c : charArr) {
                if (isBigger(c, min)) {
                    min = c - '0' + 1;
                }
            }
        }
        return min;
    }

    private String baseOperation(String a, String b, String op, int base, boolean dap) {
        // 자릿수 맞추기
        int maxLength = Math.max(a.length(), b.length());
        a = padLeft(a, maxLength, '0');
        b = padLeft(b, maxLength, '0');

        // 연산 수행
        if (op.equals("+")) {
            return addBaseNumbers(a, b, base, dap);
        } else if (op.equals("-")) {
            return subtractBaseNumbers(a, b, base, dap);
        } else {
            return "";
        }
    }

    private String addBaseNumbers(String a, String b, int base, boolean dap) {
        StringBuilder result = new StringBuilder();
        int carry = 0; // 자리올림 값

        for (int i = a.length() - 1; i >= 0; i--) {
            int digitA = charToValue(a.charAt(i));
            int digitB = charToValue(b.charAt(i));
            int sum = digitA + digitB + carry;
            carry = sum / base;
            result.append(sum % base);
            if (dap && carry > 0) {
                fixedBase = base;
            }
        }
        if (carry > 0) {
            result.append(carry);
        }

        return result.reverse().toString();
    }

    private String subtractBaseNumbers(String a, String b, int base, boolean dap) {
        StringBuilder result = new StringBuilder();
        boolean borrow = false;

        for (int i = a.length() - 1; i >= 0; i--) {
            int digitA = charToValue(a.charAt(i));
            int digitB = charToValue(b.charAt(i));
            if (borrow) {
                digitA -= 1;
                if (dap) {
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

    // 특정 문자를 해당 값으로 변환
    private int charToValue(char c) {
        if ('0' <= c && c <= '9') {
            return c - '0';
        }
        throw new IllegalArgumentException("Invalid character: " + c);
    }

    // 문자열 왼쪽에 특정 문자로 패딩
    private String padLeft(String str, int length, char padChar) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length - str.length(); i++) {
            sb.append(padChar);
        }
        sb.append(str);
        return sb.toString();
    }

    private void setSeparateList(String[] expressions, ArrayList<String> dapList, ArrayList<String> noDapList) {
        for (String string : expressions) {
            if (string.indexOf("X") >= 0) {
                noDapList.add(string);
            } else {
                dapList.add(string);
            }
        }
    }

    private boolean calcCompare(ArrayList<String> list, int base, boolean isDap, HashMap<Integer, HashSet<String>> answerMap) {
        if (isDap) {
            for (String string : list) {
                String[] strArr = string.split(" ");
                fixedBase = 0;
                if (!strArr[4].equals(baseOperation(strArr[0], strArr[2], strArr[1], base, isDap))) {
                    answerMap.clear();
                    fixedBase = 0;
                    return false;
                }
                if (fixedBase != 0) {
                    return true;
                }
            }
            return true;
        } else {
            for (int i = 0; i < list.size(); i++) {
                String string = list.get(i);
                String[] strArr = string.split(" ");
                strArr[4] = baseOperation(strArr[0], strArr[2], strArr[1], base, isDap);
                answerMap.put(i, answerMap.getOrDefault(i, new HashSet<>()));
                answerMap.get(i).add(String.join(" ", strArr));
            }
            return true;
        }
    }

    private HashMap<Integer, HashSet<String>> getAnswerHashMap(int min, ArrayList<String> dapList, ArrayList<String> noDapList) {
        HashMap<Integer, HashSet<String>> answerMap = new HashMap<>();
        for (int i = min; i <= 9; i++) {
            if (calcCompare(dapList, i, true, answerMap)) {
                if (fixedBase != 0) {
                    break;
                } else {
                    calcCompare(noDapList, i, false, answerMap);
                }
            }
        }

        if (fixedBase != 0) {
            answerMap.clear();
            calcCompare(noDapList, fixedBase, false, answerMap);
        }

        return answerMap;
    }

    public String[] solution(String[] expressions) {
        int min = getMinNum(expressions);
        ArrayList<String> dapList = new ArrayList<>();
        ArrayList<String> noDapList = new ArrayList<>();
        setSeparateList(expressions, dapList, noDapList);
        HashMap<Integer, HashSet<String>> answerMap = getAnswerHashMap(min, dapList, noDapList);
        String[] answer = new String[answerMap.size()];
        for (Entry<Integer, HashSet<String>> entry : answerMap.entrySet()) {
            if (entry.getValue().size() == 1) {
                answer[entry.getKey()] = entry.getValue().iterator().next();
            } else {
                String[] strArr = entry.getValue().iterator().next().split(" ");
                strArr[4] = "?";
                answer[entry.getKey()] = String.join(" ", strArr);
            }
        }
        return answer;
    }
}