package week11.백준_보물섬_2번;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int answer = 0;
    static int r;
    static int c;
    static int[] dr = new int[]{-1,0,1,0};
    static int[] dc = new int[]{0,1,0,-1};
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] firstLine = br.readLine()
                .split(" ");
        r = Integer.parseInt(firstLine[0]);
        c = Integer.parseInt(firstLine[1]);

        map = new char[r][c];

        for (int i = 0; i < r; i++) {
            String[] line = br.readLine().split("");
            for (int j = 0; j < c; j++) {
                map[i][j] = line[j].charAt(0);
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(map[i][j] == 'L') bfs(i,j);
            }
        }

        bw.write(answer + "\n");
        br.close();
        bw.close();
    }

    private static void bfs(int i, int j) {
        boolean[][] check = new boolean[r][c];

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(i,j,0));
        check[i][j] = true;

        while(!q.isEmpty()){
            Point curPoint = q.poll();
            int cr = curPoint.r;
            int cc = curPoint.c;
            int cd = curPoint.d;
            if(answer < cd ) answer = cd;
            for (int dir = 0; dir < 4; dir++) {
                int nr = cr + dr[dir];
                int nc = cc + dc[dir];
                int nd = cd + 1;
                if(nr < 0 || nr > r-1 || nc < 0 || nc > c-1) continue;
                if(check[nr][nc]) continue;
                if(map[nr][nc] == 'W') continue;

                check[nr][nc] = true;
                q.add(new Point(nr,nc, nd));
            }

        }

    }

    static class Point{
        int r;
        int c;
        int d;

        public Point(int r, int c,int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
}
