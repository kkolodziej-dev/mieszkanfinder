package com.example.mieszkanfinder.logic;

import com.example.mieszkanfinder.cache.TodaysMieszkaniesStore;
import com.example.mieszkanfinder.datamodels.GenericMieszkanieModel;
import com.example.mieszkanfinder.helpers.RepresentationConverter;
import com.example.mieszkanfinder.mailing.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class PostNewsletterMieszkaniesManipulator {

    @Autowired
    EmailServiceImpl emailService;

    public int publishMieszkaniesToSubscribers() {

        List<GenericMieszkanieModel> todaysMieszkanies = TodaysMieszkaniesStore.getTodaysMieszkaniesDelta();

        if (todaysMieszkanies == null || todaysMieszkanies.isEmpty()) {
            return 0;
        }

        String stringResult = RepresentationConverter.convertMieszkaniesToRepresentation(todaysMieszkanies);

        emailService.sendMailForNewMieszkanies(stringResult);

        return todaysMieszkanies.size();
    }
}
