package week5.프로그래머스_피로도_4번;

class Solution {

    int curK;
    int[][] dun;
    int n;
    int answer = 0;


    private void foo(int[] tmp, boolean[] check, int idx) {
        if (idx == n) {
            answer = Math.max(answer, checkAns(tmp));
            return;
        }

        for (int i = 0; i < n; i++) {
            if (check[i] == false) {
                check[i] = true;
                tmp[idx] = i;
                foo(tmp, check, idx + 1);
                check[i] = false;
            }
        }
    }

    private int checkAns(int[] tmp) {
        int k = curK;
        int cnt = 0;
        for (int dungeonsIdx : tmp) {
            if (k >= dun[dungeonsIdx][0]) {
                k -= dun[dungeonsIdx][1];
                cnt++;
            }
        }
        return cnt;
    }


    public int solution(int k, int[][] dungeons) {
        n = dungeons.length;
        curK = k;
        dun = dungeons;

        int[] tmp = new int[n];
        boolean[] check = new boolean[n];

        foo(tmp, check, 0);
        return answer;
    }
}