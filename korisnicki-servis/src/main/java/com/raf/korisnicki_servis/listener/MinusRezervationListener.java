package com.raf.korisnicki_servis.listener;

import com.raf.korisnicki_servis.dto.PlusReservationDto;
import com.raf.korisnicki_servis.service.UserService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
@Component
public class MinusRezervationListener {

        private MessageHelper messageHelper;
        private UserService userService;

        public MinusRezervationListener(MessageHelper messageHelper, UserService userService) {
            this.messageHelper = messageHelper;
            this.userService = userService;
        }

        @JmsListener(destination = "${destination.minusRezervation}", concurrency = "5-10")
        public void minusRezervation(Message message) throws JMSException {
            PlusReservationDto plusReservationDto = messageHelper.getMessage(message, PlusReservationDto.class);
            userService.deleteRez(plusReservationDto);
        }

}
