package week11.백준_탈출_4번;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] firstLine = br.readLine().split(" ");
        int r = Integer.parseInt(firstLine[0]);
        int c = Integer.parseInt(firstLine[1]);

        char[][] map = new char[r][c];

        for (int i = 0; i < r; i++) {
            String[] line = br.readLine().split("");
            for (int j = 0; j < c; j++) {
                map[i][j] = line[j].charAt(0);
            }
        }


        List<Point> moveWater = new ArrayList<>();
        List<Point> moveGo = new ArrayList<>();

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(map[i][j] == '*') {
                    moveWater.add(new Point(i,j));
                }
                else if(map[i][j] == 'S') moveGo.add(new Point(i,j));
            }
        }

        int[] dr = new int[]{-1,0,1,0};
        int[] dc = new int[]{0,-1,0,1};



        int time = r * c + 1;
        for (int i = 1; i < time; i++) {

            List<Point> moveNewWater = new ArrayList<>();
            List<Point> moveNewGo = new ArrayList<>();

            for (int j = 0; j < moveWater.size(); j++) {
                Point water = moveWater.get(j);
                int cr = water.r;
                int cc = water.c;

                for (int dir = 0; dir < 4; dir++) {
                    int nr = cr + dr[dir];
                    int nc = cc + dc[dir];
                    if(nr < 0 || nr > r-1 || nc < 0 || nc > c-1) continue;
                    if(map[nr][nc] == '.' || map[nr][nc] == 'S'){
                        map[nr][nc] = '*';
                        moveNewWater.add(new Point(nr,nc));
                    }
                }
            }

            for (int j = 0; j < moveGo.size(); j++) {
                Point gosm = moveGo.get(j);
                int cr = gosm.r;
                int cc = gosm.c;

                for (int dir = 0; dir < 4; dir++) {
                    int nr = cr + dr[dir];
                    int nc = cc + dc[dir];
                    if(nr < 0 || nr > r-1 || nc < 0 || nc > c-1) continue;
                    if(map[nr][nc] == '.'){
                        map[nr][nc] = 'S';
                        moveNewGo.add(new Point(nr,nc));
                    }
                    if(map[nr][nc] == 'D'){
                        bw.write(i + "\n");
                        br.close();
                        bw.close();
                        System.exit(0);
                    }
                }
            }

            moveWater = moveNewWater;
            if(moveNewGo.isEmpty()) break;
            else moveGo = moveNewGo;
        }

        bw.write("KAKTUS");
        br.close();
        bw.close();
    }

    static class Point{
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
