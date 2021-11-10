package week4.백준_줄세우기_3번;


//https://codingnojam.tistory.com/66 참고
// 순환하지 않는 그래프 일때 가능
//진행순서는 다음과 같습니다.
//        1. 그래프의 각 노드들의 진입 차수 테이블 생성 및 진입차수 계산
//        2. 진입차수가 0인 노드 큐에 넣기 (이때 어떤 노드 먼저 시작하던지 관계없음)
//        3. 큐에서 노드를 하나 꺼낸 후 꺼낸 노드와 간선으로 연결된 노드들의 진입차수 감소 (진입차수테이블 갱신)
//        4. 진입차수 테이블을 갱신 후 진입차수의 값이 0인 노드가 있다면 큐에 넣기 (없으면 아무것도 안 함)
//        5. 3~4번의 순서를 큐에 더 이상 아무것도 없을 때까지 반복
//        위와 같은 순서로 위상 정렬(Topological Sort)은 수행되며, 모든 순서가 끝났는데도 진입차수가 0이 아닌 노드가 있다면 그래프 내에서 순환이 존재한다고 볼 수 있습니다.

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = br.readLine();

        String[] splitedInput = input.split(" ");
        int n = Integer.parseInt(splitedInput[0]);
        int m = Integer.parseInt(splitedInput[1]);

        int[] edgeCount = new int[n+1];
        List[] graph = new ArrayList[n+1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList();
        }

        for (int i = 0; i < m; i++) {
            String line = br.readLine();
            String[] splitedLine = line.split(" ");
            int left = Integer.parseInt(splitedLine[0]);
            int right = Integer.parseInt(splitedLine[1]);
            edgeCount[right]++;
            graph[left].add(right);
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if(edgeCount[i] == 0) {
                q.add(i);
            }
        }

        while(!q.isEmpty()){
            int cur = q.poll();
            bw.write(cur+" ");
            List<Integer> curList = graph[cur];
            if(curList.isEmpty()) continue;
            for (Integer integer : curList) {
                edgeCount[integer]--;
                if(edgeCount[integer] == 0){
                    q.add(integer);
                }
            }
        }
        bw.flush();

        br.close();
        bw.close();
    }
}
