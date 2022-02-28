package com.raf.notifikacioni_servis.listener;

import com.raf.notifikacioni_servis.dto.UserDto;
import com.raf.notifikacioni_servis.listener.helper.MessageHelper;
import com.raf.notifikacioni_servis.service.NotificationService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class EmailListener {

    private MessageHelper messageHelper;
    private NotificationService notificationService;

    public EmailListener(MessageHelper messageHelper, NotificationService notificationService) {
        this.messageHelper = messageHelper;
        this.notificationService = notificationService;
    }

    @JmsListener(destination = "${destination.registerMail}", concurrency = "5-10")
    public void registracija(Message message) throws JMSException {
        UserDto userDto = messageHelper.getMessage(message, UserDto.class);
        String text = String.format("Potvrdite nalog na sledecem linku: http://127.0.0.1:5500/html/confirmation.html");
        notificationService.sendSimpleMessage(userDto.getEmail(), "Potvrda naloga", text);
        notificationService.createNotification(userDto.getEmail(), "registracija",text);
    }

    @JmsListener(destination = "${destination.promenaPassworda}", concurrency = "5-10")
    public void promena(Message message) throws JMSException {
        UserDto userDto = messageHelper.getMessage(message, UserDto.class);
        String text = String.format("Uspesno ste promenili password");
        notificationService.sendSimpleMessage(userDto.getEmail(), "Promena passworda!", text);
        notificationService.createNotification(userDto.getEmail(), "promena",text);
    }

    @JmsListener(destination = "${destination.rezervisano}", concurrency = "5-10")
    public void rezervisano(Message message) throws JMSException {
        UserDto userDto = messageHelper.getMessage(message, UserDto.class);
        String text = String.format("Uspesno ste rezervisali smestaj!");
        System.out.println("ide " + userDto.getEmail());
        notificationService.sendSimpleMessage(userDto.getEmail(), "Rezervacija!", text);
        notificationService.createNotification(userDto.getEmail(), "rezervacija",text);
    }

    @JmsListener(destination = "${destination.otkazano}", concurrency = "5-10")
    public void otkazivanje(Message message) throws JMSException {
        UserDto userDto = messageHelper.getMessage(message, UserDto.class);
        String text = String.format("Uspesno ste otkazali smestaj!");
        notificationService.sendSimpleMessage(userDto.getEmail(), "Otkazivanje!", text);
        notificationService.createNotification(userDto.getEmail(), "otkazivanje",text);
    }


}
