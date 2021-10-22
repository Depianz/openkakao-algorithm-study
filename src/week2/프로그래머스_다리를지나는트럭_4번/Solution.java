package week2.프로그래머스_다리를지나는트럭_4번;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        int end = truck_weights[truck_weights.length-1];

        Queue<Integer> start = new LinkedList<>();
        for (int i = 0; i < truck_weights.length; i++) {
            start.add(truck_weights[i]);
        }

        Queue<Integer> bridge = new LinkedList<>();
        for (int i = 0; i < bridge_length; i++) {
            bridge.add(-1);
        }

        while(!bridge.isEmpty()){
            answer++;
            int out = bridge.poll();
            if(out != -1) weight += out;
            if(!start.isEmpty() && weight - start.peek() >= 0) {
                int cur = start.poll();
                bridge.add(cur);
                weight -= cur;
            } else {
                if(!start.isEmpty()) bridge.add(-1);
            }
        }


        return answer;
    }
}