package season2.week2.프로그래머스_N2배열자르기_2번;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] solution(int n, long left, long right) {
       List<Integer> arl = new ArrayList<>();

        for (long i = left; i <= right; i++) {
            int leftMok = (int) (i / n);
            int leftRem =  (int) (i % n);
            arl.add(Math.max(leftMok, leftRem) + 1);
        }

        return arl.stream().mapToInt(v -> v).toArray();
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] solution = s.solution(3, 2, 5);
        for (int i : solution) {
            System.out.println("i = " + i);
        }
    }
}