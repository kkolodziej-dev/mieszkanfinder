package com.example.mieszkanfinder.logic;

import com.example.mieszkanfinder.datamodels.GenericMieszkanieModel;
import com.example.mieszkanfinder.sources.OLXSource;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MieszkaniesRetrieval {

    static String TIME_NOW = "";

    public static Map<String, List<GenericMieszkanieModel>> getMieszkanies() {
        // Set current time
        TIME_NOW = ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        System.out.println(TIME_NOW);

        Map<String, List<GenericMieszkanieModel>> result = new HashMap<>();

        result.put("olx", OLXSource.getMieszkaniesData());

        return result;
    }
}
