package season1.week3.프로그래머스_메뉴리뉴얼_4번;

import java.util.*;

class Solution {

    // 시간복잡도는 10 x 20 x 2^10??
    // 완전탐색으로 order로 만들수 있는 모든 메뉴를 찾음
    // course의 숫자만큼 되면 해쉬맵에 키값으로 넣고, 해당 메뉴의 주문 수를 기록한다.
    // 동일한 주문수의 메뉴는 같이 넣어줘야하기 때문에 max값을 찾고 또 탐색하는식으로 구성

    public String[] solution(String[] orders, int[] course) {


        List<String> tmp = new ArrayList<>();

        for (int endCnt : course) {

            Map<String, Integer> hm = new HashMap<>();

            for (String order : orders) {
                String[] orderSplited = order.split("");
                // Stirng [] { a, b,c,d,f)
                Arrays.sort(orderSplited);
                bf(orderSplited, 0, "", endCnt, hm);
            }

            int maxMenuCnt = 2;
            for (String key : hm.keySet()) {
                if(hm.get(key) > maxMenuCnt){
                    maxMenuCnt = hm.get(key);
                }
            }
            for (String key : hm.keySet()) {
                if(hm.get(key) == maxMenuCnt){
                    tmp.add(key);
                }
            }
        }

        Collections.sort(tmp);
        String[] answer = new String[tmp.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = tmp.get(i);
        }

        return answer;
    }

    private void bf(String[] order, int idx, String menu, int endCnt, Map<String,Integer> hm) {
        if(menu.length() == endCnt){
            hm.put(menu,hm.getOrDefault(menu,0)+1);
            return;
        }
        if(idx == order.length) return;
        // 문자열 넣는 연산
        bf(order,idx+1, menu + order[idx], endCnt, hm);
        // 안넣는연산
        bf(order,idx+1, menu, endCnt, hm);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        String[] solution = s.solution(new String[]{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"}, new int[]{2, 3, 4});
        for (String s1 : solution) {
//            System.out.println("s1 = " + s1);
        }
    }
}