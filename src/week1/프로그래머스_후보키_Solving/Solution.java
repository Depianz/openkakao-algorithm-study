package week1.프로그래머스_후보키_Solving;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * dfs로 구현하는게 좋을듯
 * o(n) = 2^8 * 20
 */
class Solution {
    int colN;
    boolean[] checkCol;
    String[][] rel;
    int answer = 0;
    List<String> key = new ArrayList<>();

    void dfs(int idx){
        if(idx == colN){
            //TODO 연산
            if(calKey()) answer++;
            return;
        };

        for (int i = idx; i < colN; i++) {
            if(checkCol[i] == false){
                checkCol[i] = true;
                dfs(i+1);
                checkCol[i] = false;
            }
        }
    }

    private boolean calKey() {
        List<Integer> arl = new ArrayList<>();


        for (int i = 0; i < checkCol.length; i++) {
            if(checkCol[i] == true){
                arl.add(i);
            }
        }
        System.out.println("arl.toString() = " + arl.toString());
        if(arl.size() == 0 )return false;
        StringBuilder sb = new StringBuilder();
        Set<String> hs = new HashSet<>();
        for (int i = 0; i < rel.length; i++) {
            for (Integer integer : arl) {
                sb.append(rel[i][integer]);
                sb.append("_");
            }
            String s = sb.toString();
            if(hs.contains(s)) return false;
            else hs.add(s);
        }
        return true;
    }


    public int solution(String[][] relation) {
        int answer = 0;
        colN = relation[0].length;
        checkCol = new boolean[colN];
        rel = new String[relation.length][relation[0].length];

        for (int i = 0; i < relation.length; i++) {
            for (int j = 0; j < relation[0].length; j++) {
                rel[i][j] = relation[i][j];
            }
        }


        dfs(0);

        return answer;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        String[][] a = new String[][]{{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","2"},
        {"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","2"}};
        ;
        int solution = s.solution(a);
        System.out.println("solution = " + solution);
    }
}