package season1.week1.프로그래머스_괄호회전하기;


import java.util.HashMap;
import java.util.Stack;

/**
 * 시간복잡도는 O(n^2) 일것 같다. (n = s의 길이)
 * 이유 : 0부터 s의 길이만큼 for문을 돌려야하고, 올바른 괄호문자인지 확인하는 로직 필요
 * 스택을 쓰는게 좋을것 같음
 * 리팩토링 방법이 있을까? 시간 줄이는법
 */
class Solution {
    public int solution(String s) {
        int answer = 0;
        HashMap<Character, Character> hm = new HashMap<>();
        hm.put('}', '{');
        hm.put(')', '(');
        hm.put(']', '[');

        int startIdx = 0;
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
//            Stack<Character> st = new Stack<>();
            st.clear();
            boolean flag = true;
            for (int j = 0; j < s.length(); j++) {
                Character currChar = s.charAt((startIdx + j) % s.length());

                if (hm.keySet().contains(currChar)) {
                    if (st.empty()) {
                        flag = false;
                        break;
                    } else {
                        if (hm.get(currChar).equals(st.peek())) {
                            st.pop();
                        } else {
                            flag = false;
                            break;
                        }
                    }
                } else {
                    st.add(currChar);
                }
            }
            if (flag && st.empty()) answer++;
            startIdx++;
        }

        return answer;
    }
}