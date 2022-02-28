package com.raf.rezervacioni_servis.mapper;

import com.raf.rezervacioni_servis.domain.Rezervacija;
import com.raf.rezervacioni_servis.dto.RezervacijaDto;
import org.springframework.stereotype.Component;

@Component
public class RezervacijaMapper {
    public RezervacijaMapper() {
    }

    public RezervacijaDto rezervacijaToRezervacijaDto(Rezervacija rezervacija){
        RezervacijaDto rezervacijaDto = new RezervacijaDto();
        rezervacijaDto.setId(rezervacija.getId());
        rezervacijaDto.setTerminId(rezervacija.getTermin_id());
        rezervacijaDto.setKorisnikId(rezervacija.getKlijent_id());
        rezervacijaDto.setSobaId(rezervacija.getSoba_id());
        rezervacijaDto.setCena(rezervacija.getCena());
        return rezervacijaDto;
    }
}
