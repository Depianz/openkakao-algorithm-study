package season1.week1.프로그래머스_예상대진표;

/**
 * n이 왜 필요한지 모르겠다.
 * a,b 대소가 문제에 주어지지는 않지만,
 * 요점은 홀수인 수에서 1을 더하면 다른 수가 나오느냐임
 */
class Solution {
    public int solution(int n, int a, int b) {
        int answer = 1;
        while (!isEnd(a, b)) {
            a = a % 2 == 0 ? a / 2 : a / 2 + 1;
            b = b % 2 == 0 ? b / 2 : b / 2 + 1;
            answer++;
        }
        return answer;
    }

    private boolean isEnd(int a, int b) {
        return a % 2 != 0 ? a + 1 == b : b + 1 == a;
    }
}