package week3.프로그래머스_빛의경로사이클_2번;

import java.util.ArrayList;
import java.util.List;

class Solution {

    // start는 0,0 에서 위 오른쪽 아래 왼쪽으로 가는걸로 하면 될것 같다.
    // 그렇지만 지나온 경로를 어떻게 기록하며, 사이클이 형성될 경우 다른 사이클과 같다고 표시할 수 있을까??

    int[] dr = new int[]{1, 0, -1, 0};
    int[] dc = new int[]{0, 1, 0, -1};
    boolean[][][] visited;
    char[][] map;

    public int[] solution(String[] grid) {
        List<Integer> tmp = new ArrayList<>();
        int r = grid.length;
        int c = grid[0].length();
        map = new char[r][c];
        visited = new boolean[r][c][4];

        // map index 1부터 r , 1부터 c 까지
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                map[i][j] = grid[r - 1 - i].charAt(j);
            }
        }


        for (int i = 0; i <r; i++) {
            for (int j = 0; j < c; j++) {
                for (int dir = 0; dir < 4; dir++) {
                    if(visited[i][j][dir]) continue;
                    tmp.add(startCycle(i, j, dir));
                }
            }
        }


        return tmp.stream().sorted().mapToInt(i -> i).toArray();
    }

    private int startCycle(int startR, int startC, int dir) {
        int curR = startR;
        int curC = startC;
        int curD = dir;
        int r = map.length;
        int c = map[0].length;

        int cnt = 0;

        while(true){
            if(visited[curR][curC][curD]) break;
            visited[curR][curC][curD] = true;
            cnt++;
            int nr = (curR + dr[curD] + r) % r;
            int nc = (curC + dc[curD] + c) % c;
            int ndir = map[nr][nc] == 'L' ? (curD + 3) % 4 : map[nr][nc] == 'R' ? (curD + 1) % 4 : curD;

            curR = nr;
            curC = nc;
            curD = ndir;
        }
        return cnt;
    }
}