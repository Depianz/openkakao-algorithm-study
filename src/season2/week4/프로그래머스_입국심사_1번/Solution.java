package season2.week4.프로그래머스_입국심사_1번;


// 이분 탐색
// 경계가 되는 지점을 찾아라
// 최소와 최대를 어떻게 잡을까?


class Solution {
    public long solution(int n, int[] times) {


        long max = Integer.MIN_VALUE;
        for (int time : times) {
            if(time > max) max = time;
        }

        long l = 0;
        long r = max * n;
        long answer = r;

        while(l<=r){
            long mid = (l+r)/2;
            long hap = 0;
            for (int i = 0; i < times.length; i++) {
                hap += mid/times[i];
                if(hap>=n) break;
            }

            if(hap >= n){
                r = mid -1;
                answer = Math.min(answer,mid);
            } else {
                l = mid +1;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        long solution = s.solution(10, new int[]{6, 8, 10});
        System.out.println("solution = " + solution);
    }
}