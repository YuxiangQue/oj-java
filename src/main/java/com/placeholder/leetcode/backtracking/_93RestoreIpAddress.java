package com.placeholder.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/restore-ip-addresses/
 * @author 阙宇翔
 * @version 2016/2/17
 */
public class _93RestoreIpAddress {


    public static void main(String[] args) {
        System.out.println(new Method1().restoreIpAddresses("25525511135"));
    }

    static class Method1 {
        public List<String> restoreIpAddresses(String s) {
            List<String> possibleIpAddresses = new ArrayList<>();
            if (s.length() < 4 || s.length() > 12)
                return possibleIpAddresses; //注意字符串有效长度
            dfs(s, 0, new ArrayList<>(), 1, possibleIpAddresses);
            return possibleIpAddresses;
        }


        /**
         * @param str                 要恢复字符串
         * @param startIndex          要恢复字符串当前的开始下标
         * @param ipAddress           以分段方式保存当前IpAddress的List
         * @param ipAddressCount      ipAddress中共有几个地址段
         * @param possibleIpAddresses 保存所有结果
         */
        private void dfs(String str, int startIndex, List<String> ipAddress, int ipAddressCount, List<String> possibleIpAddresses) {
            if (startIndex == str.length()) {
                if (ipAddressCount == 5) {
                    possibleIpAddresses.add(buildIpString(ipAddress));
                }
                return;
            }
            // 从str的startIndex位置开始确定ip串中的第k个段
            for (int k = 1; k <= 3 && startIndex + (k - 1) < str.length(); k++) {
                //向前探k位数字
                String num = str.substring(startIndex, startIndex + k);
                if (validIpAddress(num)) {
                    ipAddress.add(num);
                    dfs(str, startIndex + k, ipAddress, ipAddressCount + 1, possibleIpAddresses);
                    ipAddress.remove(ipAddress.size() - 1);
                }
            }
        }

        private boolean validIpAddress(String ipAddress) {
            if (ipAddress.length() == 1) {
                int value = Integer.parseInt(ipAddress);
                if (value >= 0 && value <= 9)
                    return true;
            }
            if (ipAddress.length() == 2) {
                int value = Integer.parseInt(ipAddress);
                if (value >= 10 && value <= 99)
                    return true;
            }
            if (ipAddress.length() == 3) {
                int value = Integer.parseInt(ipAddress);
                if (value >= 100 && value <= 255)
                    return true;
            }
            return false;
        }

        private String buildIpString(List<String> ipAddress) {
            //返回字符串
            String ip = ipAddress.get(0);
            for (int i = 1; i < 4; i++)
                ip += "." + ipAddress.get(i);
            return ip;
        }
    }
}
