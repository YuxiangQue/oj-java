package com.placeholder.leetcode.bsearch;

/**
 * https://leetcode.com/problems/first-bad-version/
 * @author 阙宇翔
 * @version 2016/3/21
 */
public class _278FirstBadVersion {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.firstBadVersion(3);
    }

    static class VersionControl {
        boolean[] versions31 = new boolean[]{true, true, false};
        boolean[] versions32 = new boolean[]{true, false, false};
        boolean[] versions33 = new boolean[]{false, false, false};

        boolean isBadVersion(int version) {
            return !versions32[version];
        }
    }

    // https://leetcode.com/discuss/62255/o-lgn-simple-java-solution
    static class Solution extends VersionControl {
        public int firstBadVersion(int n) {
            int lower = 1;
            int upper = n;
            while (lower < upper) {
                int mid = lower + (upper - lower) / 2;  // avoid possible overflow
                if (isBadVersion(mid)) {
                    upper = mid;
                } else {
                    lower = mid + 1;
                }
            }
            return lower;
        }
    }
}
