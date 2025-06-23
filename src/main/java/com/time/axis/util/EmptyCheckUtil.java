package com.time.axis.util;

import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 多字段判空工具类
 */
public class EmptyCheckUtil {

    // 私有构造防止实例化
    private EmptyCheckUtil() {}

    /**
     * 检查多个字段中是否有任意一个为空
     * @param objects 要检查的对象数组
     * @return true: 至少有一个为空; false: 全部非空
     */
    public static boolean isAnyEmpty(Object... objects) {
        if (objects == null) return true;
        
        for (Object obj : objects) {
            if (isEmpty(obj)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查多个字段是否全部为空
     * @param objects 要检查的对象数组
     * @return true: 全部为空; false: 至少有一个非空
     */
    public static boolean isAllEmpty(Object... objects) {
        if (objects == null) return true;
        
        for (Object obj : objects) {
            if (isNotEmpty(obj)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查对象是否为空
     * @param obj 要检查的对象
     * @return true: 空; false: 非空
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        
        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        }
        
        if (obj instanceof Collection) {
            return ((Collection<?>) obj).isEmpty();
        }
        
        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).isEmpty();
        }
        
        if (obj instanceof Object[]) {
            return ((Object[]) obj).length == 0;
        }
        
        return false;
    }

    /**
     * 检查对象是否非空
     * @param obj 要检查的对象
     * @return true: 非空; false: 空
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    /**
     * 检查多个字段是否全部非空
     * @param objects 要检查的对象数组
     * @return true: 全部非空; false: 至少有一个为空
     */
    public static boolean isAllNotEmpty(Object... objects) {
        if (objects == null) return false;
        
        for (Object obj : objects) {
            if (isEmpty(obj)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查多个字段是否全部非空，否则抛出异常
     * @param message 异常提示信息
     * @param objects 要检查的对象数组
     * @throws IllegalArgumentException 如果任何字段为空
     */
    public static void requireAllNotEmpty(String message, Object... objects) {
        if (!isAllNotEmpty(objects)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 检查多个字段是否全部非空，否则抛出异常（支持自定义异常）
     * @param exceptionSupplier 异常提供函数
     * @param objects 要检查的对象数组
     * @throws RuntimeException 如果任何字段为空
     */
    public static void requireAllNotEmpty(Supplier<? extends RuntimeException> exceptionSupplier, Object... objects) {
        if (!isAllNotEmpty(objects)) {
            throw exceptionSupplier.get();
        }
    }

    /**
     * 检查多个字段是否全部非空，否则抛出异常（带字段名提示）
     * @param fieldNames 字段名称数组（与objects顺序对应）
     * @param objects 要检查的对象数组
     * @throws IllegalArgumentException 如果任何字段为空
     */
    public static void requireAllNotEmptyWithNames(String[] fieldNames, Object... objects) {
        if (fieldNames == null || objects == null || fieldNames.length != objects.length) {
            throw new IllegalArgumentException("字段名数组和对象数组长度不一致");
        }
        
        for (int i = 0; i < objects.length; i++) {
            if (isEmpty(objects[i])) {
                throw new IllegalArgumentException("字段 '" + fieldNames[i] + "' 不能为空");
            }
        }
    }

    /**
     * 获取第一个非空对象
     * @param objects 要检查的对象数组
     * @return 第一个非空对象，如果全部为空则返回null
     */
    public static <T> T firstNonNull(T... objects) {
        if (objects == null) return null;
        
        for (T obj : objects) {
            if (isNotEmpty(obj)) {
                return obj;
            }
        }
        return null;
    }

    /**
     * 安全获取字符串（空值返回默认值）
     * @param str 原始字符串
     * @param defaultValue 默认值
     * @return 非空字符串或默认值
     */
    public static String defaultIfEmpty(String str, String defaultValue) {
        return isNotEmpty(str) ? str : defaultValue;
    }

    // ================== 特殊数据类型扩展 ==================
    
    /**
     * 检查数字是否为零或空
     * @param number 数字对象
     * @return true: 空/零; false: 非零
     */
    public static boolean isZeroOrEmpty(Number number) {
        return number == null || number.doubleValue() == 0.0;
    }

    /**
     * 检查布尔值是否为空（仅对包装类有效）
     * @param bool 布尔对象
     * @return true: 空; false: 非空
     */
    public static boolean isEmpty(Boolean bool) {
        return bool == null;
    }

    /**
     * 检查数组是否为空
     * @param array 数组对象
     * @return true: 空; false: 非空
     */
    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 检查字节数组是否为空
     * @param bytes 字节数组
     * @return true: 空; false: 非空
     */
    public static boolean isEmpty(byte[] bytes) {
        return bytes == null || bytes.length == 0;
    }

    /**
     * 检查多个字符串是否为空（忽略空白字符）
     * @param strings 字符串数组
     * @return true: 至少有一个为空或空白; false: 全部非空且非空白
     */
    public static boolean isAnyBlank(String... strings) {
        if (strings == null) return true;
        
        for (String str : strings) {
            if (str == null || str.trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }
}