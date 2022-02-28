package com.raf.korisnicki_servis.service;

import com.raf.korisnicki_servis.dto.RankDto;
import com.raf.korisnicki_servis.dto.RankUpdateDto;
import com.raf.korisnicki_servis.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RankService {
    RankDto updateRank(String ime, RankUpdateDto rankUpdateDto);
    Page<RankDto> findRanks(Pageable pageable);
}
