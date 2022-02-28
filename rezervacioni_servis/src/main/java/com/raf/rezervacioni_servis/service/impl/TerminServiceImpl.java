package com.raf.rezervacioni_servis.service.impl;

import com.raf.rezervacioni_servis.domain.*;
import com.raf.rezervacioni_servis.dto.SlobodniDto;
import com.raf.rezervacioni_servis.repository.*;
import com.raf.rezervacioni_servis.service.TerminService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class TerminServiceImpl implements TerminService {

    private TerminRepository terminRepository;
    private HotelRepository hotelRepository;
    private TipRepository tipRepository;
    private SobaRepository sobaRepository;
    private RezervacijaRepository rezervacijaRepository;

    public TerminServiceImpl(TerminRepository terminRepository, HotelRepository hotelRepository, TipRepository tipRepository, SobaRepository sobaRepository, RezervacijaRepository rezervacijaRepository) {
        this.terminRepository = terminRepository;
        this.hotelRepository = hotelRepository;
        this.tipRepository = tipRepository;
        this.sobaRepository = sobaRepository;
        this.rezervacijaRepository = rezervacijaRepository;
    }

    @Override
    public Page<Termin> findTermin(Pageable pageable) {
        return terminRepository.findAll(pageable);
    }

    @Override
    public SlobodniDto findSlobodni(String hotelIme, String grad, Long terminId, String tip) {
        SlobodniDto slobodniDto = new SlobodniDto();
        Hotel hotel = hotelRepository.findHotelByImeAndGrad(hotelIme,grad);
        Termin termin = terminRepository.findTerminById(terminId);
        Date pocetni = termin.getStartDate();
        Date kraj = termin.getEndDate();
        Tip t = tipRepository.findTipByNaziv(tip);
        Float cena = t.getCena();
        List<Soba> sobe = sobaRepository.findSobasByHotelIdAndTipIdOrderByBrSobeDesc(hotel.getId(), t.getId());
        List<Rezervacija> rezervacije = rezervacijaRepository.findAll();
        int br = 0;
        for (int i = 0; i<sobe.size(); i++){
            for (int j = 0; j<rezervacije.size(); j++){
                if(sobe.get(i).getId().equals(rezervacije.get(j).getSoba_id()) && rezervacije.get(j).getTermin_id().equals(terminId)){
                    br++;
                }
            }
        }
        int slobodne;
        System.out.println(br + " " + sobe.size());
        slobodne = sobe.size() - br;
        long diff = kraj.getTime() - pocetni.getTime();
        long brDana = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        slobodniDto.setBrSoba(slobodne);
        slobodniDto.setCena(brDana*cena);
        return slobodniDto;
    }
}
