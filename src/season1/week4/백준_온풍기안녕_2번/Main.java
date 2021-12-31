package season1.week4.백준_온풍기안녕_2번;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    static int r;
    static int c;
    static int k;
    static int[][] map;
    static int[][] walls;
    static List<On> ons;
    static List<Room> rooms;

    static int[] dr = new int[]{0, 0, -1, 1};
    static int[] dc = new int[]{1, -1, 0, 0};

    static int[] dbaR = new int[]{-1, 0, 1};
    static int[] dbaC = new int[]{-1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String firstLine = br.readLine();
        String[] splitedFirst = firstLine.split(" ");
        r = Integer.parseInt(splitedFirst[0]);
        c = Integer.parseInt(splitedFirst[1]);
        k = Integer.parseInt(splitedFirst[2]);

        map = new int[r + 1][c + 1];
        walls = new int[r + 1][c + 1];
        ons = new ArrayList<>();
        rooms = new ArrayList<>();

        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                walls[i][j] = -1;
            }
        }

        for (int i = 1; i <= r; i++) {
            String tmp = br.readLine();
            String[] splitedTmp = tmp.split(" ");
            for (int j = 1; j <= c; j++) {
                int t = Integer.parseInt(splitedTmp[j - 1]);
                if (t == 5) rooms.add(new Room(i, j));
                else if (t != 0) ons.add(new On(i, j, t));
                else map[i][j] = t;
            }
        }

        int wallCnt = Integer.parseInt(br.readLine());
        for (int i = 0; i < wallCnt; i++) {
            String tmp = br.readLine();
            String[] splitedTmp = tmp.split(" ");
            walls[Integer.parseInt(splitedTmp[0])][Integer.parseInt(splitedTmp[1])] = Integer.parseInt(splitedTmp[2]);
        }

        for (int tryNum = 1; tryNum <= 100; tryNum++) {
            tryOns();
            balanceMap();
            clearSide();

            if (checkRooms()) {
                bw.write(tryNum + "");
                br.close();
                bw.close();
                return;
            }
        }
        bw.write(101 + "");
        br.close();
        bw.close();
    }

    private static void clearSide() {
        for (int i = 1; i <= c; i++) {
            if (map[1][i] >= 1) map[1][i] -= 1;
            if (map[r][i] >= 1) map[r][i] -= 1;
        }

        for (int i = 2; i <= r - 1; i++) {
            if (map[i][1] >= 1) map[i][1] -= 1;
            if (map[i][c] >= 1) map[i][c] -= 1;
        }

    }

    private static void balanceMap() {
        int[][] newMap = new int[r + 1][c + 1];

        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                if (map[i][j] <= 3) {
                    newMap[i][j] += map[i][j];
                    continue;
                }
                int difSum = 0;
                for (int dir = 0; dir < 4; dir++) {
                    int nr = i + dr[dir];
                    int nc = j + dc[dir];
                    if (nr <= 0 || nr > r || nc <= 0 || nc > c) continue;
                    if (dir == 0 && walls[nr][j] == 1) continue;
                    else if (dir == 1 && walls[nr][nc] == 1) continue;
                    else if (dir == 2 && walls[i][nc] == 0) continue;
                    else if (dir == 3 && walls[nr][nc] == 0) continue;

                    int dif = (map[i][j] - map[nr][nc]) / 4;
                    if(dif < 0) continue;
                    newMap[nr][nc] += dif;
                    difSum += dif;
                }
                newMap[i][j] += (map[i][j] - difSum);
            }
        }
        map = newMap;
    }

    private static void tryOns() {
        for (On on : ons) {
            boolean[][] check = new boolean[r + 1][c + 1];
            int onR = on.r;
            int onC = on.c;
            int onDir = on.dir;

            Queue<Node> q = new LinkedList<>();
            q.add(new Node(onR + dr[onDir - 1], onC + dc[onDir - 1], 5));
            check[onR + dr[onDir - 1]][onC + dc[onDir - 1]] = true;

            while (!q.isEmpty()) {
                Node cur = q.poll();
                int curR = cur.r;
                int curC = cur.c;
                int curVal = cur.val;
                if (curVal == 0) continue;
                map[curR][curC] += curVal;
                for (int i = 0; i < 3; i++) {
                    int nr, nc;
                    if (onDir == 1 || onDir == 2) {
                        nr = curR + dbaR[i];
                        nc = curC + dc[onDir - 1];
                    } else {
                        nr = curR + dr[onDir - 1];
                        nc = curC + dbaC[i];
                    }
                    if (nr <= 0 || nr >= r + 1 || nc <= 0 || nc >= c + 1) continue;
                    if (check[nr][nc]) continue;
                    if (onDir == 1 && walls[nr][curC] == 1) continue;
                    else if (onDir == 2 && walls[nr][nc] == 1) continue;
                    else if (onDir == 3 && walls[curR][nc] == 0) continue;
                    else if (onDir == 4 && walls[nr][nc] == 0) continue;
                    check[nr][nc] = true;
                    q.add(new Node(nr, nc, curVal - 1));
                }
            }
        }
    }

    private static boolean checkRooms() {
        for (Room room : rooms) {
            if (map[room.r][room.c] < k) return false;
        }
        return true;
    }

    static class On {
        int r;
        int c;
        int dir;

        public On(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }

    static class Room {
        int r;
        int c;

        public Room(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Node {
        int r;
        int c;
        int val;

        public Node(int r, int c, int val) {
            this.r = r;
            this.c = c;
            this.val = val;
        }
    }

    private static void printMap() {
        System.out.println();
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void printWalls() {
        System.out.println("walls");
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                System.out.print(walls[i][j] + " ");
            }
            System.out.println();
        }
    }

}
