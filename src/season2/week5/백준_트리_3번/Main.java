package season2.week5.백준_트리_3번;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {

    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        String[] inputLines = br.readLine().split(" ");
        int deleteNode = Integer.parseInt(br.readLine());

        List<Node> arr = new ArrayList<>();

        for (int i = 0; i < inputLines.length; i++) {
            if(inputLines[i].equals("-1")){
                arr.add(new Node(i));
            } else {
                int root = Integer.parseInt(inputLines[i]);
                Node cur = new Node(i);
                arr.add(cur);
                arr.get(root).adj.add(cur);
            }
        }

        dfs(arr.get(0), deleteNode);
        bw.write(answer+"\n");
        br.close();
        bw.close();
    }

    private static void dfs(Node node, int deleteNode) {
        if(node.idx == deleteNode) return;
        if(node.adj.size() == 0 || (node.adj.size() == 1 && node.adj.get(0).idx == deleteNode)){
            answer++;
            return;
        }

        for (int i = 0; i < node.adj.size(); i++) {
            dfs(node.adj.get(i), deleteNode);
        }
    }

    static class Node {
        int idx;
        List<Node> adj = new ArrayList<>();
        public Node(int idx) {
            this.idx = idx;
        }
    }
}
