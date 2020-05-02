package leetcode.AprilThirtyDays;

//Complexity Analysis
//        Time complexity : O(2^n). There are 2^n(upper bound) ways of jumping from the first position to the last, where n is the length of array nums.
//        For a complete proof, please refer to Appendix A.
//        Space complexity : O(n). Recursion requires additional memory for the stack frames.

class JumpGameRecursive {
    int count;
    public boolean canJumpFromPosition(int position, int[] nums) {
        count++;
        if (position == nums.length - 1) {
            System.out.println(count);
            return true;
        }

        int furthestJump = Math.min(position + nums[position], nums.length - 1);
        for (int nextPosition = position + 1; nextPosition <= furthestJump; nextPosition++) {
            if (canJumpFromPosition(nextPosition, nums)) {
                return true;
            }
        }

        return false;
    }

    public boolean canJump(int[] nums) {
        return canJumpFromPosition(0, nums);
    }

    public static void main(String[] args) {
        JumpGameRecursive solution = new JumpGameRecursive();
        solution.canJump(new int[]{2,3,1,1,4});
        solution.canJump(new int[]{3,2,1,0,4});
        solution.canJump(new int[]{});
    }
}

enum Index {
    GOOD, BAD, UNKNOWN
}

class JumpGameMemoization {
    int count;
    Index[] memo;

    public boolean canJumpFromPosition(int position, int[] nums) {
        count++;
        if (memo[position] != Index.UNKNOWN) {
            return memo[position] == Index.GOOD ? true : false; // return memo[position] == Index.GOOD
        }
        int furthestJump = Math.min(position + nums[position], nums.length - 1);
        for (int nextPosition = position + 1; nextPosition <= furthestJump; nextPosition++) {
            if (canJumpFromPosition(nextPosition, nums)) {
                memo[position] = Index.GOOD;
                return true;
            }
        }

        memo[position] = Index.BAD;
        return false;
    }

    public boolean canJump(int[] nums) {
        memo = new Index[nums.length];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = Index.UNKNOWN;
        }
        memo[memo.length - 1] = Index.GOOD;
        return canJumpFromPosition(0, nums);
    }

    public static void main(String[] args) {
        JumpGameMemoization solution = new JumpGameMemoization();
        solution.canJump(new int[]{2,3,1,1,4});
        solution.canJump(new int[]{3,2,1,0,4});
        solution.canJump(new int[]{});
    }
}


class DP {
    public boolean canJump(int[] nums) {
        Index[] memo = new Index[nums.length];
//        for (Index memoe: memo) {
//            memoe = Index.UNKNOWN; // memoe is null chief 😹
//        }
        for (int i = 0; i < memo.length; i++) {
            memo[i] = Index.UNKNOWN;
        }
        memo[memo.length - 1] = Index.GOOD;

        for (int i = nums.length - 2; i >= 0; i--) {
            int furthestJump = Math.min(i + nums[i], nums.length - 1);
            for (int j = i + 1; j <= furthestJump; j++) {
                if (memo[j] == Index.GOOD) {
                    memo[i] = Index.GOOD;
                    break;
                }
            }
        }

        return memo[0] == Index.GOOD;
    }

    public static void main(String[] args) {
        DP solution = new DP();
        solution.canJump(new int[]{2,3,1,1,4});
        solution.canJump(new int[]{3,2,1,0,4});
        solution.canJump(new int[]{});
    }
}

class GreedySolution {
    public boolean canJump(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }

    public boolean canJump2(int[] nums) {
        if (nums.length == 0)
            throw new IllegalArgumentException("nums can't be empty");
        int farthest = 0;
        for (int i = 0; i <= farthest && i < nums.length; ++i) {
            farthest = Math.max(farthest, i + nums[i]);
        }
        return farthest >= nums.length - 1;
    }

    public boolean canJump3(int[] nums) {
        int len = nums.length;
        int max = 0;
        for(int i=0; i<=max; i++){
            max = Math.max(max, i+nums[i]);
            if(max >= len-1)  return true;
        }
        return false;
    }

    public static void main(String[] args) {
        GreedySolution solution = new GreedySolution();
        solution.canJump(new int[]{2,3,1,1,4});
        solution.canJump(new int[]{3,2,1,0,4});
        solution.canJump(new int[]{});
    }
}

class TLESolution {
    public boolean canJump(int[] nums) {
        return canJump(0, nums, Integer.MIN_VALUE);
    }
    public boolean canJump(int pos, int[] nums, int prevPos) {
        if (pos == nums.length - 1) {
            return true;
        }
        if (pos <= prevPos) return false;
        if (pos < 0) return false;
        int furthestJump = Math.min(pos + nums[pos], nums.length - 1);
        return canJump(furthestJump, nums, pos) || canJump(pos - 1, nums, prevPos);
        //returns fromThisPos || fromNotThisPos
    }
}