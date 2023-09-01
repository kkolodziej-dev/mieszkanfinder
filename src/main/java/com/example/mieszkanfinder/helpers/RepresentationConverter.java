package com.example.mieszkanfinder.helpers;

import com.example.mieszkanfinder.datamodels.GenericMieszkanieModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.List;

public class RepresentationConverter {

    public static String convertMieszkaniesToRepresentation(List<GenericMieszkanieModel> mieszkanies) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            return objectMapper.writeValueAsString(mieszkanies);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
