package com.example.mieszkanfinder.mailing;

import com.example.mieszkanfinder.datamodels.GenericMieszkanieModel;
import com.example.mieszkanfinder.helpers.GlobalCredentialsStore;
import com.example.mieszkanfinder.helpers.RepresentationConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.example.mieszkanfinder.helpers.GlobalCredentialsStore.TIME_NOW;

@Component
public class EmailServiceImpl {

    @Autowired
    private JavaMailSender emailSender;

    public void sendMailForNewMieszkanies(List<GenericMieszkanieModel> list) {
        String stringResult = RepresentationConverter.convertMieszkaniesToRepresentation(list);
        GlobalCredentialsStore.RECEIVERS_EMAILS.forEach(x -> sendSimpleMessage(x, "Mieszkania z dnia " + TIME_NOW, stringResult));
    }

    private void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(GlobalCredentialsStore.SENDER_MAIL);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
