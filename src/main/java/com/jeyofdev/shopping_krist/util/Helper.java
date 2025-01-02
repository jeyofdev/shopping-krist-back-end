package com.jeyofdev.shopping_krist.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {
    /**
     * Format date to yyyy-MM-dd HH:mm:ss
     *
     * @return String
     */
    public static String simpleDateFormat() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(new Date());
    }

    /**
     * Capitalize the first letter of a string
     *
     * @return String
     */
    public static String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("The 'str' field must be provided and cannot be empty.");
        }

        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
