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
    private String tytulOferty;
    private String cena;
    private String metraz;
    private String pokoje;
    private String uRL;
    private String cenaZaMetr;
    private String pietro;
    private String dataDodaniaOgloszenia;
}
