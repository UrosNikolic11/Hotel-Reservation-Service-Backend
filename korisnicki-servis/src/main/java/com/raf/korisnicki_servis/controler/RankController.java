package com.raf.korisnicki_servis.controler;

import com.raf.korisnicki_servis.dto.RankDto;
import com.raf.korisnicki_servis.dto.RankUpdateDto;
import com.raf.korisnicki_servis.dto.UserDto;
import com.raf.korisnicki_servis.secutiry.CheckSecurity;
import com.raf.korisnicki_servis.service.RankService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
@RequestMapping("/rank")
public class RankController {

    private RankService rankService;

    public RankController(RankService rankService) {
        this.rankService = rankService;
    }

    @PutMapping("/{ime}")
    @CheckSecurity(roles = {"admin"})
    public ResponseEntity<RankDto> updateRank(@RequestHeader("Authorization") String authorization,
                                              @PathVariable("ime") String ime
            , @RequestBody @Valid RankUpdateDto rankUpdateDto) {

        return new ResponseEntity<>(rankService.updateRank(ime, rankUpdateDto), HttpStatus.OK);
    }

    @GetMapping
    @CheckSecurity(roles = {"admin"})
    public ResponseEntity<Page<RankDto>> getRank(@RequestHeader("Authorization") String authorization,
                                                Pageable pageable) {
        return new ResponseEntity<>(rankService.findRanks(pageable), HttpStatus.OK);
    }
}
