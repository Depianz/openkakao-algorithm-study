package week10.프로그래머스_뉴스클러스터링_2번;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;

        Map<String, Integer> hm1 = new HashMap<>();
        Map<String, Integer> hm2 = new HashMap<>();


        for (int i = 0; i < str1.length()-1; i++) {
            String sub = str1.substring(i, i + 2).toLowerCase();
            if(!validateSub(sub)) continue;
            else hm1.put(sub,hm1.getOrDefault(sub,0)+1);
        }

        for (int i = 0; i < str2.length()-1; i++) {
            String sub = str2.substring(i, i + 2).toLowerCase();
            if(!validateSub(sub)) continue;
            else hm2.put(sub,hm2.getOrDefault(sub,0)+1);
        }

        int gyo = 0;
        int hap = 0;

        for (String s : hm1.keySet()) {
            if(hm2.get(s) != null){
                gyo += Math.min(hm1.get(s),hm2.get(s));
                hap += Math.max(hm1.get(s),hm2.get(s));
                hm2.put(s,0);
            } else {
                hap += hm1.get(s);
            }
        }

        for (String s : hm2.keySet()) {
            hap += hm2.get(s);
        }

        if(hap == 0 ) answer = 65536;
        else  answer = gyo * 65536 / hap;


        return answer;
    }

    boolean validateSub(String sub){
        for (int i = 0; i < sub.length(); i++) {
            char c = sub.charAt(i);
            if(c < 65 || (90 < c && c < 97 ) || c > 122) return false;
        }
       return true;
    }
}