package leetcode.MayThirtyOneDays;

//https://en.wikipedia.org/wiki/Binary_search_algorithm#Implementation_issues
//http://www.cs.cornell.edu/courses/cs211/2006sp/Lectures/L06-Induction/binary_search.html

public class FirstBadVersion {
    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        while (left < right) {
            int mid = left + (right - left) / 2; // beware of Integer Overflow
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public boolean isBadVersion(int num) {
        return true;
    }
}


