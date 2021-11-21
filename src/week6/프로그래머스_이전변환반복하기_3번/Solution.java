package week6.프로그래머스_이전변환반복하기_3번;

class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];

        int cnt = 0;
        int zeroCnt = 0;

        while(!s.equals("1")){
            cnt ++;
            int tmp = s.length();
            s = s.replace("0", "");
            int after = s.length();
            zeroCnt += tmp - after;
            s = Integer.toBinaryString(s.length());
        }

        answer[0] = cnt;
        answer[1] = zeroCnt;

        return answer;
    }
}