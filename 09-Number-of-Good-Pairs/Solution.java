import java.util.*;

class Solution {
    public int numIdenticalPairs(int[] nums) {

        HashMap<Integer, Integer> frequency = new HashMap<>();

        int pairs = 0;

        for (int num : nums) {

            int count = frequency.getOrDefault(num, 0);

            pairs += count;

            frequency.put(num, count + 1);
        }

        return pairs;
    }
}
