package week4.프로그래머스_삼각달팽이_4번;

import java.util.ArrayList;

class Solution {

    void foo(int[][] map, int n, int st, int sr, int sc){
        if(n<=0 ) return;


        for (int i = 0; i < n; i++) {
            map[sr++][sc]=st++;
        }
        sr--;
        for (int i = 1; i <= n-1; i++) {
            map[sr][++sc]=st++;
        }

        for (int i =0; i<n-2; i++) {
            sr--;
            sc--;
            map[sr][sc] = st++;
        }

        foo(map,n-3,st,sr+1,sc);
    }


    public int[] solution(int n) {

        int len = n*(n+1)/2;
        int[] answer = new int[len];

        int[][] map = new int[n][n];

        foo(map,n,1, 0,0);

        ArrayList<Integer> arl = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                arl.add(map[i][j]);
            }
        }

        for (int i = 0; i < len; i++) {
            answer[i] = arl.get(i);
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] tmp = s.solution(4);
        for (int i = 0; i < tmp.length; i++) {
            System.out.print(tmp[i] + " ");
        }
    }
}