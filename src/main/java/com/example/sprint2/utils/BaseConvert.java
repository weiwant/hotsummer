package com.example.sprint2.utils;

/**
 * @author ZWForum
 * @description 10进制整数转26进制
 * @date 2022/6/25
 */
public class BaseConvert {
    public static String toBase26(Integer decimal) {
        StringBuilder output = new StringBuilder();
        while (decimal > 0) {
            int temp = decimal % 26;
            if (temp == 0) {
                temp = 26;
            }
            output.append((char) (temp + 64));
            decimal = (decimal - temp) / 26;
        }
        return output.reverse().toString();
    }
}
