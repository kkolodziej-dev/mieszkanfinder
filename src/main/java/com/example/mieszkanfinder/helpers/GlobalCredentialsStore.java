package com.example.mieszkanfinder.helpers;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class GlobalCredentialsStore {
    public static String SENDER_MAIL = "";
    public static String SENDER_PASSWORD = "";
    public static String TIME_NOW;
    public static List<String> RECEIVERS_EMAILS = new LinkedList<>();

    static {
        RECEIVERS_EMAILS.add("");

        // Set current time
        refreshTimeNow();
    }

    public static void refreshTimeNow() {
        TIME_NOW = ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

}
