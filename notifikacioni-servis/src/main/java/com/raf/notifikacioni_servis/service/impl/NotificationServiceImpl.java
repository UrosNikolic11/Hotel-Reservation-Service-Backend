package com.raf.notifikacioni_servis.service.impl;

import com.raf.notifikacioni_servis.domain.Notifikacija;
import com.raf.notifikacioni_servis.dto.NotifikacijaUpdateDto;
import com.raf.notifikacioni_servis.dto.UserDto;
import com.raf.notifikacioni_servis.repository.NotificationRepository;
import com.raf.notifikacioni_servis.service.NotificationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

    private JavaMailSender mailSender;
    private NotificationRepository notificationRepository;
    private RestTemplate userServiceApiClient;

    public NotificationServiceImpl(JavaMailSender mailSender, NotificationRepository notificationRepository,
                                   @Qualifier("userServiceApiClient") RestTemplate userServiceApiClient) {
        this.mailSender = mailSender;
        this.notificationRepository = notificationRepository;
        this.userServiceApiClient = userServiceApiClient;
    }

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        System.out.println("a" + to);
        System.out.println(subject);
        System.out.println(text);
        mailSender.send(message);
    }

    @Override
    public Notifikacija createNotification(String email, String type, String text) {
        Notifikacija notification = new Notifikacija();
        notification.setText(text);
        notification.setEmail(email);
        notification.setTextType(type);
        notification.setDatumKreiranje(new Date());
        notificationRepository.save(notification);
        return  notification;
    }

    @Override
    public Page<Notifikacija> findNot(Pageable pageable) {
        return notificationRepository.findAll(pageable);
    }

    @Override
    public Page<Notifikacija> findNotByUser(Long id, Pageable pageable) {
        ResponseEntity<UserDto> userDto = userServiceApiClient.exchange("/user/getUser/" + id, HttpMethod.GET,
                null, UserDto.class);
        String email = userDto.getBody().getEmail();
        List<Notifikacija> notifikacijaList = notificationRepository.findNotifikacijasByEmail(email);
        final int start = (int)pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), notifikacijaList.size());
        final Page<Notifikacija> page = new PageImpl<>(notifikacijaList.subList(start, end), pageable, notifikacijaList.size());
        return page;
    }

    @Override
    public void delete(Long id) {
        notificationRepository.deleteById(id);
    }

    @Override
    public Notifikacija update(Long id, NotifikacijaUpdateDto notifikacijaUpdateDto) {
        Notifikacija notifikacija = notificationRepository.findNotifikacijaById(id);
        if(!(notifikacijaUpdateDto.getEmail() == null)){
            notifikacija.setEmail(notifikacijaUpdateDto.getEmail());
        }
        if(!(notifikacijaUpdateDto.getText() == null)){
            notifikacija.setText(notifikacijaUpdateDto.getText());
        }
        if(!(notifikacijaUpdateDto.getType() == null)){
            notifikacija.setTextType(notifikacijaUpdateDto.getType());
        }

        notificationRepository.save(notifikacija);
        return notifikacija;
    }
}
