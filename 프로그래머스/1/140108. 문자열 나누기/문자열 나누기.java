class Solution {
    public int solution(String s) {
        int answer = 0;

        char[] s_arr = s.toCharArray(); 

        int cnt=0;
        for(int i=0; i<s_arr.length; i= i+cnt){ 
            int preCnt=1;
            int nextCnt=0;
            cnt = 0;
            char pre = s_arr[i]; 
            for(int k=i+1; k<s_arr.length; k++){
               char next = s_arr[k];
               if(pre == next){
                   preCnt++;
               }else{
                   nextCnt++;
               } 
               if(preCnt == nextCnt){
                   cnt = preCnt + nextCnt;
                   answer++;
                   break;
               }
            }
            if(cnt == 0) return answer + 1;
        } 
        return answer;
    }
}