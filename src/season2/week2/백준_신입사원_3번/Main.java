package season2.week2.백준_신입사원_3번;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testcase = Integer.parseInt(br.readLine());

        while (testcase-- != 0) {
            int n = Integer.parseInt(br.readLine());
            List<candidate> arl = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String input = br.readLine();
                String[] splitedInput = input.split(" ");
                candidate candidate = new candidate(Integer.parseInt(splitedInput[0]), Integer.parseInt(splitedInput[1]));
                arl.add(candidate);
            }
            Collections.sort(arl);

            // A 점수를 정렬한 후, B 를 한번의 루프로 해결
            int answer = 1;
            int minB = arl.get(0).b;

            for (int i = 1; i < n; i++) {
                if(arl.get(i).b < minB){
                    answer++;
                    minB = arl.get(i).b;
                }
            }

            bw.write(answer+"\n");
        }
        ;

        br.close();
        bw.close();
    }

    static class candidate implements Comparable<candidate> {

        int a;
        int b;

        public candidate(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(candidate o) {
            return this.a - o.a;
        }
    }
}
