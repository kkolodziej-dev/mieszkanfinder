package com.example.mieszkanfinder.logic;
import com.example.mieszkanfinder.datamodels.GenericMieszkanieModel;
import com.example.mieszkanfinder.helpers.RepresentationConverter;
import com.example.mieszkanfinder.mailing.EmailServiceImpl;
import com.example.mieszkanfinder.sources.OLXSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;

import static com.example.mieszkanfinder.helpers.GlobalCredentialsStore.TIME_NOW;

@Component
public class MieszkaniesLogicManipulator {

    @Autowired
    EmailServiceImpl emailService;

    private List<GenericMieszkanieModel> todayMieszkanies;

    public List<GenericMieszkanieModel> getMieszkanies() {

        var resultList = OLXSource.getMieszkaniesData();

        todayMieszkanies = resultList.stream().filter(x -> x.getDataDodaniaOgloszenia().startsWith(TIME_NOW)).toList();

        return todayMieszkanies;
    }

    public void publishMieszkaniesToSubscribers() {

        String stringResult = RepresentationConverter.convertMieszkaniesToRepresentation(getMieszkanies());

        emailService.sendMailForNewMieszkanies(stringResult);
    }
}
