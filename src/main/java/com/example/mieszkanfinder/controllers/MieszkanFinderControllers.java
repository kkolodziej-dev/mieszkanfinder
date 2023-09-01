package com.example.mieszkanfinder.controllers;

import com.example.mieszkanfinder.datamodels.GenericMieszkanieModel;
import com.example.mieszkanfinder.helpers.GlobalCredentialsStore;
import com.example.mieszkanfinder.logic.MieszkaniesLogicManipulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MieszkanFinderControllers {

    @Autowired
    MieszkaniesLogicManipulator mieszkaniesRetrieval;

    @GetMapping(value = "/mieszkanies", produces = "application/json")
    public List<GenericMieszkanieModel> sendMieszkaniesToSubstribers() {
        return mieszkaniesRetrieval.getMieszkanies();
    }

    @PostMapping(value = "/mieszkanies", produces = "application/json")
    public String publishMieszkaniesToSubscribers() {
        mieszkaniesRetrieval.publishMieszkaniesToSubscribers();
        StringBuilder builder = new StringBuilder();
        GlobalCredentialsStore.RECEIVERS_EMAILS.forEach(x -> builder.append(x).append(", "));
        return "Maile z dzisiejszymi mieszkaniami wyslane do: " + builder;
    }
}
