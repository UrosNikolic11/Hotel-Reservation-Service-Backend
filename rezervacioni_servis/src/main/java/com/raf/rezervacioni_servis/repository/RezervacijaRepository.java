package com.raf.rezervacioni_servis.repository;

import com.raf.rezervacioni_servis.domain.Rezervacija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RezervacijaRepository extends JpaRepository<Rezervacija, Long> {
    Rezervacija findRezervacijaById(Long id);
    List<Rezervacija> findAll();
}
