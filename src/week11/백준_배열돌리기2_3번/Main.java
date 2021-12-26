package week11.백준_배열돌리기2_3번;

import java.io.*;

public class Main {

    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] firstLine = br.readLine()
                .split(" ");
        n = Integer.parseInt(firstLine[0]);
        m = Integer.parseInt(firstLine[1]);
        int r = Integer.parseInt(firstLine[2]);

        int[][] map = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[] line = br.readLine()
                    .split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }

        // 원래대로 돌아오는걸 모듈러 연산을 통해 실제 돌아야하는 수만 구함
        // 아래는 잘못 구한 로직.. 내부로 들어갈 수록 realR도 줄어들어야한다.
//        int realR = r % (2 * (n + m) - 4);
//
//        for (int i = 0; i < realR; i++) {
//            map = rotate(map);
//        }

        int cnt = Math.min(n, m) / 2;
        int N = n;
        int M = m;

        for (int start = 0; start < cnt; start++) {
            int realR = r % (2 * N + 2 * M - 4);
            for (int i = 0; i < realR; i++) {
                rotate(map, start);
            }
            N -= 2;
            M -= 2;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                bw.write(map[i][j] + " ");
            }
            bw.write("\n");
        }

        br.close();
        bw.close();
    }

    private static void rotate(int[][] map, int start) {
        int first = map[start][start];
        for (int y = start; y < m - start - 1; y++) {
            map[start][y] = map[start][y + 1];
        }
        for (int x = start; x < n - start - 1; x++) {
            map[x][m - 1 - start] = map[x + 1][m - 1 - start];
        }
        for (int y = m - 1 - start; y > start; y--) {
            map[n - 1 - start][y] = map[n - 1 - start][y - 1];
        }
        for (int x = n - 1 - start; x > start + 1; x--) {
            map[x][start] = map[x - 1][start];
        }
        map[start + 1][start] = first;
    }
}
