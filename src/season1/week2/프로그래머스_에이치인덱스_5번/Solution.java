package season1.week2.프로그래머스_에이치인덱스_5번;

import java.util.Arrays;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        int n = citations.length;
        int max = 0;

        Arrays.sort(citations);
        for (int i = 0; i < n; i++) {
            max = Math.max(max,citations[i]);
        }
        //lower bound 구하기
        for (int i = max; i >= 0; i--) {
            int lowerBoundIdx = getLowerBound(citations,i);
            if(n - lowerBoundIdx >= i) {
                answer = i;
                break;
            }
        }
        return answer;
    }

    private int getLowerBound(int[] citations, int target) {
        int start = 0;
        int end = citations.length;
        while(start< end){
            int mid = start + (end - start) / 2;
            if(target <= citations[mid]){
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return end;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int solution = s.solution(new int[]{3, 0, 6, 1, 5});
        System.out.println("solution = " + solution);
    }
}