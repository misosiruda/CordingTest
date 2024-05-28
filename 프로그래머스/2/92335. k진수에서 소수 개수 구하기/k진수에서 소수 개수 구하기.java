import java.math.BigInteger;
import java.util.*;

class Solution {

    public int isPrime(BigInteger n) {
        int len = n.sqrt().intValue();
        for (int i = 2; i <= len; i++) {
            if (n.remainder(BigInteger.valueOf(i)) == BigInteger.ZERO) {
                return 0;
            }
        }
        return 1;
    }

    public int solution(int n, int k) {
        int answer = 0;
        String number = Integer.toString(n, k);
        String[] temp = number.split("0");
        List<BigInteger> numList = new ArrayList<>();
        for (String st : temp) {
            if (!(st.equals("") || st.equals("1"))) {
                numList.add(new BigInteger(st));
            }
        }
        for (BigInteger bi : numList) {
            answer += isPrime(bi);
        }
        return answer;
    }
}