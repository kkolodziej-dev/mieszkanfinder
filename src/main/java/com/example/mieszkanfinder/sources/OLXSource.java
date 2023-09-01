package com.example.mieszkanfinder.sources;

import com.example.mieszkanfinder.datamodels.GenericMieszkanieModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class OLXSource implements GenericSource {

    // search parameters
    static String wantedSqm = "45";
    static String wantedMaxPrice = "800000";

    public static List<GenericMieszkanieModel> getMieszkaniesData() {
        List<GenericMieszkanieModel> list = new ArrayList<>();
        RestTemplate template = new RestTemplate();
        String url =
                String.format("https://www.olx.pl/api/v1/offers/?offset=0&limit=50&query=bielany&category_id=14&region_id=2&city_id=17871&sort_by=created_at:desc&filter_float_m:from=%s&filter_float_price:to=%s",
                        wantedSqm, wantedMaxPrice);
        ResponseEntity<Object> response = template.getForEntity(url, Object.class);
        ArrayList<LinkedHashMap<String, Object>> data = (ArrayList<LinkedHashMap<String, Object>>)((LinkedHashMap)response.getBody()).get("data");
        data.forEach(x -> {
            GenericMieszkanieModel mieszkanieModel = new GenericMieszkanieModel();
            mieszkanieModel.setTytulOferty((String)x.get("title"));
            mieszkanieModel.setURL((String)x.get("url"));
            mieszkanieModel.setDataDodaniaOgloszenia((String)x.get("created_time"));
            ((ArrayList)(x.get("params"))).forEach(y -> {
                LinkedHashMap<String, Object> param = (LinkedHashMap<String, Object>)y;
                Object currentItem = param.get("key");

                if (currentItem != null) {
                    // price/m2
                    if (param.get("key").equals("price_per_m")) {
                        String pricePerSqm = ((LinkedHashMap<String, String>) param.get("value")).get("label");
                        mieszkanieModel.setCenaZaMetr(pricePerSqm);
                    }

                    // floor
                    if (param.get("key").equals("floor_select")) {
                        String floor = ((LinkedHashMap<String, String>) param.get("value")).get("label");
                        mieszkanieModel.setPietro(floor);
                    }

                    // price
                    if (param.get("key").equals("price")) {
                        Integer price = (Integer)((LinkedHashMap<String, Object>) param.get("value")).get("value");
                        String finalPrice = price + "zł";
                        mieszkanieModel.setCena(finalPrice);
                    }

                    // sqm
                    if (param.get("key").equals("m")) {
                        String sqm = ((LinkedHashMap<String, String>) param.get("value")).get("label");
                        mieszkanieModel.setMetraz(sqm);
                    }

                    // sqm
                    if (param.get("key").equals("rooms")) {
                        String rooms = ((LinkedHashMap<String, String>) param.get("value")).get("label");
                        mieszkanieModel.setPokoje(rooms);
                    }
                }

            });
            list.add(mieszkanieModel);
        });

        sortListByDate(list);

        return list;
    }

    private static void sortListByDate(List<GenericMieszkanieModel> list) {
        final DateTimeFormatter dfm = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        Comparator<GenericMieszkanieModel> comparator = Comparator.comparing(s -> LocalDateTime.parse(s.getDataDodaniaOgloszenia(), dfm));
        Comparator<GenericMieszkanieModel> finalComparator = comparator.reversed();
        list.sort(finalComparator);
    }
}
