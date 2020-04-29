package leetcode;

public class SingleElemInSortedArray {
    public int singleNonDuplicate(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        int res = binarySearchIter(nums);
        return nums[res];
    }

    public int binarySearchIter(int[] nums) {
        int start = 0;
        int end = nums.length - 1;

        while(start <= end) {
            if (nums[start] != nums[start + 1])
                return start;
            if (nums[end] != nums[end - 1])
                return end;
            int mid = (start + end) /2;
            if (nums[mid] != nums[mid + 1] && nums[mid] != nums[mid - 1])
                return mid;
            if ((mid - start) % 2 == 1) {
                if (nums[mid] == nums[mid - 1])
                    start = mid + 1;
                else
                    end = mid - 1;
            } else {
                if (nums[mid] == nums[mid - 1]) {
                    end = mid - 2;
                } else {
                    start = mid + 2;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        SingleElemInSortedArray sol1 = new SingleElemInSortedArray();
        sol1.singleNonDuplicate(new int[] {1,1,2,2,3,4,4,5,5,6,6,7,7,8,8,9,9,10,10,11,11,45,45,67,78,78});
    }
}