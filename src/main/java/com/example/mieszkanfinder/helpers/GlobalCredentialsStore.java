package com.example.mieszkanfinder.helpers;

import java.util.LinkedList;
import java.util.List;

public class GlobalCredentialsStore {
    public static String SENDER_MAIL = "";
    public static String SENDER_PASSWORD = "";
    public static String TIME_NOW;
    public static List<String> RECEIVERS_EMAILS = new LinkedList<>();

    static {
        RECEIVERS_EMAILS.add("");
    }

}
