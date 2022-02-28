package com.raf.korisnicki_servis.service.impl;

import com.raf.korisnicki_servis.domain.Rank;
import com.raf.korisnicki_servis.dto.RankDto;
import com.raf.korisnicki_servis.dto.RankUpdateDto;
import com.raf.korisnicki_servis.dto.UserDto;
import com.raf.korisnicki_servis.mapper.RankMapper;
import com.raf.korisnicki_servis.repository.RankRepository;
import com.raf.korisnicki_servis.service.RankService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RankServiceImpl implements RankService {
    private RankRepository rankRepository;
    private RankMapper rankMapper;

    public RankServiceImpl(RankRepository rankRepository, RankMapper rankMapper) {
        this.rankRepository = rankRepository;
        this.rankMapper = rankMapper;
    }

    @Override
    public RankDto updateRank(String ime, RankUpdateDto rankUpdateDto) {
        Rank rank = rankRepository.findRankByIme(ime);
        if(!(rankUpdateDto.getIme() == null)){
            rank.setIme(rankUpdateDto.getIme());
        }
        if(!(rankUpdateDto.getBotLimit() == null)){
            rank.setBotLimit(rankUpdateDto.getBotLimit());
        }
        if(!(rankUpdateDto.getTopLimit() == null)){
            rank.setTopLimit(rankUpdateDto.getTopLimit());
        }
        if(!(rankUpdateDto.getPopust() == null)){
            rank.setPopust(rankUpdateDto.getPopust());
        }

        rankRepository.save(rank);
        return rankMapper.rankToRankDto(rank);
    }

    @Override
    public Page<RankDto> findRanks(Pageable pageable) {
        return rankRepository.findAll(pageable)
                .map(rankMapper::rankToRankDto);
    }
}
