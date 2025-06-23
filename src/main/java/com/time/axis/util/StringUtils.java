package com.time.axis.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author carl
 * 字符串工具类
 */
public class StringUtils {

    /**
     * 判断字符串是否为空
     * @param str 输入字符串
     * @return true: 空/null; false: 非空
     */
    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    /**
     * 判断字符串是否非空
     * @param str 输入字符串
     * @return true: 非空; false: 空/null
     */
    public static boolean isNotEmpty(CharSequence str) {
        return !isEmpty(str);
    }

    /**
     * 判断字符串是否为空白（空/null/纯空格）
     * @param str 输入字符串
     * @return true: 空白; false: 非空白
     */
    public static boolean isBlank(CharSequence str) {
        if (isEmpty(str)) return true;
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否非空白
     * @param str 输入字符串
     * @return true: 非空白; false: 空白
     */
    public static boolean isNotBlank(CharSequence str) {
        return !isBlank(str);
    }

    /**
     * 截取字符串（支持中文）
     * @param str 原始字符串
     * @param start 起始位置
     * @param length 截取长度
     * @return 截取后的字符串
     */
    public static String substring(String str, int start, int length) {
        if (isEmpty(str)) return str;
        if (start < 0) start = 0;
        if (length < 0) return "";
        
        int end = start + length;
        if (end > str.length()) end = str.length();
        
        return str.substring(start, end);
    }

    /**
     * 生成随机字符串
     * @param length 字符串长度
     * @return 随机字符串
     */
    public static String randomString(int length) {
        if (length <= 0) return "";
        
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    /**
     * 驼峰命名转下划线命名
     * @param camelCase 驼峰命名字符串
     * @return 下划线命名字符串
     */
    public static String camelToSnakeCase(String camelCase) {
        if (isBlank(camelCase)) return camelCase;
        
        StringBuilder result = new StringBuilder();
        result.append(Character.toLowerCase(camelCase.charAt(0)));
        
        for (int i = 1; i < camelCase.length(); i++) {
            char ch = camelCase.charAt(i);
            if (Character.isUpperCase(ch)) {
                result.append('_').append(Character.toLowerCase(ch));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    /**
     * 下划线命名转驼峰命名
     * @param snakeCase 下划线命名字符串
     * @param capitalizeFirst 是否首字母大写
     * @return 驼峰命名字符串
     */
    public static String snakeToCamelCase(String snakeCase, boolean capitalizeFirst) {
        if (isBlank(snakeCase)) return snakeCase;
        
        StringBuilder result = new StringBuilder();
        boolean nextUpper = capitalizeFirst;
        
        for (int i = 0; i < snakeCase.length(); i++) {
            char ch = snakeCase.charAt(i);
            if (ch == '_') {
                nextUpper = true;
            } else if (nextUpper) {
                result.append(Character.toUpperCase(ch));
                nextUpper = false;
            } else {
                result.append(Character.toLowerCase(ch));
            }
        }
        return result.toString();
    }

    /**
     * 字符串脱敏处理
     * @param str 原始字符串
     * @param start 保留开始长度
     * @param end 保留结束长度
     * @param maskChar 脱敏字符
     * @return 脱敏后的字符串
     */
    public static String maskSensitive(String str, int start, int end, char maskChar) {
        if (isBlank(str)) return str;
        if (start < 0) start = 0;
        if (end < 0) end = 0;
        if (start + end >= str.length()) return str;
        
        int maskLength = str.length() - start - end;
        StringBuilder mask = new StringBuilder();
        for (int i = 0; i < maskLength; i++) {
            mask.append(maskChar);
        }
        
        return str.substring(0, start) + mask + str.substring(str.length() - end);
    }

    /**
     * 手机号脱敏
     * @param phone 手机号
     * @return 脱敏后的手机号
     */
    public static String maskPhone(String phone) {
        if (isBlank(phone) || phone.length() < 7) return phone;
        return maskSensitive(phone, 3, 4, '*');
    }

    /**
     * 身份证号脱敏
     * @param idCard 身份证号
     * @return 脱敏后的身份证号
     */
    public static String maskIdCard(String idCard) {
        if (isBlank(idCard) || idCard.length() < 15) return idCard;
        return maskSensitive(idCard, 6, 4, '*');
    }

    /**
     * 连接字符串
     * @param delimiter 分隔符
     * @param elements 元素数组
     * @return 连接后的字符串
     */
    public static String join(String delimiter, Object... elements) {
        if (elements == null || elements.length == 0) return "";
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] != null) {
                sb.append(elements[i]);
            }
            if (i < elements.length - 1) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }

    /**
     * 分割字符串为列表
     * @param str 原始字符串
     * @param delimiter 分隔符
     * @return 分割后的字符串列表
     */
    public static List<String> splitToList(String str, String delimiter) {
        List<String> list = new ArrayList<>();
        if (isBlank(str)) return list;
        
        String[] parts = str.split(delimiter);
        for (String part : parts) {
            if (isNotBlank(part)) {
                list.add(part.trim());
            }
        }
        return list;
    }

    /**
     * 字符串转整数
     * @param str 字符串
     * @param defaultValue 转换失败时的默认值
     * @return 整数值
     */
    public static int toInt(String str, int defaultValue) {
        if (isBlank(str)) return defaultValue;
        try {
            return Integer.parseInt(str.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * 字符串转长整数
     * @param str 字符串
     * @param defaultValue 转换失败时的默认值
     * @return 长整数值
     */
    public static long toLong(String str, long defaultValue) {
        if (isBlank(str)) return defaultValue;
        try {
            return Long.parseLong(str.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * 字符串转布尔值
     * @param str 字符串
     * @param defaultValue 转换失败时的默认值
     * @return 布尔值
     */
    public static boolean toBoolean(String str, boolean defaultValue) {
        if (isBlank(str)) return defaultValue;
        
        String lower = str.trim().toLowerCase();
        if ("true".equals(lower) || "yes".equals(lower) || "1".equals(lower)) {
            return true;
        } else if ("false".equals(lower) || "no".equals(lower) || "0".equals(lower)) {
            return false;
        }
        return defaultValue;
    }

    /**
     * 首字母大写
     * @param str 原始字符串
     * @return 首字母大写后的字符串
     */
    public static String capitalize(String str) {
        if (isBlank(str)) return str;
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    /**
     * 首字母小写
     * @param str 原始字符串
     * @return 首字母小写后的字符串
     */
    public static String uncapitalize(String str) {
        if (isBlank(str)) return str;
        return Character.toLowerCase(str.charAt(0)) + str.substring(1);
    }

    /**
     * 重复字符串
     * @param str 原始字符串
     * @param times 重复次数
     * @return 重复后的字符串
     */
    public static String repeat(String str, int times) {
        if (str == null || times <= 0) return "";
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

    /**
     * 反转字符串
     * @param str 原始字符串
     * @return 反转后的字符串
     */
    public static String reverse(String str) {
        if (isBlank(str)) return str;
        return new StringBuilder(str).reverse().toString();
    }

    /**
     * 安全格式化字符串（避免NullPointerException）
     * @param format 格式字符串
     * @param args 参数
     * @return 格式化后的字符串
     */
    public static String safeFormat(String format, Object... args) {
        if (format == null) return null;
        if (args == null || args.length == 0) return format;
        
        Object[] safeArgs = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            safeArgs[i] = args[i] == null ? "null" : args[i];
        }
        
        return String.format(format, safeArgs);
    }

    /**
     * 判断是否包含中文
     * @param str 输入字符串
     * @return true: 包含中文; false: 不包含中文
     */
    public static boolean containsChinese(String str) {
        if (isBlank(str)) return false;
        Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    /**
     * 提取字符串中的数字
     * @param str 输入字符串
     * @return 数字字符串
     */
    public static String extractNumbers(String str) {
        if (isBlank(str)) return "";
        return str.replaceAll("[^0-9]", "");
    }

    /**
     * 去除所有空白字符
     * @param str 输入字符串
     * @return 去除空白后的字符串
     */
    public static String removeWhitespace(String str) {
        if (isBlank(str)) return str;
        return str.replaceAll("\\s+", "");
    }

}