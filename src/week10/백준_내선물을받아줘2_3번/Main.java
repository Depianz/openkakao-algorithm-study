package week10.백준_내선물을받아줘2_3번;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        String map = br.readLine();

        int ewCount = 0;

        for (int i = 0; i < map.length()-1; i++) {
            String substring = map.substring(i, i + 2);
            if(substring.equals("EW")) ewCount++;
        }
        if(ewCount==1 || ewCount == 0 ) bw.write("1\n");
        else bw.write(ewCount +"\n");

        br.close();
        bw.close();
    }
}
