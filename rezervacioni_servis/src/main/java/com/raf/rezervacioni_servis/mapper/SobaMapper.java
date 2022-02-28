package com.raf.rezervacioni_servis.mapper;

import com.raf.rezervacioni_servis.domain.Soba;
import com.raf.rezervacioni_servis.dto.SobaDto;
import org.springframework.stereotype.Component;

@Component
public class SobaMapper {
    public SobaMapper() {
    }

    public SobaDto sobaToSobaDto(Soba soba){
        SobaDto sobaDto = new SobaDto();
        sobaDto.setId(soba.getId());
        sobaDto.setBrSobe(soba.getBrSobe());
        sobaDto.setHotel_id(soba.getHotelId());
        sobaDto.setTip_id(soba.getTipId());
        return sobaDto;
    }
}
