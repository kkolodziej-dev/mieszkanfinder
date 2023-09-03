package com.example.mieszkanfinder.datamodels;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
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
