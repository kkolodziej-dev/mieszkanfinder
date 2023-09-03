package com.example.mieszkanfinder.controllers;

import com.example.mieszkanfinder.datamodels.GenericMieszkanieModel;
import com.example.mieszkanfinder.helpers.GlobalCredentialsStore;
import com.example.mieszkanfinder.logic.GetMieszkaniesLogicManipulator;
import com.example.mieszkanfinder.logic.PostNewsletterMieszkaniesManipulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MieszkanFinderControllers {

    @Autowired
    GetMieszkaniesLogicManipulator getMieszkaniesManipulator;

    @Autowired
    PostNewsletterMieszkaniesManipulator postMieszkaniesManipulator;

    @GetMapping(value = "/mieszkanies", produces = "application/json")
    public List<GenericMieszkanieModel> getMieszkaniesData() {
        return getMieszkaniesManipulator.getMieszkanies();
    }

    @PostMapping(value = "/mieszkanies/publish", produces = "application/json")
    public String sendMieszkaniesNewsletter() {
        int numOfNewMieszkanies = postMieszkaniesManipulator.publishMieszkaniesToSubscribers();
        if (numOfNewMieszkanies == 0) {
            return "Nie znaleziono nowych mieszkan, maile nie zostaly wyslane.";
        }

        StringBuilder builder = new StringBuilder();
        GlobalCredentialsStore.RECEIVERS_EMAILS.forEach(x -> builder.append(x).append(", "));
        return "Maile z dzisiejszymi mieszkaniami wyslane do: " + builder;
    }
}
