import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(
        int[] nums,
        int limit
    ) {

        int n = nums.length;

        int[][] pairs = new int[n][2];

        for (int i = 0; i < n; i++) {
            pairs[i][0] = nums[i];
            pairs[i][1] = i;
        }

        Arrays.sort(pairs, Comparator.comparingInt(a -> a[0]));

        int[] result = new int[n];

        int start = 0;

        while (start < n) {

            int end = start;

            while (
                end + 1 < n &&
                pairs[end + 1][0] - pairs[end][0] <= limit
            ) {
                end++;
            }

            int size = end - start + 1;

            int[] indices = new int[size];
            int[] values = new int[size];

            for (int i = start; i <= end; i++) {
                indices[i - start] = pairs[i][1];
                values[i - start] = pairs[i][0];
            }

            Arrays.sort(indices);

            for (int i = 0; i < size; i++) {
                result[indices[i]] = values[i];
            }

            start = end + 1;
        }

        return result;
    }
}
