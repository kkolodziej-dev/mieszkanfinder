package com.example.mieszkanfinder.logic;

import com.example.mieszkanfinder.datamodels.GenericMieszkanieModel;
import com.example.mieszkanfinder.helpers.GlobalCredentialsStore;
import com.example.mieszkanfinder.helpers.RepresentationConverter;
import com.example.mieszkanfinder.mailing.EmailServiceImpl;
import com.example.mieszkanfinder.sources.OLXSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.example.mieszkanfinder.helpers.GlobalCredentialsStore.TIME_NOW;

@Component
public class MieszkaniesRetrieval {

    @Autowired
    EmailServiceImpl emailService;

    public List<GenericMieszkanieModel> getMieszkanies() {
        // Set current time
        TIME_NOW = ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE);

        List<GenericMieszkanieModel> result = new LinkedList<>();

        result.addAll(OLXSource.getMieszkaniesData());

        List<GenericMieszkanieModel> finalList = result.stream().filter(x -> x.getDateAdded().startsWith(TIME_NOW)).toList();

        emailService.sendMailForNewMieszkanies(finalList);

        return finalList;
    }
}
