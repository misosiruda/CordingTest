import java.util.Queue;
import java.util.LinkedList;
class Solution {
    public boolean solution(String s){
        Queue<Character> que = new LinkedList<>();

        if(s.length() % 2 != 0) return false;
        for(char item : s.toCharArray()){
            if(que.size() == 0 && item == ')'){
                return false;
            }
            if(item == '('){
                que.add(item);
            } else que.remove();  
        }
        if(que.size() != 0) return false;
        return true;
    }
}