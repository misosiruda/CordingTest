class Solution {

    public int solution(int[] money) {
        int answer = 0;
        int[] mf = new int[money.length];
        int[] ml = new int[money.length];
        for (int i = 1; i < money.length; i++) {
            mf[i] = money[i - 1];
            ml[i] = money[i];
        }
        int[] dpf = new int[mf.length];
        int[] dpl = new int[ml.length];
        dpf[1] = mf[1];
        dpl[1] = ml[1];
        for (int i = 2; i < dpl.length; i++) {
            dpf[i] = Math.max(dpf[i - 1], (dpf[i - 2] + mf[i]));
            dpl[i] = Math.max(dpl[i - 1], (dpl[i - 2] + ml[i]));
        }
        return Math.max(dpf[dpf.length - 1], dpl[dpl.length - 1]);
    }
}