package com.example.mieszkanfinder.datamodels;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GenericMieszkanieModel {
    private String name;
    private String dateAdded;
    private String url;
    private Double sqm;
}
