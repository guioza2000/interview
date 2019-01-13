package com.hub.gui.wag.utils;

public class Utils {

    /**
     * Format a number and put a comma separator for each 3 digits.<br>
     * Example: 12345 became 12,345
     * @param number
     * @return
     */
    public static String formatNumberWithComma(int number){
        return String.format("%,d",number);
    }
}
