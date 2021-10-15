package week1.프로그래머스_후보키;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * dfs로 구현하는게 좋을듯
 * o(n) = 2^8 * 20 *+++
 * 리팩토링 필요
 */
class Solution {
    int colN;
    boolean[] checkCol;
    String[][] rel;
    int answer = 0;
    List<String> keys = new ArrayList<>();

    void dfs(int idx, int cnt, int hap) {
        if (hap == cnt) {
            if (calKey()) answer++;
            return;
        }
        if (idx == colN) return;

        for (int i = idx; i < colN; i++) {
            if (checkCol[i] == false) {
                checkCol[i] = true;
                dfs(i + 1, cnt, hap + 1);
                checkCol[i] = false;
            }
        }
    }

    private boolean calKey() {
        List<Integer> arl = new ArrayList<>();
        for (int i = 0; i < checkCol.length; i++) {
            if (checkCol[i] == true) {
                arl.add(i);
            }
        }
        if (arl.size() == 0) return false;
        Set<String> hs = new HashSet<>();
        for (int i = 0; i < rel.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (Integer integer : arl) {
                sb.append(rel[i][integer]);
                sb.append("_");
            }
            String s = sb.toString();
            if (hs.contains(s)) return false;
            else hs.add(s);
        }
        String makedKey = makeKey(arl);
        for (String s : keys) {
            boolean[] check = new boolean[s.length()];
            for (int i = 0; i < s.length(); i++) {
                for (int j = 0; j < makedKey.length(); j++) {
                    if (makedKey.charAt(j) == s.charAt(i)) {
                        check[i] = true;
                    }
                }
            }
            int flag = 0;
            for (int i = 0; i < s.length(); i++) {
                if (check[i] == true) flag++;
            }
            if (flag == s.length()) return false;
        }
        keys.add(makedKey);
        return true;
    }

    private String makeKey(List<Integer> arl) {
        StringBuilder sb = new StringBuilder();
        for (Integer integer : arl) {
            sb.append(integer);
        }
        return sb.toString();
    }


    public int solution(String[][] relation) {
        colN = relation[0].length;
        checkCol = new boolean[colN];
        rel = relation;

        for (int i = 1; i <= colN; i++) {
            dfs(0, i, 0);
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        String[][] a = {{"a", "1", "aaa", "c", "ng"},
                {"a", "1", "bbb", "e", "g"},
                {"c", "1", "aaa", "d", "ng"},
                {"d", "2", "bbb", "d", "ng"}};
        int solution = s.solution(a);
        System.out.println("solution = " + solution);
    }
}