package season1.week5.프로그래머스_구명보트_2번;

import java.util.Arrays;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;

        Arrays.sort(people);
        int[] sortedPeople = new int[people.length];
        for (int i = 0; i < sortedPeople.length; i++) {
            sortedPeople[i]=people[people.length-1-i];
        }

        int idx = 0;
        int eidx = people.length-1;

        while(idx <= eidx){
            if(sortedPeople[idx] <= limit/2){
                answer += Math.ceil(((double)eidx-(double) idx+1)/2);
                break;
            }

            if(sortedPeople[idx] + sortedPeople[eidx] <= limit){
                idx++;
                eidx--;
            } else {
                idx++;
            }
            answer++;
        }
        return answer;
    }
}