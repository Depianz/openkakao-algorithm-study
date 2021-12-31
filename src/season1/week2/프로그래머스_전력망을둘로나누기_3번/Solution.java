package season1.week2.프로그래머스_전력망을둘로나누기_3번;

class Solution {
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;

        boolean[][] connect = new boolean[n+1][n+1];

        for (int i = 0; i < wires.length; i++) {
            int a = wires[i][0];
            int b = wires[i][1];

            connect[a][b] = true;
            connect[b][a] = true;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if(connect[i][j] == true){
                    connect[i][j] = false;
                    connect[j][i] = false;
                    boolean[] check = new boolean[n+1];
                    check[1] = true;
                    int sideA = countConnect(n,connect,1, check);
                    int sideB = n - sideA;
                    answer = Math.min(answer,Math.abs(sideA-sideB));
                    connect[i][j] = true;
                    connect[j][i] = true;
                }
            }
        }


        return answer;
    }

    private int countConnect(int n, boolean[][] connect, int start, boolean[] check) {
        int tmp = 1;

        for (int i = 1; i <= n; i++) {
            if(!check[i] && connect[start][i]) {
                check[i] = true;
                tmp += countConnect(n,connect,i,check);
            }
        }

        return tmp;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int answer = s.solution(9, new int[][]{{1, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 7}, {7, 8}, {7, 9}});
        System.out.println("answer = " + answer);
    }
}