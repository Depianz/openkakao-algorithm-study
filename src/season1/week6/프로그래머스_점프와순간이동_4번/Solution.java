package season1.week6.프로그래머스_점프와순간이동_4번;

public class Solution {
    public int solution(int n) {
        int ans = 0;

        String s = Integer.toBinaryString(n);
        for (int i = 0; i<s.length();i++){
            if(s.charAt(i)=='1') ans++;
        }

        return ans;
    }
}