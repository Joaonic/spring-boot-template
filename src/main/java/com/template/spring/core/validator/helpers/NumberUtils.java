package com.template.spring.core.validator.helpers;

import org.springframework.lang.Nullable;

public class NumberUtils {

    public static boolean isNullOrEmpty(@Nullable Number number) {

        if (number == null)
            return true;

        if (number instanceof Double) {
            return number.doubleValue() == Double.MIN_VALUE;
        } else if (number instanceof Float) {
            return number.floatValue() == Float.MIN_VALUE;
        } else if (number instanceof Long) {
            return number.longValue() == Long.MIN_VALUE;
        } else if (number instanceof Integer) {
            return number.intValue() == Integer.MIN_VALUE;
        } else if (number instanceof Short) {
            return number.shortValue() == Short.MIN_VALUE;
        } else {
            return number.byteValue() == Byte.MIN_VALUE;
        }
    }

    public static boolean moreThan(@Nullable Number number, int size) {
        if (number instanceof Double) {
            return number.doubleValue() >= size;
        } else if (number instanceof Float) {
            return number.floatValue() >= size;
        } else if (number instanceof Long) {
            return number.longValue() >= size;
        } else if (number instanceof Integer) {
            return number.intValue() >= size;
        } else if (number instanceof Short) {
            return number.shortValue() >= size;
        } else {
            return number.byteValue() >= size;
        }
    }

    public static boolean lessThan(@Nullable Number number, int size) {

        if (number instanceof Double) {
            return number.doubleValue() <= size;
        } else if (number instanceof Float) {
            return number.floatValue() <= size;
        } else if (number instanceof Long) {
            return number.longValue() <= size;
        } else if (number instanceof Integer) {
            return number.intValue() <= size;
        } else if (number instanceof Short) {
            return number.shortValue() <= size;
        } else {
            return number.byteValue() <= size;
        }
    }

    public static boolean between(@Nullable Number number, int minSize, int maxSize) {
        return moreThan(number, minSize) && lessThan(number, maxSize);
    }
}
