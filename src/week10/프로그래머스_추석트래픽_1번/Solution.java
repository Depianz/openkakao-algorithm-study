package week10.프로그래머스_추석트래픽_1번;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {

    class log{
        int start;
        int end;

        log (int a, int b){
            this.start = a;
            this.end = b;
        }
    }

    public int solution(String[] lines) {
        int answer = 0;

        Pattern pattern = Pattern.compile( "2016-09-15 (\\d+):(\\d+):(\\d+)\\.(\\d+) (\\d+)\\.*(\\d*)s");


        log[] logs = new log[lines.length];

        for (int i = 0; i < lines.length; i++) {
            Matcher m = pattern.matcher(lines[i]);
            if(m.find()){
                int milLine = 0;
                milLine += Integer.parseInt(m.group(1)) * 60 * 60 * 1000;
                milLine += Integer.parseInt(m.group(2)) * 60 * 1000;
                milLine += Integer.parseInt(m.group(3)) * 1000;
                milLine += Integer.parseInt(m.group(4));
                int end = milLine;
                int start = milLine - (Integer.parseInt(m.group(5))*1000) +1;
                if(!m.group(6).equals("")) start -= Integer.parseInt(m.group(6));
                logs[i] = new log(start,end);
            }
        }

        for (int i = 0; i < logs.length; i++) {
            log target = logs[i];
            int cnt = 0;
            int tst = target.start-999;
            int tend = target.start;

            for (int j = 0; j < logs.length; j++) {
                if(tend >=  logs[j].start && logs[j].end >= tst) cnt++;
            }
            if(cnt > answer) answer = cnt;

        }

        return answer;
    }
}