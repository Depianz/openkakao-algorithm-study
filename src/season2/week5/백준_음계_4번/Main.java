package season2.week5.백준_음계_4번;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = br.readLine();
        String asc = "1 2 3 4 5 6 7 8";
        String desc = "8 7 6 5 4 3 2 1";

        if(input.equals(asc)) bw.write("ascending\n");
        else if(input.equals(desc)) bw.write("descending\n");
        else bw.write("mixed\n");

        br.close();
        bw.close();
    }
}
