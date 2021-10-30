package week3.프로그래머스_프렌즈4블록_3번;

import java.util.ArrayList;
import java.util.List;

class Solution {

    int answer = 0 ;

    public int solution(int m, int n, String[] board) {
        char[][] map = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = board[m-1-i].charAt(j);
            }
        }

        while(checkMap(map,m,n)){

            // 지울게 있어서 지운 경우, 자리를 재배치 해줘야한다.
            // 컬럼마다 확인해서 빈자리 채우기
            // 여기에서가 핵심일 것 같은데, array로 이것을 구현할 경우, 굉장히 복잡한 로직이 될 수 있을것 같다.
            // 나의 경우는 array를 list로 만들고, list를 다시 array로 만들었음
            // map의 크기가 작고, 문제의 시간복잡도가 크지않아서 가능한듯

            List<List<Character>> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                List<Character> tmp = new ArrayList<>();
                for (int j = 0; j < m; j++) {
                    if(map[j][i] == '0') continue;
                    tmp.add(map[j][i]);
                }
                list.add(tmp);
            }

            map = new char[m][n];
            for (int i = 0; i < n; i++) {
                List<Character> tmp2 = list.get(i);
                for (int j = 0; j < tmp2.size(); j++) {
                    map[j][i] = list.get(i).get(j);
                }
                for (int j = tmp2.size(); j < m; j++) {
                    map[j][i] = '0';
                }
            }
        }

        return answer;
    }


    // 지울게 있는지 없는지 확인하고, 지울게 있으면 그것을 map에 '0'으로 표시함
    private boolean checkMap(char[][] map, int m, int n) {
        boolean[][] checkMap = new boolean[m][n];
        boolean flag = false;

        for (int i = 0; i < m-1; i++) {
            for (int j = 0; j < n-1; j++) {
                if(map[i][j] == '0') continue;
                if(map[i][j] == map[i][j+1] && map[i][j] == map[i+1][j] && map[i][j] == map[i+1][j+1]){
                    checkMap[i][j] = true;
                    checkMap[i][j+1] = true;
                    checkMap[i+1][j] = true;
                    checkMap[i+1][j+1] = true;
                    flag = true;
                }
            }
        }

        // 지울게 없으면 리턴
       if( flag== false) return false;


       // 지울게 있으면 동일모양 제거하여 map에 담아 주기
        // 지우는 숫자도 answer에 더함
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(checkMap[i][j]){
                    answer++;
                    map[i][j] = '0';
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int an = s.solution(4, 5, new String[]{"CCBDE", "AAADE", "AAABF", "CCBBF"});
        System.out.println("an = " + an);
    }
}
