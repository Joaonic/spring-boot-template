package com.template.spring.core.helpers;

import com.template.spring.core.config.Config;
import com.template.spring.core.util.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringHelpers {

    private StringHelpers() {
        throw new IllegalStateException("Utility class");
    }

    public static String formatTimetable(Date s) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(Config.DATE_FORMAT_TIME_TABLE);

        return dateFormat.format(s);

    }

    public static String formatDate(Date s, String format) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(format);

        return dateFormat.format(s);

    }

    public static String formatDate(Date s) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(Config.DATE_FORMAT);

        return dateFormat.format(s);

    }

    public static Date parseDateISO(String s) {

        try {

            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(s);

        } catch (ParseException e) {


            return null;

        }

    }

    public static Date parseDateIncompleteISO(String s) {

        try {

            return new SimpleDateFormat("yyyy-MM-dd").parse(s);

        } catch (ParseException e) {


            return null;

        }

    }

    public static Date parseDateISOTimezone(String s) {

        try {

            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX").parse(s);

        } catch (ParseException e) {


            return null;

        }

    }

    public static Date parseTimeOnly(String s) {

        return DateHelpers.setHours(new Date(Constants.FIRST_MILLISECONDS_DATE), Integer.parseInt(s.split(":")[0]), Integer.parseInt(s.split(":")[1]), 0, 0);

    }

    public static Date parseDate(String s) {

        try {

            return new SimpleDateFormat(Config.DATE_FORMAT).parse(s);

        } catch (ParseException e) {


            return null;

        }

    }

    public static Date parseDateTimetable(String s) {

        try {

            return new SimpleDateFormat(Config.DATE_FORMAT_TIME_TABLE).parse(s);

        } catch (ParseException e) {


            return null;

        }

    }
}
