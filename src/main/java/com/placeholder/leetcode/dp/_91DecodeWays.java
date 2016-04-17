package com.placeholder.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/decode-ways/
 *
 * @author yuxiangque
 * @version 2016/4/17
 */
public class _91DecodeWays {

    Map<Integer, Integer> cache;

    // https://leetcode.com/discuss/8527/dp-solution-java-for-reference
    public int numDecodings2(String s) {
        int n = s.length();
        if (n == 0) return 0;

        int[] memo = new int[n + 1];
        memo[n] = 1;
        memo[n - 1] = s.charAt(n - 1) != '0' ? 1 : 0;

        for (int i = n - 2; i >= 0; i--)
            if (s.charAt(i) == '0')
                continue;
            else
                memo[i] = (Integer.parseInt(s.substring(i, i + 2)) <= 26) ? memo[i + 1] + memo[i + 2] : memo[i + 1];
        return memo[0];
    }

    public int numDecodings(String s) {
        if (s == null || s.length() == 0)
            return 0;
        cache = new HashMap<>();
        return dp(s, 0);
    }

    // doing dp from tail to head. great!
    // dp[i] means ways of decoding for substring(i, end).
    // at a certain point i, if the char is '0' then it must be combine with char i-1
    // dp[n] = 1;
    // dp[n-1] = If s[n-1] == '0' Then 0 Else 1
    // dp[i] = If s[i] == '0' Then
    //             0
    //         Else
    //             If int(s[i..i+1]) <= 26 Then
    //                 dp[i+1]+dp[i+2]
    //             Else
    //                 dp[i+1]
    public int dp(String s, int i) {
        int n = s.length();
        if (cache.containsKey(i)) {
            return cache.get(i);
        }
        if (i == n) {
            int ways = 1;
            cache.put(i, ways);
            return ways;
        }
        if (i == n - 1) {
            int ways = s.charAt(n - 1) != '0' ? 1 : 0;
            cache.put(i, ways);
            return ways;
        }
        if (s.charAt(i) == '0') {
            int ways = 0;
            cache.put(i, ways);
            return ways;
        }
        int ways = (Integer.parseInt(s.substring(i, i + 2)) <= 26) ? dp(s, i + 1) + dp(s, i + 2) : dp(s, i + 1);
        cache.put(i, ways);
        return ways;
    }

    @Test
    public void test() {
        Assert.assertEquals(0, numDecodings(""));
        Assert.assertEquals(1, numDecodings("1"));
        Assert.assertEquals(2, numDecodings("12"));
        Assert.assertEquals(3, numDecodings("123"));
        Assert.assertEquals(3, numDecodings("1232"));
        Assert.assertEquals(numDecodings2("234234234234242356165243462534234"), numDecodings("234234234234242356165243462534234"));
        Assert.assertEquals(numDecodings2("6065812287883668764831544958683283296479682877898293612168136334983851946827579555449329483852397155"), numDecodings("6065812287883668764831544958683283296479682877898293612168136334983851946827579555449329483852397155"));
    }
}
