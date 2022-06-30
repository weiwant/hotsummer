package com.example.sprint2.utils;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ZWForum
 * @description
 * @date 2022/6/30
 */
public class TimeParser {
    public static String getNaturalYear(String academicYear, Integer semester) {
        String regex = "^([1-9]\\d{3})-([1-9]\\d{3})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(academicYear);
        if (matcher.matches()) {
            if (Integer.parseInt(matcher.group(1)) == Integer.parseInt(matcher.group(2)) - 1) {
                if (Arrays.asList(1, 2, 3).contains(semester)) {
                    return matcher.group(Math.min(semester, 2));
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
