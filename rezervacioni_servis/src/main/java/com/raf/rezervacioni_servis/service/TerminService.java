package com.raf.rezervacioni_servis.service;

import com.raf.rezervacioni_servis.domain.Soba;
import com.raf.rezervacioni_servis.domain.Termin;
import com.raf.rezervacioni_servis.dto.RezervacijaDto;
import com.raf.rezervacioni_servis.dto.SlobodniDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TerminService {

    Page<Termin> findTermin(Pageable pageable);
    SlobodniDto findSlobodni(String hotelIme, String grad, Long terminId, String tip);
}
