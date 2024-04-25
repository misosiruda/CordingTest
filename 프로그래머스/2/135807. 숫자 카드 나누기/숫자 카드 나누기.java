class Solution {

    int gcd(int left, int right) {
        int a = left > right ? left : right;
        int b = left < right ? left : right;
        int n = -1;
        while (n != 0) {
            n = a % b;
            if (n == 0) {
                return b;
            }
            a = b;
            b = n;
        }
        return n;
    }

    boolean check(int[] arr, int n) {
        boolean isDivied = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % n == 0) {
                return true;
            }
        }
        return false;
    }

    public int solution(int[] arrayA, int[] arrayB) {
        int a = arrayA[0];
        for (int i = 1; i < arrayA.length; i++) {
            a = gcd(a, arrayA[i]);
        }
        boolean aOK = check(arrayB, a);
        int b = arrayB[0];
        for (int i = 1; i < arrayB.length; i++) {
            b = gcd(b, arrayB[i]);
        }
        boolean bOK = check(arrayA, b);
        if (aOK && bOK) {
            return 0;
        } else if (!aOK && !bOK) {
            return Math.max(a, b);
        } else if (!aOK) {
            return a;
        } else {
            return b;
        }
    }
}