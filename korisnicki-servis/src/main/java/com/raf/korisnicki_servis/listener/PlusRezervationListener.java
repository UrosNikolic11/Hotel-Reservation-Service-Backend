package com.raf.korisnicki_servis.listener;

import com.raf.korisnicki_servis.dto.PlusReservationDto;
import com.raf.korisnicki_servis.service.UserService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class PlusRezervationListener {
    private MessageHelper messageHelper;
    private UserService userService;

    public PlusRezervationListener(MessageHelper messageHelper, UserService userService) {
        this.messageHelper = messageHelper;
        this.userService = userService;
    }

    @JmsListener(destination = "${destination.plusRezervation}", concurrency = "5-10")
    public void plusRezervation(Message message) throws JMSException {
        PlusReservationDto plusReservationDto = messageHelper.getMessage(message, PlusReservationDto.class);
        userService.addRez(plusReservationDto);
    }
}
