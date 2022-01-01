package season2.week5.프로그래머스_기능개발_2번;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> tmp = new ArrayList<>();

        Stack<Integer> st= new Stack<>();
        Stack<Integer> speedsSt = new Stack<>();
        for (int i = progresses.length-1; i >= 0 ; i--) {
            st.add(progresses[i]);
            speedsSt.add(speeds[i]);
        }

        for (int i = 1; i <= 100; i++) {
            int count = 0;
            while(!st.isEmpty() && st.peek() + speedsSt.peek() * i >= 100){
                st.pop();
                speedsSt.pop();
                count++;
            }
            if(count != 0 ) tmp.add(count);
        }

        int[] answer = new int[tmp.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = tmp.get(i);
        }

        return answer;
    }
}