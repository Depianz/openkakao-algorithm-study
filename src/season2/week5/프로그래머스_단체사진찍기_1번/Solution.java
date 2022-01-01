package season2.week5.프로그래머스_단체사진찍기_1번;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {

    char[] arr = new char[]{'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    int answer = 0;


    private void foo(int idx, String[] data, boolean[] check, Map<Character, Integer> hm) {
        if (idx == 8) {
            if (checkCondition(hm, data)) {
                answer++;
            }
            return;
        }
        for (int i = 0; i < 8; i++) {
            if (check[i] == true) continue;
            check[i] = true;
            hm.put(arr[i], idx);
            foo(idx + 1, data, check, hm);
            check[i] = false;
        }
    }

    private boolean checkCondition(Map<Character, Integer> hm, String[] data) {

        for (String conditionLine : data) {
//            Pattern pattern = Pattern.compile("(.)~(.)(.)(.)");
//            Matcher m = pattern.matcher(conditionLine);
//            if(m.find()){
//                char side1 = m.group(1).charAt(0);
//                char side2 = m.group(2).charAt(0);
//                char condition = m.group(3).charAt(0);
//                int dist = Integer.parseInt(m.group(4));
//
//                int side1Idx = hm.get(side1);
//                int side2Idx = hm.get(side2);
//
//                int diff = Math.abs(side1Idx - side2Idx)-1;
//                if(condition == '=' && diff != dist) return false;
//                if(condition == '>' && (diff < dist || diff == dist)) return false;
//                if(condition == '<' && (diff > dist || diff == dist)) return false;
//
//            } else {
//                System.out.println("패턴이 안맞습니다.");
//            }
            char side1 = conditionLine.charAt(0);
            char side2 = conditionLine.charAt(2);
            char condition = conditionLine.charAt(3);
            int dist = Integer.parseInt(conditionLine.charAt(4) + "");

            int diff = Math.abs(hm.get(side1) - hm.get(side2)) - 1;
            if (condition == '=' && diff != dist) return false;
            if (condition == '>' && (diff < dist || diff == dist)) return false;
            if (condition == '<' && (diff > dist || diff == dist)) return false;
        }

        return true;
    }


    public int solution(int n, String[] data) {

        boolean[] check = new boolean[8];
        Map<Character, Integer> hm = new HashMap<>();
        foo(0, data, check, hm);

        return answer;
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        int solution = s.solution(2, new String[]{"N~F=0", "R~T>2"});
        System.out.println("solution = " + solution);
    }
}