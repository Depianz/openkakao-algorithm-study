package season2.week1.백준_암호만들기_4번;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String firstInput = br.readLine();
        String[] splitedInput = firstInput.split(" ");
        int l = Integer.parseInt(splitedInput[0]);
        int c = Integer.parseInt(splitedInput[1]);

        String secondInput = br.readLine();
        String[] splitedInput2 = secondInput.split(" ");

        List<String> arl = new ArrayList<>();
        for (int i = 0; i < splitedInput2.length; i++) {
            arl.add(splitedInput2[i]);
        }

        Collections.sort(arl);
        solve(l, arl, "", 0);

        br.close();
        bw.close();
    }

    private static void solve(int count, List<String> arl, String password, int idx) {
        if(password.length() == count){
            if(check(password)){
                System.out.println(password);
                return;
            }
        }
        if(idx == arl.size()) return;

        solve(count,arl,password+arl.get(idx), idx+1);
        solve(count,arl,password, idx+1);

    }

    private static boolean check(String password) {
        int mo = 0, za = 0;
        for (int i = 0; i < password.length(); i++) {
            char tmp = password.charAt(i);
            if(tmp == 'a' || tmp == 'e' || tmp == 'i' || tmp == 'o' || tmp =='u'){
                mo++;
            } else {
                za ++;
            }
        }
        return mo >= 1 && za >= 2;
    }
}
