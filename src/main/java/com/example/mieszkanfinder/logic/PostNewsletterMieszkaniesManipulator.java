package com.example.mieszkanfinder.logic;

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

    @Autowired
    GetMieszkaniesLogicManipulator getMieszkaniesManipulator;

    public int publishMieszkaniesToSubscribers() {

        List<GenericMieszkanieModel> result = getMieszkaniesManipulator.getMieszkanies();

        if (result == null || result.isEmpty()) {
            return 0;
        }

        String stringResult = RepresentationConverter.convertMieszkaniesToRepresentation(result);

        emailService.sendMailForNewMieszkanies(stringResult);

        return result.size();
    }
}
