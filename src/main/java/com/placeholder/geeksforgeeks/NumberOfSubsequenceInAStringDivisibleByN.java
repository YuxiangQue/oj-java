package com.placeholder.geeksforgeeks;

/**
 * http://www.geeksforgeeks.org/number-subsequences-string-divisible-n/
 */
public class NumberOfSubsequenceInAStringDivisibleByN {

    static int dfs(String str, int n, int index, int num) {
        if (index == str.length()) {
            if (num != 0 && num % n == 0) {
                return 1;
            }
            return 0;
        }
        int ans = 0;
        ans += dfs(str, n, index + 1, num);
        ans += dfs(str, n, index + 1, (num * 10 + str.charAt(index) - '0'));
        return ans;
    }
}
