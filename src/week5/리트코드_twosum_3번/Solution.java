package week5.리트코드_twosum_3번;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hm = new HashMap<>();
        int[] ans = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            if(hm.containsKey(diff)){
                return new int[]{i,hm.get(diff)};
            }
            hm.put(nums[i],i);
        }
        return ans;
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ints = s.twoSum(new int[]{2, 7, 11, 15}, 9);
        for (int anInt : ints) {
            System.out.println("anInt = " + anInt);
        }
    }
}