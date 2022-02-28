package com.raf.notifikacioni_servis.repository;

import com.raf.notifikacioni_servis.domain.Notifikacija;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notifikacija, Long> {
    List<Notifikacija> findNotifikacijasByEmail(String email);
    Notifikacija findNotifikacijaById(Long id);
}
