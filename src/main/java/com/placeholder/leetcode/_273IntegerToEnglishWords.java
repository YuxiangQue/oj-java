package com.placeholder.leetcode;

import org.junit.Test;

/**
 * https://leetcode.com/problems/integer-to-english-words/
 *
 * @author yuxiangque
 * @version 2016/4/17
 */
public class _273IntegerToEnglishWords {

    @Test
    public void test() {
        System.out.println(numberToWords(113_123_523));
        System.out.println(numberToWords(1_234_567_891));
    }

    public String numberToWords(int num) {
        if (num == 0)
            return "Zero";
        int i = 0;
        String words = "";
        while (num > 0) {
            if (num % 1000 != 0) {
                words = helper(num % 1000) + thousands[i] + " " + words;
            }
            num /= 1000;
            ++i;
        }
        return words.trim();
    }

    String[] lessThan20 = {
            "",
            "One",
            "Two",
            "Three",
            "Four",
            "Five",
            "Six",
            "Seven",
            "Eight",
            "Nine",
            "Ten",
            "Eleven",
            "Twelve",
            "Thirteen",
            "Fourteen",
            "Fifteen",
            "Sixteen",
            "Seventeen",
            "Eighteen",
            "Nineteen"
    };

    String[] tens = {
            "",
            "Ten",
            "Twenty",
            "Thirty",
            "Forty",
            "Fifty",
            "Sixty",
            "Seventy",
            "Eighty",
            "Ninety"
    };

    public String numberToWords(int num) {
        if (num == 0)
            return "Zero";
        int i = 0;
        String words = "";
        while (num > 0) {
            if (num % 1000 != 0) {
                words = helper(num % 1000) + thousands[i] + " " + words;
            }
            num /= 1000;
            ++i;
        }
        return words.trim();
    }

    String[] thousands = {
            "",
            "Thousand",
            "Million",
            "Billion"
    };

    private String helper(int num) {
        if (num == 0) {
            return "";

        } else if (num < 20) {
            return lessThan20[num] + " ";
        } else if (num < 100) {
            return tens[num / 10] + " " + helper(num % 10);
        } else {
            return lessThan20[num / 100] + " Hundred " + helper(num % 100);
        }
    }
}
