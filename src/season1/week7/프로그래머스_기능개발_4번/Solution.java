package season1.week7.프로그래머스_기능개발_4번;

import java.util.ArrayList;
import java.util.Stack;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> arl = new ArrayList<>();

        int idx = 0;
        Stack<Integer> st = new Stack<>();

        while(idx != progresses.length){
            int cnt = 0;
            for (int i = idx; i < progresses.length; i++) {
                if(progresses[i] < 100) {
                    progresses[i] += speeds[i];
                    if(progresses[i] >= 100) {
                        st.add(i);
                    }
                }
            }
            if(progresses[idx] >= 100){
                while(!st.empty()){
                    st.pop();
                    idx++;
                    cnt++;
                    if(idx != progresses.length && progresses[idx] < 100) break;
                }
                arl.add(cnt);
            }
        }

        int[] answer = new int[arl.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = arl.get(i);
        }


        return answer;
    }

    public static void main(String[] args) {
        int[] tmp2 = new Solution().solution(new int[]{95, 90, 99, 99, 80, 99}, new int[]{1, 1, 1, 1, 1, 1});
        for (int i = 0; i < tmp2.length; i++) {
            System.out.println("tmp[i] = " + tmp2[i]);
        }
    }
}