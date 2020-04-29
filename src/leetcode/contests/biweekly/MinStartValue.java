package leetcode.contests.biweekly;

class Solution1 {
    public int minStartValue(int[] nums) {
        int start = 0;
        int currsum = 0;
        for(int val : nums) {
            currsum += val;
            if(currsum < 1) {
                start += Math.abs(currsum) + 1;
                currsum = 1;
            }
        }
        return start == 0 ? 1 : start;
    }
}

// Binary Search method
class BinarySearchSolution {
    public static int minStartValue(int[] nums) {
        int min = Integer.MAX_VALUE;
        int lo = 1;
        int hi = min;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (isValid(mid, nums)) {
                min = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return min;
    }

    private static boolean isValid(int min, int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (min + nums[i] >= 1) {
                min += nums[i];
            } else {
                return false;
            }
        }
        return true;
    }
}

class Solution3 {
    public int minStartValue(int[] nums) {
        int sum = 0, minPrefixSum = Integer.MAX_VALUE;
        for (int n : nums) {
            sum += n;
            minPrefixSum = Math.min(sum, minPrefixSum);
        }
        return 1 - minPrefixSum < 1 ? 1 : 1 - minPrefixSum;
    }
}

class NegativeSolution {
    public int minStartValue(int[] nums) {

        int max = 1;
        int sum = 1;
        for(int i=0;i<nums.length;i++)
        {
            sum -= nums[i]; // I get this now
            if(max<sum)
                max = sum;
        }
        return max;
    }

}

