package com.placeholder.test.mstest2016april1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author yuxiangque
 * @version 2016/4/6
 */
public class Forbidden403 {


    static List<Rule> rules = new ArrayList<>();

    private static boolean netMatch(String addr, String addr1) {
        String[] parts = addr.split("/");
        String ip = parts[0];
        int prefix;

        if (parts.length < 2) {
            prefix = 0;
        } else {
            prefix = Integer.parseInt(parts[1]);
        }

        String[] a = ip.split("\\.");
        String[] a1 = addr1.split("\\.");

        int ipInt = ((Integer.valueOf(a[0]) & 0xFF) << 24) |
                ((Integer.valueOf(a[1]) & 0xFF) << 16) |
                ((Integer.valueOf(a[2]) & 0xFF) << 8) |
                ((Integer.valueOf(a[3]) & 0xFF) << 0);

        int ipInt1 = ((Integer.valueOf(a1[0]) & 0xFF) << 24) |
                ((Integer.valueOf(a1[1]) & 0xFF) << 16) |
                ((Integer.valueOf(a1[2]) & 0xFF) << 8) |
                ((Integer.valueOf(a1[3]) & 0xFF) << 0);

        int mask = ~((1 << (32 - prefix)) - 1);

        if ((ipInt & mask) == (ipInt1 & mask)) {
            return true;
        } else {
            return false;
        }
    }

    public static void addRule(String ruleString) {
        String[] ipMaskString = ruleString.split(" ");
        String[] ipAndMaskStrings = ipMaskString[1].split("/");
        String ip = ipAndMaskStrings[0];
        int mask = 32;
        if (ipAndMaskStrings.length != 1) {
            mask = Integer.parseInt(ipAndMaskStrings[1]);
        }
        if (Objects.equals(ipMaskString[0], "allow")) {
            Rule rule = new Rule(ip + "/" + mask, true);
            rules.add(rule);

        } else {
            mask = 32 - mask;
            Rule rule = new Rule(ip + "/" + mask, false);
            rules.add(rule);
        }
        return;
    }

    public static boolean allowOrDeny(String ip) {
        for (Rule rule : rules) {
            if (netMatch(rule.ip, ip))
                return rule.allowOrDeny;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.nextLine();  // jump
        for (int i = 0; i < n; i++) {
            addRule(scanner.nextLine());
        }
        for (int i = 0; i < m; i++) {
            if (allowOrDeny(scanner.nextLine())) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    static class Rule {
        String ip;
        boolean allowOrDeny;

        public Rule(String ip, boolean allowOrDeny) {
            this.ip = ip;
            this.allowOrDeny = allowOrDeny;
        }
    }
}
