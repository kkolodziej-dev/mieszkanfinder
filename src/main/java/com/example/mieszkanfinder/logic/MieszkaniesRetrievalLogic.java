package com.example.mieszkanfinder.logic;

import com.example.mieszkanfinder.datamodels.GenericMieszkanieModel;
import com.example.mieszkanfinder.sources.OLXSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MieszkaniesRetrievalLogic {

    public static Map<String, List<GenericMieszkanieModel>> getMieszkanies() {
        Map<String, List<GenericMieszkanieModel>> result = new HashMap<>();
        result.put("olx", OLXSource.getMieszkaniesData());
        return result;
    }
}
