package com.example.mieszkanfinder.datamodels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GenericMieszkanieModel {
    private String name;
    private String dateAdded;
    private String url;
    private String pricePerSqm;
    private String floor;
    private String price;
    private String sqm;
    private String rooms;
}
