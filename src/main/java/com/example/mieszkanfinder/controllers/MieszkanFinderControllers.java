package com.example.mieszkanfinder.controllers;

import com.example.mieszkanfinder.datamodels.GenericMieszkanieModel;
import com.example.mieszkanfinder.logic.MieszkaniesRetrieval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class MieszkanFinderControllers {

    @Autowired
    MieszkaniesRetrieval mieszkaniesRetrieval;

    @GetMapping(value = "/mieszkanies", produces = "application/json")
    public List<GenericMieszkanieModel> getMieszkanies() {
        return mieszkaniesRetrieval.getMieszkanies();
    }
}
