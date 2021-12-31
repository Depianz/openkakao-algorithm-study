package season1.week6.프로그래머스_다트게임_1번;

import java.util.Deque;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
    public int solution(String dartResult) {
        int answer = 0;

        Pattern p = Pattern.compile("([0-9]*)([SDT]+)([*#]?)");
        Matcher m = p.matcher(dartResult);
        Deque<Integer> dq = new LinkedList<>();

        while (m.find()) {
            int pt = Integer.parseInt(m.group(1));
            String bonus = m.group(2);
            String op = "";
            if (m.groupCount() == 3) op = m.group(3);
            if (bonus.equals("D")) pt = pt * pt;
            else if (bonus.equals("T")) pt = pt * pt * pt;
            if (op.equals("#")) pt *= -1;
            else if (op.equals("*")) {
                if (!dq.isEmpty()) {
                    int last = dq.pollLast();
                    last *= 2;
                    dq.add(last);
                }
                pt *= 2;
            }
            dq.add(pt);
        }
        while (!dq.isEmpty()) {
            answer += dq.poll();
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int solution = s.solution("1S2D*3T");
        System.out.println("solution = " + solution);
    }
}