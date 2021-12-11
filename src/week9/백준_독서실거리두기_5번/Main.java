package week9.백준_독서실거리두기_5번;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n, t, p;
    static Time[] time;
    static int first;
    static int last;
    static boolean[][] isSeated;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 좌석의 총 개수
        t = Integer.parseInt(st.nextToken()); // 예약 개수
        p = Integer.parseInt(st.nextToken()); // 민규가 좋아하는 좌석 번호
        time = new Time[t];
        first = hour2Min("9");
        last = hour2Min("21");

        isSeated = new boolean[last + 100][n + 1]; // ex) isSeated[600][3] = true; 600분에 2번좌석이 차있다.

        int start, end;
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            String from = st.nextToken();
            start = 0;
            start += hour2Min(from.substring(0,2));
            start += Integer.parseInt(from.substring(2,4));

            String to = st.nextToken();
            end = 0;
            end += hour2Min(to.substring(0, 2));
            end += Integer.parseInt(to.substring(2, 4));

            time[i] = new Time(start, end);
        }

        Arrays.sort(time);

        for (int i = 0; i < t; i++) {
            Time cur = time[i];

            int s = cur.start;
            int e = cur.end;
            int seat = findSeat(s);

            for (int j = s; j < e; j++) {
                isSeated[j][seat] = true;
            }
        }

        int answer = 0;
        for (int i = first; i < last; i++) {
            if (!isSeated[i][p]) {
                answer++;
            }
        }

        bw.write(answer+"\n");

        br.close();
        bw.close();
    }

    private static int findSeat(int min) {
        int maxDist = 0;
        int pos = 0;

        for (int i = 1; i <= n; i++) {
            if (!isSeated[min][i]) {
                int dist = getDistance(min, i);
                if (maxDist < dist) { // 가장 멀리 앉을 수 있는 곳을 반환 할 거야
                    maxDist = dist;
                    pos = i;
                }
            }
        }
        return pos;
    }

    static class Time implements Comparable<Time> {
        int start, end;
        public Time(int start, int end) {
            this.start = start;
            this.end = end;
        }
        public int compareTo(Time o) {
            if (start != o.start) {
                return start - o.start;
            }
            return end - o.end;
        }
    }

    static int hour2Min(String s) {
        int num = Integer.parseInt(s);
        return num * 60;
    }

    static int getDistance(int min, int num) {
        int minDist = 110;
        for (int i = 1; i <= n; i++) {
            if (i == num) continue;
            if (isSeated[min][i]) {
                int dist = Math.abs(i - num);
                if (minDist > dist) {
                    minDist = dist;
                }
            }
        }
        return minDist;
    }
}
