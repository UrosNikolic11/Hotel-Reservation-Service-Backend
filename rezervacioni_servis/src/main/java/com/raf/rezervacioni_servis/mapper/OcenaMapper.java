package com.raf.rezervacioni_servis.mapper;

import com.raf.rezervacioni_servis.domain.Ocena;
import com.raf.rezervacioni_servis.dto.OcenaDto;
import org.springframework.stereotype.Component;

@Component
public class OcenaMapper {

    public OcenaMapper() {
    }

    public OcenaDto ocenaToOcenaDto(Ocena ocena){
        OcenaDto ocenaDto = new OcenaDto();
        ocenaDto.setId(ocena.getId());
        ocenaDto.setOcena(ocena.getOcena());
        ocenaDto.setOpis(ocena.getKomentar());
        ocenaDto.setHotelId(ocena.getHotel_id());
        ocenaDto.setKlijentId(ocena.getKlijent_id());
        return ocenaDto;
    }
}
