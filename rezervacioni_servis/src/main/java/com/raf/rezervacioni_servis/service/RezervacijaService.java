package com.raf.rezervacioni_servis.service;

import com.raf.rezervacioni_servis.dto.RezervacijaCreateDto;
import com.raf.rezervacioni_servis.dto.RezervacijaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface RezervacijaService {
    RezervacijaDto addRezervacija(RezervacijaCreateDto rezervacijaCreateDto);
    void deleteRezervacija(Long id);
    Page<RezervacijaDto> findRezervacija(Pageable pageable);
}
