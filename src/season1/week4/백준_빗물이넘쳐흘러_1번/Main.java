package season1.week4.백준_빗물이넘쳐흘러_1번;

import java.io.*;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String firstLine = br.readLine();
        String[] firstlineSplited = firstLine.split(" ");
        int n = Integer.parseInt(firstlineSplited[0]);
        int k = Integer.parseInt(firstlineSplited[1]);

        String secondLine = br.readLine();
        String[] secondLineSplited = secondLine.split(" ");

        long ans = 0, cnt = 0;
        long[] arr = new long[101010];
        Stack<node> st = new Stack<>();

        for (int i = 1; i <= n + 1; i++) {
            if (i != n + 1) arr[i] = Integer.parseInt(secondLineSplited[i - 1]);
            int l = i, flag = 0, remain = 0;
            while (st.size() > 0 && st.peek().h >= arr[i]) {
                node before = st.pop();
                l = before.l;
                long val = before.h - arr[i];

                if (before.h == arr[i]) {
                    remain += before.v;
                } else {
                    if (flag == 0) {
                        cnt++;
                        flag = 1;
                        remain++;
                    }
                    cnt -= before.v;
                }
                if (cnt == k) {
                    System.out.println(ans);
                    return;
                }
                ans += val * (before.r - before.l + 1);
            }
            st.push(new node(l, i, arr[i], remain));
        }
        System.out.println("-1");

        br.close();
        bw.close();
    }

    static class node {
        int l, r, v;
        long h;

        public node(int l, int r, long h, int v) {
            this.l = l;
            this.r = r;
            this.h = h;
            this.v = v;
        }
    }

}
