class Solution {

    private void swapArr(int[] array) {
        int temp = array[0];
        array[0] = array[1];
        array[1] = temp;
    }

    private void foldArr(int[] array) {
        if (array[0] >= array[1]) {
            array[0] = array[0] / 2;
        } else {
            array[1] = array[1] / 2;
        }
    }

    private boolean canStorage(int[] wallet, int[] bill) {
        if (wallet[0] >= bill[0] && wallet[1] >= bill[1]) {
            return true;
        } else {
            swapArr(wallet);
            return wallet[0] >= bill[0] && wallet[1] >= bill[1];
        }
    }

    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        while (bill[0] > 0) {
            if (canStorage(wallet, bill)) {
                break;
            }
            foldArr(bill);
            answer++;
        }
        return answer;
    }
}