package week3.프로그래머스_빛의경로사이클_2번;

class Solution {

    // start는 0,0 에서 위 오른쪽 아래 왼쪽으로 가는걸로 하면 될것 같다.
    // 그렇지만 지나온 경로를 어떻게 기록하며, 사이클이 형성될 경우 다른 사이클과 같다고 표시할 수 있을까??

    public int[] solution(String[] grid) {
        int[] answer = {};
        int r = grid.length;
        int c = grid[0].length();
        char[][] map = new char[r][c];


        // map index 1부터 r , 1부터 c 까지
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                map[i][j] = grid[r-1-i].charAt(j);
            }
        }

        int[] dr = new int[]{-1,0,1,0};
        int[] dc = new int[]{0,1,0,-1};

        for (int dir = 0; dir < 4; dir++) {
            startCycle(0,0,dir,0,dr[dir],dc[dir]);
        }

        return answer;
    }

    private void startCycle(int i, int i1, int dir, int i2, int i3, int i4) {

    }
}