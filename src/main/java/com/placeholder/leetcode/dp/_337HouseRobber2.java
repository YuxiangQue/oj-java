package com.placeholder.leetcode.dp;

import com.placeholder.common.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/house-robber-iii/
 *
 * @author yuxiangque
 * @version 2016/4/16
 */
public class _337HouseRobber2 {


    // dp[root] = money[root] + dp[root.left.left] + dp[root.left.right] + dp[root.right.left] + dp[root.right.right]
    // dp[root] = dp[root.left] + dp[root.right]
    public static int rob(TreeNode root) {
        Map<TreeNode, Integer> cache = new HashMap<>();
        return dp(root, cache);
    }

    private static int dp(TreeNode root, Map<TreeNode, Integer> cache) {
        if (root == null) {
            return 0;
        }
        if (cache.containsKey(root)) {
            return cache.get(root);
        }

        // 方案一，孙子+自己
        int dp1 = root.val;
        if (root.left != null) {
            dp1 += dp(root.left.left, cache);
            dp1 += dp(root.left.right, cache);
        }
        if (root.right != null) {
            dp1 += dp(root.right.left, cache);
            dp1 += dp(root.right.right, cache);
        }

        // 方案二，儿子中大的
        int dp2 = dp(root.left, cache) + dp(root.right, cache);

        int dp = Math.max(dp1, dp2);
        cache.put(root, dp);

        return dp;
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);
        Assert.assertEquals(7, rob(root));

        root = new TreeNode(3);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);
        Assert.assertEquals(9, rob(root));
    }
}
