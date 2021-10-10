package week1.프로그래머스_게임맵최단거리;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 문제풀이
 * 맵 탐색의 경우, dfs ,bfs가 있으나 최단거리의 경우 무조건 bfs를 쓰는게 좋음
 * dfs의 경우, 모든 경로를 다 탐색하게 되므로 시간이 오래걸림
 * bfs의 경우, 가장 먼저 도착한게 최단거리가 되므로, 도착한 후 break 등으로 나오면 됨
 * 내부 Node 클래스를 통해 하는것은 메모리에 손해가 아닐까?
 */
class Solution {

    class Node {
        int r;
        int c;
        int d;
        Node(int a, int b, int c) {
            this.r = a;
            this.c = b;
            this.d = c;
        }
    }

    int[] dr = new int[]{-1, 0, 1, 0};
    int[] dc = new int[]{0, 1, 0, -1};

    public int solution(int[][] maps) {
        int answer = 0;
        int n = maps.length;
        int m = maps[0].length;
        boolean[][] check = new boolean[n][m];

        Queue<Node> q = new LinkedList<>();
        Node start = new Node(0, 0, 1);
        check[0][0] = true;
        q.add(start);

        while (!q.isEmpty()) {
            Node cur = q.poll();
            int cr = cur.r;
            int cc = cur.c;
            int cd = cur.d;
            if (cr == n - 1 && cc == m - 1) {
                return cd;
            }
            for (int i = 0; i < 4; i++) {
                int nr = cr + dr[i];
                int nc = cc + dc[i];
                if (nr < 0 || nr > n - 1 || nc < 0 || nc > m - 1) continue;
                if (check[nr][nc] == false && maps[nr][nc] == 1) {
                    check[nr][nc] = true;
                    q.add(new Node(nr, nc, cd + 1));
                }
            }
        }
        return -1;
    }
}
