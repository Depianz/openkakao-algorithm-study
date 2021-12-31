package season1.week1.프로그래머스_순위검색;


import java.util.*;

/**
 * 풀스캔을 한다고 했을때
 * 시간복잡도는 10,000 (행수) * 100,000 (쿼리수)
 * 그러므로 인덱싱 처럼 만들어서 해결하자
 * 미리 솔팅을 하고 
 */
class Solution {

    Map<String, List<Integer>> hm = new HashMap<>();

    public int[] solution(String[] info, String[] query) {
        List<Integer> tmp = new ArrayList<>();

        makeIndexMap();
        inputInfo(info);
        sortMap();

        for (String qu : query) {
            doQuery(tmp, qu);
        }

        int[] answer = new int[tmp.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = tmp.get(i);
        }

        return answer;
    }

    private void doQuery(List<Integer> tmp, String qu) {
        String[] querySplited = qu.split(" and ");
        String lang = querySplited[0];
        String field = querySplited[1];
        String year = querySplited[2];
        String[] last = querySplited[3].split(" ");
        String food = last[0];
        int point = Integer.parseInt(last[1]);

        StringBuilder sb = new StringBuilder();
        sb.append(lang);
        sb.append(field);
        sb.append(year);
        sb.append(food);
        List<Integer> points = hm.get(sb.toString());
        int ans = binarySearch(points, point);
        tmp.add(points.size() - ans);
    }

    private void sortMap() {
        for (String s : hm.keySet()) {
            Collections.sort(hm.get(s));
        }
    }

    private int binarySearch(List<Integer> points, int point) {
        int low = 0;
        int high = points.size();
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (point <= points.get(mid)) high = mid;
            else low = mid + 1;
        }
        return low;
    }

    private void inputInfo(String[] infos) {
        for (String info : infos) {
            String[] split = info.split(" ");

            String[] lang = new String[]{split[0], "-"};
            String[] field = new String[]{split[1], "-"};
            String[] year = new String[]{split[2], "-"};
            String[] food = new String[]{split[3], "-"};
            int point = Integer.parseInt(split[4]);

            for (String s : lang) {
                for (String s1 : field) {
                    for (String s2 : year) {
                        for (String s3 : food) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(s);
                            sb.append(s1);
                            sb.append(s2);
                            sb.append(s3);
                            String key = sb.toString();
                            List<Integer> integers = hm.get(key);
                            integers.add(point);
                            hm.put(key, integers);
                        }
                    }
                }
            }
        }
    }

    private void makeIndexMap() {
        String[] lang = new String[]{"java", "python", "cpp", "-"};
        String[] field = new String[]{"frontend", "backend", "-"};
        String[] year = new String[]{"junior", "senior", "-"};
        String[] food = new String[]{"pizza", "chicken", "-"};

        for (String s : lang) {
            for (String s1 : field) {
                for (String s2 : year) {
                    for (String s3 : food) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(s);
                        sb.append(s1);
                        sb.append(s2);
                        sb.append(s3);
                        hm.put(sb.toString(), new ArrayList<>());
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] tmp = s.solution(new String[]{"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"},
                new String[]{"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"}
        );
        for (int i : tmp) {
            System.out.println(i);
        }
    }
}