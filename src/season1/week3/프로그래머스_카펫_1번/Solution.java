package season1.week3.프로그래머스_카펫_1번;

/**
 * 수학적 공식을 이용한 문제
 * r = 가로길이 , c = 세로길이 라고 할때
 * 2 * r + 2 * c - 4 == brown 과 (r - 2) * (c - 2) == yellow
 * 를 만족해야함
 * 가로길이가 갈색과 노란색의 합의 길이를 넘을 수 없으니, for문으로 탐색하여 아래 조건도 만족하는 길이를 확인한다
 * 조건) r * c = total 되어야함 / r >= c 임
 */

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];

        int total = brown + yellow;

        for (int r = 1; r < total; r++) {
            if (total % r != 0) continue;
            int c = total/r;
            if(c > r) continue;
            if (2 * r + 2 * c - 4 == brown && (r - 2) * (c - 2) == yellow) {
                answer[0] = r;
                answer[1] = c;
                break;
            }
        }
        return answer;
    }
}