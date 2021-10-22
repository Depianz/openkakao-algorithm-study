package week2.프로그래머스_위장_2번;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;

        Map<String, List<String>> hm = new HashMap<>();

        for (int i = 0; i < clothes.length; i++) {
            String clothe = clothes[i][0];
            String kind = clothes[i][1];
            if (!hm.containsKey(kind)) hm.put(kind, new ArrayList<>());
            hm.get(kind).add(clothe);
        }

        for (String s : hm.keySet()) {
            answer *= (hm.get(s).size()+1);
        }

        answer--;
        return answer;
    }
}