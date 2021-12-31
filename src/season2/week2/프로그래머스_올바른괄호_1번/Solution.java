package season2.week2.프로그래머스_올바른괄호_1번;

import java.util.Stack;

class Solution {
    boolean solution(String s) {
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '(') st.push(c);
            else {
                if(st.empty()) return false;
                st.pop();
            }
        }
        return st.empty();
    }
}