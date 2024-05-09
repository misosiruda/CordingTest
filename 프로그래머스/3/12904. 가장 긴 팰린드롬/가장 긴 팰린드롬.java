class Solution {
static int answer =1;
    static char[]arr;
    public int solution(String s){
        char[]arr = s.toCharArray();
        int len = arr.length;
        for (int i = len-1; i>=0 ; i--) {
            for (int start = 0; start < len-i; start++) {
                int end = start+i;
                if(arr[start]==arr[end]){
                    boolean flag = true;
                    for (int j = start+1; j <=(start+end)/2 ; j++) {
                        int k = start+end-j;
                        if(arr[j]==arr[k]){
                            flag = true;
                        }else{
                            flag = false;
                            break;
                        }
                    }
                    if(flag){
                        return end-start+1;
                    }
                }
            }
        }
        return answer;
    }
}