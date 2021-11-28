package week7.프로그래머스_입국심사_2번;

import java.util.Arrays;

class Solution {
    public long solution(int n, int[] times) {

        Arrays.sort(times);
        long l = 0;
        long r = (long)times[times.length-1] * (long) n;
        long answer = r;

        while(l <= r){
            long mid = (l + r) / 2;
            long hap = 0;
            for (int i = 0; i < times.length; i++) {
                hap += mid / times[i];
                if(hap >= n ) break;
            }

            if(hap >= n ){
                r = mid -1;
                answer = Math.min(answer, mid);
            } else {
                l = mid + 1;
            }
        }

        return answer;
    }
}