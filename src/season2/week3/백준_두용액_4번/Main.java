package season2.week3.백준_두용액_4번;

import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        String[] inputs = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(inputs[i]);
        }

        Arrays.sort(arr);

        int start = 0;
        int end = n-1;

        int hap = Integer.MAX_VALUE;
        int[] answer = new int[2];

        while(start < end){
            int cur = arr[start] + arr[end];
            if(cur == 0) {
                answer[0] = start;
                answer[1] = end;
                break;
            } else {
                if(Math.abs(cur) < Math.abs(hap)){
                    answer[0] = start;
                    answer[1] = end;
                    hap = cur;
                }
                if(cur < 0 ) start++;
                else end--;
            }
        }

        bw.write(arr[answer[0]] + " " + arr[answer[1]] + "\n");

        br.close();
        bw.close();
    }
}
