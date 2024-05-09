import java.util.*;

class Solution {
    public int solution(String s) {
    int answer = 0;
    char[] ch = s.toCharArray();
    Queue<Character> queue = new LinkedList<>();
    for (char x: ch){
        queue.offer(x);
    }
    int count = 0;

    first:for(int i=0; i<s.length(); i++){
        Stack<Character> stack = new Stack<>();

        for (char x: queue){
            if (x=='(' || x=='{' || x=='['){
                stack.push(x);
            }else{
                if (stack.isEmpty()){
                  count++;
                    char leftTmp = queue.remove();
                    queue.offer(leftTmp);
                  continue first;
                }else if (stack.peek()=='(' && x==')'){
                    stack.pop();
                }else if (stack.peek()=='[' && x==']'){
                    stack.pop();
                }else if (stack.peek()=='{' && x=='}'){
                    stack.pop();
                }else{
                    count++;
                    char leftTmp = queue.remove();
                    queue.offer(leftTmp);
                    continue first;
                }
            }
        }
        if (stack.size()!=0){
            count++;
        }

        char leftTmp = queue.remove();
        queue.offer(leftTmp);
    }
    answer = s.length() - count;

    return answer;
}
}