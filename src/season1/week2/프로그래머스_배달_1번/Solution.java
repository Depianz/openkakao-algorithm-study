package season1.week2.프로그래머스_배달_1번;

class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        int[][] adj = new int[N + 1][N + 1];
        for (int i = 0; i < road.length; i++) {
            int a = road[i][0];
            int b = road[i][1];
            int d = road[i][2];
            int readD = adj[a][b] != 0 ? Math.min(d,adj[a][b]) : d;
            adj[a][b] = readD;
            adj[b][a] = readD;
        }

        boolean[] check = new boolean[N + 1];
        int[] dist = new int[N + 1];

        for (int i = 0; i < N + 1; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[1] = 0;
        check[1] = true;

        for (int i = 1; i <= N; i++) {
            if (!check[i] && adj[1][i] != 0)
                dist[i] = adj[1][i];
        }

        for (int i = 1; i <= N - 1; i++) {
            int min = Integer.MAX_VALUE;
            int min_idx = -1;
            for (int j = 1; j <= N; j++) {
                // 1번 노드와 연결된 것 중, 가장 작은 거리(간선값 합)을 가진 것 찾기
                if (!check[j] && dist[j] < Integer.MAX_VALUE) {
                    if (dist[j] < min) {
                        // min 에 거리 저장하고
                        // min_idx 에 가장 작은 거리(간선값 합) 가진 노드 저장
                        min = dist[j];
                        min_idx = j;
                    }
                }
            }

            check[min_idx] = true;
            // 어떠한 점 A에 대해,
            // (1 -> min_idx 거리 + min_idx -> A) 거리와
            //  1 -> A 거리 비교
            for (int j = 1; j <= N; j++) {
                if (!check[j] && adj[min_idx][j] != 0) {
                    // (1 -> min_idx 거리 + min_idx -> A) 거리가 더 짧은 경우, dist 배열 (1에서 부터 노드까지 거리) 업데이트 진행
                    dist[j] = Math.min(dist[j], dist[min_idx] + adj[min_idx][j]);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if (dist[i] <= K) answer++;
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int answer = s.solution(5, new int[][]{{1, 2, 1}, {2, 3, 3}, {5, 2, 2}, {1, 4, 2}, {5, 3, 1}, {5, 4, 2}}, 3);
        System.out.println("answer = " + answer);
    }
}