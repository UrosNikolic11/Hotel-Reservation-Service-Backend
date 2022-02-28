package com.raf.notifikacioni_servis.service;

import com.raf.notifikacioni_servis.domain.Notifikacija;
import com.raf.notifikacioni_servis.dto.NotifikacijaUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NotificationService {
    void sendSimpleMessage(String to, String subject, String text);
    Notifikacija createNotification(String email, String type, String text);
    Page<Notifikacija> findNot(Pageable pageable);
    Page<Notifikacija> findNotByUser(Long id,Pageable pageable);
    void delete(Long id);
    Notifikacija update(Long id, NotifikacijaUpdateDto notifikacijaUpdateDto);
}
