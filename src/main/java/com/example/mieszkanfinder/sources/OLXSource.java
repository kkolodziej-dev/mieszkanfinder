package com.example.mieszkanfinder.sources;

import com.example.mieszkanfinder.datamodels.GenericMieszkanieModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.*;

public class OLXSource implements GenericSource {
    public static List<GenericMieszkanieModel> getMieszkaniesData() {
        List<GenericMieszkanieModel> list = new ArrayList<>();
        RestTemplate template = new RestTemplate();
//        String url = "https://www.olx.pl/api/v1/offers/?offset=0&limit=50&query=bielany&category_id=14&region_id=2&city_id=17871&sort_by=created_at%3Adesc";
        String url = "https://www.olx.pl/api/v1/offers/?offset=0&limit=50&query=bielany&category_id=14&region_id=2&city_id=17871";
        ResponseEntity<Object> response = template.getForEntity(url, Object.class);
        ArrayList<LinkedHashMap<String, Object>> data = (ArrayList<LinkedHashMap<String, Object>>)((LinkedHashMap)response.getBody()).get("data");
        data.forEach(x -> {
            GenericMieszkanieModel mieszkanieModel = new GenericMieszkanieModel();
            mieszkanieModel.setName((String)x.get("title"));
            mieszkanieModel.setUrl((String)x.get("url"));
            mieszkanieModel.setDateAdded((String)x.get("created_time"));
            ((ArrayList)(x.get("params"))).forEach(y -> {
                LinkedHashMap<String, Object> param = (LinkedHashMap<String, Object>)y;

                // price/m2
                if (param.get("key") != null && param.get("key").equals("price_per_m")) {
                    String pricePerSqm = ((LinkedHashMap<String, String>) param.get("value")).get("label");
                    mieszkanieModel.setPricePerSqm(pricePerSqm);
                }

                // floor
                if (param.get("key") != null && param.get("key").equals("floor_select")) {
                    String floor = ((LinkedHashMap<String, String>) param.get("value")).get("label");
                    mieszkanieModel.setFloor(floor);
                }

                // price
                if (param.get("key") != null && param.get("key").equals("price")) {
                    Integer price = (Integer)((LinkedHashMap<String, Object>) param.get("value")).get("value");
                    String finalPrice = price + "zł";
                    mieszkanieModel.setPrice(finalPrice);
                }

                // sqm
                if (param.get("key") != null && param.get("key").equals("m")) {
                    String sqm = ((LinkedHashMap<String, String>) param.get("value")).get("label");
                    mieszkanieModel.setSqm(sqm);
                }

                // sqm
                if (param.get("key") != null && param.get("key").equals("rooms")) {
                    String rooms = ((LinkedHashMap<String, String>) param.get("value")).get("label");
                    mieszkanieModel.setRooms(rooms);
                }
            });
            list.add(mieszkanieModel);
        });
        return list;
    }
}
