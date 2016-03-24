package com.placeholder.leetcode.string;

import org.junit.Test;

import java.util.Objects;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/simplify-path/
 * http://www.cnblogs.com/ganganloveu/p/3782727.html
 *
 * @author yuxiangque
 * @version 2016/3/24
 */
public class _71SimplifyPath {

    /**
     * 分割，处理空白字符，堆栈
     *
     * @param path
     * @return
     */
    public static String simplifyPath(String path) {
        String[] pathStrings = path.split("/");
        Stack<String> stack = new Stack<>();
        for (String pathString : pathStrings) {
            if (Objects.equals(pathString, "")) { // ignore
            } else if (Objects.equals(pathString, ".")) { // ignore
            } else if (Objects.equals(pathString, "..")) { // parent
                if (!stack.empty())
                    stack.pop();
            } else {
                stack.push(pathString);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String s : stack) {
            sb.append("/").append(s);
        }
        if (sb.length() == 0)
            sb.append("/");
        return sb.toString();
    }

    @Test
    public void test() {
        assertEquals("/c", simplifyPath("/a/./b/../../c/"));
        assertEquals("/home", simplifyPath("/home/"));
        assertEquals("/", simplifyPath("/.."));
        assertEquals("/a/b/c", simplifyPath("/../a/b/c"));
    }
}
