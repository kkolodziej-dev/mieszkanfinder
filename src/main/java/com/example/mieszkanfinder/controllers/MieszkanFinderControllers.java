package com.example.mieszkanfinder.controllers;

import com.example.mieszkanfinder.datamodels.GenericMieszkanieModel;
import com.example.mieszkanfinder.logic.Logikacnie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class MieszkanFinderControllers {

    @GetMapping(value = "/mieszkanies", consumes = "application/json", produces = "application/json")
    public Map<String, List<GenericMieszkanieModel>> getMieszkanies() {
        return Logikacnie.getMieszkanies();
    }
}
