package week3.프로그래머스_메뉴리뉴얼_4번;

import java.util.*;

class Solution {

    Map<String, Integer> hm = new HashMap<>();

    // 시간복잡도는 10 x 20 x 2^10??
    // 완전탐색으로 order로 만들수 있는 모든 메뉴를 찾음
    // course의 숫자만큼 되면 해쉬맵에 키값으로 넣고, 해당 메뉴의 주문 수를 기록한다.
    // 동일한 주문수의 메뉴는 같이 넣어줘야하기 때문에 max값을 찾고 또 탐색하는식으로 구성

    public String[] solution(String[] orders, int[] course) {


        List<String> tmp = new ArrayList<>();

        for (int endCnt : course) {
            for (String order : orders) {
                String[] orderSplited = order.split("");
                Arrays.sort(orderSplited);
                bf(orderSplited, 0, "", endCnt);
            }

            int maxMenuCnt = 2;
            for (String key : hm.keySet()) {
                if(key.length() == endCnt && hm.get(key) > maxMenuCnt){
                    maxMenuCnt = hm.get(key);
                }
            }
            for (String key : hm.keySet()) {
                if(hm.get(key) == maxMenuCnt && key.length() == endCnt){
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

    private void bf(String[] order, int idx, String menu, int endCnt) {
        if(menu.length() == endCnt){
            hm.put(menu,hm.getOrDefault(menu,0)+1);
            return;
        }
        if(idx == order.length) return;

        bf(order,idx+1, menu + order[idx], endCnt);
        bf(order,idx+1, menu, endCnt);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        String[] solution = s.solution(new String[]{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"}, new int[]{2, 3, 4});
        for (String s1 : solution) {
//            System.out.println("s1 = " + s1);
        }
    }
}