package com.raf.korisnicki_servis.mapper;

import com.raf.korisnicki_servis.domain.Rank;
import com.raf.korisnicki_servis.dto.RankDto;
import org.springframework.stereotype.Component;

@Component
public class RankMapper {
    public RankMapper() {
    }

    public RankDto rankToRankDto(Rank rank){
        RankDto rankDto = new RankDto();
        rankDto.setName(rank.getIme());
        rankDto.setBotLimit(rank.getBotLimit());
        rankDto.setTopLimit(rank.getTopLimit());
        rankDto.setPopust(rank.getPopust());
        return rankDto;
    }
}
